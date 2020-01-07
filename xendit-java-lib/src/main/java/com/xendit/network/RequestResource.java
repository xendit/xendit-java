package com.xendit.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import javax.annotation.Nullable;

public abstract class RequestResource {
    public final static String CHARSET = "UTF-8";

    public enum Method {
        GET("GET"),
        POST("POST"),
        PATCH("PATCH");

        private String text;

        Method(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        @Nullable
        public static Method fromString(String text) {
            for (Method m : Method.values()) {
                if (m.text.equalsIgnoreCase(text)) {
                    return m;
                }
            }
            return null;
        }
    }
}
