package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditCardCharge {
  @SerializedName("id")
  private static String id;

  @SerializedName("external_id")
  private static String externalId;

  @SerializedName("created")
  private static String created;

  @SerializedName("status")
  private static CreditCardEnum.ChargeStatus status;

  @SerializedName("business_id")
  private static String businessId;

  @SerializedName("authorized_amount")
  private static Number authorizedAmount;

  @SerializedName("merchant_id")
  private static String merchantId;

  @SerializedName("merchant_reference_code")
  private static String merchantReferenceCode;

  @SerializedName("card_type")
  private static CreditCardEnum.CardType cardType;

  @SerializedName("masked_card_number")
  private static String maskedCardNumber;

  @SerializedName("charge_type")
  private static CreditCardEnum.ChargeType chargeType;

  @SerializedName("card_brand")
  private static String cardBrand;

  @SerializedName("bank_reconciliation_id")
  private static String bankReconciliationId;

  @SerializedName("eci")
  private static CreditCardEnum.EciCode eci;

  @SerializedName("capture_amount")
  private static Number captureAmount;

  @SerializedName("descriptor")
  private static String descriptor;

  @SerializedName("failure_reason")
  private static CreditCardEnum.FailureReason failureReason;
}
