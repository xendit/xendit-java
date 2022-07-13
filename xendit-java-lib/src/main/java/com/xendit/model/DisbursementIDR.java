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
public class DisbursementIDR {
  @SerializedName("id")
  private String id;

  @SerializedName("user_id")
  private String userId;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("amount")
  private BigInteger amount;

  @SerializedName("bank_code")
  private String bankCode;

  @SerializedName("account_holder_name")
  private String accountHolderName;

  @SerializedName("disbursement_description")
  private String disbursementDescription;

  @SerializedName("status")
  private String status;

  // optionals
  @SerializedName("email_to")
  private String[] emailTo;

  @SerializedName("email_cc")
  private String[] emailCC;

  @SerializedName("email_bcc")
  private String[] emailBcc;

  @SerializedName("failure_code")
  private String failure_code;

  private static DisbursementClient disbursementClient;

  /**
   * Create disbursement with all parameter as HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-disbursement.
   * @return Disbursement
   * @throws XenditException
   */
  public static DisbursementIDR create(Map<String, Object> params) throws XenditException {
    return createRequest(new HashMap<>(), params);
  }

  /**
   * Create disbursement with all parameter as HashMap
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#create-disbursement.
   * @return Disbursement
   * @throws XenditException
   */
  public static DisbursementIDR create(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return createRequest(headers, params);
  }

  /**
   * Create disbursement with required parameters
   *
   * @param externalId ID of the disbursement in your system, used to reconcile disbursements after
   *     they have been completed.
   * @param bankCode Code of the destination bank.
   * @param accountHolderName Name of account holder as per the bank's or e-wallet's records. Used
   *     for verification and error/customer support scenarios.
   * @param accountNumber Destination bank account number. If disbursing to an e-wallet, phone
   *     number registered with the e-wallet account.
   * @param description Description to send with the disbursement
   * @param amount Amount to disburse
   * @return Disbursement
   * @throws XenditException
   */
  public static DisbursementIDR create(
      String externalId,
      String bankCode,
      String accountHolderName,
      String accountNumber,
      String description,
      BigInteger amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("account_holder_name", accountHolderName);
    params.put("account_number", accountNumber);
    params.put("description", description);
    params.put("amount", amount);
    return createRequest(new HashMap<>(), params);
  }

  /**
   * Create disbursement with required parameters
   *
   * @param externalId ID of the disbursement in your system, used to reconcile disbursements after
   *     they have been completed.
   * @param bankCode Code of the destination bank.
   * @param accountHolderName Name of account holder as per the bank's or e-wallet's records. Used
   *     for verification and error/customer support scenarios.
   * @param accountNumber Destination bank account number. If disbursing to an e-wallet, phone
   *     number registered with the e-wallet account.
   * @param description Description to send with the disbursement
   * @param amount Amount to disburse
   * @param emailTo Email addresses that get notified of disbursement details after the disbursement
   *     is completed. Maximum 3 email addresses accepted.
   * @return Disbursement
   * @throws XenditException
   */
  public static DisbursementIDR create(
      String externalId,
      String bankCode,
      String accountHolderName,
      String accountNumber,
      String description,
      BigInteger amount,
      String[] emailTo)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("account_holder_name", accountHolderName);
    params.put("account_number", accountNumber);
    params.put("description", description);
    params.put("amount", amount);
    params.put("email_to", emailTo);
    return createRequest(new HashMap<>(), params);
  }

  /**
   * Create disbursement with required parameters
   *
   * @param externalId ID of the disbursement in your system, used to reconcile disbursements after
   *     they have been completed.
   * @param bankCode Code of the destination bank.
   * @param accountHolderName Name of account holder as per the bank's or e-wallet's records. Used
   *     for verification and error/customer support scenarios.
   * @param accountNumber Destination bank account number. If disbursing to an e-wallet, phone
   *     number registered with the e-wallet account.
   * @param description Description to send with the disbursement
   * @param amount Amount to disburse
   * @param emailTo Email addresses that get notified of disbursement details after the disbursement
   *     is completed. Maximum 3 email addresses accepted.
   * @param emailCc Email addresses that get notified as carbon copy receiver of disbursement
   *     details after the disbursement is completed. Maximum 3 email addresses accepted. Only
   *     allowed if email_to provided.
   * @return Disbursement
   * @throws XenditException
   */
  public static DisbursementIDR create(
      String externalId,
      String bankCode,
      String accountHolderName,
      String accountNumber,
      String description,
      BigInteger amount,
      String[] emailTo,
      String[] emailCc)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("account_holder_name", accountHolderName);
    params.put("account_number", accountNumber);
    params.put("description", description);
    params.put("amount", amount);
    params.put("email_to", emailTo);
    params.put("email_cc", emailCc);
    return createRequest(new HashMap<>(), params);
  }

  /**
   * Create disbursement with required parameters
   *
   * @param externalId ID of the disbursement in your system, used to reconcile disbursements after
   *     they have been completed.
   * @param bankCode Code of the destination bank.
   * @param accountHolderName Name of account holder as per the bank's or e-wallet's records. Used
   *     for verification and error/customer support scenarios.
   * @param accountNumber Destination bank account number. If disbursing to an e-wallet, phone
   *     number registered with the e-wallet account.
   * @param description Description to send with the disbursement
   * @param amount Amount to disburse
   * @param emailTo Email addresses that get notified of disbursement details after the disbursement
   *     is completed. Maximum 3 email addresses accepted.
   * @param emailCc Email addresses that get notified as carbon copy receiver of disbursement
   *     details after the disbursement is completed. Maximum 3 email addresses accepted. Only
   *     allowed if email_to provided.
   * @param emailBcc Email addresses that get notified as blind carbon copy receiver of disbursement
   *     details after the disbursement is completed. Maximum 3 email addresses accepted. Only
   *     allowed if email_to provided.
   * @return Disbursement
   * @throws XenditException
   */
  public static DisbursementIDR create(
      String externalId,
      String bankCode,
      String accountHolderName,
      String accountNumber,
      String description,
      BigInteger amount,
      String[] emailTo,
      String[] emailCc,
      String[] emailBcc)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("account_holder_name", accountHolderName);
    params.put("account_number", accountNumber);
    params.put("description", description);
    params.put("amount", amount);
    params.put("email_to", emailTo);
    params.put("email_cc", emailCc);
    params.put("email_bcc", emailBcc);
    return createRequest(new HashMap<>(), params);
  }

  private static DisbursementIDR createRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    DisbursementClient client = getClient();
    return client.createRequest(headers, params);
  }

  /**
   * Get disbursement available bank
   *
   * @param headers
   * @return
   * @throws XenditException
   */
  public static AvailableBank[] getAvailableBanks(Map<String, String> headers)
      throws XenditException {

    DisbursementClient client = getClient();
    return client.getAvailableBanks(headers);
  }

  /**
   * Get disbursement available bank
   *
   * @return
   * @throws XenditException
   */
  public static AvailableBank[] getAvailableBanks() throws XenditException {
    return getAvailableBanks(new HashMap<>());
  }

  /**
   * Get array of object disbursements by external id
   *
   * @param headers
   * @param externalId
   * @return
   * @throws XenditException
   */
  public static DisbursementIDR[] getByExternalId(Map<String, String> headers, String externalId)
      throws XenditException {

    DisbursementClient client = getClient();
    return client.getByExternalId(headers, externalId);
  }

  /**
   * Get array of object disbursements by external id
   *
   * @param externalId
   * @return
   * @throws XenditException
   */
  public static DisbursementIDR[] getByExternalId(String externalId) throws XenditException {
    return getByExternalId(new HashMap<>(), externalId);
  }

  /**
   * Get object disbursement by id
   *
   * @param headers
   * @param id
   * @return
   * @throws XenditException
   */
  public static DisbursementIDR getById(Map<String, String> headers, String id)
      throws XenditException {
    DisbursementClient client = getClient();
    return client.getById(headers, id);
  }

  /**
   * Get object disbursement by id
   *
   * @param id
   * @return
   * @throws XenditException
   */
  public static DisbursementIDR getById(String id) throws XenditException {
    return getById(new HashMap<>(), id);
  }

  /**
   * Its create a client for Disbursement
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
}
