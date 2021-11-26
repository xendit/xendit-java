package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.Optional;
import lombok.*;

@Getter
@Setter
@Builder
public class RegionalRetailOutletPaymentCode {
  public enum ChannelCode {
    SEVENELEVEN("7ELEVEN"),
    SEVENELEVENCLIQQ("7ELEVEN_CLIQQ"),
    CEBUANA("CEBUANA"),
    DPMLHUILLIER("DPMLHUILLIER"),
    DPPALAWAN("DPPALAWAN"),
    ECPAY("ECPAY"),
    LBC("LBC");

    private String code;

    ChannelCode(String code) {
      this.code = code;
    }

    public String getChannelCode() {
      return code;
    }

    public static Optional<ChannelCode> get(String code) {
      return Arrays.stream(ChannelCode.values())
          .filter(element -> element.code.equals(code))
          .findFirst();
    }
  }

  public enum Market {
    PH
  }

  public enum Currency {
    PHP
  }

  @SerializedName("id")
  private String id;

  @SerializedName("business_id")
  private String businessId;

  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("customerName")
  private String customerName;

  @SerializedName("payment_code")
  private String paymentCode;

  @SerializedName("currency")
  private Currency currency;

  @SerializedName("amount")
  private String amount;

  @SerializedName("channel_code")
  private String channelCode;

  @SerializedName("description")
  private Number description;

  @SerializedName("is_single_use")
  private Boolean isSingleUse;

  @SerializedName("market")
  private Market market;

  @SerializedName("status")
  private String status;

  @SerializedName("metadata")
  private String metadata;

  @SerializedName("created_at")
  private String createdAt;

  @SerializedName("updated_at")
  private String updatedAt;

  @SerializedName("expires_at")
  private String expiresAt;
}
