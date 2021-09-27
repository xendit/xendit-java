package com.xenditclient.virtualAccount;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.Xendit;
import lombok.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FixedVirtualAccount {

  @SerializedName("id")
  private String id;

  @SerializedName("owner_id")
  private String ownerId;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("merchant_code")
  private String merchantCode;

  @SerializedName("account_number")
  private String accountNumber;

  @SerializedName("bank_code")
  private String bankCode;

  @SerializedName("name")
  private String name;

  @SerializedName("is_closed")
  private Boolean isClosed;

  @SerializedName("expiration_date")
  private Date expirationDate;

  @SerializedName("is_single_use")
  private Boolean isSingleUse;

  @SerializedName("status")
  private String status;

  // optionals
  @SerializedName("suggested_amount")
  private Long suggestedAmount;

  @SerializedName("expected_amount")
  private Long expectedAmount;

  @SerializedName("currency")
  private String currency;

  @SerializedName("description")
  private String description;

  private static FixedVirtualAccountClient fixedVirtualAccountClient;

  /**
   * Create closed VA with complete object
   *
   * @param params listed here https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createClosed(Map<String, Object> params)
      throws XenditException {
    return create(new HashMap<>(), params, true);
  }

  /**
   * Create closed VA with complete object
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createClosed(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return create(headers, params, true);
  }

  /**
   * Create closed VA with only required parameters
   *
   * @param externalId An ID of your choice, usually something that link Xendit VA with your
   *     internal system.
   * @param bankCode Bank code of the VA you want to create. See BankCode enum.
   * @param name Name of the VA, usually your end user's name or your company's.
   * @param expectedAmount Expected payment amount for this VA.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createClosed(
      String externalId, String bankCode, String name, Long expectedAmount) throws XenditException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("name", name);
    params.put("expected_amount", expectedAmount);

    return create(new HashMap<>(), params, true);
  }

  /**
   * Create closed VA with required parameters and can accept additional params
   *
   * @param externalId An ID of your choice, usually something that link Xendit VA with your
   *     internal system.
   * @param bankCode Bank code of the VA you want to create. See BankCode enum.
   * @param name Name of the VA, usually your end user's name or your company's.
   * @param expectedAmount Expected payment amount for this VA.
   * @param additionalParam Optional params. Check
   *     https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createClosed(
      String externalId,
      String bankCode,
      String name,
      Long expectedAmount,
      Map<String, Object> additionalParam)
      throws XenditException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("name", name);
    params.put("expected_amount", expectedAmount);
    params.putAll(additionalParam);

    return create(new HashMap<>(), params, true);
  }

  /**
   * Create closed VA with complete object
   *
   * @param params listed here https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createOpen(Map<String, Object> params) throws XenditException {
    return create(new HashMap<>(), params, false);
  }

  /**
   * Create closed VA with complete object
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createOpen(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return create(headers, params, false);
  }

  /**
   * Create open VA with only required parameters.
   *
   * @param externalId An ID of your choice, usually something that link Xendit VA with your
   *     internal system.
   * @param bankCode Bank code of the VA you want to create. See BankCode enum.
   * @param name Name of the VA, usually your end user's name or your company's.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createOpen(String externalId, String bankCode, String name)
      throws XenditException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("name", name);

    return create(new HashMap<>(), params, false);
  }

  /**
   * Create open VA with required params and optional params.
   *
   * @param externalId An ID of your choice, usually something that link Xendit VA with your
   *     internal system.
   * @param bankCode Bank code of the VA you want to create. See BankCode enum.
   * @param name Name of the VA, usually your end user's name or your company's.
   * @param additionalParam Optional params. Check
   *     https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createOpen(
      String externalId, String bankCode, String name, Map<String, Object> additionalParam)
      throws XenditException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("name", name);
    params.putAll(additionalParam);

    return create(new HashMap<>(), params, false);
  }

  /**
   * @return array of AvailableBank model.
   * @throws XenditException
   */
  public static AvailableBank[] getAvailableBanks() throws XenditException {
    FixedVirtualAccountClient client = getClient();
    return client.getAvailableBanks();
  }

  /**
   * Get fixed VA based on its ID
   *
   * @param headers Optional request headers
   * @param id ID of the fixed virtual account to retrieve
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount getFixedVA(Map<String, String> headers, String id)
      throws XenditException {
    FixedVirtualAccountClient client = getClient();
    return client.getFixedVA(headers, id);
  }

  /**
   * Get fixed VA based on its ID
   *
   * @param id ID of the fixed virtual account to retrieve
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount getFixedVA(String id) throws XenditException {
    return getFixedVA(new HashMap<>(), id);
  }

  /**
   * Update fixed VA based on its ID
   *
   * @param id ID of the fixed virtual account to update
   * @param params listed here https://xendit.github.io/apireference/#update-fixed-virtual-account
   * @return FixedVirtualAccount
   * @throws XenditException XenditException
   */
  public static FixedVirtualAccount update(String id, Map<String, Object> params)
      throws XenditException {
    FixedVirtualAccountClient client = getClient();
    return client.update(id, params);
  }

  /**
   * Get VA payment based on its payment ID
   *
   * @param paymentId ID of the payment to retrieve
   * @return VirtualAccountPayment model.
   * @throws XenditException
   */
  public static FixedVirtualAccountPayment getPayment(String paymentId) throws XenditException {
    FixedVirtualAccountClient client = getClient();
    return client.getPayment(paymentId);
  }

  private static FixedVirtualAccount create(
      Map<String, String> headers, Map<String, Object> params, Boolean isClosed)
      throws XenditException {
    FixedVirtualAccountClient client = getClient();
    return client.create(headers, params, isClosed);
  }


  /**
   * Its create a client for FixedVirtualAccount
   *
   * @return FixedVirtualAccountClient
   */
  private static FixedVirtualAccountClient getClient() {
    if (isApiKeyExist()) {
      if (fixedVirtualAccountClient == null
              || !fixedVirtualAccountClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return fixedVirtualAccountClient =
                new FixedVirtualAccountClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (fixedVirtualAccountClient == null
              || !fixedVirtualAccountClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
        return fixedVirtualAccountClient = new FixedVirtualAccountClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return fixedVirtualAccountClient;
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
