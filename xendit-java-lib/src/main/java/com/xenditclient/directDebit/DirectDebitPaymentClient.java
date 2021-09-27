package com.xenditclient.directDebit;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xendit.Xendit;
import com.xendit.network.NetworkClient;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class DirectDebitPaymentClient {

    private Xendit.Option opt;

    private NetworkClient requestClient;

    public Xendit.Option getOpt() {
        return opt;
    }

    public DirectDebitPayment createDirectDebitPayment(
            String referenceId,
            String paymentMethodId,
            String currency,
            Number amount,
            String callbackUrl,
            Boolean enableOtp,
            String description,
            DirectDebitBasketItem[] basket,
            DirectDebitDevice device,
            String successRedirectUrl,
            String failureRedirectUrl,
            Map<String, Object> metadata,
            String idempotencyKey)
            throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("reference_id", referenceId);
        params.put("payment_method_id", paymentMethodId);
        params.put("currency", currency);
        params.put("amount", amount);
        params.put("callback_url", callbackUrl);
        params.put("enable_otp", enableOtp);
        params.put("description", description);
        params.put("basket", basket);
        params.put("device", device);
        params.put("success_redirect_url", successRedirectUrl);
        params.put("failure_redirect_url", failureRedirectUrl);
        params.put("metadata", metadata);
        Map<String, String> headers = new HashMap<>();
        headers.put("Idempotency-key", idempotencyKey);
        return createDirectDebitPaymentRequest(headers, params);
    }

    public DirectDebitPayment createDirectDebitPayment(
            Map<String, Object> params, String idempotencyKey) throws XenditException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Idempotency-key", idempotencyKey);
        return createDirectDebitPaymentRequest(headers, params);
    }

    public DirectDebitPayment createDirectDebitPayment(
            Map<String, String> headers, Map<String, Object> params, String idempotencyKey)
            throws XenditException {
        headers.put("Idempotency-key", idempotencyKey);
        return createDirectDebitPaymentRequest(headers, params);
    }

    public DirectDebitPayment validateOTP(String directDebitPaymentId, String otpCode)
            throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("otp_code", otpCode);
        return validateOTPRequest(directDebitPaymentId, new HashMap<>(), params);
    }

    public DirectDebitPayment validateOTP(
            String directDebitPaymentId, Map<String, Object> params) throws XenditException {
        return validateOTPRequest(directDebitPaymentId, new HashMap<>(), params);
    }

    public DirectDebitPayment validateOTP(
            String directDebitPaymentId, Map<String, String> headers, Map<String, Object> params)
            throws XenditException {
        return validateOTPRequest(directDebitPaymentId, headers, params);
    }

    public DirectDebitPayment getDirectDebitPaymentStatusById(String directDebitPaymentId)
            throws XenditException {
        String url =
                String.format("%s%s%s%s", Xendit.Opt.getXenditURL(), "/direct_debits/", directDebitPaymentId, "/");
        return this.requestClient.request(
                RequestResource.Method.GET, url, null,opt.getApiKey(), DirectDebitPayment.class);
    }

    public DirectDebitPayment[] getDirectDebitPaymentStatusByReferenceId(String referenceId)
            throws XenditException {
        String url =
                String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/direct_debits?reference_id=", referenceId);
        return this.requestClient.request(
                RequestResource.Method.GET, url, null,opt.getApiKey(), DirectDebitPayment[].class);
    }

    public DirectDebitPayment createDirectDebitPaymentRequest(
            Map<String, String> headers, Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/direct_debits");

        return this.requestClient.request(
                RequestResource.Method.POST, url, headers, params,opt.getApiKey(), DirectDebitPayment.class);
    }

    public DirectDebitPayment validateOTPRequest(
            String directDebitPaymentId, Map<String, String> headers, Map<String, Object> params)
            throws XenditException {
        String url =
                String.format(
                        "%s%s%s%s", Xendit.Opt.getXenditURL(), "/direct_debits/", directDebitPaymentId, "/validate_otp/");

        return this.requestClient.request(
                RequestResource.Method.POST, url, headers, params,opt.getApiKey(), DirectDebitPayment.class);
    }

    public PaymentMethod createPaymentMethod(
            String customerId,
            LinkedAccountEnum.AccountType type,
            Map<String, Object> properties,
            Map<String, Object> metadata)
            throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", customerId);
        params.put("type", type);
        params.put("properties", properties);
        params.put("metadata", metadata);
        return createPaymentMethodRequest(new HashMap<>(), params);
    }

    public PaymentMethod createPaymentMethod(Map<String, Object> params)
            throws XenditException {
        return createPaymentMethodRequest(new HashMap<>(), params);
    }

    public PaymentMethod createPaymentMethod(
            Map<String, String> headers, Map<String, Object> params) throws XenditException {
        return createPaymentMethodRequest(headers, params);
    }

    public PaymentMethod[] getPaymentMethodsByCustomerId(String customerId)
            throws XenditException {
        String url =
                String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/payment_methods?customer_id=", customerId);
        return this.requestClient.request(
                RequestResource.Method.GET, url, null,opt.getApiKey(), PaymentMethod[].class);
    }

    public PaymentMethod createPaymentMethodRequest(
            Map<String, String> headers, Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/payment_methods");

        return this.requestClient.request(
                RequestResource.Method.POST, url, headers, params,opt.getApiKey(), PaymentMethod.class);
    }

    public InitializedLinkedAccount initializeLinkedAccountTokenization(
            String customerId,
            LinkedAccountEnum.ChannelCode channelCode,
            Map<String, Object> properties,
            Map<String, Object> metadata)
            throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("customer_id", customerId);
        params.put("channel_code", channelCode);
        params.put("properties", properties);
        params.put("metadata", metadata);
        return initializeLinkedAccountTokenizationRequest(new HashMap<>(), params);
    }

    public InitializedLinkedAccount initializeLinkedAccountTokenization(
            Map<String, Object> params) throws XenditException {
        return initializeLinkedAccountTokenizationRequest(new HashMap<>(), params);
    }

    public InitializedLinkedAccount initializeLinkedAccountTokenization(
            Map<String, String> headers, Map<String, Object> params) throws XenditException {
        return initializeLinkedAccountTokenizationRequest(headers, params);
    }

    public InitializedLinkedAccount initializeLinkedAccountTokenizationRequest(
            Map<String, String> headers, Map<String, Object> params) throws XenditException {
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/linked_account_tokens/auth");

        return this.requestClient.request(
                RequestResource.Method.POST, url, headers, params,opt.getApiKey(), InitializedLinkedAccount.class);
    }

    public ValidatedLinkedAccount validateOTPWithToken(String tokenId, String otpCode)
            throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("otp_code", otpCode);
        return validateOTPRequestWithToken(tokenId, new HashMap<>(), params);
    }

    public ValidatedLinkedAccount validateOTPWithToken(String tokenId, Map<String, Object> params)
            throws XenditException {
        return validateOTPRequestWithToken(tokenId, new HashMap<>(), params);
    }

    public ValidatedLinkedAccount validateOTPWithToken(
            String tokenId, Map<String, String> headers, Map<String, Object> params)
            throws XenditException {
        return validateOTPRequestWithToken(tokenId, headers, params);
    }

    public ValidatedLinkedAccount validateOTPRequestWithToken(
            String tokenId, Map<String, String> headers, Map<String, Object> params)
            throws XenditException {
        String url =
                String.format(
                        "%s%s%s%s", Xendit.Opt.getXenditURL(), "/linked_account_tokens/", tokenId, "/validate_otp");

        return this.requestClient.request(
                RequestResource.Method.POST, url, headers, params,opt.getApiKey(), ValidatedLinkedAccount.class);
    }

    public AccessibleLinkedAccount[] retrieveAccessibleLinkedAccounts(
            String linkedAccountTokenId) throws XenditException {
        String url =
                String.format(
                        "%s%s%s%s",
                        Xendit.Opt.getXenditURL(), "/linked_account_tokens/", linkedAccountTokenId, "/accounts");
        return this.requestClient.request(
                RequestResource.Method.GET, url, null,opt.getApiKey(), AccessibleLinkedAccount[].class);
    }

    public UnbindedLinkedAccount unbindLinkedAccountToken(String linkedAccountTokenId)
            throws XenditException {
        String url =
                String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/linked_account_tokens/", linkedAccountTokenId);
        return this.requestClient.request(
                RequestResource.Method.DELETE, url, null,opt.getApiKey(), UnbindedLinkedAccount.class);
    }

}
