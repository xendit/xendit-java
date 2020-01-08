package com.xendit.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xendit.exception.XenditException;
import com.xendit.network.BaseRequest;
import com.xendit.network.RequestResource;

import java.lang.reflect.Field;
import java.util.Map;

public class BaseModel {
    public static <T> T request(
            RequestResource.Method method,
            String url,
            Map<String, Object> params,
            Class<T> clazz
    ) throws XenditException {
        return new BaseRequest().request(method, url, params, clazz);
    }

    public static final Gson PRETTY_PRINT_GSON =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

    @Override
    public String toString() {
        return String.format(
                "<%s@%s> JSON: %s",
                this.getClass().getName(),
                System.identityHashCode(this),
                PRETTY_PRINT_GSON.toJson(this));
    }

    private Object getIdString() {
        try {
            Field idField = this.getClass().getDeclaredField("id");
            return idField.get(this);
        } catch (SecurityException e) {
            return "";
        } catch (NoSuchFieldException e) {
            return "";
        } catch (IllegalArgumentException e) {
            return "";
        } catch (IllegalAccessException e) {
            return "";
        }
    }
}
