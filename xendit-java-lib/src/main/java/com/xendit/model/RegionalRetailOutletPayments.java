package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.model.RegionalRetailOutletPaymentCode.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegionalRetailOutletPayments extends AbstractResponseHeaders {
  @SerializedName("id")
  private String id;

  @SerializedName("payment_code_id")
  private String paymentCodeId;

  @SerializedName("payment_code")
  private String paymentCode;

  @SerializedName("channel_code")
  private String channelCode;

  @SerializedName("currency")
  private Currency currency;

  @SerializedName("amount")
  private String amount;

  @SerializedName("status")
  private String status;

  @SerializedName("remarks")
  private String remarks;

  @SerializedName("created_at")
  private String createdAt;

  @SerializedName("updated_at")
  private String updatedAt;
}
