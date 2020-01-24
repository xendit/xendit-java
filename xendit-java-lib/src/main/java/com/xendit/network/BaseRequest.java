package com.xendit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.xendit.Xendit;
import com.xendit.exception.ApiException;
import com.xendit.exception.AuthException;
import com.xendit.exception.XenditException;
import com.xendit.model.XenditError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BaseRequest implements NetworkClient {
  private static final int DEFAULT_CONNECT_TIMEOUT = 60000;

  public <T> T request(
      RequestResource.Method method, String url, Map<String, Object> params, Class<T> clazz)
      throws XenditException {
    return staticRequest(method, url, params, clazz);
  }

  private static Map<String, String> getHeaders(String apiKey) throws XenditException {
    Map<String, String> headers = new HashMap<>();

    headers.put("User-Agent", "Xendit Java Library/v1");
    headers.put("Accept", "application/json");

    String base64Key = encodeBase64(apiKey + ":");
    headers.put("Authorization", "Basic " + base64Key);

    return headers;
  }

  private static <T> T staticRequest(
      RequestResource.Method method, String url, Map<String, Object> params, Class<T> clazz)
      throws XenditException {
    XenditResponse response = rawRequest(method, url, params);

    int responseCode = response.getStatusCode();
    String responseBody = response.getBody();

    if (responseCode < 200 || responseCode >= 300) {
      handleApiError(response, params);
    }

    T resource = null;
    try {
      resource = new Gson().fromJson(responseBody, clazz);
    } catch (JsonSyntaxException e) {
      raiseMalformedJsonError(responseBody, responseCode);
    }

    return resource;
  }

  private static XenditResponse rawRequest(
      RequestResource.Method method, String url, Map<String, Object> params)
      throws XenditException {
    String apiKey = Xendit.getApiKey();

    if (apiKey == null || apiKey.trim().isEmpty()) {
      throw new AuthException("No API key is provided yet.");
    }

    String jsonParams = "";

    if (params != null) {
      Gson gson = new GsonBuilder().create();
      jsonParams = gson.toJson(params);
    }

    HttpURLConnection connection = null;

    try {
      allowHttpMethods(method.getText());

      connection = createXenditConnection(url, apiKey, jsonParams);

      connection.setRequestMethod(method.getText());

      if (method == RequestResource.Method.POST || method == RequestResource.Method.PATCH) {
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", "utf-8");
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        OutputStream stream = connection.getOutputStream();
        stream.write(jsonParams.getBytes("utf-8"));
        stream.close();
      }

      int responseCode = connection.getResponseCode();
      String responseBody;

      if (responseCode >= 200 && responseCode < 300) {
        responseBody = getResponseBody(connection.getInputStream());
      } else {
        responseBody = getResponseBody(connection.getErrorStream());
      }

      return new XenditResponse(responseCode, responseBody);
    } catch (IOException e) {
      throw new XenditException("Connection error");
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

  private static void allowHttpMethods(String... methods) {
    try {
      Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

      methodsField.setAccessible(true);

      String[] oldMethods = (String[]) methodsField.get(null);
      Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
      methodsSet.addAll(Arrays.asList(methods));
      String[] newMethods = methodsSet.toArray(new String[0]);

      methodsField.set(null /*static field*/, newMethods);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new IllegalStateException(e);
    }
  }

  private static HttpURLConnection createXenditConnection(
      String url, String apiKey, String jsonParams) throws IOException, XenditException {
    URL xenditUrl = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) xenditUrl.openConnection();

    connection.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
    connection.setUseCaches(false);

    for (Map.Entry<String, String> header : getHeaders(apiKey).entrySet()) {
      connection.setRequestProperty(header.getKey(), header.getValue());
    }

    return connection;
  }

  private static String encodeBase64(String key) throws XenditException {
    try {
      byte[] keyData = key.getBytes(StandardCharsets.UTF_8);
      return Base64.getEncoder().encodeToString(keyData);
    } catch (Exception e) {
      throw new XenditException("Failed to encode API key");
    }
  }

  private static String getResponseBody(InputStream responseStream) throws IOException {
    try (final Scanner scanner = new Scanner(responseStream, RequestResource.CHARSET)) {
      // \A is the beginning of the stream boundary
      final String responseBody = scanner.useDelimiter("\\A").next();
      responseStream.close();
      return responseBody;
    }
  }

  private static void handleApiError(XenditResponse response, Map<String, Object> params)
      throws ApiException {
    Gson gson = new Gson();

    try {
      XenditError xenditError = gson.fromJson(response.getBody(), XenditError.class);
      throw new ApiException(xenditError.getMessage(), xenditError.getErrorCode(), params);
    } catch (JsonSyntaxException e) {
      raiseMalformedJsonError(response.getBody(), response.getStatusCode());
    }
  }

  private static void raiseMalformedJsonError(String body, int code) throws ApiException {
    throw new ApiException(
        String.format("Invalid response from Xendit API: %s. HTTP response code: %d", body, code),
        "500",
        null);
  }
}
