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

  public static PaylaterPlans initiatePaylaterPlans(
    String customerId,
    String channelCode,
    String currency,
    String amount,
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

    public static PaylaterPlans initiatePaylaterPlans(Map<String, Object> params)
        throws XenditException {
            return initiatePaylaterPlans(new HashMap<>(), params);
    }

    public static PaylaterPlans initiatePaylaterPlans(
        Map<String, String> headers, Map<String, Object> params) throws XenditException {
            return initiatePaylaterPlans(headers, params);
    }

    public Paylater createPaylaterCharges(Map<String, Object> params) throws XenditException {
      return createPayout(new HashMap<>(), params);
    }

    public Paylater createPaylaterCharges(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return createChargeRequest(headers, params);
    }
    
    public Paylater createPaylaterChargeRequest(Map<String, String> headers, Map<String, Object> params)
      throws XenditException{
        String url = String.format("%s%s", opt.getXenditURL(), "/paylater/charges");
        return this.requestClient.request(
            RequestResource.Method.POST, url, headers, params, opt.getApiKey(), PaylaterCharge.class);
    }

    public Payout getPaylater(String id) throws XenditException {
      String url = String.format("%s%s%s", opt.getXenditURL(), "/paylater/charges/", id);
      return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), Paylater.class);
    }
}