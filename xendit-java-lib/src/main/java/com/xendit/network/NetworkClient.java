package com.xendit.network;

import com.xendit.exception.XenditException;
import java.util.Map;

public interface NetworkClient {
  <T> T request(
      RequestResource.Method method, String url, Map<String, Object> params, Class<T> clazz)
      throws XenditException;
}
