package com.xendit.network;

public class XenditResponse {
  int statusCode;
  String body;
  String signature;

  public XenditResponse(int statusCode, String body, String signature) {
    this.statusCode = statusCode;
    this.body = body;
    this.signature = signature;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public String getBody() {
    return this.body;
  }

  public String getSignature() {
    return this.signature;
  }
}
