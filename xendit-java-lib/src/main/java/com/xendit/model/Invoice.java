package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.ParamException;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Invoice extends BaseModel {
    @SerializedName("id")
    @Getter
    private String id;

    @SerializedName("external_id")
    @Getter
    private String externalId;

    @SerializedName("user_id")
    @Getter
    private String userId;

    @SerializedName("status")
    @Getter
    private String status;

    @SerializedName("merchant_name")
    @Getter
    private String merchantName;

    @SerializedName("merchant_profile_picture_url")
    @Getter
    private String merchantProfilePictureUrl;

    @SerializedName("amount")
    @Getter
    private Number amount;

    @SerializedName("payer_email")
    @Getter
    private String payerEmail;

    @SerializedName("description")
    @Getter
    private String description;

    @SerializedName("expiry_date")
    @Getter
    private String expiryDate;

    @SerializedName("invoice_url")
    @Getter
    private String invoiceUrl;

    @SerializedName("available_banks")
    @Getter
    private AvailableBankInvoice[] availableBanks;

    @SerializedName("available_ewallets")
    @Getter
    private AvailableEwalletInvoice[] availableEwallets;

    @SerializedName("should_exclude_credit_card")
    @Getter
    private String shouldExcludeCreditCard;

    @SerializedName("should_send_email")
    @Getter
    private String shouldSendEmail;

    @SerializedName("created")
    @Getter
    private String created;

    @SerializedName("updated")
    @Getter
    private String updated;

    @SerializedName("currency")
    @Getter
    private String currency;

    /**
     * Create invoice with given parameters
     * @param externalId ID of your choice (typically the unique identifier of an invoice in your system)
     * @param amount Description of the invoice
     * @param payerEmail Email of the end user you're charging
     * @param description Amount on the invoice. The minimum amount to create an invoice is 10000.
     * @return Invoice
     * @throws XenditException XenditException
     */
    public static Invoice create(
            String externalId,
            Number amount,
            String payerEmail,
            String description
    ) throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("external_id", externalId);
        params.put("amount", amount);
        params.put("payer_email", payerEmail);
        params.put("description", description);
        return createRequest(params);
    }

    /**
     * Create invoice with all parameters as HashMap
     * @param params listed here https://xendit.github.io/apireference/#create-invoice.
     * @return Invoice
     * @throws XenditException XenditException
     */
    public static Invoice create(Map<String, Object> params) throws XenditException {
        return createRequest(params);
    }

    /**
     * Get invoice detail by ID
     * @param id ID of the invoice to retrieve
     * @return Invoice
     * @throws XenditException XenditException
     */
    public static Invoice retrieve(String id) throws XenditException {
        String url = String.format("%s%s%s", Xendit.getUrl(), "/v2/invoices/", id);
        return request(RequestResource.Method.GET, url, null, Invoice.class);
    }

    private static Invoice createRequest(Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/v2/invoices");
        return request(RequestResource.Method.POST, url, params, Invoice.class);
    }
}
