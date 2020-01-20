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

  /**
   * Create payout with given parameters
   *
   * @param externalId ID of your choice (typically the unique identifier of a payout in your
   *     system).
   * @param amount Amount to be paid out. The maximum amount depends on your balance.
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout create(String externalId, Number amount) throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    String url = String.format("%s%s", Xendit.getUrl(), "/payouts");
    return Xendit.requestClient.request(RequestResource.Method.POST, url, params, Payout.class);
  }

  /**
   * Create payout with all parameters as HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-payout
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout create(Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/payouts");
    return Xendit.requestClient.request(RequestResource.Method.POST, url, params, Payout.class);
  }

  /**
   * Get payout detail by ID
   *
   * @param id ID of the payout to retrieve
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout get(String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.getUrl(), "/payouts/", id);
    return Xendit.requestClient.request(RequestResource.Method.GET, url, null, Payout.class);
  }

  /**
   * Void a payout by ID
   *
   * @param id ID of the payout to void
   * @return Payout
   * @throws XenditException XenditException
   */
  public static Payout voidPayout(String id) throws XenditException {
    String url = String.format("%s%s%s%s", Xendit.getUrl(), "/payouts/", id, "/void");
    return Xendit.requestClient.request(RequestResource.Method.POST, url, null, Payout.class);
  }
}
