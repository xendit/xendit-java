package com.xendit.network;

import javax.annotation.Nullable;

public abstract class RequestResource {
  public static final String CHARSET = "UTF-8";

  public enum Method {
    GET("GET"),
    POST("POST"),
    PATCH("PATCH"),
    DELETE("DELETE");

    private String text;

    Method(String text) {
      this.text = text;
    }

    public String getText() {
      return this.text;
    }

    @Nullable
    public static Method fromString(String text) {
      for (Method m : Method.values()) {
        if (m.text.equalsIgnoreCase(text)) {
          return m;
        }
      }
      return null;
    }
  }
}
