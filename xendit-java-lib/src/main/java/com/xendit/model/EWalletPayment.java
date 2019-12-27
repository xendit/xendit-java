package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.ParamException;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import lombok.Getter;

public class EWalletPayment extends BaseModel {
    @SerializedName("external_id")
    @Getter
    private String externalId;

    @SerializedName("business_id")
    @Getter
    private String businessId;

    @SerializedName("phone")
    @Getter
    private String phone;

    @SerializedName("amount")
    @Getter
    private Number amount;

    @SerializedName("expiration_date")
    @Getter
    private String expirationDate;

    @SerializedName("ewallet_type")
    @Getter
    private String ewalletType;

    @SerializedName("checkout_url")
    @Getter
    private String checkoutUrl;

    @SerializedName("status")
    @Getter
    private String status;

    public static EWalletPayment getPaymentStatus(String externalId, String ewalletType) throws XenditException {
        String url = String.format("%s%s%s%s%s", Xendit.getUrl(), "/ewallets/?external_id=", externalId, "&ewallet_type=", ewalletType);
        return request(RequestResource.Method.GET, url, null, EWalletPayment.class);
    }

}
