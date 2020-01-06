package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
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

    public Invoice() {}

    public Invoice(Map<String, Object> params) {
        if (params.containsKey("id")) this.id = params.get("id").toString();
        if (params.containsKey("external_id")) this.externalId = params.get("external_id").toString();
        if (params.containsKey("user_id")) this.userId = params.get("user_id").toString();
        if (params.containsKey("status")) this.status = params.get("status").toString();
        if (params.containsKey("merchant_name")) this.merchantName = params.get("merchant_name").toString();
        if (params.containsKey("merchant_profile_picture_url")) this.merchantProfilePictureUrl = params.get("merchant_profile_picture_url").toString();
        if (params.containsKey("bank_code")) this.bankCode = params.get("bank_code").toString();
        if (params.containsKey("amount")) this.amount = (int)params.get("amount");
        if (params.containsKey("initial_amount")) this.initialAmount = (int)params.get("initial_amount");
        if (params.containsKey("payer_email")) this.payerEmail = params.get("payer_email").toString();
        if (params.containsKey("description")) this.description = params.get("description").toString();
        if (params.containsKey("expiry_date")) this.expiryDate = params.get("expiry_date").toString();
        if (params.containsKey("invoice_url")) this.invoiceUrl = params.get("invoice_url").toString();
        if (params.containsKey("available_banks")) this.availableBanks = (AvailableBankInvoice[])params.get("available_banks");
        if (params.containsKey("available_retail_outlets")) this.availableRetailOutlets = (AvailableRetailOutletInvoice[])params.get("available_retail_outlets");
        if (params.containsKey("available_ewallets")) this.availableEwallets = (AvailableEwalletInvoice[])params.get("available_ewallets");
        if (params.containsKey("paid_at")) this.paidAt = params.get("paid_at").toString();
        if (params.containsKey("paid_amount")) this.paidAmount = (int)params.get("paid_amount");
        if (params.containsKey("adjusted_received_amount")) this.adjustedReceivedAmount = (int)params.get("adjusted_received_amount");
        if (params.containsKey("should_exclude_credit_card")) this.shouldExcludeCreditCard = (boolean)params.get("should_exclude_credit_card");
        if (params.containsKey("should_send_email")) this.shouldSendEmail = (boolean)params.get("should_send_email");
        if (params.containsKey("created")) this.created = params.get("created").toString();
        if (params.containsKey("updated")) this.updated = params.get("updated").toString();
        if (params.containsKey("currency")) this.currency = params.get("currency").toString();
        if (params.containsKey("initial_currency")) this.initialCurrency = params.get("initial_currency").toString();
        if (params.containsKey("on_demand_link")) this.onDemandLink = params.get("on_demand_link").toString();
        if (params.containsKey("on_demand_payload")) this.onDemandPayload = params.get("on_demand_payload");
        if (params.containsKey("recurring_payment_id")) this.recurringPaymentId = params.get("recurring_payment_id").toString();
        if (params.containsKey("credit_card_charge_id")) this.creditCardChargeId = params.get("credit_card_charge_id").toString();
        if (params.containsKey("mid_label")) this.midLabel = params.get("mid_label").toString();
        if (params.containsKey("payment_channel")) this.paymentChannel = params.get("payment_channel").toString();
        if (params.containsKey("payment_method")) this.paymentMethod = params.get("payment_method").toString();
        if (params.containsKey("payment_destination")) this.paymentDestination = params.get("payment_destination").toString();
        if (params.containsKey("success_redirect_url")) this.successRedirectUrl = params.get("success_redirect_url").toString();
        if (params.containsKey("failure_redirect_url")) this.failureRedirectUrl = params.get("failure_redirect_url").toString();
        if (params.containsKey("items")) this.items = (ItemInvoice[])params.get("items");
        if (params.containsKey("fixed_va")) this.fixedVa = (boolean)params.get("fixed_va");
    }

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
    public static Invoice getById(String id) throws XenditException {
        String url = String.format("%s%s%s", Xendit.getUrl(), "/v2/invoices/", id);
        return request(RequestResource.Method.GET, url, null, Invoice.class);
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
        return request(RequestResource.Method.GET, url, null, Invoice[].class);
    }

    /**
     * Expire an already created invoice
     * @param id ID of the invoice to be expired / canceled
     * @return Invoice
     * @throws XenditException XenditException
     */
    public static Invoice expire(String id) throws XenditException {
        String url = String.format("%s%s%s%s", Xendit.getUrl(), "/invoices/", id, "/expire!");
        return request(RequestResource.Method.POST, url, null, Invoice.class);
    }

    private static Invoice createRequest(Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/v2/invoices");
        return request(RequestResource.Method.POST, url, params, Invoice.class);
    }
}
