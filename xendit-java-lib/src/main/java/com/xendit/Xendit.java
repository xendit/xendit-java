package com.xendit;

import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import java.util.Map;

public class Xendit {

  private static volatile NetworkClient requestClient = new BaseRequest();

  public static String apiKey;

  public static Option Opt = new Option();

  public static NetworkClient getRequestClient() {
    return requestClient;
  }

  public static void setRequestClient(NetworkClient requestClient) {
    Xendit.requestClient = requestClient;
  }

  protected static Map<String, String> responseHeaders;

  public static Map<String, String> getResponseHeaders() {
    return responseHeaders;
  }

  public static void setResponseHeaders(Map<String, String> headers) {
    Xendit.responseHeaders = headers;
  }

  public static class Option {

    private String apiKey;

    public String getApiKey() {
      return apiKey;
    }

    public Option setApiKey(String secretKey) {
      this.apiKey = secretKey;
      return this;
    }

    public String getXenditURL() {
      return "https://api.xendit.co";
    }

    public String getVersion() {
      return "1.22.0";
    }
  }
}
