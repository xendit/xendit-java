package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditCardReverseAuth extends AbstractResponseHeaders {
  @SerializedName("id")
  private String id;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("business_id")
  private String businessId;

  @SerializedName("merchant_reference_code")
  private String merchantReferenceCode;

  @SerializedName("masked_card_number")
  private String maskedCardNumber;

  @SerializedName("charge_type")
  private CreditCardEnum.ChargeType chargeType;

  @SerializedName("card_brand")
  private String cardBrand;

  @SerializedName("authorized_amount")
  private String authorizedAmount;

  @SerializedName("status")
  private CreditCardEnum.ChargeStatus status;

  @SerializedName("created")
  private String created;
}
