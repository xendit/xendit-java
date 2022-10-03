package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
public class DisbursementPHP {
  @SerializedName("id")
  private String id;

  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("currency")
  private String currency;

  @SerializedName("amount")
  private double amount;

  @SerializedName("channel_code")
  private String channelCode;

  @SerializedName("description")
  private String description;

  @SerializedName("status")
  private String status;

  @SerializedName("created")
  private Date created;

  @SerializedName("updated")
  private Date updated;

  @SerializedName("failure_code")
  private String failureCode;

  // optionals
  @SerializedName("receipt_notification")
  private ReceiptNotification receiptNotification;

  private static DisbursementPHPClient disbursementPHPClient;

  /**
   * Get object disbursementPHP by id
   *
   * @param headers
   * @param id
   * @return
   * @throws XenditException
   */
  public static DisbursementPHP getPHPById(Map<String, String> headers, String id)
      throws XenditException {
    DisbursementPHPClient client = getClient();
    return client.getPHPById(headers, id);
  }

  /**
   * Get object disbursementPHP by id
   *
   * @param id
   * @return
   * @throws XenditException
   */
  public static DisbursementPHP getPHPById(String id) throws XenditException {
    return getPHPById(new HashMap<>(), id);
  }

  /**
   * Its create a client for DisbursementPH
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

  /**
   * Create disbursementPHP with all parameter as HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-php-disbursement.
   * @return DisbursementPHP
   * @throws XenditException
   */
  public static DisbursementPHP createPHPDisbursement(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return createRequest(headers, params);
  }

  /**
   * Create v1 disbursementPHP with required parameters
   *
   * @param xendit_idempotency_key
   * @param reference_id
   * @param currency
   * @param channel_code
   * @param account_name
   * @param account_number
   * @param description
   * @param amount
   * @return DisbursementPHP
   * @throws XenditException
   */
  public static DisbursementPHP createPHPDisbursement(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      Integer amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    headers.put("xendit-idempotency-key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    return createRequest(headers, params);
  }

  /**
   * Create v1 disbursement with required and optional parameters
   *
   * @param xendit_idempotency_key
   * @param reference_id
   * @param currency
   * @param channel_code
   * @param account_name
   * @param account_number
   * @param description
   * @param amount
   * @param receiptNotification ReceiptNotification
   * @return DisbursementPHP
   * @throws XenditException
   */
  public static DisbursementPHP createPHPDisbursement(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      Integer amount,
      ReceiptNotification receiptNotification)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    headers.put("xendit-idempotency-key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    params.put("receipt_notification", receiptNotification);
    return createRequest(headers, params);
  }

  /**
   * Create v1 disbursementPHP with required and optional parameters
   *
   * @param xendit_idempotency_key
   * @param reference_id
   * @param currency
   * @param channel_code
   * @param account_name
   * @param account_number
   * @param description
   * @param amount
   * @param beneficiary Beneficiary
   * @return DisbursementPHP
   * @throws XenditException
   */
  public static DisbursementPHP createPHPDisbursement(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      Integer amount,
      Beneficiary beneficiary)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    headers.put("xendit-idempotency-key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    params.put("beneficiary", beneficiary);
    return createRequest(headers, params);
  }

  /**
   * Create v1 disbursementPHP with required and optional parameters
   *
   * @param xendit_idempotency_key
   * @param reference_id
   * @param currency
   * @param channel_code
   * @param account_name
   * @param account_number
   * @param description
   * @param amount
   * @param receiptNotification ReceiptNotification
   * @param beneficiary Beneficiary
   * @return DisbursementPHP
   * @throws XenditException
   */
  public static DisbursementPHP createPHPDisbursement(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      Integer amount,
      ReceiptNotification receiptNotification,
      Beneficiary beneficiary)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    headers.put("xendit-idempotency-key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    params.put("receipt_notification", receiptNotification);
    params.put("beneficiary", beneficiary);
    return createRequest(headers, params);
  }

  /**
   * Get array of object PHP disbursements by refernce id
   *
   * @param referenceId
   * @return
   * @throws XenditException
   */
  public static DisbursementPHP[] getPHPByReferenceId(String referenceId) throws XenditException {
    return getPHPByReferenceId(new HashMap<String, String>(), referenceId);
  }

  /**
   * Get array of object PHP disbursements by refernce id
   *
   * @param headers
   * @param referenceId
   * @return
   * @throws XenditException
   */
  public static DisbursementPHP[] getPHPByReferenceId(
      Map<String, String> headers, String referenceId) throws XenditException {

    DisbursementPHPClient client = getClient();
    return client.getPHPByReferenceId(headers, referenceId);
  }

  private static DisbursementPHP createRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    DisbursementPHPClient client = getClient();
    return client.createPHPRequest(headers, params);
  }
}
