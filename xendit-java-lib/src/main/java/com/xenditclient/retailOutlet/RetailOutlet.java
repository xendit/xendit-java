package com.xenditclient.retailOutlet;

import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;
import com.xenditclient.Xendit;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class RetailOutlet {
    private static RetailOutletClient retailOutletClient;

    /**
     * Create fixed payment code with all parameters as HashMap
     *
     * @param params listed here https://xendit.github.io/apireference/#update-fixed-payment-code
     * @return FixedPaymentCodeRetailOutlet
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode createFixedPaymentCode(Map<String, Object> params)
            throws XenditException {
        return createFixedPaymentCode(new HashMap<>(), params);
    }

    /**
     * Create fixed payment code with all parameters as HashMap
     *
     * @param headers
     * @param params  listed here https://xendit.github.io/apireference/#update-fixed-payment-code
     * @return FixedPaymentCodeRetailOutlet
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode createFixedPaymentCode(
            Map<String, String> headers, Map<String, Object> params) throws XenditException {
        RetailOutletClient client = getClient();
        return client.createFixedPaymentCode(headers, params);
    }

    /**
     * Create fixed payment code with required parameters
     *
     * @param externalId       An ID of your choice. Often it is unique identifier like a phone number,
     *                         email or transaction ID. Maximum length allowed is 1000 characters.
     * @param retailOutletName Name of the fixed payment code you want to create.
     * @param name             Name of user - this might be used by the Retail Outlets cashier to validate the end
     *                         user.
     * @param expectedAmount   The amount that is expected to be paid by end customer.
     * @return FixedPaymentCodeRetailOutlet
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode createFixedPaymentCode(
            String externalId,
            FixedPaymentCode.RetailOutletName retailOutletName,
            String name,
            Number expectedAmount)
            throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("external_id", externalId);
        params.put("retail_outlet_name", retailOutletName);
        params.put("name", name);
        params.put("expected_amount", expectedAmount);
        RetailOutletClient client = getClient();
        return client.createFixedPaymentCode(externalId, retailOutletName, name, expectedAmount);
    }

    /**
     * Get fixed payment code by ID
     *
     * @param id ID of the fixed payment code to retrieve
     * @return FixedPaymentCode
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode getFixedPaymentCode(String id) throws XenditException {
        return getFixedPaymentCode(id, new HashMap<>());
    }

    /**
     * Get fixed payment code by ID
     *
     * @param id      ID of the fixed payment code to retrieve
     * @param headers
     * @return FixedPaymentCode
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode getFixedPaymentCode(String id, Map<String, String> headers)
            throws XenditException {
        RetailOutletClient client = getClient();
        return client.getFixedPaymentCode(id, headers);
    }

    /**
     * Update fixed payment code by ID and with all parameters as HashMap
     *
     * @param id     ID of the fixed payment code to be updated
     * @param params listed here https://xendit.github.io/apireference/#update-fixed-payment-code
     * @return FixedPaymentCode
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode updateFixedPaymentCode(String id, Map<String, Object> params)
            throws XenditException {
        RetailOutletClient client = getClient();
        return client.updateFixedPaymentCode(id, params);
    }

    /**
     * Update fixed payment code by ID and with all parameters as HashMap
     *
     * @param id      ID of the fixed payment code to be updated
     * @param headers
     * @param params  listed here https://xendit.github.io/apireference/#update-fixed-payment-code
     * @return FixedPaymentCode
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode updateFixedPaymentCode(
            String id, Map<String, String> headers, Map<String, Object> params) throws XenditException {
        RetailOutletClient client = getClient();
        return client.updateFixedPaymentCode(id, headers, params);
    }

    /**
     * Update fixed payment code by ID and with parameters
     *
     * @param id             ID of the fixed payment code to be updated
     * @param name           Name for the fixed payment code
     * @param expectedAmount The amount that is expected to be paid by end customer
     * @param expirationDate The time when the fixed payment code will be expired. You can set it to
     *                       be days in the past to expire fixed payment code immediately
     * @return FixedPaymentCode
     * @throws XenditException XenditException
     */
    public static FixedPaymentCode updateFixedPaymentCode(
            String id, String name, Number expectedAmount, String expirationDate) throws XenditException {
        RetailOutletClient client = getClient();
        return client.updateFixedPaymentCode(id, name, expectedAmount, expirationDate);
    }

    /**
     * Its create a client for RetailOutlet
     *
     * @return RetailOutletClient
     */
    private static RetailOutletClient getClient() {
        if (isApiKeyExist()) {
            if (retailOutletClient == null
                    || !retailOutletClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
                return retailOutletClient =
                        new RetailOutletClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
            }
        } else {
            if (retailOutletClient == null
                    || !retailOutletClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
                return retailOutletClient = new RetailOutletClient(Xendit.Opt, Xendit.getRequestClient());
            }
        }
        return retailOutletClient;
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
