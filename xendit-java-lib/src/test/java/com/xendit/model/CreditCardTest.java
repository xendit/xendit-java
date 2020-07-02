package com.xendit.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.BaseRequest;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class CreditCardTest {
  private static String URL = String.format("%s%s", Xendit.getUrl(), "/credit_card_charges");
  private static String TEST_ID = "test_id";
  private static String TEST_TOKEN_ID = "test";
  private static String TEST_EXTERNAL_ID = "test";
  private static Number TEST_AMOUNT = 100000;
  private static String TEST_AUTHENTICATION_ID = "test";
  private static String TEST_CARD_CVN = "123";
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  private static CreditCardCharge VALID_CHARGE = CreditCardCharge.builder().build();

  @Before
  public void initMocks() {
    Xendit.requestClient = mock(BaseRequest.class);
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

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, CreditCardCharge.class))
        .thenReturn(VALID_CHARGE);
    CreditCardCharge creditCardCharge = CreditCard.createCharge(PARAMS);

    assertEquals(creditCardCharge, VALID_CHARGE);
  }

  @Test(expected = XenditException.class)
  public void createCharge_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.put("token_id", "fake_id");
    PARAMS.put("external_id", "fake_id");

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, CreditCardCharge.class))
        .thenThrow(new XenditException("Token id is invalid"));
    CreditCard.createCharge(PARAMS);
  }

  @Test
  public void createAuthorization_Success_IfParamsAreValid() throws XenditException {
    initCreateParams();
    PARAMS.put("capture", false);

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, CreditCardCharge.class))
        .thenReturn(VALID_CHARGE);
    CreditCardCharge creditCardCharge = CreditCard.createAuthorization(PARAMS);

    assertEquals(creditCardCharge, VALID_CHARGE);
  }

  @Test(expected = XenditException.class)
  public void createAuthorization_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.put("token_id", "fake_id");
    PARAMS.put("external_id", "fake_id");

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, CreditCardCharge.class))
        .thenThrow(new XenditException("Token id is invalid"));
    CreditCard.createCharge(PARAMS);
  }

  @Test
  public void reverseAuthorization_Success_IfParamsAreValid() throws XenditException {
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/auth_reversal");
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    CreditCardReverseAuth creditCardReverseAuth = CreditCardReverseAuth.builder().build();

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, HEADERS, PARAMS, CreditCardReverseAuth.class))
        .thenReturn(creditCardReverseAuth);
    CreditCardReverseAuth result = CreditCard.reverseAuthorization(TEST_ID, TEST_EXTERNAL_ID);

    assertEquals(creditCardReverseAuth, result);
  }

  @Test(expected = XenditException.class)
  public void reverseAuthorization_ThrowsException_IfParamsAreInvalid() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/auth_reversal");
    PARAMS.put("external_id", TEST_EXTERNAL_ID);

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, HEADERS, PARAMS, CreditCardReverseAuth.class))
        .thenThrow(new XenditException("Could not find credit card charge"));
    CreditCard.reverseAuthorization("fake_id", TEST_EXTERNAL_ID);
  }

  @Test
  public void captureCharge_Success_IfParamsAreValid() throws XenditException {
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/capture");
    PARAMS.put("amount", TEST_AMOUNT);

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, HEADERS, PARAMS, CreditCardCharge.class))
        .thenReturn(VALID_CHARGE);
    CreditCardCharge creditCardCharge = CreditCard.captureCharge(TEST_ID, TEST_AMOUNT);

    assertEquals(creditCardCharge, VALID_CHARGE);
  }

  @Test(expected = XenditException.class)
  public void captureCharge_ThrowsException_IfParamsAreInvalid() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/capture");
    PARAMS.put("amount", TEST_AMOUNT);

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, HEADERS, PARAMS, CreditCardCharge.class))
        .thenThrow(new XenditException("Could not find one credit card charge"));
    CreditCard.captureCharge("fake_id", TEST_AMOUNT);
  }

  @Test
  public void getCharge_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);

    when(Xendit.requestClient.request(
            RequestResource.Method.GET, url, null, CreditCardCharge.class))
        .thenReturn(VALID_CHARGE);
    CreditCardCharge creditCardCharge = CreditCard.getCharge(TEST_ID);

    assertEquals(creditCardCharge, VALID_CHARGE);
  }

  @Test(expected = XenditException.class)
  public void getCharge_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id");

    when(Xendit.requestClient.request(
            RequestResource.Method.GET, url, null, CreditCardCharge.class))
        .thenThrow(new XenditException("Could not find one credit card charge"));
    CreditCard.getCharge("fake_id");
  }

  @Test
  public void createRefund_Success_IfParamsAreValid() throws XenditException {
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/refunds");
    PARAMS.put("amount", TEST_AMOUNT);
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    CreditCardRefund creditCardRefund = CreditCardRefund.builder().build();

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, HEADERS, PARAMS, CreditCardRefund.class))
        .thenReturn(creditCardRefund);
    CreditCardRefund result = CreditCard.createRefund(TEST_ID, TEST_AMOUNT, TEST_EXTERNAL_ID);

    assertEquals(result, creditCardRefund);
  }

  @Test(expected = XenditException.class)
  public void createRefund_ThrowsException_IfParamsAreInvalid() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/refunds");
    PARAMS.put("amount", TEST_AMOUNT);
    PARAMS.put("external_id", TEST_EXTERNAL_ID);

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, HEADERS, PARAMS, CreditCardRefund.class))
        .thenThrow(new XenditException("Refundable credit card charge not found"));
    CreditCard.createRefund("fake_id", TEST_AMOUNT, TEST_EXTERNAL_ID);
  }
}
