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
public class PaylaterPlans extends AbstractResponseHeaders {
  @SerializedName("id")
  private String id;

  @SerializedName("customer_id")
  private String customerId;

  @SerializedName("channel_code")
  private String channelCode;

  @SerializedName("currency")
  private String currency;

  @SerializedName("amount")
  private Number amount;

  @SerializedName("order_items")
  private PaylaterOrderItem[] orderItems;

  @SerializedName("options")
  private PaylaterOptions[] options;

  @SerializedName("created")
  private String created;

  private static PaylaterClient paylaterClient;

  /**
   * Create new e-wallet charge
   *
   * @param customerId.
   * @param channelCode.
   * @param currency.
   * @param amount.
   * @param orderItems, Array of objects describing the item/s purchased using PayLater.
   * @return PaylaterPlans model.
   * @throws XenditException XenditException
   */
  public static PaylaterPlans initiatePaylaterPlans(
      String customerId,
      String channelCode,
      String currency,
      Number amount,
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
    PaylaterClient client = getClient();
    return client.initiatePaylaterPlans(headers, params);
  }

  /**
   * Its create a client for paylater
   *
   * @return PaylaterClient
   */
  private static PaylaterClient getClient() {
    if (isApiKeyExist()) {
      if (paylaterClient == null
          || !paylaterClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return paylaterClient =
            new PaylaterClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (paylaterClient == null
          || !paylaterClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
        return paylaterClient = new PaylaterClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return paylaterClient;
  }

  /**
   * check if api-key is exist or not
   *
   * @return boolean
   */
  private static boolean isApiKeyExist() {
    return Xendit.apiKey != null;
  }
}
