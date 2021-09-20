package com.xenditclient.balance;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.Xendit;
import com.xenditclient.network.NetworkClient;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class BalanceClient {

    private Xendit.Option opt;

    private NetworkClient requestClient;

    protected Xendit.Option getOpt() {
        return opt;
    }

    public Balance get() throws XenditException {
        return getBalance(new HashMap<>(), null);
    }

    public Balance get(Balance.AccountType accountType) throws XenditException {
        return getBalance(new HashMap<>(), accountType);
    }

    public Balance get(Map<String, String> headers, Balance.AccountType accountType)
            throws XenditException {
        return getBalance(headers, accountType);
    }

    public Balance getBalance(Map<String, String> headers, Balance.AccountType accountType) throws XenditException {
        String url = String.format("%s%s", opt.getXenditURL(), "/balance");
        if (accountType != null) {
            url = String.format("%s%s%s", url, "?account_type=", accountType);
        }

        return this.requestClient.request(
                RequestResource.Method.GET, url, headers, null, opt.getApiKey(), Balance.class);
    }
}
