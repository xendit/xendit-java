package com.xenditclient.invoice;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xenditclient.retailOutlet.AvailableRetailOutletInvoice;
import com.xendit.Xendit;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

  private static InvoiceClient invoiceClient;

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
    InvoiceClient client = getClient();
    return client.create(externalId, amount, payerEmail, description);
  }

  /**
   * Create invoice with all parameters as HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-invoice.
   * @return Invoice
   * @throws XenditException XenditException
   */
  public static Invoice create(Map<String, Object> params) throws XenditException {
    return create(new HashMap<>(), params);
  }

  /**
   * Create invoice with all parameters as HashMap
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#create-invoice.
   * @return Invoice
   * @throws XenditException XenditException
   */
  public static Invoice create(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    InvoiceClient client = getClient();
    return client.create(headers, params);
  }

  /**
   * Get invoice detail by ID
   *
   * @param headers
   * @param id ID of the invoice to retrieve
   * @return Invoice
   * @throws XenditException XenditException
   */
  public static Invoice getById(Map<String, String> headers, String id) throws XenditException {
    InvoiceClient client = getClient();
    return client.getById(headers, id);
  }

  /**
   * Get invoice detail by ID
   *
   * @param id ID of the invoice to retrieve
   * @return Invoice
   * @throws XenditException XenditException
   */
  public static Invoice getById(String id) throws XenditException {
    return getById(new HashMap<>(), id);
  }

  /**
   * Get all invoices by given parameters
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#list-all-invoices
   * @return Array of invoices
   * @throws XenditException XenditException
   */
  public static Invoice[] getAll(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    InvoiceClient client = getClient();
    return client.getAll(headers, params);
  }

  /**
   * Get all invoices by given parameters
   *
   * @param params listed here https://xendit.github.io/apireference/#list-all-invoices
   * @return Array of invoices
   * @throws XenditException XenditException
   */
  public static Invoice[] getAll(Map<String, Object> params) throws XenditException {
    return getAll(new HashMap<>(), params);
  }

  /**
   * Expire an already created invoice
   *
   * @param headers
   * @param id ID of the invoice to be expired / canceled
   * @return Invoice
   * @throws XenditException XenditException
   */
  public static Invoice expire(Map<String, String> headers, String id) throws XenditException {
    InvoiceClient client = getClient();
    return client.expire(headers, id);
  }

  /**
   * Expire an already created invoice
   *
   * @param id ID of the invoice to be expired / canceled
   * @return Invoice
   * @throws XenditException XenditException
   */
  public static Invoice expire(String id) throws XenditException {
    return expire(new HashMap<>(), id);
  }

  /**
   * Its create a client for Invoice
   *
   * @return InvoiceClient
   */
  private static InvoiceClient getClient() {
    if (isApiKeyExist()) {
      if (invoiceClient == null
          || !invoiceClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return invoiceClient =
            new InvoiceClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (invoiceClient == null
          || !invoiceClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
        return invoiceClient = new InvoiceClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return invoiceClient;
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
