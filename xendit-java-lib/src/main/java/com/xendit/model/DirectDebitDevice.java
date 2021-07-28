package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DirectDebitDevice {
  @SerializedName("id")
  private String id;

  @SerializedName("ip_address")
  private String ipAddress;

  @SerializedName("user_agent")
  private String userAgent;

  @SerializedName("ad_id")
  private String adId;

  @SerializedName("imei")
  private String imei;
}
