package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
public class PaylaterPlans {
  @SerializedName("customer_id")
  private String customerId;

  @SerializedName("channel_code")
  private String channelCode;

  @SerializedName("currency")
  private String status;

  @SerializedName("amount")
  private String amount;

  @SerializedName("order_items")
  private PaylaterOrderItem[] orderItems;

  private static PaylaterClient paylaterClient;

  /**
   * Create new e-wallet charge
   *
   * @param customerId.
   * @param channel code.
   * @param currency.
   * @param amount.
   * @param order_items, Array of objects describing the item/s purchased using PayLater.
   * @return Paylater model.
   * @throws XenditException XenditException
   */

    public static PaylaterPlans initiatePaylaterPlans(
       String customerId,
       String channelCode,
       String currency,
       String amount,
       PaylaterOrderItem[] orderItems)
       throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", customerId);
        params.put("channel_code", channelCode);
        params.put("currency", currency);
        params.put("amount", amount);
        params.put("order_items", orderItems);
        return initiatePaylaterPlans(new HashMap<>(), params);
    }

    /**
   * initiate paylater plans with all parameter as HashMap
   *
   * @param params listed here https://developers.xendit.co/api-reference/#initiate-paylater-plans.
   * @return PaylaterPlans
   * @throws XenditException
   */
    public static PaylaterPlans initiatePaylaterPlans(Map<String, Object> params)
        throws XenditException {
        return initiatePaylaterPlans(new HashMap<>(), params);
    }

    /**
    * initiate paylater plans with all parameter as HashMap with headers and all parameter as HashMap
    *
    * @param headers
    * @param params listed here https://developers.xendit.co/api-reference/#initiate-paylater-plans.
    * @return EWalletCharge
    * @throws XenditException
    */
    public static PaylaterPlans initiatePaylaterPlans(
     Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return initiatePaylaterPlans(headers, params);
    }
}