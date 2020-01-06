package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class FixedPaymentCode extends BaseModel {
    @SerializedName("id")
    @Getter
    private String id;

    @SerializedName("owner_id")
    @Getter
    private String ownerId;

    @SerializedName("external_id")
    @Getter
    private String externalId;

    @SerializedName("retail_outlet_name")
    @Getter
    private String retailOutletName;

    @SerializedName("prefix")
    @Getter
    private String prefix;

    @SerializedName("name")
    @Getter
    private String name;

    @SerializedName("payment_code")
    @Getter
    private String paymentCode;

    @SerializedName("type")
    @Getter
    private String type;

    @SerializedName("expected_amount")
    @Getter
    private Number expectedAmount;

    @SerializedName("status")
    @Getter
    private String status;

    @SerializedName("is_single_use")
    @Getter
    private Boolean isSingleUse;

    @SerializedName("expiration_date")
    @Getter
    private String expirationDate;

    /**
     * Create fixed payment code with all parameters as HashMap
     * @param params listed here https://xendit.github.io/apireference/#update-fixed-payment-code
     * @return FixedPaymentCodeRetailOutlet
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode create(Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/fixed_payment_code");
        return request(RequestResource.Method.POST, url, params, FixedPaymentCode.class);
    }

    /**
     * Create fixed payment code with required parameters
     * @param externalId An ID of your choice. Often it is unique identifier like a phone number, email or transaction ID.
     *                   Maximum length allowed is 1000 characters.
     * @param retailOutletName Name of the fixed payment code you want to create.
     * @param name Name of user - this might be used by the Retail Outlets cashier to validate the end user.
     * @param expectedAmount The amount that is expected to be paid by end customer.
     * @return FixedPaymentCodeRetailOutlet
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode create(
            String externalId,
            String retailOutletName,
            String name,
            Number expectedAmount
    ) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/fixed_payment_code");
        Map<String, Object> params = new HashMap<>();
        params.put("external_id", externalId);
        params.put("retail_outlet_name", retailOutletName);
        params.put("name", name);
        params.put("expected_amount", expectedAmount);
        return request(RequestResource.Method.POST, url, params, FixedPaymentCode.class);
    }
}
