package com.xenditclient.virtualAccount;

import com.xendit.exception.ParamException;
import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.network.RequestResource;
import com.xendit.Xendit;
import com.xendit.network.NetworkClient;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class FixedVirtualAccountClient {
    private Xendit.Option opt;

    private NetworkClient requestClient;

    public Xendit.Option getOpt() {
        return opt;
    }

    public FixedVirtualAccount createClosed(Map<String, Object> params)
            throws XenditException {
        return create(new HashMap<>(), params, true);
    }

    public FixedVirtualAccount createClosed(
            Map<String, String> headers, Map<String, Object> params) throws XenditException {
        return create(headers, params, true);
    }

    public FixedVirtualAccount createClosed(
            String externalId, String bankCode, String name, Long expectedAmount) throws XenditException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("name", name);
        params.put("expected_amount", expectedAmount);

        return create(new HashMap<>(), params, true);
    }

    public FixedVirtualAccount createClosed(
            String externalId,
            String bankCode,
            String name,
            Long expectedAmount,
            Map<String, Object> additionalParam)
            throws XenditException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("name", name);
        params.put("expected_amount", expectedAmount);
        params.putAll(additionalParam);

        return create(new HashMap<>(), params, true);
    }

    public FixedVirtualAccount createOpen(Map<String, Object> params) throws XenditException {
        return create(new HashMap<>(), params, false);
    }

    public FixedVirtualAccount createOpen(
            Map<String, String> headers, Map<String, Object> params) throws XenditException {
        return create(headers, params, false);
    }

    public FixedVirtualAccount createOpen(String externalId, String bankCode, String name)
            throws XenditException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("name", name);

        return create(new HashMap<>(), params, false);
    }

    public FixedVirtualAccount createOpen(
            String externalId, String bankCode, String name, Map<String, Object> additionalParam)
            throws XenditException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("name", name);
        params.putAll(additionalParam);

        return create(new HashMap<>(), params, false);
    }

    public AvailableBank[] getAvailableBanks() throws XenditException {
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/available_virtual_account_banks");
        return this.requestClient.request(
                RequestResource.Method.GET, url, null,opt.getApiKey(), AvailableBank[].class);
    }

    public FixedVirtualAccount getFixedVA(Map<String, String> headers, String id)
            throws XenditException {
        String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/callback_virtual_accounts/", id);
        return this.requestClient.request(
                RequestResource.Method.GET, url, headers, null,opt.getApiKey(), FixedVirtualAccount.class);
    }

    public FixedVirtualAccount getFixedVA(String id) throws XenditException {
        return getFixedVA(new HashMap<>(), id);
    }

    public FixedVirtualAccount update(String id, Map<String, Object> params)
            throws XenditException {
        String url = String.format("%s%s%s",Xendit.Opt.getXenditURL(), "/callback_virtual_accounts/", id);
        return this.requestClient.request(
                RequestResource.Method.PATCH, url, params,opt.getApiKey(), FixedVirtualAccount.class);
    }

    public FixedVirtualAccountPayment getPayment(String paymentId) throws XenditException {
        String url =
                String.format(
                        "%s%s%s", Xendit.Opt.getXenditURL(), "/callback_virtual_account_payments/payment_id=", paymentId);
        return this.requestClient.request(
                RequestResource.Method.GET, url, null,opt.getApiKey(), FixedVirtualAccountPayment.class);
    }

    public FixedVirtualAccount create(
            Map<String, String> headers, Map<String, Object> params, Boolean isClosed)
            throws XenditException {
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/callback_virtual_accounts");

        params.put("is_closed", isClosed);

        if (isClosed && params.containsKey("suggested_amount")) {
            throw new ParamException("Suggested amount is not supported for closed VA");
        }

        return this.requestClient.request(
                RequestResource.Method.POST, url, headers, params,opt.getApiKey(), FixedVirtualAccount.class);
    }
}
