package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.ParamException;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
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

  /**
   * Create closed VA with complete object
   *
   * @param params listed here https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createClosed(Map<String, Object> params)
      throws XenditException {
    return create(params, true);
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

    return create(params, true);
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

    return create(params, true);
  }

  /**
   * Create closed VA with complete object
   *
   * @param params listed here https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount createOpen(Map<String, Object> params) throws XenditException {
    return create(params, false);
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

    return create(params, false);
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

    return create(params, false);
  }

  /**
   * @return array of AvailableBank model.
   * @throws XenditException
   */
  public static AvailableBank[] getAvailableBanks() throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/available_virtual_account_banks");
    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, null, AvailableBank[].class);
  }

  /**
   * Get fixed VA based on its ID
   *
   * @param id ID of the fixed virtual account to retrieve
   * @return FixedVirtualAccount model.
   * @throws XenditException
   */
  public static FixedVirtualAccount getFixedVA(String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.getUrl(), "/callback_virtual_accounts/", id);
    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, null, FixedVirtualAccount.class);
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
    String url = String.format("%s%s%s", Xendit.getUrl(), "/callback_virtual_accounts/", id);
    return Xendit.requestClient.request(
        RequestResource.Method.PATCH, url, params, FixedVirtualAccount.class);
  }

  /**
   * Get VA payment based on its payment ID
   *
   * @param paymentId ID of the payment to retrieve
   * @return VirtualAccountPayment model.
   * @throws XenditException
   */
  public static FixedVirtualAccountPayment getPayment(String paymentId) throws XenditException {
    String url =
        String.format(
            "%s%s%s", Xendit.getUrl(), "/callback_virtual_account_payments/payment_id=", paymentId);
    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, null, FixedVirtualAccountPayment.class);
  }

  private static FixedVirtualAccount create(Map<String, Object> params, Boolean isClosed)
      throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/callback_virtual_accounts");

    params.put("is_closed", isClosed);

    if (isClosed && params.containsKey("suggested_amount")) {
      throw new ParamException("Suggested amount is not supported for closed VA");
    }

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, FixedVirtualAccount.class);
  }
}
