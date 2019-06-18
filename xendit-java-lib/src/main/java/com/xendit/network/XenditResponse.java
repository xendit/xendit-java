package com.xendit.network;

public class XenditResponse {
    int statusCode;
    String body;

    public XenditResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getBody() {
        return this.body;
    }
}
