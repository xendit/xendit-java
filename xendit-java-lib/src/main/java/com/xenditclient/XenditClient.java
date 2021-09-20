package com.xenditclient;

import com.xenditclient.balance.BalanceClient;
import com.xenditclient.invoice.InvoiceClient;

public class XenditClient {

    private static Xendit.Option opt;
    public InvoiceClient invoice;
    public BalanceClient balance;

    private XenditClient() {
    }

    public static class Builder {

        private String apikey;

        public Builder() {
        }

        public Builder apikey(String apikey) {
            this.apikey = apikey;
            return this;
        }

        public XenditClient build() {
            Xendit.Option option = new Xendit.Option();
            option.setApiKey(this.apikey);
            return initClient(option);
        }

        public String getApikey() {
            return apikey;
        }
    }

    private static XenditClient initClient(Xendit.Option option) {
        XenditClient.opt = option;
        XenditClient xenditClient = new XenditClient();
        xenditClient.invoice = new InvoiceClient(option, Xendit.getRequestClient());
        xenditClient.balance = new BalanceClient(option, Xendit.getRequestClient());
        return xenditClient;
    }
}
