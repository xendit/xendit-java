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

  public RegionalRetailOutletPaymentCode createPaymentCode(
      String referenceId,
      RegionalRetailOutletPaymentCode.ChannelCode channelCode,
      String customerName,
      Number amount,
      RegionalRetailOutletPaymentCode.Currency currency,
      RegionalRetailOutletPaymentCode.Market market)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/payment_codes");
    Map<String, Object> params = new HashMap<>();
    params.put("reference_id", referenceId);
    params.put("channel_code", channelCode);
    params.put("customer_name", customerName);
    params.put("amount", amount);
    params.put("currency", currency);
    params.put("market", market);
    return this.requestClient.request(
        RequestResource.Method.POST,
        url,
        params,
        opt.getApiKey(),
        RegionalRetailOutletPaymentCode.class);
  }

  public RegionalRetailOutletPaymentCode createPaymentCode(Map<String, Object> params)
      throws XenditException {
    return createPaymentCode(new HashMap<>(), params);
  }

  public RegionalRetailOutletPaymentCode createPaymentCode(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/payment_codes");
    return this.requestClient.request(
        RequestResource.Method.POST,
        url,
        headers,
        params,
        opt.getApiKey(),
        RegionalRetailOutletPaymentCode.class);
  }

  public RegionalRetailOutletPaymentCode getPaymentCode(String id) throws XenditException {
    return getPaymentCode(id, new HashMap<>());
  }

  public RegionalRetailOutletPaymentCode getPaymentCode(String id, Map<String, String> headers)
      throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/payment_codes/", id);
    return this.requestClient.request(
        RequestResource.Method.GET,
        url,
        headers,
        null,
        opt.getApiKey(),
        RegionalRetailOutletPaymentCode.class);
  }

  public RegionalRetailOutletPaymentCode updatePaymentCode(String id, Map<String, Object> params)
      throws XenditException {
    return updatePaymentCode(id, new HashMap<>(), params);
  }

  public RegionalRetailOutletPaymentCode updatePaymentCode(
      String id, Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/payment_codes/", id);
    return this.requestClient.request(
        RequestResource.Method.PATCH,
        url,
        headers,
        params,
        opt.getApiKey(),
        RegionalRetailOutletPaymentCode.class);
  }

  public RegionalRetailOutletPaymentCode updatePaymentCode(
      String id,
      String name,
      Number amount,
      RegionalRetailOutletPaymentCode.Currency currency,
      String expiresAt,
      String description)
      throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/payment_codes/", id);
    Map<String, Object> params = new HashMap<>();
    if (name != null) params.put("customer_name", name);
    if (amount != null) params.put("amount", amount);
    if (currency != null) params.put("currency", currency);
    if (expiresAt != null) params.put("expires_at", expiresAt);
    if (description != null) params.put("description", description);
    return this.requestClient.request(
        RequestResource.Method.PATCH,
        url,
        params,
        opt.getApiKey(),
        RegionalRetailOutletPaymentCode.class);
  }

  public RegionalRetailOutletPayments[] getPaymentsByPaymentCodeId(String id)
      throws XenditException {
    return getPaymentsByPaymentCodeId(id, new HashMap<>());
  }

  public RegionalRetailOutletPayments[] getPaymentsByPaymentCodeId(
      String id, Map<String, String> headers) throws XenditException {
    String url = String.format("%s%s%s%s", opt.getXenditURL(), "/payment_codes/", id, "/payments");
    return this.requestClient.request(
        RequestResource.Method.GET,
        url,
        headers,
        null,
        opt.getApiKey(),
        RegionalRetailOutletPayments[].class);
  }
}
