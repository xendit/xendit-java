package com.xendit.model;

import java.util.Map;

public class AbstractResponseHeaders {
  Map<String, String> responseHeaders;

  public Map<String, String> getResponseHeaders() {
    return responseHeaders;
  }

  public void setResponseHeaders(Map<String, String> responseHeaders) {
    this.responseHeaders = responseHeaders;
  }
}
