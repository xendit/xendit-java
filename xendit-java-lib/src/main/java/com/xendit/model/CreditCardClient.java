package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreditCardClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public CreditCardCharge createAuthorization(
      String tokenId,
      String externalId,
      Number amount,
      String authenticationId,
      String cardCVN,
      Boolean capture)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("token_id", tokenId);
    params.put("external_id", externalId);
    params.put("amount", amount);
    if (isNotEmpty(authenticationId)) params.put("authentication_id", authenticationId);
    if (isNotEmpty(cardCVN)) params.put("card_cvn", cardCVN);
    params.put("capture", capture);
    String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/credit_card_charges");

    return this.requestClient.request(
        RequestResource.Method.POST, url, params, opt.getApiKey(), CreditCardCharge.class);
  }

  public CreditCardCharge createAuthorization(Map<String, Object> params) throws XenditException {
    return createAuthorization(new HashMap<>(), params);
  }

  public CreditCardCharge createAuthorization(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/credit_card_charges");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), CreditCardCharge.class);
  }

  public CreditCardCharge createCharge(
      String tokenId,
      String externalId,
      Number amount,
      String authenticationId,
      String cardCVN,
      String descriptor)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("token_id", tokenId);
    params.put("external_id", externalId);
    params.put("amount", amount);
    if (isNotEmpty(authenticationId)) params.put("authentication_id", authenticationId);
    if (isNotEmpty(cardCVN)) params.put("card_cvn", cardCVN);
    if (isNotEmpty(descriptor)) params.put("descriptor", descriptor);
    String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/credit_card_charges");

    return this.requestClient.request(
        RequestResource.Method.POST, url, params, opt.getApiKey(), CreditCardCharge.class);
  }

  public CreditCardCharge createCharge(Map<String, Object> params) throws XenditException {
    return createCharge(new HashMap<>(), params);
  }

  public CreditCardCharge createCharge(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", Xendit.Opt.getApiKey(), "/credit_card_charges");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), CreditCardCharge.class);
  }

  public CreditCardReverseAuth reverseAuthorization(String chargeId, String externalId)
      throws XenditException {
    return reverseAuthorization(new HashMap<>(), chargeId, externalId);
  }

  public CreditCardReverseAuth reverseAuthorization(
      Map<String, String> headers, String chargeId, String externalId) throws XenditException {
    String url =
        String.format(
            "%s%s%s%s",
            Xendit.Opt.getXenditURL(), "/credit_card_charges/", chargeId, "/auth_reversal");
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);

    return this.requestClient.request(
        RequestResource.Method.POST,
        url,
        headers,
        params,
        opt.getApiKey(),
        CreditCardReverseAuth.class);
  }

  public CreditCardCharge captureCharge(String chargeId, Number amount) throws XenditException {
    return captureCharge(new HashMap<>(), chargeId, amount);
  }

  public CreditCardCharge captureCharge(Map<String, String> headers, String chargeId, Number amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("amount", amount);
    String url =
        String.format(
            "%s%s%s%s", Xendit.Opt.getXenditURL(), "/credit_card_charges/", chargeId, "/capture");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), CreditCardCharge.class);
  }

  public CreditCardCharge getCharge(String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/credit_card_charges/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), CreditCardCharge.class);
  }

  public CreditCardRefund createRefund(String id, Number amount, String externalId)
      throws XenditException {
    return createRefund(new HashMap<>(), id, amount, externalId);
  }

  public CreditCardRefund createRefund(
      Map<String, String> headers, String id, Number amount, String externalId)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("amount", amount);
    params.put("external_id", externalId);
    String url =
        String.format(
            "%s%s%s%s", Xendit.Opt.getXenditURL(), "/credit_card_charges/", id, "/refunds");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), CreditCardRefund.class);
  }

  private boolean isNotEmpty(String param) {
    return param != null && !"".equals(param);
  }
}
