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
public class EWalletCharge {
  @SerializedName("id")
  private String id;

  @SerializedName("business_id")
  private String businessId;

  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("status")
  private String status;

  @SerializedName("currency")
  private String currency;

  @SerializedName("charge_amount")
  private String chargeAmount;

  @SerializedName("capture_amount")
  private String captureAmount;

  @SerializedName("checkout_method")
  private String checkoutMethod;

  @SerializedName("channel_code")
  private String channelCode;

  @SerializedName("channel_properties")
  private Map<String, String> channelProperties;

  @SerializedName("actions")
  private Map<String, String> actions;

  @SerializedName("is_redirect_required")
  private Boolean isRedirectRequired;

  @SerializedName("callback_url")
  private String callbackUrl;

  @SerializedName("created")
  private String created;

  @SerializedName("updated")
  private String updated;

  @SerializedName("voided_at")
  private String voidedAt;

  @SerializedName("capture_now")
  private Boolean captureNow;

  @SerializedName("customer_id")
  private String customerId;

  @SerializedName("payment_method_id")
  private String paymentMethodId;

  @SerializedName("failure_code")
  private String failureCode;

  @SerializedName("basket")
  private EWalletBasketItem[] basket;

  @SerializedName("metadata")
  private Map<String, Object> metadata;

  /**
   * Create new e-wallet charge
   *
   * @param referenceId An ID of your choice. Often it is unique identifier like a phone number,
   *     email or transaction ID.
   * @param currency Currency that is used by end-customer.
   * @param amount Amount end-customer will pay.
   * @param checkoutMethod Checkout method end-customer will apply.
   * @param channelCode Specifies which e-Wallet will be used by end-customer.
   * @param channelProperties Channel specific information.
   * @param customerId ID of customer object to which the account token will be linked to.
   * @param basket Array of objects describing the item(s) purchased.
   * @param metadata Object of additional information the user may use.
   * @return EWalletCharge model.
   * @throws XenditException XenditException
   */
  public static EWalletCharge createEWalletCharge(
      String referenceId,
      String currency,
      Number amount,
      String checkoutMethod,
      String channelCode,
      Map<String, String> channelProperties,
      String customerId,
      EWalletBasketItem[] basket,
      Map<String, Object> metadata)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("reference_id", referenceId);
    params.put("currency", currency);
    params.put("amount", amount);
    params.put("checkout_method", checkoutMethod);
    params.put("channel_code", channelCode);
    params.put("channel_properties", channelProperties);
    params.put("customer_id", customerId);
    params.put("basket", basket);
    params.put("metadata", metadata);
    return createChargeRequest(new HashMap<>(), params);
  }

  /**
   * Create new e-wallet charge with all parameter as HashMap
   *
   * @param params listed here https://developers.xendit.co/api-reference/#create-ewallet-charge.
   * @return EWalletCharge
   * @throws XenditException
   */
  public static EWalletCharge createEWalletCharge(Map<String, Object> params)
      throws XenditException {
    return createChargeRequest(new HashMap<>(), params);
  }

  /**
   * Create new e-wallet charge with headers and all parameter as HashMap
   *
   * @param headers
   * @param params listed here https://developers.xendit.co/api-reference/#create-ewallet-charge.
   * @return EWalletCharge
   * @throws XenditException
   */
  public static EWalletCharge createEWalletCharge(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return createChargeRequest(headers, params);
  }

  /**
   * Get e-Wallet charge by id
   *
   * @param chargeId e-Wallet charge ID
   * @return EWalletCharge
   * @throws XenditException XenditException
   */
  public static EWalletCharge getEWalletChargeStatus(String chargeId) throws XenditException {
    String url = String.format("%s%s%s", Xendit.getUrl(), "/ewallets/charges/", chargeId);
    return Xendit.requestClient.request(RequestResource.Method.GET, url, null, EWalletCharge.class);
  }

  public static EWalletCharge createChargeRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/ewallets/charges");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, headers, params, EWalletCharge.class);
  }
}
