package com.xenditclient.payout;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.Xendit;
import com.xenditclient.network.NetworkClient;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PayoutClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public Payout createPayout(Map<String, Object> params) throws XenditException {
    return createPayout(new HashMap<>(), params);
  }

  public Payout createPayout(String externalId, Number amount) throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    String url = String.format("%s%s", opt.getXenditURL(), "/payouts");
    return this.requestClient.request(
        RequestResource.Method.POST, url, params, opt.getApiKey(), Payout.class);
  }

  public Payout createPayout(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/payouts");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), Payout.class);
  }

  public Payout getPayout(String id) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/payouts/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), Payout.class);
  }

  public Payout voidPayout(String id) throws XenditException {
    String url = String.format("%s%s%s%s", opt.getXenditURL(), "/payouts/", id, "/void");
    return this.requestClient.request(
        RequestResource.Method.POST, url, null, opt.getApiKey(), Payout.class);
  }
}
