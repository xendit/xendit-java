package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.LinkedAccountEnum.ChannelCode;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
public class DirectDebitPayment {
  @SerializedName("id")
  private String id;

  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("channel_code")
  private ChannelCode channelCode;

  @SerializedName("payment_method_id")
  private String paymentMethodId;

  @SerializedName("currency")
  private String currency;

  @SerializedName("amount")
  private Number amount;

  @SerializedName("description")
  private String description;

  @SerializedName("status")
  private String status;

  @SerializedName("failure_code")
  private String failureCode;

  @SerializedName("is_otp_required")
  private Boolean isOtpRequired;

  @SerializedName("otp_mobile_number")
  private String otpMobileNumber;

  @SerializedName("otp_expiration_timestamp")
  private String otpExpirationTimestamp;

  @SerializedName("created")
  private String created;

  @SerializedName("updated")
  private String updated;

  @SerializedName("basket")
  private DirectDebitBasketItem[] basket;

  @SerializedName("metadata")
  private Map<String, Object> metadata;

  @SerializedName("device")
  private DirectDebitDevice device;

  @SerializedName("refunded_amount")
  private Number refundedAmount;

  @SerializedName("refunds")
  private DirectDebitRefunds refunds;

  @SerializedName("success_redirect_url")
  private String successRedirectUrl;

  @SerializedName("checkout_url")
  private String checkoutUrl;

  @SerializedName("failure_redirect_url")
  private String failureRedirectUrl;

  @SerializedName("required_action")
  private String requiredAction;

  private static DirectDebitPaymentClient directDebitPaymentClient;

  /**
   * Create Direct Debit Payment
   *
   * @param referenceId Merchant-provided identifier for this transaction.
   * @param paymentMethodId Identifier for specific payment method.
   * @param currency Currency of amount to debit in ISO 4217.
   * @param amount Amount to debit from the end-customer’s account.
   * @param callbackUrl URL where payment notification will be sent after transaction process.
   * @param enableOtp A boolean that marks whether the charging comes with OTP.
   * @param description Description for the direct debit transaction.
   * @param basket Array of objects describing the item/s purchased using direct debit.
   * @param device tbc
   * @param successRedirectUrl tbc
   * @param failureRedirectUrl tbc
   * @param metadata Object of additional information the user may use.
   * @return DirectDebitPayment model.
   * @throws XenditException XenditException
   */
  public static DirectDebitPayment createDirectDebitPayment(
      String referenceId,
      String paymentMethodId,
      String currency,
      Number amount,
      String callbackUrl,
      Boolean enableOtp,
      String description,
      DirectDebitBasketItem[] basket,
      DirectDebitDevice device,
      String successRedirectUrl,
      String failureRedirectUrl,
      Map<String, Object> metadata,
      String idempotencyKey)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("reference_id", referenceId);
    params.put("payment_method_id", paymentMethodId);
    params.put("currency", currency);
    params.put("amount", amount);
    params.put("callback_url", callbackUrl);
    params.put("enable_otp", enableOtp);
    params.put("description", description);
    params.put("basket", basket);
    params.put("device", device);
    params.put("success_redirect_url", successRedirectUrl);
    params.put("failure_redirect_url", failureRedirectUrl);
    params.put("metadata", metadata);
    Map<String, String> headers = new HashMap<>();
    headers.put("Idempotency-key", idempotencyKey);
    return createDirectDebitPaymentRequest(headers, params);
  }

  /**
   * Create direct debit payment with all parameter as HashMap
   *
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#create-direct-debit-payment.
   * @return DirectDebitPayment
   * @throws XenditException
   */
  public static DirectDebitPayment createDirectDebitPayment(
      Map<String, Object> params, String idempotencyKey) throws XenditException {
    Map<String, String> headers = new HashMap<>();
    headers.put("Idempotency-key", idempotencyKey);
    return createDirectDebitPaymentRequest(headers, params);
  }

  /**
   * Create direct debit payment with headers and all parameter as HashMap
   *
   * @param headers
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#create-direct-debit-payment.
   * @return DirectDebitPayment
   * @throws XenditException
   */
  public static DirectDebitPayment createDirectDebitPayment(
      Map<String, String> headers, Map<String, Object> params, String idempotencyKey)
      throws XenditException {
    headers.put("Idempotency-key", idempotencyKey);
    return createDirectDebitPaymentRequest(headers, params);
  }

  /**
   * Validate OTP for direct debit payment
   *
   * @param directDebitPaymentId Merchant provided identifier for specified direct debit
   *     transaction.
   * @param otpCode One-time-password input from end customer.
   * @return DirectDebitPayment model.
   * @throws XenditException XenditException
   */
  public static DirectDebitPayment validateOTP(String directDebitPaymentId, String otpCode)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("otp_code", otpCode);
    return validateOTPRequest(directDebitPaymentId, new HashMap<>(), params);
  }

  /**
   * Validate OTP for direct debit payment with all parameter as HashMap
   *
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#validate-otp-for-direct-debit-payment.
   * @return DirectDebitPayment
   * @throws XenditException
   */
  public static DirectDebitPayment validateOTP(
      String directDebitPaymentId, Map<String, Object> params) throws XenditException {
    return validateOTPRequest(directDebitPaymentId, new HashMap<>(), params);
  }

  /**
   * Validate OTP for direct debit payment with headers and all parameter as HashMap
   *
   * @param headers
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#validate-otp-for-direct-debit-payment.
   * @return DirectDebitPayment
   * @throws XenditException
   */
  public static DirectDebitPayment validateOTP(
      String directDebitPaymentId, Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return validateOTPRequest(directDebitPaymentId, headers, params);
  }

  /**
   * Get direct debit payment status by id
   *
   * @param directDebitPaymentId Xendit identifier for specified direct debit transaction
   * @return DirectDebitPayment
   * @throws XenditException XenditException
   */
  public static DirectDebitPayment getDirectDebitPaymentStatusById(String directDebitPaymentId)
      throws XenditException {
    DirectDebitPaymentClient client = getClient();
    return client.getDirectDebitPaymentStatusById(directDebitPaymentId);
  }

  /**
   * Get direct debit payment status by reference id
   *
   * @param referenceId Direct debit payment reference ID
   * @return DirectDebitPayment
   * @throws XenditException XenditException
   */
  public static DirectDebitPayment[] getDirectDebitPaymentStatusByReferenceId(String referenceId)
      throws XenditException {
    DirectDebitPaymentClient client = getClient();
    return client.getDirectDebitPaymentStatusByReferenceId(referenceId);
  }

  private static DirectDebitPayment createDirectDebitPaymentRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    DirectDebitPaymentClient client = getClient();
    return client.createDirectDebitPaymentRequest(headers, params);
  }

  private static DirectDebitPayment validateOTPRequest(
      String directDebitPaymentId, Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    DirectDebitPaymentClient client = getClient();
    return client.validateOTPRequest(directDebitPaymentId, headers, params);
  }

  /**
   * Its create a client for DirectDebitPayment
   *
   * @return DirectDebitPaymentClient
   */
  protected static DirectDebitPaymentClient getClient() {
    if (isApiKeyExist()) {
      if (directDebitPaymentClient == null
          || !directDebitPaymentClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return directDebitPaymentClient =
            new DirectDebitPaymentClient(
                Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (directDebitPaymentClient == null
          || !directDebitPaymentClient
              .getOpt()
              .getApiKey()
              .trim()
              .equals(Xendit.Opt.getApiKey().trim())) {
        return directDebitPaymentClient =
            new DirectDebitPaymentClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return directDebitPaymentClient;
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
