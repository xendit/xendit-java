package com.xendit.model.recurringPayment;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.invoice.Invoice;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecurringPaymentClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public RecurringPayment create(
      String externalId,
      String payerEmail,
      String interval,
      Number intervalCount,
      String description,
      Number amount)
      throws XenditException {
    String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/recurring_payments");
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("payer_email", payerEmail);
    params.put("interval", interval);
    params.put("interval_count", intervalCount);
    params.put("description", description);
    params.put("amount", amount);
    return this.requestClient.request(
        RequestResource.Method.POST, url, params, opt.getApiKey(), RecurringPayment.class);
  }

  public RecurringPayment create(Map<String, Object> params) throws XenditException {
    return create(new HashMap<>(), params);
  }

  public RecurringPayment create(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/recurring_payments");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), RecurringPayment.class);
  }

  public RecurringPayment edit(String id, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/recurring_payments/", id);
    return this.requestClient.request(
        RequestResource.Method.PATCH, url, params, opt.getApiKey(), RecurringPayment.class);
  }

  public RecurringPayment get(String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/recurring_payments/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), RecurringPayment.class);
  }

  public RecurringPayment stop(String id) throws XenditException {
    String url =
        String.format("%s%s%s%s", Xendit.Opt.getXenditURL(), "/recurring_payments/", id, "/stop!");
    return this.requestClient.request(
        RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class);
  }

  public RecurringPayment pause(String id) throws XenditException {
    String url =
        String.format("%s%s%s%s", Xendit.Opt.getXenditURL(), "/recurring_payments/", id, "/pause!");
    return this.requestClient.request(
        RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class);
  }

  public RecurringPayment resume(String id) throws XenditException {
    String url =
        String.format(
            "%s%s%s%s", Xendit.Opt.getXenditURL(), "/recurring_payments/", id, "/resume!");
    return this.requestClient.request(
        RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class);
  }

  public Invoice[] getPaymentsById(String id) throws XenditException {
    String url =
        String.format(
            "%s%s%s", Xendit.Opt.getXenditURL(), "/v2/invoices?recurring_payment_id=", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), Invoice[].class);
  }
}
