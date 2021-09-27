package com.xenditclient;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xenditclient.creditCard.CreditCardCharge;
import com.xenditclient.creditCard.CreditCardRefund;
import com.xenditclient.creditCard.CreditCardReverseAuth;
import com.xendit.network.RequestResource;
import com.xenditclient.creditCard.CreditCardClient;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreditCardTest {
    private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/credit_card_charges");
    private static String TEST_ID = "test_id";
    private static String TEST_TOKEN_ID = "test";
    private static String TEST_EXTERNAL_ID = "test";
    private static Number TEST_AMOUNT = 100000;
    private static String TEST_AUTHENTICATION_ID = "test";
    private static String TEST_CARD_CVN = "123";
    private static Map<String, Object> PARAMS = new HashMap<>();
    private static Map<String, String> HEADERS = new HashMap<>();
    NetworkClient requestClient = mock(BaseRequest.class);
    Xendit.Option opt = mock(Xendit.Option.class);
    CreditCardClient creditCardClient = mock(CreditCardClient.class);
    private static CreditCardCharge VALID_CHARGE = CreditCardCharge.builder().build();

    @Before
    public void initMocks() {
        Xendit.Opt.setApiKey(
                "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
        Xendit.setRequestClient(requestClient);
        PARAMS.clear();
    }

    private void initCreateParams() {
        PARAMS.put("token_id", TEST_TOKEN_ID);
        PARAMS.put("external_id", TEST_EXTERNAL_ID);
        PARAMS.put("amount", TEST_AMOUNT);
        PARAMS.put("authentication_id", TEST_AUTHENTICATION_ID);
        PARAMS.put("card_cvn", TEST_CARD_CVN);
    }

    @Test
    public void createCharge_Success_IfParamsAreValid() throws XenditException {
        initCreateParams();
        PARAMS.put("descriptor", "lorem ipsum");

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), CreditCardCharge.class))
                .thenReturn(VALID_CHARGE);
        when(creditCardClient.createCharge(PARAMS)).thenReturn(VALID_CHARGE);
        CreditCardCharge creditCardCharge = creditCardClient.createCharge(PARAMS);

        assertEquals(creditCardCharge, VALID_CHARGE);
    }

    @Test(expected = XenditException.class)
    public void createCharge_ThrowsException_IfParamsAreInvalid() throws XenditException {
        PARAMS.put("token_id", "fake_id");
        PARAMS.put("external_id", "fake_id");

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), CreditCardCharge.class))
                .thenThrow(new XenditException("Token id is invalid"));
        when(creditCardClient.createCharge(PARAMS)).thenThrow(new XenditException("Token id is invalid"));
        creditCardClient.createCharge(PARAMS);
    }

    @Test
    public void createAuthorization_Success_IfParamsAreValid() throws XenditException {
        initCreateParams();
        PARAMS.put("capture", false);

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), CreditCardCharge.class))
                .thenReturn(VALID_CHARGE);
        when(creditCardClient.createAuthorization(PARAMS)).thenReturn(VALID_CHARGE);
        CreditCardCharge creditCardCharge = creditCardClient.createAuthorization(PARAMS);

        assertEquals(creditCardCharge, VALID_CHARGE);
    }

    @Test(expected = XenditException.class)
    public void createAuthorization_ThrowsException_IfParamsAreInvalid() throws XenditException {
        PARAMS.put("token_id", "fake_id");
        PARAMS.put("external_id", "fake_id");

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), CreditCardCharge.class))
                .thenThrow(new XenditException("Token id is invalid"));
        when(creditCardClient.createAuthorization(PARAMS)).thenThrow(new XenditException("Token id is invalid"));
        creditCardClient.createAuthorization(PARAMS);
    }

    @Test
    public void reverseAuthorization_Success_IfParamsAreValid() throws XenditException {
        String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/auth_reversal");
        PARAMS.put("external_id", TEST_EXTERNAL_ID);
        CreditCardReverseAuth creditCardReverseAuth = CreditCardReverseAuth.builder().build();

        when(this.requestClient.request(
                RequestResource.Method.POST, url, HEADERS, PARAMS, opt.getApiKey(), CreditCardReverseAuth.class))
                .thenReturn(creditCardReverseAuth);
        when(creditCardClient.reverseAuthorization(TEST_ID, TEST_EXTERNAL_ID)).thenReturn(creditCardReverseAuth);
        CreditCardReverseAuth result = creditCardClient.reverseAuthorization(TEST_ID, TEST_EXTERNAL_ID);

        assertEquals(creditCardReverseAuth, result);
    }

    @Test(expected = XenditException.class)
    public void reverseAuthorization_ThrowsException_IfParamsAreInvalid() throws XenditException {
        String url = String.format("%s%s", URL, "/fake_id/auth_reversal");
        PARAMS.put("external_id", TEST_EXTERNAL_ID);

        when(this.requestClient.request(
                RequestResource.Method.POST, url, HEADERS, PARAMS, opt.getApiKey(), CreditCardReverseAuth.class))
                .thenThrow(new XenditException("Could not find credit card charge"));
        when(creditCardClient.reverseAuthorization("fake_id", TEST_EXTERNAL_ID)).thenThrow(new XenditException("Could not find credit card charge"));
        creditCardClient.reverseAuthorization("fake_id", TEST_EXTERNAL_ID);
    }

    @Test
    public void captureCharge_Success_IfParamsAreValid() throws XenditException {
        String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/capture");
        PARAMS.put("amount", TEST_AMOUNT);

        when(this.requestClient.request(
                RequestResource.Method.POST, url, HEADERS, PARAMS, opt.getApiKey(), CreditCardCharge.class))
                .thenReturn(VALID_CHARGE);
        when(creditCardClient.captureCharge(TEST_ID, TEST_AMOUNT)).thenReturn(VALID_CHARGE);

        CreditCardCharge creditCardCharge = creditCardClient.captureCharge(TEST_ID, TEST_AMOUNT);

        assertEquals(creditCardCharge, VALID_CHARGE);
    }

    @Test(expected = XenditException.class)
    public void captureCharge_ThrowsException_IfParamsAreInvalid() throws XenditException {
        String url = String.format("%s%s", URL, "/fake_id/capture");
        PARAMS.put("amount", TEST_AMOUNT);

        when(this.requestClient.request(
                RequestResource.Method.POST, url, HEADERS, PARAMS, opt.getApiKey(), CreditCardCharge.class))
                .thenThrow(new XenditException("Could not find one credit card charge"));
        when(creditCardClient.captureCharge("fake_id", TEST_AMOUNT)).thenThrow(new XenditException("Could not find one credit card charge"));
        creditCardClient.captureCharge("fake_id", TEST_AMOUNT);
    }

    @Test
    public void getCharge_Success_IfIdIsAvailable() throws XenditException {
        String url = String.format("%s%s%s", URL, "/", TEST_ID);

        when(this.requestClient.request(
                RequestResource.Method.GET, url, null, opt.getApiKey(), CreditCardCharge.class))
                .thenReturn(VALID_CHARGE);
        when(creditCardClient.getCharge(TEST_ID)).thenReturn(VALID_CHARGE);
        CreditCardCharge creditCardCharge = creditCardClient.getCharge(TEST_ID);

        assertEquals(creditCardCharge, VALID_CHARGE);
    }

    @Test(expected = XenditException.class)
    public void getCharge_ThrowsException_IfIdIsNotAvailable() throws XenditException {
        String url = String.format("%s%s", URL, "/fake_id");

        when(this.requestClient.request(
                RequestResource.Method.GET, url, null, opt.getApiKey(), CreditCardCharge.class))
                .thenThrow(new XenditException("Could not find one credit card charge"));
        when(creditCardClient.getCharge("fake_id")).thenThrow(new XenditException("Could not find one credit card charge"));
        creditCardClient.getCharge("fake_id");
    }

    @Test
    public void createRefund_Success_IfParamsAreValid() throws XenditException {
        String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/refunds");
        PARAMS.put("amount", TEST_AMOUNT);
        PARAMS.put("external_id", TEST_EXTERNAL_ID);
        CreditCardRefund creditCardRefund = CreditCardRefund.builder().build();

        when(this.requestClient.request(
                RequestResource.Method.POST, url, HEADERS, PARAMS, opt.getApiKey(), CreditCardRefund.class))
                .thenReturn(creditCardRefund);
        when(creditCardClient.createRefund(TEST_ID, TEST_AMOUNT, TEST_EXTERNAL_ID)).thenReturn(creditCardRefund);
        CreditCardRefund result = creditCardClient.createRefund(TEST_ID, TEST_AMOUNT, TEST_EXTERNAL_ID);

        assertEquals(result, creditCardRefund);
    }

    @Test(expected = XenditException.class)
    public void createRefund_ThrowsException_IfParamsAreInvalid() throws XenditException {
        String url = String.format("%s%s", URL, "/fake_id/refunds");
        PARAMS.put("amount", TEST_AMOUNT);
        PARAMS.put("external_id", TEST_EXTERNAL_ID);

        when(this.requestClient.request(
                RequestResource.Method.POST, url, HEADERS, PARAMS, opt.getApiKey(), CreditCardRefund.class))
                .thenThrow(new XenditException("Refundable credit card charge not found"));
        when(creditCardClient.createRefund("fake_id", TEST_AMOUNT, TEST_EXTERNAL_ID)).thenThrow(new XenditException("Refundable credit card charge not found"));
        creditCardClient.createRefund("fake_id", TEST_AMOUNT, TEST_EXTERNAL_ID);
    }
}
