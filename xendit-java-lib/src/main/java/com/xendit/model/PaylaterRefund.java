package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Builder
@Getter
@Setter
public class PaylaterRefund {
    @SerializedName("refund_id")
    private String refundId;

    @SerializedName("charge_id")
    private String chargeId;

    @SerializedName("channel_code")
    private String channelCode;

    @SerializedName("currency")
    private String currency;

    @SerializedName("amount")
    private Number amount;

    @SerializedName("reason")
    private PaylaterEnum.RefundReasons reason;

    @SerializedName("status")
    private PaylaterEnum.RefundStatus status;

    @SerializedName("created")
    private String created;

    @SerializedName("updated")
    private String updated;

    private static PaylaterClient paylaterClient;

    /**
     * Create a refund
     *
     * @param id     Charge ID of the payment that will be refunded
     * @param amount The amount to be refunded
     * @param reason reason for the refund.
     * @return PaylaterRefund
     * @throws XenditException XenditException
     */
    public static PaylaterRefund createPaylaterRefund(String chargeId, Number amount, PaylaterEnum.RefundReasons reason)
            throws XenditException {
        return createPaylaterRefund(new HashMap<>(), chargeId, amount, reason);
    }

    /**
     * Create a refund
     *
     * @param headers
     * @param id      Charge ID of the payment that will be refunded
     * @param amount  The amount to be refunded
     * @param reason  reason for the refund.
     * @return PaylaterRefund
     * @throws XenditException XenditException
     */
    public static PaylaterRefund createPaylaterRefund(
            Map<String, String> headers, String chargeId, Number amount, PaylaterEnum.RefundReasons reason)
            throws XenditException {
        PaylaterClient client = getClient();
        return client.createPaylaterRefund(headers, chargeId, amount, reason);
    }

    /**
     * Get paylater refund by id
     *
     * @param chargeId paylater charge ID
     * @param
     * @return PaylaterCharge
     * @throws XenditException XenditException
     */
    public static PaylaterRefund getPaylaterRefundStatus(String chargeId, String refundId) throws XenditException {
        PaylaterClient client = getClient();
        return client.getPaylaterRefundStatus(chargeId, refundId);
    }

    /**
     * Its create a client for Paylater
     *
     * @return PaylaterClient
     */
    private static PaylaterClient getClient() {
        if (isApiKeyExist()) {
            if (paylaterClient == null
                    || !paylaterClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
                return paylaterClient = new PaylaterClient(Xendit.Opt.setApiKey(Xendit.apiKey),
                        Xendit.getRequestClient());
            }
        } else {
            if (paylaterClient == null
                    || !paylaterClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
                return paylaterClient = new PaylaterClient(Xendit.Opt, Xendit.getRequestClient());
            }
        }
        return paylaterClient;
    }

    /**
     * check if api-key is exist or not
     *
     * @return boolean
     */
    private static boolean isApiKeyExist() {
        return Xendit.apiKey != null;
    }
}
