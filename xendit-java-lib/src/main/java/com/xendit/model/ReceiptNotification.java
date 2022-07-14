package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptNotification {
  @SerializedName("email_to")
  private String[] emailTo;

  @SerializedName("email_cc")
  private String[] emailCC;

  @SerializedName("email_bcc")
  private String[] emailBcc;
}
