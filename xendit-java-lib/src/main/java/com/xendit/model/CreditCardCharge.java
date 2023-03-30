package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditCardCharge extends AbstractResponseHeaders {
  @SerializedName("id")
  private String id;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("created")
  private String created;

  @SerializedName("status")
  private CreditCardEnum.ChargeStatus status;

  @SerializedName("business_id")
  private String businessId;

  @SerializedName("authorized_amount")
  private Number authorizedAmount;

  @SerializedName("merchant_id")
  private String merchantId;

  @SerializedName("merchant_reference_code")
  private String merchantReferenceCode;

  @SerializedName("card_type")
  private CreditCardEnum.CardType cardType;

  @SerializedName("masked_card_number")
  private String maskedCardNumber;

  @SerializedName("charge_type")
  private CreditCardEnum.ChargeType chargeType;

  @SerializedName("card_brand")
  private String cardBrand;

  @SerializedName("bank_reconciliation_id")
  private String bankReconciliationId;

  @SerializedName("eci")
  private CreditCardEnum.EciCode eci;

  @SerializedName("capture_amount")
  private Number captureAmount;

  @SerializedName("descriptor")
  private String descriptor;

  @SerializedName("failure_reason")
  private CreditCardEnum.FailureReason failureReason;

  @SerializedName("installment")
  private Map<String, Object> installment;
}
