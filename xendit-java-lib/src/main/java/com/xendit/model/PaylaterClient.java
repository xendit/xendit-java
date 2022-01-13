package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaylaterClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public PaylaterPlans initiatePaylaterPlans(
      String customerId,
      String channelCode,
      String currency,
      Number amount,
      PaylaterOrderItem[] orderItems)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
    params.put("channel_code", channelCode);
    params.put("currency", currency);
    params.put("amount", amount);
    params.put("order_items", orderItems);
    return initiatePaylaterPlans(new HashMap<>(), params);
  }

  public PaylaterCharge createPaylaterCharges(
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
    params.put("paymentMethodId", paymentMethodId);
    params.put("metadata", metadata);
    return createPaylaterChargeRequest(new HashMap<>(), params);
  }

  public PaylaterPlans initiatePaylaterPlans(Map<String, Object> params) throws XenditException {
    return initiatePaylaterPlans(new HashMap<>(), params);
  }

  public PaylaterCharge createPaylaterCharges(Map<String, Object> params) throws XenditException {
    return createPaylaterChargeRequest(new HashMap<>(), params);
  }

  public PaylaterCharge createPaylaterCharges(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return createPaylaterChargeRequest(headers, params);
  }

  public PaylaterCharge getPaylaterChargeStatus(String chargeId) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/paylater/charges/", chargeId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), PaylaterCharge.class);
  }

  public PaylaterPlans initiatePaylaterPlans(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/paylater/plans");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), PaylaterPlans.class);
  }

  public PaylaterCharge createPaylaterChargeRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/paylater/charges");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), PaylaterCharge.class);
  }

  public PaylaterRefund createPaylaterRefund(String chargeId, Number amount, PaylaterEnum.RefundReasons reason)
      throws XenditException {
    return createPaylaterRefund(new HashMap<>(), chargeId, amount, reason);
  }

  public PaylaterRefund createPaylaterRefund(
      Map<String, String> headers, String chargeId, Number amount, PaylaterEnum.RefundReasons reason)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("amount", amount);
    params.put("reason", reason);
    String url = String.format(
        "%s%s%s%s", Xendit.Opt.getXenditURL(), "/paylater/charges/", chargeId, "/refunds");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), PaylaterRefund.class);
  }

  public PaylaterRefund getPaylaterRefundStatus(String chargeId, String refundId) throws XenditException {
    String url = String.format("%s%s%s%s%s", Xendit.Opt.getXenditURL(), "/paylater/charges/", chargeId, "/refunds/",
        refundId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), PaylaterRefund.class);
  }
}
