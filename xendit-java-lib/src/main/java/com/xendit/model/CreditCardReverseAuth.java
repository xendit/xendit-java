package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditCardReverseAuth {
  @SerializedName("id")
  private static String id;

  @SerializedName("external_id")
  private static String externalId;

  @SerializedName("business_id")
  private static String businessId;

  @SerializedName("merchant_reference_code")
  private static String merchantReferenceCode;

  @SerializedName("masked_card_number")
  private static String maskedCardNumber;

  @SerializedName("charge_type")
  private static CreditCardEnum.ChargeType chargeType;

  @SerializedName("card_brand")
  private static String cardBrand;

  @SerializedName("authorized_amount")
  private static String authorizedAmount;

  @SerializedName("status")
  private static CreditCardEnum.ChargeStatus status;

  @SerializedName("created")
  private static String created;
}
