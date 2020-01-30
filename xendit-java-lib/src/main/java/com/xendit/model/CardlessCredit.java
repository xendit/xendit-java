package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CardlessCredit {
  @SerializedName("redirect_url")
  private String redirectUrl;

  @SerializedName("transaction_id")
  private String transactionId;

  @SerializedName("order_id")
  private String orderId;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("cardless_credit_type")
  private String cardlessCreditType;

  public enum PaymentType {
    THIRTY_DAYS("30_days"),
    THREE_MONTHS("3_months"),
    SIX_MONTHS("6_months"),
    TWELVE_MONTHS("12_months");

    private String val;

    PaymentType(String val) {
      this.val = val;
    }
  }

  public static CardlessCredit create(
      String cardlessCreditType,
      String externalId,
      Number amount,
      PaymentType paymentType,
      CardlessCreditItem[] items,
      CardlessCreditCustomer customerDetails,
      CardlessCreditShippingAddress shippingAddress,
      String redirectUrl,
      String callbackUrl)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("cardless_credit_type", cardlessCreditType);
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("payment_type", paymentType);
    params.put("items", items);
    params.put("customer_details", customerDetails);
    params.put("shipping_address", shippingAddress);
    params.put("redirect_url", redirectUrl);
    params.put("callback_url", callbackUrl);
    String url = String.format("%s%s", Xendit.getUrl(), "/cardless-credit");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CardlessCredit.class);
  }
}
