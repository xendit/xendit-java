package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private String amount;

    @SerializedName("order_items")
    private PaylaterOrderItem[] orderItems;

    @SerializedName("options")
    private PaylaterOptions[] options;

    @SerializedName("created")
    private String created;

    private static PaylaterClient paylaterClient;

   /**
   * Create payout with all parameters as HashMap
   *
   * @param headers
   * @param body listed here https://developers.xendit.co/api-reference/?java#create-paylater-charges
   * @return Paylater
   * @throws XenditException XenditException
   */
    public static Payout createPaylaterCharges(Map<String, String> headers, Map<String, Object> params)
        throws XenditException {
            PayoutClient client = getClient();
            return client.createPayout(headers, params);
    }

    /**
   * Get paylater detail by ID
   *
   * @param id ID of the paylater charge to retrieve
   * @return Paylater
   * @throws XenditException XenditException
   */
  public static Paylater getPaylater(String id) throws XenditException {
    PaylaterClient client = getClient();
    return client.getPaylater(id);
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