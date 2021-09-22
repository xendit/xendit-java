package com.xenditclient.invoice;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.Xendit;
import com.xenditclient.network.NetworkClient;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceClient {

  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public Invoice create(String externalId, Number amount, String payerEmail, String description)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("payer_email", payerEmail);
    params.put("description", description);

    String url = String.format("%s%s", opt.getXenditURL(), "/v2/invoices");
    return this.requestClient.request(
        RequestResource.Method.POST, url, params, opt.getApiKey(), Invoice.class);
  }

  public Invoice create(Map<String, Object> params) throws XenditException {
    return create(new HashMap<>(), params);
  }

  public Invoice create(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/v2/invoices");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), Invoice.class);
  }

  public Invoice getById(String id) throws XenditException {
    return getById(new HashMap<>(), id);
  }

  public Invoice getById(Map<String, String> headers, String id) throws XenditException {
    String url = String.format("%s%s%s", com.xendit.Xendit.getUrl(), "/v2/invoices/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), Invoice.class);
  }

  public Invoice[] getAll(Map<String, Object> params) throws XenditException {
    return getAll(new HashMap<>(), params);
  }

  public Invoice[] getAll(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    StringBuilder parameters = new StringBuilder();
    String[] paramList =
        new String[] {
          "statuses",
          "limit",
          "created_after",
          "created_before",
          "paid_after",
          "paid_before",
          "expired_after",
          "expired_before",
          "last_invoice_id",
          "client_types",
          "payment_channels",
          "on_demand_link",
          "recurring_payment_id",
        };
    for (String key : paramList) {
      if (params.containsKey(key))
        parameters.append(String.format("%s%s%s%s", "&", key, "=", params.get(key)));
    }

    String url =
        String.format("%s%s%s", com.xendit.Xendit.getUrl(), "/v2/invoices?", parameters.toString());
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), Invoice[].class);
  }

  public Invoice expire(String id) throws XenditException {
    return expire(new HashMap<>(), id);
  }

  public Invoice expire(Map<String, String> headers, String id) throws XenditException {
    String url =
        String.format("%s%s%s%s", com.xendit.Xendit.getUrl(), "/invoices/", id, "/expire!");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, null, opt.getApiKey(), Invoice.class);
  }
}
