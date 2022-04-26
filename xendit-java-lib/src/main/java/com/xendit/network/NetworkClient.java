package com.xendit.network;

import com.xendit.exception.XenditException;
import java.util.Map;

public interface NetworkClient {
  <T> T request(
      RequestResource.Method method,
      String url,
      Map<String, Object> params,
      String apiKey,
      Class<T> clazz)
      throws XenditException;

  <T> T request(
      RequestResource.Method method,
      String url,
      Map<String, String> headers,
      Map<String, Object> params,
      String apiKey,
      Class<T> clazz)
      throws XenditException;

  <T> T request(
      RequestResource.Method method,
      String url,
      Map<String, String> headers,
      Map<String, Object> params,
      String apiKey,
      String privateKey,
      Class<T> clazz)
      throws XenditException;
}
