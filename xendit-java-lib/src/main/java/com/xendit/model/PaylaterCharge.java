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
public class PaylaterCharge {
  @SerializedName("plan_id")
  private String planId;

  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("checkout_method")
  private String checkoutMethod;

  @SerializedName("success_redirect_url")
  private String successRedirectUrl;

  @SerializedName("failure_redirect_url")
  private String failureRedirectUrl;

  @SerializedName("payment_method_id")
  private String paymentMethodId;

  @SerializedName("metadata")
  private Map<String, Object> metadata;

  private static PaylaterClient paylaterClient;

  /**
   * Create new paylater charge
   *
   * @param planId.            Installment Plan ID retrieved from Initiate
   *                           PayLater Plans API.
   * @param referenceId        An ID of your choice. Reference ID provided by
   *                           merchant for the transaction.
   * @param checkoutMethod     Checkout method end-customer will apply.
   * @param successRedirectUrl where the customer is redirected after completing
   *                           transaction(must be HTTP or HTTPS).
   * @param failureRedirectUrl where the customer is redirected if the payment has
   *                           failed.
   * @param paymentMethodId    of customer source of funds.
   * @param metadata           Object of additional information the user may use.
   * @return PaylaterCharge model.
   * @throws XenditException XenditException
   */
  public static PaylaterCharge createPaylaterCharges(
      String planId,
      String referenceId,
      String checkoutMethod,
      String successRedirectUrl,
      String failureRedirectUrl,
      String paymentMethodId,
      Map<String, Object> metadata)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("plan_id", planId);
    params.put("reference_id", referenceId);
    params.put("checkout_method", checkoutMethod);
    params.put("success_redirect_url", successRedirectUrl);
    params.put("failure_redirect_url", failureRedirectUrl);
    params.put("payment_method_id", paymentMethodId);
    params.put("metadata", metadata);
    return createPaylaterChargeRequest(new HashMap<>(), params);
  }

  /**
   * Create new paylater charge with all parameter as HashMap
   *
   * @param params listed here
   *               https://developers.xendit.co/api-reference/#create-paylater-charges.
   * @return PaylaterCharge
   * @throws XenditException
   */
  public static PaylaterCharge createPaylaterCharges(Map<String, Object> params)
      throws XenditException {
    return createPaylaterChargeRequest(new HashMap<>(), params);
  }

  /**
   * Create new paylater charge with headers and all parameter as HashMap
   *
   * @param headers
   * @param params  listed here
   *                https://developers.xendit.co/api-reference/#create-paylater-charges.
   * @return PaylaterCharge
   * @throws XenditException
   */
  public static PaylaterCharge createPaylaterCharges(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return createPaylaterChargeRequest(headers, params);
  }

  public static PaylaterCharge createPaylaterChargeRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    PaylaterClient client = getClient();
    return client.createPaylaterChargeRequest(headers, params);
  }

  /**
   * Its create a client for paylater
   *
   * @return PaylaterClient
   */
  private static PaylaterClient getClient() {
    if (isApiKeyExist()) {
      if (paylaterClient == null
          || !paylaterClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return paylaterClient = new PaylaterClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (paylaterClient == null
          || !paylaterClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
        return paylaterClient = new PaylaterClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return paylaterClient;
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