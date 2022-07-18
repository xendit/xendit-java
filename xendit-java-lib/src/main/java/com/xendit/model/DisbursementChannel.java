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
public class DisbursementChannel {
  @SerializedName("channel_code")
  private String channelCode;

  @SerializedName("name")
  private String name;

  @SerializedName("channel_category")
  private String channelCategory;

  @SerializedName("currency")
  private String currency;

  @SerializedName("minimum")
  private Integer minimum;

  @SerializedName("maximum")
  private Integer maximum;

  @SerializedName("minimum_increment")
  private Double minimumIncrement;

  private static DisbursementPHPClient disbursementPHPClient;

  /**
   * Get array of object disbursements channels
   *
   * @return
   * @throws XenditException
   */
  public static DisbursementChannel[] getDisbursementChannels() throws XenditException {
    DisbursementPHPClient client = getClient();
    return client.getDisbursementChannels(new HashMap<>());
  }

  /**
   * Get array of object disbursements channels by channel category
   *
   * @param channelCategory
   * @return
   * @throws XenditException
   */
  public static DisbursementChannel[] getByChannelCategory(String channelCategory)
      throws XenditException {
    return getByChannelCategory(new HashMap<>(), channelCategory);
  }

  /**
   * Get array of object disbursements channels by channel category
   *
   * @param headers
   * @param channelCategory
   * @return
   * @throws XenditException
   */
  public static DisbursementChannel[] getByChannelCategory(
      Map<String, String> headers, String channelCategory) throws XenditException {
    DisbursementPHPClient client = getClient();
    return client.getByChannelCategory(headers, channelCategory);
  }

  /**
   * Get array of object disbursements channels by channel code
   *
   * @param channelCode
   * @return
   * @throws XenditException
   */
  public static DisbursementChannel[] getByChannelCode(String channelCode) throws XenditException {
    return getByChannelCode(new HashMap<>(), channelCode);
  }

  /**
   * Get array of object disbursements channels by channel category
   *
   * @param headers
   * @param channelCode
   * @return
   * @throws XenditException
   */
  public static DisbursementChannel[] getByChannelCode(
      Map<String, String> headers, String channelCode) throws XenditException {
    DisbursementPHPClient client = getClient();
    return client.getByChannelCode(headers, channelCode);
  }

  /**
   * Its create a client for Disbursement
   *
   * @return DisbursementPHPClient
   */
  private static DisbursementPHPClient getClient() {
    if (isApiKeyExist()) {
      if (disbursementPHPClient == null
          || !disbursementPHPClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return disbursementPHPClient =
            new DisbursementPHPClient(
                Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (disbursementPHPClient == null
          || !disbursementPHPClient
              .getOpt()
              .getApiKey()
              .trim()
              .equals(Xendit.Opt.getApiKey().trim())) {
        return disbursementPHPClient =
            new DisbursementPHPClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return disbursementPHPClient;
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
