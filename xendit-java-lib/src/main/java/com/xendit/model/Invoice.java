package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Builder
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

    @SerializedName("bank_code")
    @Getter
    private String bankCode;

    @SerializedName("amount")
    @Getter
    private Number amount;

    @SerializedName("initial_amount")
    @Getter
    private Number initialAmount;

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

    @SerializedName("available_retail_outlets")
    @Getter
    private AvailableRetailOutletInvoice[] availableRetailOutlets;

    @SerializedName("available_ewallets")
    @Getter
    private AvailableEwalletInvoice[] availableEwallets;

    @SerializedName("paid_at")
    @Getter
    private String paidAt;

    @SerializedName("paid_amount")
    @Getter
    private Number paidAmount;

    @SerializedName("adjusted_received_amount")
    @Getter
    private Number adjustedReceivedAmount;

    @SerializedName("should_exclude_credit_card")
    @Getter
    private Boolean shouldExcludeCreditCard;

    @SerializedName("should_send_email")
    @Getter
    private Boolean shouldSendEmail;

    @SerializedName("created")
    @Getter
    private String created;

    @SerializedName("updated")
    @Getter
    private String updated;

    @SerializedName("currency")
    @Getter
    private String currency;

    @SerializedName("initial_currency")
    @Getter
    private String initialCurrency;

    @SerializedName("on_demand_link")
    @Getter
    private String onDemandLink;

    @SerializedName("on_demand_payload")
    @Getter
    private Object onDemandPayload;

    @SerializedName("recurring_payment_id")
    @Getter
    private String recurringPaymentId;

    @SerializedName("credit_card_charge_id")
    @Getter
    private String creditCardChargeId;

    @SerializedName("mid_label")
    @Getter
    private String midLabel;

    @SerializedName("payment_channel")
    @Getter
    private String paymentChannel;

    @SerializedName("payment_method")
    @Getter
    private String paymentMethod;

    @SerializedName("payment_destination")
    @Getter
    private String paymentDestination;

    @SerializedName("success_redirect_url")
    @Getter
    private String successRedirectUrl;

    @SerializedName("failure_redirect_url")
    @Getter
    private String failureRedirectUrl;

    @SerializedName("items")
    @Getter
    private ItemInvoice[] items;

    @SerializedName("fixed_va")
    @Getter
    private Boolean fixedVa;

    /**
     * Create invoice with given parameters
     * @param externalId ID of your choice (typically the unique identifier of an invoice in your system)
     * @param amount Amount on the invoice. The minimum amount to create an invoice is 10000.
     * @param payerEmail Email of the end user you're charging.
     * @param description Description of the invoice.
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
        String url = String.format("%s%s", Xendit.getUrl(), "/v2/invoices");
        return Xendit.requestClient.request(RequestResource.Method.POST, url, params, Invoice.class);
    }

    /**
     * Create invoice with all parameters as HashMap
     * @param params listed here https://xendit.github.io/apireference/#create-invoice.
     * @return Invoice
     * @throws XenditException XenditException
     */
    public static Invoice create(Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/v2/invoices");
        return Xendit.requestClient.request(RequestResource.Method.POST, url, params, Invoice.class);
    }

    /**
     * Get invoice detail by ID
     * @param id ID of the invoice to retrieve
     * @return Invoice
     * @throws XenditException XenditException
     */
    public static Invoice getById(String id) throws XenditException {
        String url = String.format("%s%s%s", Xendit.getUrl(), "/v2/invoices/", id);
        return Xendit.requestClient.request(RequestResource.Method.GET, url, null, Invoice.class);
    }

    /**
     * Get all invoices by given parameters
     * @param params listed here https://xendit.github.io/apireference/#list-all-invoices
     * @return Array of invoices
     * @throws XenditException XenditException
     */
    public static Invoice[] getAll(Map<String, Object> params) throws XenditException {
        String parameters = "";
        if (params.containsKey("limit")) parameters += String.format("%s%s", "&limit=", params.get("limit"));
        if (params.containsKey("statuses")) parameters += String.format("%s%s", "&statuses=", params.get("statuses"));
        if (params.containsKey("last_invoice_id")) parameters += String.format("%s%s", "&last_invoice_id=", params.get("last_invoice_id"));
        if (params.containsKey("client_types")) parameters += String.format("%s%s", "&client_types=", params.get("client_types"));
        if (params.containsKey("after")) parameters += String.format("%s%s", "&after=", params.get("after"));
        if (params.containsKey("before")) parameters += String.format("%s%s", "&before=", params.get("before"));
        String url = String.format("%s%s%s", Xendit.getUrl(), "/v2/invoices?", parameters);
        return Xendit.requestClient.request(RequestResource.Method.GET, url, null, Invoice[].class);
    }

    /**
     * Expire an already created invoice
     * @param id ID of the invoice to be expired / canceled
     * @return Invoice
     * @throws XenditException XenditException
     */
    public static Invoice expire(String id) throws XenditException {
        String url = String.format("%s%s%s%s", Xendit.getUrl(), "/invoices/", id, "/expire!");
        return Xendit.requestClient.request(RequestResource.Method.POST, url, null, Invoice.class);
    }
}
