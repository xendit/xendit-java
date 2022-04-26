package com.xendit;

import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;

public class Xendit {

  private static volatile NetworkClient requestClient = new BaseRequest();

  public static String apiKey;

  public static String privateKey;

  public static Option Opt = new Option();

  public static NetworkClient getRequestClient() {
    return requestClient;
  }

  public static void setRequestClient(NetworkClient requestClient) {
    Xendit.requestClient = requestClient;
  }

  public static class Option {

    private String apiKey;

    private String privateKey;

    private String publicKey;

    public String getApiKey() {
      return apiKey;
    }

    public String getPrivateKey() {
      return privateKey;
    }

    public Option setApiKey(String secretKey) {
      this.apiKey = secretKey;
      return this;
    }

    public Option setPrivateKey(String privateKey) {
      this.privateKey = privateKey;
      return this;
    }

    public String getXenditURL() {
      return "https://api.xendit.co";
    }

    public String getVersion() {
      return "1.19.0";
    }
  }
}
