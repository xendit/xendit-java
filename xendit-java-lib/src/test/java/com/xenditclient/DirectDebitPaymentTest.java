package com.xenditclient;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.directDebit.*;
import com.xenditclient.directDebit.LinkedAccountEnum.ChannelCode;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DirectDebitPaymentTest {
    private static String DIRECT_DEBIT_PAYMENT_ID = "ddpy-7e61b0a7-92f9-4762-a994-c2936306f44c";
    private static String REFERENCE_ID = "direct-debit-ref-id";
    private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/direct_debits");
    private static String VALIDATE_URL =
            String.format(
                    "%s%s%s%s",
                    Xendit.Opt.getXenditURL(), "/direct_debits/", DIRECT_DEBIT_PAYMENT_ID, "/validate_otp/");
    private static String GET_BY_ID_URL =
            String.format("%s%s%s%s", Xendit.Opt.getXenditURL(), "/direct_debits/", DIRECT_DEBIT_PAYMENT_ID, "/");
    private static String GET_BY_REFERENCE_ID_URL =
            String.format("%s%s%s", URL, "?reference_id=", REFERENCE_ID);
    private static String PAYMENT_METHOD_ID = "pm-ebb1c863-c7b5-4f20-b116-b3071b1d3aef";
    private static ChannelCode CHANNEL_CODE = ChannelCode.DC_BRI;
    private static String CURRENCY = "IDR";
    private static Number AMOUNT = 150000;
    private static Boolean IS_OTP_REQUIRED = true;
    private static Boolean ENABLE_OTP = true;
    NetworkClient requestClient = mock(BaseRequest.class);
    Xendit.Option opt = mock(Xendit.Option.class);
    DirectDebitPaymentClient directDebitPaymentClient = mock(DirectDebitPaymentClient.class);
    private static DirectDebitBasketItem BASKET_ITEM =
            DirectDebitBasketItem.builder()
                    .referenceId("basket-product-ref-id")
                    .name("product-name")
                    .category("mechanics")
                    .market("ID")
                    .price(50000)
                    .quantity(5)
                    .type("product type")
                    .subCategory("product sub category")
                    .description("product description")
                    .url("https://product.url")
                    .build();
    private static DirectDebitBasketItem[] BASKET_ITEM_ARRAY =
            new DirectDebitBasketItem[]{BASKET_ITEM};
    private static String DESCRIPTION = "test description";
    private static String PENDING_STATUS = "PENDING";
    private static String COMPLETED_STATUS = "COMPLETED";
    private static String CREATED = "2021-07-16T02:19:07.277466Z";
    private static String UPDATED = "2021-07-16T02:19:07.277466Z";
    private static DirectDebitDevice DEVICE =
            DirectDebitDevice.builder()
                    .id("device-id")
                    .ipAddress("0.0.0.0")
                    .userAgent("user-agent")
                    .adId("ad-id")
                    .imei("123a456b789c")
                    .build();
    private static Number REFUNDED_AMOUNT = 0;
    private static DirectDebitRefunds REFUNDS =
            DirectDebitRefunds.builder()
                    .data(new String[]{"a1b", "c2d", "e3f", "g4h"})
                    .hasMore(false)
                    .url("https://ref.unds")
                    .build();
    private static String FAILURE_CODE = "failure-code";
    private static String OTP_MOBILE_NUMBER = "+6281234567890";
    private static String OTP_EXPIRATION_TIMESTAMP = "2100-07-16T02:19:07.277466Z";
    private static String SUCCESS_REDIRECT_URL = "https://success-redirect.url";
    private static String CHECKOUT_URL = "https://checkout.url";
    private static String FAILURE_REDIRECT_URL = "https://failure-redirect.url";
    private static String REQUIRED_ACTION = "VALIDATE_OTP";
    private static String OTP_CODE = "222000";
    private static String CALLBACK_URL = "http://webhook.site/";
    private static String IDEMPOTENCY_KEY = "idempotency-key-3";
    private static Map<String, Object> PARAMS = new HashMap<>();
    private static Map<String, String> HEADERS = new HashMap<>();
    private static Map<String, Object> METADATA =
            new HashMap<String, Object>() {
                {
                    put("tes", "123");
                }
            };
    private static DirectDebitPayment VALID_DIRECT_DEBIT_PAYMENT =
            DirectDebitPayment.builder()
                    .id(DIRECT_DEBIT_PAYMENT_ID)
                    .referenceId(REFERENCE_ID)
                    .paymentMethodId(PAYMENT_METHOD_ID)
                    .channelCode(CHANNEL_CODE)
                    .currency(CURRENCY)
                    .amount(AMOUNT)
                    .isOtpRequired(IS_OTP_REQUIRED)
                    .basket(BASKET_ITEM_ARRAY)
                    .description(DESCRIPTION)
                    .status(PENDING_STATUS)
                    .metadata(METADATA)
                    .created(CREATED)
                    .updated(UPDATED)
                    .device(DEVICE)
                    .refundedAmount(REFUNDED_AMOUNT)
                    .refunds(REFUNDS)
                    .failureCode(FAILURE_CODE)
                    .otpMobileNumber(OTP_MOBILE_NUMBER)
                    .otpExpirationTimestamp(OTP_EXPIRATION_TIMESTAMP)
                    .successRedirectUrl(SUCCESS_REDIRECT_URL)
                    .checkoutUrl(CHECKOUT_URL)
                    .failureRedirectUrl(FAILURE_REDIRECT_URL)
                    .requiredAction(REQUIRED_ACTION)
                    .build();
    private static DirectDebitPayment VALID_VALIDATED_DIRECT_DEBIT_PAYMENT =
            DirectDebitPayment.builder()
                    .id(DIRECT_DEBIT_PAYMENT_ID)
                    .referenceId(REFERENCE_ID)
                    .paymentMethodId(PAYMENT_METHOD_ID)
                    .channelCode(CHANNEL_CODE)
                    .currency(CURRENCY)
                    .amount(AMOUNT)
                    .isOtpRequired(IS_OTP_REQUIRED)
                    .basket(BASKET_ITEM_ARRAY)
                    .description(DESCRIPTION)
                    .status(COMPLETED_STATUS)
                    .metadata(METADATA)
                    .created(CREATED)
                    .updated(UPDATED)
                    .device(DEVICE)
                    .refundedAmount(REFUNDED_AMOUNT)
                    .refunds(REFUNDS)
                    .failureCode(FAILURE_CODE)
                    .otpMobileNumber(OTP_MOBILE_NUMBER)
                    .otpExpirationTimestamp(OTP_EXPIRATION_TIMESTAMP)
                    .successRedirectUrl(SUCCESS_REDIRECT_URL)
                    .checkoutUrl(CHECKOUT_URL)
                    .failureRedirectUrl(FAILURE_REDIRECT_URL)
                    .requiredAction(REQUIRED_ACTION)
                    .build();
    DirectDebitPayment[] DIRECT_DEBIT_PAYMENT_ARRAY =
            new DirectDebitPayment[]{VALID_VALIDATED_DIRECT_DEBIT_PAYMENT};

    @Before
    public void initMocks() {
        Xendit.Opt.setApiKey(
                "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
        Xendit.setRequestClient(requestClient);
        HEADERS.clear();
        PARAMS.clear();
    }

    private void initCreateParams() {
        PARAMS.put("reference_id", REFERENCE_ID);
        PARAMS.put("payment_method_id", PAYMENT_METHOD_ID);
        PARAMS.put("currency", CURRENCY);
        PARAMS.put("amount", AMOUNT);
        PARAMS.put("callback_url", CALLBACK_URL);
        PARAMS.put("enable_otp", ENABLE_OTP);
        PARAMS.put("description", DESCRIPTION);
        PARAMS.put("basket", BASKET_ITEM_ARRAY);
        PARAMS.put("success_redirect_url", SUCCESS_REDIRECT_URL);
        PARAMS.put("failure_redirect_url", FAILURE_REDIRECT_URL);
        PARAMS.put("device", DEVICE);
        PARAMS.put("metadata", METADATA);
    }

    private void initValidateOTPParams() {
        PARAMS.put("otp_code", OTP_CODE);
    }

    @Test
    public void createDirectDebitPayment_Success_IfMethodCalledCorrectly() throws XenditException {
        initCreateParams();
        HEADERS.put("Idempotency-key", IDEMPOTENCY_KEY);

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), DirectDebitPayment.class))
                .thenReturn(VALID_DIRECT_DEBIT_PAYMENT);

        when(directDebitPaymentClient.createDirectDebitPayment(REFERENCE_ID,
                PAYMENT_METHOD_ID,
                CURRENCY,
                AMOUNT,
                CALLBACK_URL,
                ENABLE_OTP,
                DESCRIPTION,
                BASKET_ITEM_ARRAY,
                DEVICE,
                SUCCESS_REDIRECT_URL,
                FAILURE_REDIRECT_URL,
                METADATA,
                IDEMPOTENCY_KEY)).thenReturn(VALID_DIRECT_DEBIT_PAYMENT);

        DirectDebitPayment directDebitPayment =
                directDebitPaymentClient.createDirectDebitPayment(
                        REFERENCE_ID,
                        PAYMENT_METHOD_ID,
                        CURRENCY,
                        AMOUNT,
                        CALLBACK_URL,
                        ENABLE_OTP,
                        DESCRIPTION,
                        BASKET_ITEM_ARRAY,
                        DEVICE,
                        SUCCESS_REDIRECT_URL,
                        FAILURE_REDIRECT_URL,
                        METADATA,
                        IDEMPOTENCY_KEY);

        assertEquals(VALID_DIRECT_DEBIT_PAYMENT, directDebitPayment);
    }

    @Test
    public void createDirectDebitPayment_Success_IfParamsIsValid() throws XenditException {
        initCreateParams();
        HEADERS.put("Idempotency-key", IDEMPOTENCY_KEY);

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), DirectDebitPayment.class))
                .thenReturn(VALID_DIRECT_DEBIT_PAYMENT);
        when(directDebitPaymentClient.createDirectDebitPayment(PARAMS, IDEMPOTENCY_KEY)).thenReturn(VALID_DIRECT_DEBIT_PAYMENT);

        DirectDebitPayment directDebitPayment =
                directDebitPaymentClient.createDirectDebitPayment(PARAMS, IDEMPOTENCY_KEY);

        assertEquals(VALID_DIRECT_DEBIT_PAYMENT, directDebitPayment);
    }

    @Test
    public void createDirectDebitPayment_Success_WithHeaderProvided() throws XenditException {
        initCreateParams();
        HEADERS.put("for-user-id", "user-id");
        HEADERS.put("Idempotency-key", IDEMPOTENCY_KEY);

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), DirectDebitPayment.class))
                .thenReturn(VALID_DIRECT_DEBIT_PAYMENT);
        when(directDebitPaymentClient.createDirectDebitPayment(HEADERS, PARAMS, IDEMPOTENCY_KEY)).thenReturn(VALID_DIRECT_DEBIT_PAYMENT);

        DirectDebitPayment directDebitPayment =
                directDebitPaymentClient.createDirectDebitPayment(HEADERS, PARAMS, IDEMPOTENCY_KEY);

        assertEquals(VALID_DIRECT_DEBIT_PAYMENT, directDebitPayment);
    }

    @Test(expected = XenditException.class)
    public void createDirectDebitPayment_ThrowsException_IfParamsIsInvalid() throws XenditException {
        initCreateParams();
        PARAMS.put("channel_code", "NOT_DC_BRI");
        HEADERS.put("Idempotency-key", IDEMPOTENCY_KEY);

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), DirectDebitPayment.class))
                .thenThrow(new XenditException("Channel code is invalid"));

        when(directDebitPaymentClient.createDirectDebitPayment(PARAMS, IDEMPOTENCY_KEY)).thenThrow(new XenditException("Channel code is invalid"));

        directDebitPaymentClient.createDirectDebitPayment(PARAMS, IDEMPOTENCY_KEY);
    }

    @Test
    public void validateOTP_Success_IfMethodCalledCorrectly() throws XenditException {
        initValidateOTPParams();

        when(this.requestClient.request(
                RequestResource.Method.POST, VALIDATE_URL, HEADERS, PARAMS, opt.getApiKey(), DirectDebitPayment.class))
                .thenReturn(VALID_VALIDATED_DIRECT_DEBIT_PAYMENT);

        when(directDebitPaymentClient.validateOTP(DIRECT_DEBIT_PAYMENT_ID, OTP_CODE)).thenReturn(VALID_VALIDATED_DIRECT_DEBIT_PAYMENT);

        DirectDebitPayment directDebitPayment =
                directDebitPaymentClient.validateOTP(DIRECT_DEBIT_PAYMENT_ID, OTP_CODE);

        assertEquals(VALID_VALIDATED_DIRECT_DEBIT_PAYMENT, directDebitPayment);
    }

    @Test
    public void validateOTP_Success_IfParamsIsValid() throws XenditException {
        initValidateOTPParams();

        when(this.requestClient.request(
                RequestResource.Method.POST, VALIDATE_URL, HEADERS, PARAMS, opt.getApiKey(), DirectDebitPayment.class))
                .thenReturn(VALID_VALIDATED_DIRECT_DEBIT_PAYMENT);
        when(directDebitPaymentClient.validateOTP(DIRECT_DEBIT_PAYMENT_ID, PARAMS)).thenReturn(VALID_VALIDATED_DIRECT_DEBIT_PAYMENT);

        DirectDebitPayment directDebitPayment =
                directDebitPaymentClient.validateOTP(DIRECT_DEBIT_PAYMENT_ID, PARAMS);

        assertEquals(VALID_VALIDATED_DIRECT_DEBIT_PAYMENT, directDebitPayment);
    }

    @Test
    public void validateOTP_Success_WithHeaderProvided() throws XenditException {
        initValidateOTPParams();
        HEADERS.put("for-user-id", "user-id");

        when(this.requestClient.request(
                RequestResource.Method.POST, VALIDATE_URL, HEADERS, PARAMS, opt.getApiKey(), DirectDebitPayment.class))
                .thenReturn(VALID_VALIDATED_DIRECT_DEBIT_PAYMENT);
        when(directDebitPaymentClient.validateOTP(DIRECT_DEBIT_PAYMENT_ID, HEADERS, PARAMS)).thenReturn(VALID_VALIDATED_DIRECT_DEBIT_PAYMENT);

        DirectDebitPayment directDebitPayment =
                directDebitPaymentClient.validateOTP(DIRECT_DEBIT_PAYMENT_ID, HEADERS, PARAMS);

        assertEquals(directDebitPayment, VALID_VALIDATED_DIRECT_DEBIT_PAYMENT);
    }

    @Test(expected = XenditException.class)
    public void validateOTP_ThrowsException_IfParamsIsInvalid() throws XenditException {
        initValidateOTPParams();
        PARAMS.put("otp_code", "000000");

        when(this.requestClient.request(
                RequestResource.Method.POST,
                VALIDATE_URL,
                new HashMap<>(),
                PARAMS,
                opt.getApiKey(),
                DirectDebitPayment.class))
                .thenThrow(new XenditException("OTP code is invalid"));
        when(directDebitPaymentClient.validateOTP(DIRECT_DEBIT_PAYMENT_ID, PARAMS)).thenThrow(new XenditException("OTP code is invalid"));

        directDebitPaymentClient.validateOTP(DIRECT_DEBIT_PAYMENT_ID, PARAMS);
    }

    @Test
    public void getDirectDebitPaymentStatusById_Success_IfIdIsAvailable() throws XenditException {
        when(this.requestClient.request(
                RequestResource.Method.GET, GET_BY_ID_URL, null, opt.getApiKey(), DirectDebitPayment.class))
                .thenReturn(VALID_DIRECT_DEBIT_PAYMENT);
        when(directDebitPaymentClient.getDirectDebitPaymentStatusById(DIRECT_DEBIT_PAYMENT_ID)).thenReturn(VALID_DIRECT_DEBIT_PAYMENT);

        DirectDebitPayment directDebitPayment =
                directDebitPaymentClient.getDirectDebitPaymentStatusById(DIRECT_DEBIT_PAYMENT_ID);

        assertEquals(VALID_DIRECT_DEBIT_PAYMENT, directDebitPayment);
    }

    @Test(expected = XenditException.class)
    public void getDirectDebitPaymentStatusById_ThrowsException_IfIdIsNotAvailable()
            throws XenditException {
        String NOT_AVAILABLE_ID = "not-available-id";
        GET_BY_ID_URL = String.format("%s%s%s%s", URL, "/", NOT_AVAILABLE_ID, "/");

        when(this.requestClient.request(
                RequestResource.Method.GET, GET_BY_ID_URL, null, opt.getApiKey(), DirectDebitPayment.class))
                .thenThrow(new XenditException("Direct debit payment not found"));
        when(directDebitPaymentClient.getDirectDebitPaymentStatusById(NOT_AVAILABLE_ID)).thenThrow(new XenditException("Direct debit payment not found"));

        directDebitPaymentClient.getDirectDebitPaymentStatusById(NOT_AVAILABLE_ID);
    }

    @Test
    public void getDirectDebitPaymentStatusByReferenceId_Success_IfReferenceIdIsAvailable()
            throws XenditException {
        when(this.requestClient.request(
                RequestResource.Method.GET, GET_BY_REFERENCE_ID_URL, null, opt.getApiKey(), DirectDebitPayment[].class))
                .thenReturn(DIRECT_DEBIT_PAYMENT_ARRAY);
        when(directDebitPaymentClient.getDirectDebitPaymentStatusByReferenceId(REFERENCE_ID)).thenReturn(DIRECT_DEBIT_PAYMENT_ARRAY);

        DirectDebitPayment[] directDebitPayments =
                directDebitPaymentClient.getDirectDebitPaymentStatusByReferenceId(REFERENCE_ID);

        assertEquals(DIRECT_DEBIT_PAYMENT_ARRAY, directDebitPayments);
    }

    @Test(expected = XenditException.class)
    public void getDirectDebitPaymentStatusByReferenceId_ThrowsException_IfReferenceIdIsNotAvailable()
            throws XenditException {
        String NOT_AVAILABLE_REFERENCE_ID = "not-available-reference-id";
        GET_BY_REFERENCE_ID_URL =
                String.format("%s%s%s", URL, "?reference_id=", NOT_AVAILABLE_REFERENCE_ID);

        when(this.requestClient.request(
                RequestResource.Method.GET, GET_BY_REFERENCE_ID_URL, null, opt.getApiKey(), DirectDebitPayment[].class))
                .thenThrow(new XenditException("Direct debit payments not found"));
        when(directDebitPaymentClient.getDirectDebitPaymentStatusByReferenceId(NOT_AVAILABLE_REFERENCE_ID))
                .thenThrow(new XenditException("Direct debit payments not found"));

        directDebitPaymentClient.getDirectDebitPaymentStatusByReferenceId(NOT_AVAILABLE_REFERENCE_ID);
    }
}
