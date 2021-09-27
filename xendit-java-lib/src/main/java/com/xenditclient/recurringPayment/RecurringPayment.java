package com.xenditclient.recurringPayment;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.invoice.Invoice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecurringPayment {
    @SerializedName("id")
    private String id;

    @SerializedName("external_id")
    private String externalId;

    @SerializedName("payer_email")
    private String payerEmail;

    @SerializedName("description")
    private String description;

    @SerializedName("amount")
    private Number amount;

    @SerializedName("interval")
    private String interval;

    @SerializedName("interval_count")
    private Number intervalCount;

    @SerializedName("status")
    private String status;

    @SerializedName("total_recurrence")
    private Number totalRecurrence;

    @SerializedName("invoice_duration")
    private Number invoiceDuration;

    @SerializedName("should_send_email")
    private Boolean shouldSendEmail;

    @SerializedName("missed_payment_action")
    private String missedPaymentAction;

    @SerializedName("credit_card_token")
    private String creditCardToken;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("success_redirect_url")
    private String successRedirectUrl;

    @SerializedName("failure_redirect_url")
    private String failureRedirectUrl;

    @SerializedName("recharge")
    private Boolean recharge;

    @SerializedName("charge_immediately")
    private Boolean chargeImmediately;

    @SerializedName("currency")
    private String currency;

    private static RecurringPaymentClient recurringPaymentClient;

    /**
     * Create recurring payment with given parameters
     *
     * @param externalId    ID of your choice (typically the unique identifier of a recurring payment in
     *                      your system)
     * @param payerEmail    Email of the end user you're charging
     * @param interval      One of DAY, WEEK, MONTH. The frequency with which a recurring payment invoice
     *                      should be billed.
     * @param intervalCount The number of intervals (specified in the interval property) between
     *                      recurring. For example, interval=MONTH and interval_count=3 bills every 3 months.
     * @param description   Description for the recurring payment and invoices
     * @param amount        Amount per invoice per interval. The minimum amount to create an invoice is
     *                      10.000 IDR. The maximum amount is 1.000.000.000 IDR
     * @return RecurringPayment
     * @throws XenditException XenditException
     */
    public static RecurringPayment create(
            String externalId,
            String payerEmail,
            String interval,
            Number intervalCount,
            String description,
            Number amount)
            throws XenditException {
        RecurringPaymentClient client = getClient();
        return client.create(externalId, payerEmail, interval, intervalCount, description, amount);
    }

    /**
     * Create recurring payment with given parameters as a HashMap object
     *
     * @param params listed here https://xendit.github.io/apireference/#create-a-recurring-payment
     * @return RecurringPayment
     * @throws XenditException XenditException
     */
    public static RecurringPayment create(Map<String, Object> params) throws XenditException {
        return create(new HashMap<>(), params);
    }

    /**
     * Create recurring payment with given parameters as a HashMap object
     *
     * @param headers
     * @param params  listed here https://xendit.github.io/apireference/#create-a-recurring-payment
     * @return RecurringPayment
     * @throws XenditException XenditException
     */
    public static RecurringPayment create(Map<String, String> headers, Map<String, Object> params)
            throws XenditException {
        RecurringPaymentClient client = getClient();
        return client.create(headers, params);
    }

    /**
     * Edit a recurring payment by ID and given parameters
     *
     * @param id     id of recurring you want to update
     * @param params listed here https://xendit.github.io/apireference/#edit-recurring-payment
     * @return RecurringPayment
     * @throws XenditException XenditException
     */
    public static RecurringPayment edit(String id, Map<String, Object> params)
            throws XenditException {
        RecurringPaymentClient client = getClient();
        return client.edit(id, params);
    }

    /**
     * Get a recurring payment by ID
     *
     * @param id ID of the recurring payment to retrieve
     * @return RecurringPayment
     * @throws XenditException XenditException
     */
    public static RecurringPayment get(String id) throws XenditException {
        RecurringPaymentClient client = getClient();
        return client.get(id);
    }

    /**
     * Stop a recurring payment by ID
     *
     * @param id ID of the recurring payment to stop
     * @return RecurringPayment
     * @throws XenditException XenditException
     */
    public static RecurringPayment stop(String id) throws XenditException {
        RecurringPaymentClient client = getClient();
        return client.stop(id);
    }

    /**
     * Pause a recurring payment by ID
     *
     * @param id ID of the recurring payment to pause
     * @return RecurringPayment
     * @throws XenditException XenditException
     */
    public static RecurringPayment pause(String id) throws XenditException {
        RecurringPaymentClient client = getClient();
        return client.pause(id);
    }

    /**
     * Resume a recurring payment by ID
     *
     * @param id ID of the recurring payment to resume
     * @return RecurringPayment
     * @throws XenditException XenditException
     */
    public static RecurringPayment resume(String id) throws XenditException {
        RecurringPaymentClient client = getClient();
        return client.resume(id);
    }

    /**
     * Get list of payments by ID
     *
     * @param id ID of the recurring payment to get
     * @return Invoices
     * @throws XenditException XenditException
     */
    public static Invoice[] getPaymentsById(String id) throws XenditException {
        RecurringPaymentClient client = getClient();
        return client.getPaymentsById(id);
    }

    /**
     * Its create a client for RecurringPayment
     *
     * @return RecurringPaymentClient
     */
    private static RecurringPaymentClient getClient() {
        if (isApiKeyExist()) {
            if (recurringPaymentClient == null
                    || !recurringPaymentClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
                return recurringPaymentClient =
                        new RecurringPaymentClient(Xendit.Opt.setApiKey(Xendit.apiKey),Xendit.getRequestClient());
            }
        } else {
            if (recurringPaymentClient == null
                    || !recurringPaymentClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
                return recurringPaymentClient = new RecurringPaymentClient(Xendit.Opt,Xendit.getRequestClient());
            }
        }
        return recurringPaymentClient;
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
