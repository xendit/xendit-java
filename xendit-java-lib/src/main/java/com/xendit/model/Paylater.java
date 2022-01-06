package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Builder
@Getter
@Setter
public class Paylater {
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
   * initiate paylater plan with all parameters as HashMap
   *
   * @param params listed here
   *     https://developers.xendit.co/api-reference/?java#create-paylater-charges
   * @return Paylater
   * @throws XenditException XenditException
   */
  public static Paylater initiatePaylaterCharges(Map<String, Object> params)
      throws XenditException {
    return initiatePaylaterCharges(new HashMap<>(), params);
  }

  /**
   * initiate paylater plan with all parameters as HashMap
   *
   * @param headers
   * @param params listed here
   *     https://developers.xendit.co/api-reference/?java#create-paylater-charges
   * @return Paylater
   * @throws XenditException XenditException
   */
  public static Paylater initiatePaylaterCharges(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return initiatePaylaterCharges(headers, params);
  }

  /**
   * Its create a client for Paylater
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
