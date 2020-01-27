package com.xendit.model;

import com.google.gson.annotations.SerializedName;

public class XenditError {
  @SerializedName("error_code")
  private String errorCode;

  @SerializedName("message")
  private String message;

  public String getErrorCode() {
    return errorCode;
  }

  public String getMessage() {
    return message;
  }
}
