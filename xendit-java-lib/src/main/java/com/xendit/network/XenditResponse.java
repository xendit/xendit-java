package com.xendit.network;

import java.util.Map;

public class XenditResponse {
  int statusCode;
  String body;
  Map<String, String> responseHeaders;

  public XenditResponse(int statusCode, String body, Map<String, String> responseHeaders) {
    this.statusCode = statusCode;
    this.body = body;
    this.responseHeaders = responseHeaders;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public String getBody() {
    return this.body;
  }

  public Map<String, String> getResponseHeaders() {
    return this.responseHeaders;
  }

  public void setResponseHeaders(Map<String, String> responseHeaders) {
    this.responseHeaders = responseHeaders;
  }
}
