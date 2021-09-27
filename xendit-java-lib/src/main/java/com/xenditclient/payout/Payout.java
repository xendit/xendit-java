package com.xenditclient.payout;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Payout {
  @SerializedName("id")
  private String id;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("amount")
  private Number amount;

  @SerializedName("passcode")
  private String passcode;

  @SerializedName("merchant_name")
  private String merchantName;

  @SerializedName("status")
  private String status;

  @SerializedName("expiration_timestamp")
  private String expirationTimestamp;

  @SerializedName("created")
  private String created;

  @SerializedName("payout_url")
  private String payoutUrl;

  @SerializedName("email")
  private String email;

  @SerializedName("bank_code")
  private String bankCode;

  @SerializedName("account_holder_name")
  private String accountHolderName;

  @SerializedName("account_number")
  private String accountNumber;

  @SerializedName("disbursement_id")
  private String disbursementId;

  @SerializedName("failure_reason")
  private String failureReason;

  @SerializedName("claimed_timestamp")
  private String claimedTimestamp;

  @SerializedName("completed_timestamp")
  private String completedTimestamp;

  @SerializedName("failed_timestamp")
  private String failedTimestamp;

  @SerializedName("payment_id")
  private String paymentId;

  private static PayoutClient payoutClient;

  /**
   * Create payout with given parameters
   *
   * @param externalId ID of your choice (typically the unique identifier of a payout in your
   *     system).
   * @param amount Amount to be paid out. The maximum amount depends on your balance.
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout createPayout(String externalId, Number amount) throws XenditException {
    PayoutClient client = getClient();
    return client.createPayout(externalId, amount);
  }

  /**
   * Create payout with all parameters as HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-payout
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout createPayout(Map<String, Object> params) throws XenditException {
    return createPayout(new HashMap<>(), params);
  }

  /**
   * Create payout with all parameters as HashMap
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#create-payout
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout createPayout(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    PayoutClient client = getClient();
    return client.createPayout(headers, params);
  }

  /**
   * Get payout detail by ID
   *
   * @param id ID of the payout to retrieve
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout getPayout(String id) throws XenditException {
    PayoutClient client = getClient();
    return client.getPayout(id);
  }

  /**
   * Void a payout by ID
   *
   * @param id ID of the payout to void
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout voidPayout(String id) throws XenditException {
    PayoutClient client = getClient();
    return client.voidPayout(id);
  }

  /**
   * Its create a client for Payout
   *
   * @return PayoutClient
   */
  private static PayoutClient getClient() {
    if (isApiKeyExist()) {
      if (payoutClient == null
          || !payoutClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return payoutClient =
            new PayoutClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (payoutClient == null
          || !payoutClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
        return payoutClient = new PayoutClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return payoutClient;
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
