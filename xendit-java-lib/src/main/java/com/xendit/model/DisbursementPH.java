package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
public class DisbursementPH {
  @SerializedName("id")
  private String id;

  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("currency")
  private String currency;

  @SerializedName("amount")
  private BigInteger amount;

  @SerializedName("channel_code")
  private BigInteger channelCode;

  @SerializedName("description")
  private String description;

  @SerializedName("status")
  private String status;

  @SerializedName("created")
  private String created;

  @SerializedName("updated")
  private String updated;

  // optionals
  @SerializedName("email_to")
  private String[] emailTo;

  @SerializedName("email_cc")
  private String[] emailCC;

  @SerializedName("email_bcc")
  private String[] emailBcc;

  private static DisbursementClient disbursementClient;

  /**
   * Get object disbursementPH by id
   *
   * @param headers
   * @param id
   * @return
   * @throws XenditException
   */
  public static DisbursementPH getById(Map<String, String> headers, String id)
      throws XenditException {
    DisbursementClient client = getClient();
    return client.getPHById(headers, id);
  }

  /**
   * Get object disbursementPH by id
   *
   * @param id
   * @return
   * @throws XenditException
   */
  public static DisbursementPH getById(String id) throws XenditException {
    return getById(new HashMap<>(), id);
  }

  /**
   * Its create a client for DisbursementPH
   *
   * @return DisbursementClient
   */
  private static DisbursementClient getClient() {
    if (isApiKeyExist()) {
      if (disbursementClient == null
          || !disbursementClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return disbursementClient =
            new DisbursementClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (disbursementClient == null
          || !disbursementClient
              .getOpt()
              .getApiKey()
              .trim()
              .equals(Xendit.Opt.getApiKey().trim())) {
        return disbursementClient = new DisbursementClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return disbursementClient;
  }

  /**
   * check if api-key is exist or not
   *
   * @return boolean
   */
  private static boolean isApiKeyExist() {
    return Xendit.apiKey != null;
  }

  /**
   * Create v1 disbursement with required parameters
   *
   * @param xendit_idempotency_key
   * @param reference_id
   * @param currency
   * @param channel_code
   * @param account_name
   * @param account_number
   * @param description
   * @param amount
   * @return Disbursement
   * @throws XenditException
   */
  public static DisbursementPH create(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      BigInteger amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("xendit_idempotency_key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    return createRequest(new HashMap<>(), params);
  }

  /**
   * Get array of object disbursements by external id
   *
   * @param headers
   * @param referenceId
   * @return
   * @throws XenditException
   */
  public static DisbursementPH[] getByReferenceId(Map<String, String> headers, String referenceId)
      throws XenditException {

    DisbursementClient client = getClient();
    return client.getByReferenceId(headers, referenceId);
  }

  private static DisbursementPH createRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    DisbursementClient client = getClient();
    return client.createPHRequest(headers, params);
  }
}
