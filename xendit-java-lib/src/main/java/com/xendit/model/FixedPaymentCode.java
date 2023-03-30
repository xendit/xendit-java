package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
public class FixedPaymentCode extends AbstractResponseHeaders {
  public enum RetailOutletName {
    ALFAMART,
    INDOMARET
  }

  @SerializedName("id")
  private String id;

  @SerializedName("owner_id")
  private String ownerId;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("retail_outlet_name")
  private RetailOutletName retailOutletName;

  @SerializedName("prefix")
  private String prefix;

  @SerializedName("name")
  private String name;

  @SerializedName("payment_code")
  private String paymentCode;

  @SerializedName("type")
  private String type;

  @SerializedName("expected_amount")
  private Number expectedAmount;

  @SerializedName("status")
  private String status;

  @SerializedName("is_single_use")
  private Boolean isSingleUse;

  @SerializedName("expiration_date")
  private String expirationDate;
}
