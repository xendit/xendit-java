package com.xendit.exception;

import java.util.Map;

public class XenditException extends Exception {
  private String code;
  private Map<String, Object> context;

  public XenditException(String message) {
    super(message);
  }

  public XenditException(String message, String code, Map<String, Object> context) {
    super(message);
    this.code = code;
    this.context = context;
  }

  public String getErrorCode() {
    return this.code;
  }

  public Map<String, Object> getContext() {
    return this.context;
  }
}
