package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterClient;
import com.xendit.model.PaylaterOrderItem;
import com.xendit.model.PaylaterPlans;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class InitializePaylaterTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/paylater/plans");
  private static String CUSTOMER_ID = "test-customer-id";
  private static String CHANNEL_CODE = "ID_KREDIVO";
  private static String CURRENCY = "IDR";
  private static Number AMOUNT = new Integer("50000");
  private static Map<String, String> ACTIONS = new HashMap<>();
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  PaylaterClient paylaterClient = mock(PaylaterClient.class);
  private static PaylaterOrderItem ORDER_ITEMS =
      PaylaterOrderItem.builder()
          .type("PHYSICAL_PRODUCT")
          .referenceId("SKU_backtoschool-promotion123")
          .name("Nymbus twothousand")
          .netUnitAmount("5000")
          .quantity(1)
          .url("https://www.zngmyhome.com/nymbus")
          .category("Sports")
          .subCategory(null)
          .description(null)
          .metadata(null)
          .build();
  private static PaylaterOrderItem[] ORDER_ITEMS_ARRAY = new PaylaterOrderItem[] {ORDER_ITEMS};
  private static PaylaterPlans VALID_INITIALIZE_PAYLATER =
      PaylaterPlans.builder()
          .customerId(CUSTOMER_ID)
          .channelCode(CHANNEL_CODE)
          .currency(CURRENCY)
          .amount(AMOUNT)
          .orderItems(ORDER_ITEMS_ARRAY)
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
    PARAMS.put("customer_id", CUSTOMER_ID);
    PARAMS.put("channel_code", CHANNEL_CODE);
    PARAMS.put("currency", CURRENCY);
    PARAMS.put("amount", AMOUNT);
    PARAMS.put("order_items", ORDER_ITEMS_ARRAY);
  }

  @Test
  public void createPaylater_Success_IfMethodCalledCorrectly() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            PaylaterPlans.class))
        .thenReturn(VALID_INITIALIZE_PAYLATER);
    when(paylaterClient.initiatePaylaterPlans(
            CUSTOMER_ID, CHANNEL_CODE, CURRENCY, AMOUNT, ORDER_ITEMS_ARRAY))
        .thenReturn(VALID_INITIALIZE_PAYLATER);

    PaylaterPlans paylaterPlans =
        paylaterClient.initiatePaylaterPlans(
            CUSTOMER_ID, CHANNEL_CODE, CURRENCY, AMOUNT, ORDER_ITEMS_ARRAY);

    assertEquals(VALID_INITIALIZE_PAYLATER, paylaterPlans);
  }

  @Test
  public void initializePaylaterPlans_Success_IfParamsIsValid() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            PaylaterPlans.class))
        .thenReturn(VALID_INITIALIZE_PAYLATER);
    when(paylaterClient.initiatePaylaterPlans(PARAMS)).thenReturn(VALID_INITIALIZE_PAYLATER);

    PaylaterPlans paylaterPlans = paylaterClient.initiatePaylaterPlans(PARAMS);

    assertEquals(VALID_INITIALIZE_PAYLATER, paylaterPlans);
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
            PaylaterPlans.class))
        .thenReturn(VALID_INITIALIZE_PAYLATER);
    when(paylaterClient.initiatePaylaterPlans(HEADERS, PARAMS))
        .thenReturn(VALID_INITIALIZE_PAYLATER);

    PaylaterPlans paylaterPlans = paylaterClient.initiatePaylaterPlans(HEADERS, PARAMS);

    assertEquals(paylaterPlans, VALID_INITIALIZE_PAYLATER);
  }

  @Test(expected = XenditException.class)
  public void initiatePaylaterPlans_ThrowsException_IfParamsIsInvalid() throws XenditException {
    initCreateParams();
    PARAMS.put("currency", "NOT_IDR");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            new HashMap<>(),
            PARAMS,
            opt.getApiKey(),
            PaylaterPlans.class))
        .thenThrow(new XenditException("Paylater currency is invalid"));
    when(paylaterClient.initiatePaylaterPlans(PARAMS))
        .thenThrow(new XenditException("Paylater currency is invalid"));

    paylaterClient.initiatePaylaterPlans(PARAMS);
  }
}
