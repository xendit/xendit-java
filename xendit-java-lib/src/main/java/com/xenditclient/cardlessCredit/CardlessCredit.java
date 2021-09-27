package com.xenditclient.cardlessCredit;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xendit.model.CardlessCreditCustomer;
import com.xendit.model.CardlessCreditItem;
import com.xendit.model.CardlessCreditShippingAddress;
import com.xendit.network.RequestResource;
import com.xenditclient.Xendit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardlessCredit {
    @SerializedName("redirect_url")
    private String redirectUrl;

    @SerializedName("transaction_id")
    private String transactionId;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("external_id")
    private String externalId;

    @SerializedName("cardless_credit_type")
    private String cardlessCreditType;

    private static CardlessCreditClient cardlessCreditClient;

    public enum PaymentType {
        THIRTY_DAYS("30_days"),
        THREE_MONTHS("3_months"),
        SIX_MONTHS("6_months"),
        TWELVE_MONTHS("12_months");

        private String val;

        PaymentType(String val) {
            this.val = val;
        }

        public String getVal() {
            return this.val;
        }
    }

    public static CardlessCredit create(
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

        CardlessCreditClient client = getClient();
        return client.create(cardlessCreditType, externalId, amount, paymentType, items,
                customerDetails, shippingAddress, redirectUrl, callbackUrl);
    }

    public static CardlessCredit create(Map<String, Object> params) throws XenditException {
        return create(new HashMap<>(), params);
    }

    public static CardlessCredit create(Map<String, String> headers, Map<String, Object> params)
            throws XenditException {
        CardlessCreditClient client = getClient();
        return client.create(headers, params);
    }

    /**
     * Its create a client for BatchDisbursement
     *
     * @return BatchDisbursementClient
     */
    private static CardlessCreditClient getClient() {
        if (isApiKeyExist()) {
            if (cardlessCreditClient == null
                    || !cardlessCreditClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
                return cardlessCreditClient =
                        new CardlessCreditClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
            }
        } else {
            if (cardlessCreditClient == null
                    || !cardlessCreditClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
                return cardlessCreditClient = new CardlessCreditClient(Xendit.Opt, Xendit.getRequestClient());
            }
        }
        return cardlessCreditClient;
    }

    /**
     * check if api-key is exist or not
     *
     * @return boolean
     */
    private static boolean isApiKeyExist() {
        return com.xenditclient.Xendit.apiKey != null;
    }
}
