package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RetailOutletClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public FixedPaymentCode createFixedPaymentCode(Map<String, Object> params)
      throws XenditException {
    return createFixedPaymentCode(new HashMap<>(), params);
  }

  public FixedPaymentCode createFixedPaymentCode(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/fixed_payment_code");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), FixedPaymentCode.class);
  }

  public FixedPaymentCode createFixedPaymentCode(
      String externalId,
      FixedPaymentCode.RetailOutletName retailOutletName,
      String name,
      Number expectedAmount)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/fixed_payment_code");
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("retail_outlet_name", retailOutletName);
    params.put("name", name);
    params.put("expected_amount", expectedAmount);
    return this.requestClient.request(
        RequestResource.Method.POST, url, params, opt.getApiKey(), FixedPaymentCode.class);
  }

  public FixedPaymentCode getFixedPaymentCode(String id) throws XenditException {
    return getFixedPaymentCode(id, new HashMap<>());
  }

  public FixedPaymentCode getFixedPaymentCode(String id, Map<String, String> headers)
      throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/fixed_payment_code/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), FixedPaymentCode.class);
  }

  public FixedPaymentCode updateFixedPaymentCode(String id, Map<String, Object> params)
      throws XenditException {
    return updateFixedPaymentCode(id, new HashMap<>(), params);
  }

  public FixedPaymentCode updateFixedPaymentCode(
      String id, Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/fixed_payment_code/", id);
    return this.requestClient.request(
        RequestResource.Method.PATCH,
        url,
        headers,
        params,
        opt.getApiKey(),
        FixedPaymentCode.class);
  }

  public FixedPaymentCode updateFixedPaymentCode(
      String id, String name, Number expectedAmount, String expirationDate) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/fixed_payment_code/", id);
    Map<String, Object> params = new HashMap<>();
    if (name != null) params.put("name", name);
    if (expectedAmount != null) params.put("expected_amount", expectedAmount);
    if (expirationDate != null) params.put("expiration_date", expirationDate);
    return this.requestClient.request(
        RequestResource.Method.PATCH, url, params, opt.getApiKey(), FixedPaymentCode.class);
  }
}
