package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.ewallet.EWalletBasketItem;
import com.xendit.model.ewallet.EWalletCharge;
import com.xendit.model.ewallet.EWalletClient;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class EWalletChargeTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/ewallets/charges");
  private static String REFERENCE_ID = "test-reference-id";
  private static String CURRENCY = "IDR";
  private static String CHECKOUT_METHOD = "ONE_TIME_PAYMENT";
  private static String CHANNEL_CODE = "ID_SHOPEEPAY";
  private static String CHARGE_ID = "ewc_f3925450-5c54-4777-98c1-fcf22b0d1e1c";
  private static String BUSINESS_ID = "business-id-example";
  private static String AMOUNT = "10000";
  private static String REDIRECT_URL = "https://yourwebsite.com/order/123";
  private static Map<String, String> CHANNEL_PROPERTIES = new HashMap<>();
  private static Map<String, String> ACTIONS = new HashMap<>();
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  EWalletClient eWalletClient = mock(EWalletClient.class);
  private static EWalletBasketItem BASKET_ITEM =
      EWalletBasketItem.builder()
          .referenceId("basket-product-ref-id")
          .name("product-name")
          .category("mechanics")
          .currency("IDR")
          .price(50000)
          .quantity(5)
          .type("product type")
          .subCategory("product sub category")
          .build();
  private static EWalletBasketItem[] BASKET_ITEM_ARRAY = new EWalletBasketItem[] {BASKET_ITEM};
  private static EWalletCharge VALID_EWALLET_CHARGE = new EWalletCharge();

  @Before
  public void initMocks() {

    VALID_EWALLET_CHARGE.setId(CHARGE_ID);
    VALID_EWALLET_CHARGE.setBusinessId(BUSINESS_ID);
    VALID_EWALLET_CHARGE.setCurrency(CURRENCY);
    VALID_EWALLET_CHARGE.setChargeAmount(AMOUNT);
    VALID_EWALLET_CHARGE.setCaptureAmount(AMOUNT);
    VALID_EWALLET_CHARGE.setCheckoutMethod(CHECKOUT_METHOD);
    VALID_EWALLET_CHARGE.setChannelCode(CHANNEL_CODE);
    VALID_EWALLET_CHARGE.setChannelProperties(
        new HashMap<String, String>() {
          {
            put("success_redirect_url", REDIRECT_URL);
          }
        });
    VALID_EWALLET_CHARGE.setActions(
        new HashMap<String, String>() {
          {
            put("desktop_web_checkout_url", null);
            put("mobile_web_checkout_url", null);
            put("mobile_deeplink_checkout_url", "https://mobile.deeplink.checkout.url");
            put("qr_checkout_string", "test-qr-string");
          }
        });
    VALID_EWALLET_CHARGE.setIsRedirectRequired(Boolean.TRUE);
    VALID_EWALLET_CHARGE.setCallbackUrl(REDIRECT_URL);
    VALID_EWALLET_CHARGE.setCreated("2021-02-09T06:22:35.064408Z");
    VALID_EWALLET_CHARGE.setUpdated("2021-02-09T06:22:35.064408Z");
    VALID_EWALLET_CHARGE.setCaptureNow(Boolean.TRUE);

    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);

    HEADERS.clear();
    PARAMS.clear();
  }

  private void initCreateParams() {
    PARAMS.put("reference_id", REFERENCE_ID);
    PARAMS.put("currency", CURRENCY);
    PARAMS.put("amount", new Integer(AMOUNT));
    PARAMS.put("checkout_method", CHECKOUT_METHOD);
    PARAMS.put("channel_code", CHANNEL_CODE);
    PARAMS.put("channel_properties", CHANNEL_PROPERTIES);
    PARAMS.put("basket", BASKET_ITEM_ARRAY);
    PARAMS.put("customer_id", null);
    PARAMS.put("metadata", null);
  }

  @Test
  public void createEWalletCharge_Success_IfMethodCalledCorrectly() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            EWalletCharge.class))
        .thenReturn(VALID_EWALLET_CHARGE);
    when(eWalletClient.createEWalletCharge(
            REFERENCE_ID,
            CURRENCY,
            new Integer(AMOUNT),
            CHECKOUT_METHOD,
            CHANNEL_CODE,
            CHANNEL_PROPERTIES,
            null,
            BASKET_ITEM_ARRAY,
            null))
        .thenReturn(VALID_EWALLET_CHARGE);

    EWalletCharge eWalletCharge =
        eWalletClient.createEWalletCharge(
            REFERENCE_ID,
            CURRENCY,
            new Integer(AMOUNT),
            CHECKOUT_METHOD,
            CHANNEL_CODE,
            CHANNEL_PROPERTIES,
            null,
            BASKET_ITEM_ARRAY,
            null);

    assertEquals(VALID_EWALLET_CHARGE, eWalletCharge);
  }

  @Test
  public void createEWalletCharge_Success_IfParamsIsValid() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            EWalletCharge.class))
        .thenReturn(VALID_EWALLET_CHARGE);
    when(eWalletClient.createEWalletCharge(PARAMS)).thenReturn(VALID_EWALLET_CHARGE);

    EWalletCharge eWalletCharge = eWalletClient.createEWalletCharge(PARAMS);

    assertEquals(VALID_EWALLET_CHARGE, eWalletCharge);
  }

  @Test
  public void createEWalletCharge_Success_WithHeaderProvided() throws XenditException {
    initCreateParams();
    HEADERS.put("for-user-id", "user-id");
    HEADERS.put("with-fee-rule", "fee-rule");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            EWalletCharge.class))
        .thenReturn(VALID_EWALLET_CHARGE);
    when(eWalletClient.createEWalletCharge(HEADERS, PARAMS)).thenReturn(VALID_EWALLET_CHARGE);

    EWalletCharge eWalletCharge = eWalletClient.createEWalletCharge(HEADERS, PARAMS);

    assertEquals(eWalletCharge, VALID_EWALLET_CHARGE);
  }

  @Test(expected = XenditException.class)
  public void createEWalletCharge_ThrowsException_IfParamsIsInvalid() throws XenditException {
    initCreateParams();
    PARAMS.put("currency", "NOT_IDR");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            new HashMap<>(),
            PARAMS,
            opt.getApiKey(),
            EWalletCharge.class))
        .thenThrow(new XenditException("E-Wallet charge currency is invalid"));
    when(eWalletClient.createEWalletCharge(PARAMS))
        .thenThrow(new XenditException("E-Wallet charge currency is invalid"));

    eWalletClient.createEWalletCharge(PARAMS);
  }

  @Test
  public void getEWalletChargeStatus_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s/%s", URL, CHARGE_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), EWalletCharge.class))
        .thenReturn(VALID_EWALLET_CHARGE);
    when(eWalletClient.getEWalletChargeStatus(CHARGE_ID)).thenReturn(VALID_EWALLET_CHARGE);

    EWalletCharge eWalletCharge = eWalletClient.getEWalletChargeStatus(CHARGE_ID);

    assertEquals(VALID_EWALLET_CHARGE, eWalletCharge);
  }

  @Test(expected = XenditException.class)
  public void getEWalletChargeStatus_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String NOT_AVAILABLE_ID = "not-available-id";
    String url = String.format("%s/%s", URL, NOT_AVAILABLE_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), EWalletCharge.class))
        .thenThrow(new XenditException("E-Wallet charge not found"));
    when(eWalletClient.getEWalletChargeStatus(NOT_AVAILABLE_ID))
        .thenThrow(new XenditException("E-Wallet charge not found"));

    eWalletClient.getEWalletChargeStatus(NOT_AVAILABLE_ID);
  }
}
