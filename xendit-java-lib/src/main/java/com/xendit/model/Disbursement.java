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

public class Disbursement extends BaseModel {
    private static final BigInteger MINIMUM_AMOUNT = new BigInteger("10000");
    private static final BigInteger MAXIMUM_AMOUNT = new BigInteger("25000000");

    @SerializedName("id")
    @Getter
    private String id;

    @SerializedName("user_id")
    @Getter
    private String userId;

    @SerializedName("external_id")
    @Getter
    private String externalId;

    @SerializedName("amount")
    @Getter
    private BigInteger amount;

    @SerializedName("bank_code")
    @Getter
    private String bankCode;

    @SerializedName("account_holder_name")
    @Getter
    private String accountHolderName;

    @SerializedName("disbursement_description")
    @Getter
    private String disbursementDescription;

    @SerializedName("status")
    @Getter
    private String status;

    // optionals
    @SerializedName("email_to")
    @Getter
    private String[] emailTo;

    @SerializedName("email_cc")
    @Getter
    private String[] emailCC;

    @SerializedName("email_bcc")
    @Getter
    private String[] emailBcc;

    /**
     * Create disbursement with all parameter as HashMap
     * @param params listed here https://xendit.github.io/apireference/#create-disbursement.
     * @return Disbursement
     * @throws XenditException
     */
    public static Disbursement create(Map<String, Object> params) throws XenditException {
        return createRequest(params);
    }

    /**
     * Create disbursement with required parameters
     * @param externalId ID of the disbursement in your system, used to reconcile disbursements after they have been
     *                   completed.
     * @param bankCode Code of the destination bank.
     * @param accountHolderName Name of account holder as per the bank's or e-wallet's records. Used for verification
     *                          and error/customer support scenarios.
     * @param accountNumber Destination bank account number. If disbursing to an e-wallet, phone number registered with
     *                      the e-wallet account.
     * @param description Description to send with the disbursement
     * @param amount Amount to disburse
     * @return Disbursement
     * @throws XenditException
     */
    public static Disbursement create(
            String externalId,
            String  bankCode,
            String accountHolderName,
            String accountNumber,
            String description,
            BigInteger amount
    ) throws XenditException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("account_holder_name", accountHolderName);
        params.put("account_number", accountNumber);
        params.put("description", description);
        params.put("amount", amount);
        return createRequest(params);
    }

    /**
     * Create disbursement with required parameters
     * @param externalId ID of the disbursement in your system, used to reconcile disbursements after they have been
     *                   completed.
     * @param bankCode Code of the destination bank.
     * @param accountHolderName Name of account holder as per the bank's or e-wallet's records. Used for verification
     *                          and error/customer support scenarios.
     * @param accountNumber Destination bank account number. If disbursing to an e-wallet, phone number registered with
     *                      the e-wallet account.
     * @param description Description to send with the disbursement
     * @param amount Amount to disburse
     * @param emailTo Email addresses that get notified of disbursement details after the disbursement is completed.
     *                Maximum 3 email addresses accepted.
     * @return Disbursement
     * @throws XenditException
     */
    public static Disbursement create(
            String externalId,
            String  bankCode,
            String accountHolderName,
            String accountNumber,
            String description,
            BigInteger amount,
            String[] emailTo
    ) throws XenditException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("account_holder_name", accountHolderName);
        params.put("account_number", accountNumber);
        params.put("description", description);
        params.put("amount", amount);
        params.put("email_to", emailTo);
        return createRequest(params);
    }

    /**
     * Create disbursement with required parameters
     * @param externalId ID of the disbursement in your system, used to reconcile disbursements after they have been
     *                   completed.
     * @param bankCode Code of the destination bank.
     * @param accountHolderName Name of account holder as per the bank's or e-wallet's records. Used for verification
     *                          and error/customer support scenarios.
     * @param accountNumber Destination bank account number. If disbursing to an e-wallet, phone number registered with
     *                      the e-wallet account.
     * @param description Description to send with the disbursement
     * @param amount Amount to disburse
     * @param emailTo Email addresses that get notified of disbursement details after the disbursement is completed.
     *                Maximum 3 email addresses accepted.
     * @param emailCc Email addresses that get notified as carbon copy receiver of disbursement details after the
     *                disbursement is completed.
     *                Maximum 3 email addresses accepted.
     *                Only allowed if email_to provided.
     * @return Disbursement
     * @throws XenditException
     */
    public static Disbursement create(
            String externalId,
            String  bankCode,
            String accountHolderName,
            String accountNumber,
            String description,
            BigInteger amount,
            String[] emailTo,
            String[] emailCc
    ) throws XenditException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("account_holder_name", accountHolderName);
        params.put("account_number", accountNumber);
        params.put("description", description);
        params.put("amount", amount);
        params.put("email_to", emailTo);
        params.put("email_cc", emailCc);
        return createRequest(params);
    }

    /**
     * Create disbursement with required parameters
     * @param externalId ID of the disbursement in your system, used to reconcile disbursements after they have been
     *                   completed.
     * @param bankCode Code of the destination bank.
     * @param accountHolderName Name of account holder as per the bank's or e-wallet's records. Used for verification
     *                          and error/customer support scenarios.
     * @param accountNumber Destination bank account number. If disbursing to an e-wallet, phone number registered with
     *                      the e-wallet account.
     * @param description Description to send with the disbursement
     * @param amount Amount to disburse
     * @param emailTo Email addresses that get notified of disbursement details after the disbursement is completed.
     *                Maximum 3 email addresses accepted.
     * @param emailCc Email addresses that get notified as carbon copy receiver of disbursement details after the
     *                disbursement is completed.
     *                Maximum 3 email addresses accepted.
     *                Only allowed if email_to provided.
     * @param emailBcc Email addresses that get notified as blind carbon copy receiver of disbursement details after
     *                 the disbursement is completed.
     *                 Maximum 3 email addresses accepted.
     *                 Only allowed if email_to provided.
     * @return Disbursement
     * @throws XenditException
     */
    public static Disbursement create(
            String externalId,
            String  bankCode,
            String accountHolderName,
            String accountNumber,
            String description,
            BigInteger amount,
            String[] emailTo,
            String[] emailCc,
            String[] emailBcc
    ) throws XenditException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("account_holder_name", accountHolderName);
        params.put("account_number", accountNumber);
        params.put("description", description);
        params.put("amount", amount);
        params.put("email_to", emailTo);
        params.put("email_cc", emailCc);
        params.put("email_bcc", emailBcc);
        return createRequest(params);
    }

    /**
     * Get disbursement available bank
     * @return
     * @throws XenditException
     */
    public static AvailableBank[] getAvailableBank() throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/available_disbursements_banks");
        return request(RequestResource.Method.GET, url, null, AvailableBank[].class);
    }

    /**
     * Get array of object disbursements by external id
     * @param externalId
     * @return
     * @throws XenditException
     */
    public static Disbursement[] getByExternalId(String externalId) throws XenditException {
        String url = String.format("%s%s%s", Xendit.getUrl(), "/disbursements?external_id=", externalId);
        return request(RequestResource.Method.GET, url, null, Disbursement[].class);
    }

    /**
     * Get object disbursement by id
     * @param id
     * @return
     * @throws XenditException
     */
    public static Disbursement getById(String id) throws XenditException {
        String url = String.format("%s%s%s", Xendit.getUrl(), "/disbursements/", id);
        return request(RequestResource.Method.GET, url, null, Disbursement.class);
    }

    private static Disbursement createRequest(Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/disbursements");
        String amount = params.get("amount").toString();

        amountValidation(amount);

        return request(RequestResource.Method.POST, url, params, Disbursement.class);
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
}
