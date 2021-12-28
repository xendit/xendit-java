package com.xendit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.xendit.exception.ApiException;
import com.xendit.exception.AuthException;
import com.xendit.exception.XenditException;
import com.xendit.model.XenditError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BaseRequest implements NetworkClient {
  private static final int DEFAULT_CONNECT_TIMEOUT = 60000;

  public <T> T request(
      RequestResource.Method method,
      String url,
      Map<String, Object> params,
      String apiKey,
      Class<T> clazz)
      throws XenditException {
    return request(method, url, new HashMap<>(), params, apiKey, clazz);
  }

  @Override
  public <T> T request(
      RequestResource.Method method,
      String url,
      Map<String, String> headers,
      Map<String, Object> params,
      String apiKey,
      Class<T> clazz)
      throws XenditException {
    return staticRequest(method, url, headers, params, apiKey, clazz);
  }

  private static Map<String, String> constructCustomHeaders(
      Map<String, String> customHeaders, Map<String, Object> params) throws XenditException {
    Map<String, String> headers = new HashMap<>();
    Map<String, Object> clonedParams = new HashMap<String, Object>(params);
    for (Map.Entry<String, String> header : customHeaders.entrySet()) {
      headers.put(header.getKey(), header.getValue());
    }

    for (Map.Entry<String, Object> param : clonedParams.entrySet()) {
      if (param.getKey().equals("for-user-id")) {
        
        headers.put(param.getKey(), param.getValue().toString());
        params.remove(param.getKey());
      }
      if (param.getKey().equals("with-fee-rule")) {
        headers.put(param.getKey(), param.getValue().toString());
        params.remove(param.getKey());
      }
      if (param.getKey().equals("Idempotency-key")) {
        headers.put(param.getKey(), param.getValue().toString());
        params.remove(param.getKey());
      }
      if (param.getKey().equals("X-IDEMPOTENCY-KEY")) {
        headers.put(param.getKey(), param.getValue().toString());
        params.remove(param.getKey());
      }
      if (param.getKey().equals("X-api-version")) {
        headers.put(param.getKey(), param.getValue().toString());
        params.remove(param.getKey());
      }
    }
    return headers;
  }

  private static Map<String, Object> filterParams(Map<String, Object> params)
      throws XenditException {
    Map<String, Object> clonedParams = new HashMap<String, Object>(params);

    for (Map.Entry<String, Object> param : clonedParams.entrySet()) {
      if (param.getKey().equals("for-user-id")) {
        params.remove(param.getKey());
      }
      if (param.getKey().equals("with-fee-rule")) {
        params.remove(param.getKey());
      }
      if (param.getKey().equals("Idempotency-key")) {
        params.remove(param.getKey());
      }
      if (param.getKey().equals("X-IDEMPOTENCY-KEY")) {
        params.remove(param.getKey());
      }
      if (param.getKey().equals("X-api-version")) {
        params.remove(param.getKey());
      }
    }
    return params;
  }

  private static Map<String, String> getHeaders(String apiKey, Map<String, String> customHeaders)
      throws XenditException {
    Map<String, String> headers = new HashMap<>();

    for (Map.Entry<String, String> header : customHeaders.entrySet()) {
      headers.put(header.getKey(), header.getValue());
    }

    headers.put("User-Agent", "Xendit Java Library/v1");
    headers.put("Accept", "application/json");

    // Override apiKey with Authorization key
    // Can be used as workaround for https://github.com/xendit/xendit-java/issues/30
    String base64Key = encodeBase64(apiKey + ":");
    String authorization = customHeaders.getOrDefault("Authorization", "Basic " + base64Key);
    headers.put("Authorization", authorization);

    return headers;
  }

  private static <T> T staticRequest(
      RequestResource.Method method,
      String url,
      Map<String, String> headers,
      Map<String, Object> params,
      String apiKey,
      Class<T> clazz)
      throws XenditException {
    XenditResponse response = rawRequest(method, url, headers, params, apiKey);

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
      RequestResource.Method method,
      String url,
      Map<String, String> headers,
      Map<String, Object> params,
      String apiKey)
      throws XenditException {

    if (apiKey == null || apiKey.trim().isEmpty()) {
      throw new AuthException("No API key is provided yet.");
    }

    // Hacky way to inject params for XP into headers
    headers = constructCustomHeaders(headers, params);

    // Hacky way to remove injected params appended to headers
    params = filterParams(params);

    String jsonParams = "";

    if (params != null) {
      Gson gson = new GsonBuilder().create();
      jsonParams = gson.toJson(params);
    }

    if (method == RequestResource.Method.PATCH) {
      // new Implementation using org.apache.http to support PATCH Method
      return httpPatchXenditConnection(url, apiKey, headers, jsonParams);
    } else {
      // default Implementation of Xendit Connection
      return defaultHttpXenditConnection(method, url, apiKey, headers, jsonParams);
    }
  }

  private static XenditResponse httpPatchXenditConnection(
      String url, String apiKey, Map<String, String> headers, String jsonParams)
      throws XenditException {

    try {
      CloseableHttpClient httpClient =
          HttpClients.custom()
              .setDefaultRequestConfig(
                  RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
              .build();
      HttpPatch request = new HttpPatch(url);
      HttpEntity httpEntity = new ByteArrayEntity(jsonParams.getBytes("UTF-8"));
      if (headers.get("Content-Type") == null) {
        request.setHeader("Content-Type", "application/json");
      }
      for (Map.Entry<String, String> header : getHeaders(apiKey, headers).entrySet()) {
        request.setHeader(header.getKey(), header.getValue());
      }

      request.setEntity(httpEntity);
      CloseableHttpResponse response;
      response = httpClient.execute(request);
      int responseCode = response.getStatusLine().getStatusCode();
      String responseBody = EntityUtils.toString(response.getEntity());

      return new XenditResponse(responseCode, responseBody);
    } catch (IOException e) {
      throw new XenditException("Connection error : "+e.getMessage());
    }
  }

  private static XenditResponse defaultHttpXenditConnection(
      RequestResource.Method method,
      String url,
      String apiKey,
      Map<String, String> headers,
      String jsonParams)
      throws XenditException {
    HttpURLConnection connection = null;
    try {
      connection = createXenditConnection(url, apiKey, headers);
      connection.setRequestMethod(method.getText());
      if (method == RequestResource.Method.POST) {
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

  private static HttpURLConnection createXenditConnection(
      String url, String apiKey, Map<String, String> headers) throws IOException, XenditException {
    URL xenditUrl = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) xenditUrl.openConnection();

    connection.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
    connection.setUseCaches(false);

    for (Map.Entry<String, String> header : getHeaders(apiKey, headers).entrySet()) {
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
