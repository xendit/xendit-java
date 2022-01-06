package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterCharge;
import com.xendit.model.PaylaterClient;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class PaylaterChargeTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/paylater/charges");
  private static String PLAN_ID = "test-plan-id";
  private static String REFERENCE_ID = "test-reference-id";
  private static String CHECKOUT_METHOD = "ONE_TIME_PAYMENT";
  private static String SUCCESS_REDIRECT_URL = "https://yourwebsite.com/order/123";
  private static String FAILURE_REDIRECT_URL = "https://twitter.com/";
  private static Map<String, String> ACTIONS = new HashMap<>();
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  PaylaterClient paylaterClient = mock(PaylaterClient.class);
  private static PaylaterCharge VALID_PAYLATER_CHARGE = PaylaterCharge.builder()
      .planId(PLAN_ID)
      .referenceId(REFERENCE_ID)
      .checkoutMethod(CHECKOUT_METHOD)
      .successRedirectUrl(SUCCESS_REDIRECT_URL)
      .failureRedirectUrl(FAILURE_REDIRECT_URL)
      .build();

  @Before
  public void initMocks() {
    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);

    HEADERS.clear();
    PARAMS.clear();
  }

  private void initCreateParams() {
    PARAMS.put("plan_id", PLAN_ID);
    PARAMS.put("reference_id", REFERENCE_ID);
    PARAMS.put("checKout_method", CHECKOUT_METHOD);
    PARAMS.put("success_redirect_url", SUCCESS_REDIRECT_URL);
    PARAMS.put("failure_redirect_url", FAILURE_REDIRECT_URL);
    PARAMS.put("metadata", null);
  }

  @Test
  public void createPaylaterCharge_Success_IfMethodCalledCorrectly() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
        RequestResource.Method.POST,
        URL,
        HEADERS,
        PARAMS,
        opt.getApiKey(),
        PaylaterCharge.class))
            .thenReturn(VALID_PAYLATER_CHARGE);
    when(paylaterClient.createPaylaterCharges(
        PLAN_ID,
        REFERENCE_ID,
        CHECKOUT_METHOD,
        SUCCESS_REDIRECT_URL,
        FAILURE_REDIRECT_URL,
        null,
        null))
            .thenReturn(VALID_PAYLATER_CHARGE);

    PaylaterCharge paylaterCharge = paylaterClient.createPaylaterCharges(
        PLAN_ID,
        REFERENCE_ID,
        CHECKOUT_METHOD,
        SUCCESS_REDIRECT_URL,
        FAILURE_REDIRECT_URL,
        null,
        null);

    assertEquals(VALID_PAYLATER_CHARGE, paylaterCharge);
  }

  @Test
  public void createPaylaterCharge_Success_IfParamsIsValid() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
        RequestResource.Method.POST,
        URL,
        HEADERS,
        PARAMS,
        opt.getApiKey(),
        PaylaterCharge.class))
            .thenReturn(VALID_PAYLATER_CHARGE);
    when(paylaterClient.createPaylaterCharges(PARAMS)).thenReturn(VALID_PAYLATER_CHARGE);

    PaylaterCharge paylaterCharge = paylaterClient.createPaylaterCharges(PARAMS);

    assertEquals(VALID_PAYLATER_CHARGE, paylaterCharge);
  }

  @Test
  public void createPaylaterCharge_Success_WithHeaderProvided() throws XenditException {
    initCreateParams();
    HEADERS.put("for-user-id", "user-id");

    when(this.requestClient.request(
        RequestResource.Method.POST,
        URL,
        HEADERS,
        PARAMS,
        opt.getApiKey(),
        PaylaterCharge.class))
            .thenReturn(VALID_PAYLATER_CHARGE);
    when(paylaterClient.createPaylaterCharges(HEADERS, PARAMS)).thenReturn(VALID_PAYLATER_CHARGE);

    PaylaterCharge paylaterCharge = paylaterClient.createPaylaterCharges(HEADERS, PARAMS);

    assertEquals(paylaterCharge, VALID_PAYLATER_CHARGE);
  }

  @Test(expected = XenditException.class)
  public void createPaylaterCharge_ThrowsException_IfParamsIsInvalid() throws XenditException {
    initCreateParams();
    PARAMS.put("checkout_method", "NOT_ONE_TIME_PAYMENT");

    when(this.requestClient.request(
        RequestResource.Method.POST,
        URL,
        new HashMap<>(),
        PARAMS,
        opt.getApiKey(),
        PaylaterCharge.class))
            .thenThrow(new XenditException("Paylater checkout_method is invalid"));
    when(paylaterClient.createPaylaterCharges(PARAMS))
        .thenThrow(new XenditException("Paylater checkout_method is invalid"));

    paylaterClient.createPaylaterCharges(PARAMS);
  }
}
