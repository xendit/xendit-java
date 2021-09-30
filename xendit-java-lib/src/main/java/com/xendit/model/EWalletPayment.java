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
public class EWalletPayment {
  public enum EWalletType {
    DANA,
    OVO,
    LINKAJA
  }

  @SerializedName("id")
  private String id;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("business_id")
  private String businessId;

  @SerializedName("phone")
  private String phone;

  @SerializedName("amount")
  private Number amount;

  @SerializedName("expiration_date")
  private String expirationDate;

  @SerializedName("ewallet_type")
  private String ewalletType;

  @SerializedName("transaction_date")
  private String transactionDate;

  @SerializedName("checkout_url")
  private String checkoutUrl;

  @SerializedName("status")
  private String status;

  private static EWalletClient eWalletClient;

  /**
   * Create new payment for LINKAJA
   *
   * @param externalId An ID of your choice. Often it is unique identifier like a phone number,
   *     email or transaction ID. Maximum length allowed is 1000 characters.
   * @param amount Amount end-customer will pay.
   * @param phone Phone number of end-customer (e.g. 08123123123)
   * @param items List of items / products.
   * @param callbackUrl The URL to receive the callback after payment made by end-customer
   * @param redirectUrl The URL to redirect to after payment made by end-customer
   * @return EWalletPayment model.
   * @throws XenditException XenditException
   */
  public static EWalletPayment createLinkajaPayment(
      String externalId,
      Number amount,
      String phone,
      EWalletLinkajaItem[] items,
      String callbackUrl,
      String redirectUrl)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("phone", phone);
    params.put("items", items);
    params.put("ewallet_type", EWalletType.LINKAJA);
    params.put("callback_url", callbackUrl);
    params.put("redirect_url", redirectUrl);
    return createPaymentRequest(new HashMap<>(), params);
  }

  /**
   * Create new payment for OVO
   *
   * @param externalId An ID of your choice. Often it is unique identifier like a phone number,
   *     email or transaction ID. Maximum length allowed is 1000 characters.
   * @param amount Amount end-customer will pay.
   * @param phone Phone number of end-customer (e.g. 08123123123)
   * @return EWalletPayment model.
   * @throws XenditException XenditException
   */
  public static EWalletPayment createOvoPayment(String externalId, Number amount, String phone)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("phone", phone);
    params.put("ewallet_type", EWalletType.OVO);
    return createPaymentRequest(new HashMap<>(), params);
  }

  /**
   * Create new payment for DANA
   *
   * @param externalId An ID of your choice. Often it is unique identifier like a phone number,
   *     email or transaction ID. Maximum length allowed is 1000 characters.
   * @param amount Amount end-customer will pay.
   * @param phone Phone number of end-customer (e.g. 08123123123)
   * @param expirationDate End-customer cannot complete the payment past the expiration date
   * @param callbackUrl The URL to receive the callback after payment made by end-customer
   * @param redirectUrl The URL to redirect to after payment made by end-customer
   * @return EWalletPayment
   * @throws XenditException XenditException
   */
  public static EWalletPayment createDanaPayment(
      String externalId,
      Number amount,
      String phone,
      String expirationDate,
      String callbackUrl,
      String redirectUrl)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("phone", phone);
    params.put("expiration_date", expirationDate);
    params.put("callback_url", callbackUrl);
    params.put("redirect_url", redirectUrl);
    params.put("ewallet_type", EWalletType.DANA);
    return createPaymentRequest(new HashMap<>(), params);
  }

  /**
   * @param externalId An ID of your choice. Often it is unique identifier like a phone number,
   *     email or transaction ID.
   * @param ewalletType The type of ewallet to be paid. Must be in capital letters.
   * @return EWalletPayment model.
   * @throws XenditException XenditException
   */
  public static EWalletPayment getPaymentStatus(String externalId, EWalletType ewalletType)
      throws XenditException {
    EWalletClient client = getClient();
    return client.getPaymentStatus(externalId, ewalletType);
  }

  public static EWalletPayment createPaymentRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    EWalletClient client = getClient();
    return client.createPaymentRequest(headers, params);
  }

  /**
   * Its create a client for Payout
   *
   * @return PayoutClient
   */
  private static EWalletClient getClient() {
    if (isApiKeyExist()) {
      if (eWalletClient == null
          || !eWalletClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return eWalletClient =
            new EWalletClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (eWalletClient == null
          || !eWalletClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
        return eWalletClient = new EWalletClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return eWalletClient;
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
