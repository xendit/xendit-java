package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditCardRefund extends AbstractResponseHeaders {
  @SerializedName("id")
  private String id;

  @SerializedName("updated")
  private String updated;

  @SerializedName("created")
  private String created;

  @SerializedName("credit_card_charge_id")
  private String creditCardChargeId;

  @SerializedName("user_id")
  private String userId;

  @SerializedName("amount")
  private Number amount;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("status")
  private CreditCardEnum.RefundStatus status;

  @SerializedName("failure_reason")
  private CreditCardEnum.RefundFailureReason failureReason;

  @SerializedName("fee_refund_amount")
  private Number feeRefundAmount;
}
