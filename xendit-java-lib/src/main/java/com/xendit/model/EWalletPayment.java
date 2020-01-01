package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.ParamException;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import lombok.Getter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class EWalletPayment extends BaseModel {
    private static final BigInteger MINIMUM_AMOUNT = new BigInteger("1");
    private static final BigInteger MAXIMUM_AMOUNT = new BigInteger("10000000");

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

    /**
     * Create new payment for LINKAJA
     * @param externalId An ID of your choice. Often it is unique identifier like a phone number, email or transaction ID. Maximum length allowed is 1000 characters.
     * @param amount Amount end-customer will pay.
     * @param phone Phone number of end-customer (e.g. 08123123123)
     * @param items List of items / products.
     * @param ewalletType The type of ewallet to be paid. Must be in capital letters.
     * @param callbackUrl The URL to receive the callback after payment made by end-customer
     * @param redirectUrl The URL to redirect to after payment made by end-customer
     * @return EWalletPayment model.
     * @throws XenditException XenditException
     */
    public static EWalletPayment create(
            String externalId,
            Number amount,
            String phone,
            EWalletLinkajaItem[] items,
            String ewalletType,
            String callbackUrl,
            String redirectUrl
    ) throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("external_id", externalId);
        params.put("amount", amount);
        params.put("phone", phone);
        params.put("items", items);
        params.put("ewallet_type", ewalletType);
        params.put("callback_url", callbackUrl);
        params.put("redirect_url", redirectUrl);
        return createRequest(params);
    }

    /**
     * Create new payment for OVO
     * @param externalId An ID of your choice. Often it is unique identifier like a phone number, email or transaction ID. Maximum length allowed is 1000 characters.
     * @param amount Amount end-customer will pay.
     * @param phone Phone number of end-customer (e.g. 08123123123)
     * @param ewalletType The type of ewallet to be paid. Must be in capital letters.
     * @return EWalletPayment model.
     * @throws XenditException XenditException
     */
    public static EWalletPayment create(
            String externalId,
            Number amount,
            String phone,
            String ewalletType
    ) throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("external_id", externalId);
        params.put("amount", amount);
        params.put("phone", phone);
        params.put("ewallet_type", ewalletType);
        return createRequest(params);
    }

    /**
     *
     * @param externalId An ID of your choice. Often it is unique identifier like a phone number, email or transaction ID.
     * @param ewalletType The type of ewallet to be paid. Must be in capital letters.
     * @return EWalletPayment model.
     * @throws XenditException XenditException
     */
    public static EWalletPayment getPaymentStatus(String externalId, String ewalletType) throws XenditException {
        String url = String.format("%s%s%s%s%s", Xendit.getUrl(), "/ewallets/?external_id=", externalId, "&ewallet_type=", ewalletType);
        return request(RequestResource.Method.GET, url, null, EWalletPayment.class);
    }

    private static void amountValidation(String amount) throws ParamException {
        try {
            BigInteger bigInteger = new BigInteger(amount);

            if (bigInteger.compareTo(MINIMUM_AMOUNT) == -1) {
                throw new ParamException(String.format("Minimum amount is %s", MINIMUM_AMOUNT));
            }

            if (bigInteger.compareTo(MAXIMUM_AMOUNT) == 1) {
                throw new ParamException(String.format("Maximum amount is %s", MAXIMUM_AMOUNT));
            }
        } catch (NumberFormatException e) {
            throw new ParamException("Invalid amount format");
        }
    }

    private static EWalletPayment createRequest(Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/ewallets");
        String amount = params.get("amount").toString();

        amountValidation(amount);

        return request(RequestResource.Method.POST, url, params, EWalletPayment.class);
    }

}
