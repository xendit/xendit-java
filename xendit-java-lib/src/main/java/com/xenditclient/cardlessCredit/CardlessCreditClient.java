package com.xenditclient.cardlessCredit;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xendit.Xendit;
import com.xendit.network.NetworkClient;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CardlessCreditClient {
    private Xendit.Option opt;

    private NetworkClient requestClient;

    public Xendit.Option getOpt() {
        return opt;
    }

    public CardlessCredit create(
            String cardlessCreditType,
            String externalId,
            Number amount,
            String paymentType,
            CardlessCreditItem[] items,
            CardlessCreditCustomer customerDetails,
            CardlessCreditShippingAddress shippingAddress,
            String redirectUrl,
            String callbackUrl)
            throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("cardless_credit_type", cardlessCreditType);
        params.put("external_id", externalId);
        params.put("amount", amount);
        params.put("payment_type", paymentType);
        params.put("items", items);
        params.put("customer_details", customerDetails);
        params.put("shipping_address", shippingAddress);
        params.put("redirect_url", redirectUrl);
        params.put("callback_url", callbackUrl);
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/cardless-credit");

        return this.requestClient.request(
                RequestResource.Method.POST, url, params,opt.getApiKey(), CardlessCredit.class);
    }

    public CardlessCredit create(Map<String, Object> params) throws XenditException {
        return create(new HashMap<>(), params);
    }

    public CardlessCredit create(Map<String, String> headers, Map<String, Object> params)
            throws XenditException {
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/cardless-credit");
        return this.requestClient.request(
                RequestResource.Method.POST, url, headers, params,opt.getApiKey(), CardlessCredit.class);
    }
}
