package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xendit.resources.CreateClosedVirtualAccount;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VirtualAccount extends BaseModel {
    @SerializedName("id")
    private String id;

    @SerializedName("owner_id")
    private String ownerId;

    @SerializedName("external_id")
    private String externalId;

    @SerializedName("merchant_code")
    private String merchantCode;

    @SerializedName("account_number")
    private String accountNumber;

    @SerializedName("bank_code")
    private String bankCode;

    @SerializedName("name")
    private String name;

    @SerializedName("is_closed")
    private Boolean isClosed;

    @SerializedName("expiration_date")
    private Date expirationDate;

    @SerializedName("is_single_use")
    private Boolean isSingleUse;

    @SerializedName("status")
    private String status;

    // optionals
    @SerializedName("suggested_amount")
    private Long suggestedAmount;

    @SerializedName("expected_amount")
    private Long expectedAmount;

    @SerializedName("currency")
    private String currency;

    @SerializedName("description")
    private Long description;

    public class AvailableBank {
        @SerializedName("name")
        String name;

        @SerializedName("code")
        String code;
    }

    public enum BankCode {
        BNI("BNI"),
        MANDIRI("MANDIRI"),
        BRI("BRI"),
        BCA("BCA"),
        PERMATA("PERMATA");

        private String text;

        BankCode(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        @Nullable
        public static VirtualAccount.BankCode fromString(String text) {
            for (VirtualAccount.BankCode m : VirtualAccount.BankCode.values()) {
                if (m.text.equalsIgnoreCase(text)) {
                    return m;
                }
            }
            return null;
        }
    }

    /**
     * Create closed VA with complete object
     * @param params listed here https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
     * @return VirtualAccount model.
     * @throws XenditException
     */
    public static VirtualAccount createClosed(Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/payment/xendit/virtual-accounts");
        params.put("is_closed", true);

        return request(RequestResource.Method.POST, url, params, VirtualAccount.class);
    }

    /**
     * Create closed VA with only required parameters
     * @param externalId String
     * @param bankCode String
     * @param name String
     * @return VirtualAccount model.
     * @throws XenditException
     */
    public static VirtualAccount createClosed(String externalId, String bankCode, String name, Long expectedAmount) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/payment/xendit/virtual-accounts");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("name", name);
        params.put("expected_amount", expectedAmount);
        params.put("is_closed", true);

        return request(RequestResource.Method.POST, url, params, VirtualAccount.class);
    }

    /**
     * Create open VA with required parameters and can accept additional params
     * @param externalId String
     * @param bankCode String
     * @param name String
     * @param additionalParam Map<String, Object>
     * @return VirtualAccount model.
     * @throws XenditException
     */
    public static VirtualAccount createClosed(String externalId, String bankCode, String name, Long expectedAmount,
                                       Map<String, Object> additionalParam)
            throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/payment/xendit/virtual-accounts");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("name", name);
        params.put("expected_amount", expectedAmount);
        params.put("is_closed", true);
        params.putAll(additionalParam);

        return request(RequestResource.Method.POST, url, params, VirtualAccount.class);
    }

    /**
     * Create closed VA with complete object
     * @param params listed here https://xendit.github.io/apireference/#create-fixed-virtual-accounts.
     * @return VirtualAccount model.
     * @throws XenditException
     */
    public static VirtualAccount createOpen(Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/payment/xendit/virtual-accounts");
        params.put("is_closed", false);

        return request(RequestResource.Method.POST, url, params, VirtualAccount.class);
    }

    /**
     * Create open VA with only required parameters
     * @param externalId String
     * @param bankCode String
     * @param name String
     * @return VirtualAccount model.
     * @throws XenditException
     */
    public static VirtualAccount createOpen(String externalId, String bankCode, String name) throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/payment/xendit/virtual-accounts");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("name", name);
        params.put("is_closed", false);

        return request(RequestResource.Method.POST, url, params, VirtualAccount.class);
    }

    /**
     * Create closed VA with required parameters and can accept additional params
     * @param externalId String
     * @param bankCode String
     * @param name String
     * @param additionalParam Map<String, Object>
     * @return VirtualAccount model.
     * @throws XenditException
     */
    public static VirtualAccount createOpen(String externalId, String bankCode, String name,
                                       Map<String, Object> additionalParam)
            throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/payment/xendit/virtual-accounts");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("external_id", externalId);
        params.put("bank_code", bankCode);
        params.put("name", name);
        params.put("is_closed", false);
        params.putAll(additionalParam);

        return request(RequestResource.Method.POST, url, params, VirtualAccount.class);
    }

    /**
     *
     * @return array of AvailableBank. The bank code returned can be used to create
     * VirtualAccount.
     * @throws XenditException
     */
    public static AvailableBank[] getAvailableBank() throws XenditException {
        String url = String.format("%s%s", Xendit.getUrl(), "/payment/xendit/virtual-accounts/available-banks");
        return request(RequestResource.Method.GET, url, null, AvailableBank[].class);
    }
}
