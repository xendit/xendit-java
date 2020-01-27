package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.ParamException;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EWalletPayment {
  public enum EWalletType {
    DANA,
    OVO,
    LINKAJA
  }

  private static final BigInteger MINIMUM_AMOUNT = new BigInteger("1");
  private static final BigInteger MAXIMUM_AMOUNT = new BigInteger("10000000");

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
    return createRequest(params);
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
    return createRequest(params);
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
    return createRequest(params);
  }

  /**
   * @param externalId An ID of your choice. Often it is unique identifier like a phone number,
   *     email or transaction ID.
   * @param ewalletType The type of ewallet to be paid. Must be in capital letters.
   * @return EWalletPayment model.
   * @throws XenditException XenditException
   */
  public static EWalletPayment getPaymentStatus(String externalId, String ewalletType)
      throws XenditException {
    String url =
        String.format(
            "%s%s%s%s%s",
            Xendit.getUrl(), "/ewallets/?external_id=", externalId, "&ewallet_type=", ewalletType);
    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, null, EWalletPayment.class);
  }

  private static void amountValidation(String amount) throws ParamException {
    try {
      BigInteger bigInteger = new BigInteger(amount);

      if (bigInteger.compareTo(MINIMUM_AMOUNT) == -1) {
        throw new ParamException(String.format("Minimum amount is %s", MINIMUM_AMOUNT));
      }

      if (bigInteger.compareTo(MAXIMUM_AMOUNT) == 1) {
        throw new ParamException(String.format("Maximum amount is %s", MAXIMUM_AMOUNT));
      }
    } catch (NumberFormatException e) {
      throw new ParamException("Invalid amount format");
    }
  }

  private static EWalletPayment createRequest(Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/ewallets");
    String amount = params.get("amount").toString();

    amountValidation(amount);

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, EWalletPayment.class);
  }
}
