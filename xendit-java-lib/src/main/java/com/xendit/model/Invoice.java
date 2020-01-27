package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Invoice {
  @SerializedName("id")
  private String id;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("user_id")
  private String userId;

  @SerializedName("status")
  private String status;

  @SerializedName("merchant_name")
  private String merchantName;

  @SerializedName("merchant_profile_picture_url")
  private String merchantProfilePictureUrl;

  @SerializedName("bank_code")
  private String bankCode;

  @SerializedName("amount")
  private Number amount;

  @SerializedName("initial_amount")
  private Number initialAmount;

  @SerializedName("payer_email")
  private String payerEmail;

  @SerializedName("description")
  private String description;

  @SerializedName("expiry_date")
  private String expiryDate;

  @SerializedName("invoice_url")
  private String invoiceUrl;

  @SerializedName("available_banks")
  private AvailableBankInvoice[] availableBanks;

  @SerializedName("available_retail_outlets")
  private AvailableRetailOutletInvoice[] availableRetailOutlets;

  @SerializedName("available_ewallets")
  private AvailableEwalletInvoice[] availableEwallets;

  @SerializedName("paid_at")
  private String paidAt;

  @SerializedName("paid_amount")
  private Number paidAmount;

  @SerializedName("adjusted_received_amount")
  private Number adjustedReceivedAmount;

  @SerializedName("should_exclude_credit_card")
  private Boolean shouldExcludeCreditCard;

  @SerializedName("should_send_email")
  private Boolean shouldSendEmail;

  @SerializedName("created")
  private String created;

  @SerializedName("updated")
  private String updated;

  @SerializedName("currency")
  private String currency;

  @SerializedName("initial_currency")
  private String initialCurrency;

  @SerializedName("on_demand_link")
  private String onDemandLink;

  @SerializedName("on_demand_payload")
  private Object onDemandPayload;

  @SerializedName("recurring_payment_id")
  private String recurringPaymentId;

  @SerializedName("credit_card_charge_id")
  private String creditCardChargeId;

  @SerializedName("mid_label")
  private String midLabel;

  @SerializedName("payment_channel")
  private String paymentChannel;

  @SerializedName("payment_method")
  private String paymentMethod;

  @SerializedName("payment_destination")
  private String paymentDestination;

  @SerializedName("success_redirect_url")
  private String successRedirectUrl;

  @SerializedName("failure_redirect_url")
  private String failureRedirectUrl;

  @SerializedName("items")
  private ItemInvoice[] items;

  @SerializedName("fixed_va")
  private Boolean fixedVa;

  /**
   * Create invoice with given parameters
   *
   * @param externalId ID of your choice (typically the unique identifier of an invoice in your
   *     system)
   * @param amount Amount on the invoice. The minimum amount to create an invoice is 10000.
   * @param payerEmail Email of the end user you're charging.
   * @param description Description of the invoice.
   * @return Invoice
   * @throws XenditException XenditException
   */
  public static Invoice create(
      String externalId, Number amount, String payerEmail, String description)
      throws XenditException {
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
   *
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
   *
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
   *
   * @param params listed here https://xendit.github.io/apireference/#list-all-invoices
   * @return Array of invoices
   * @throws XenditException XenditException
   */
  public static Invoice[] getAll(Map<String, Object> params) throws XenditException {
    String parameters = "";
    String[] paramList =
        new String[] {
          "statuses",
          "limit",
          "created_after",
          "created_before",
          "paid_after",
          "paid_before",
          "expired_after",
          "expired_before",
          "last_invoice_id",
          "client_types",
          "payment_channels",
          "on_demand_link",
          "recurring_payment_id",
        };
    for (int i = 0; i < paramList.length; i++) {
      String key = paramList[i];
      if (params.containsKey(key))
        parameters += String.format("%s%s%s%s", "&", key, "=", params.get(key));
    }

    String url = String.format("%s%s%s", Xendit.getUrl(), "/v2/invoices?", parameters);
    return Xendit.requestClient.request(RequestResource.Method.GET, url, null, Invoice[].class);
  }

  /**
   * Expire an already created invoice
   *
   * @param id ID of the invoice to be expired / canceled
   * @return Invoice
   * @throws XenditException XenditException
   */
  public static Invoice expire(String id) throws XenditException {
    String url = String.format("%s%s%s%s", Xendit.getUrl(), "/invoices/", id, "/expire!");
    return Xendit.requestClient.request(RequestResource.Method.POST, url, null, Invoice.class);
  }
}
