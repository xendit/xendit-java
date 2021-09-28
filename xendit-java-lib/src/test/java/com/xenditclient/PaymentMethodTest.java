package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.directDebit.DirectDebitPaymentClient;
import com.xendit.model.directDebit.LinkedAccountEnum.AccountType;
import com.xendit.model.directDebit.PaymentMethod;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class PaymentMethodTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/payment_methods");
  private static String CUSTOMER_ID = "4b7b6050-0830-440a-903b-37d527dbbaa9";
  private static AccountType PAYMENT_METHOD_TYPE = AccountType.DEBIT_CARD;
  private static String STATUS = "ACTIVE";
  private static String PAYMENT_METHOD_ID = "pm-ebb1c863-c7b5-4f20-b116-b3071b1d3aef";
  private static String CREATED = "2021-07-15T03:17:53.989Z";
  private static String UPDATED = "2021-07-15T03:17:53.989Z";
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  DirectDebitPaymentClient directDebitPaymentClient = mock(DirectDebitPaymentClient.class);
  private static Map<String, Object> REQUEST_PROPERTIES =
      new HashMap<String, Object>() {
        {
          put("id", "la-55048b41-a7ab-4799-9f33-6ec5cc078db0");
        }
      };
  private static Map<String, Object> RESPONSE_PROPERTIES =
      new HashMap<String, Object>() {
        {
          put("id", "la-55048b41-a7ab-4799-9f33-6ec5cc078db0");
          put("currency", "IDR");
          put("card_expiry", "11/23");
          put("description", "");
          put("channel_code", "DC_BRI");
          put("card_last_four", "8888");
        }
      };
  private static Map<String, Object> METADATA =
      new HashMap<String, Object>() {
        {
          put("tes", "123");
        }
      };
  private static PaymentMethod VALID_PAYMENT_METHOD =
      PaymentMethod.builder()
          .id(PAYMENT_METHOD_ID)
          .customerId(CUSTOMER_ID)
          .type(PAYMENT_METHOD_TYPE)
          .status(STATUS)
          .properties(RESPONSE_PROPERTIES)
          .metadata(METADATA)
          .created(CREATED)
          .updated(UPDATED)
          .build();
  private static PaymentMethod[] PAYMENT_METHOD_ARRAY = new PaymentMethod[] {VALID_PAYMENT_METHOD};

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
    PARAMS.put("type", PAYMENT_METHOD_TYPE);
    PARAMS.put("properties", REQUEST_PROPERTIES);
    PARAMS.put("metadata", METADATA);
  }

  @Test
  public void createPaymentMethod_Success_IfMethodCalledCorrectly() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            PaymentMethod.class))
        .thenReturn(VALID_PAYMENT_METHOD);
    when(directDebitPaymentClient.createPaymentMethod(
            CUSTOMER_ID, PAYMENT_METHOD_TYPE, REQUEST_PROPERTIES, METADATA))
        .thenReturn(VALID_PAYMENT_METHOD);

    PaymentMethod paymentMethod =
        directDebitPaymentClient.createPaymentMethod(
            CUSTOMER_ID, PAYMENT_METHOD_TYPE, REQUEST_PROPERTIES, METADATA);

    assertEquals(VALID_PAYMENT_METHOD, paymentMethod);
  }

  @Test
  public void createPaymentMethod_Success_IfParamsIsValid() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            PaymentMethod.class))
        .thenReturn(VALID_PAYMENT_METHOD);
    when(directDebitPaymentClient.createPaymentMethod(PARAMS)).thenReturn(VALID_PAYMENT_METHOD);

    PaymentMethod paymentMethod = directDebitPaymentClient.createPaymentMethod(PARAMS);

    assertEquals(VALID_PAYMENT_METHOD, paymentMethod);
  }

  @Test
  public void createPaymentMethod_Success_WithHeaderProvided() throws XenditException {
    initCreateParams();
    HEADERS.put("for-user-id", "user-id");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            PaymentMethod.class))
        .thenReturn(VALID_PAYMENT_METHOD);
    when(directDebitPaymentClient.createPaymentMethod(HEADERS, PARAMS))
        .thenReturn(VALID_PAYMENT_METHOD);

    PaymentMethod paymentMethod = directDebitPaymentClient.createPaymentMethod(HEADERS, PARAMS);

    assertEquals(VALID_PAYMENT_METHOD, paymentMethod);
  }

  @Test(expected = XenditException.class)
  public void createPaymentMethod_ThrowsException_IfParamsIsInvalid() throws XenditException {
    initCreateParams();
    PARAMS.put("type", "NOT_DEBIT_CARD");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            new HashMap<>(),
            PARAMS,
            opt.getApiKey(),
            PaymentMethod.class))
        .thenThrow(new XenditException("Payment method type is invalid"));

    when(directDebitPaymentClient.createPaymentMethod(PARAMS))
        .thenThrow(new XenditException("Payment method type is invalid"));

    directDebitPaymentClient.createPaymentMethod(PARAMS);
  }

  @Test
  public void getPaymentMethods_Success_IfCustomerIdIsAvailable() throws XenditException {
    String url = String.format("%s?customer_id=%s", URL, CUSTOMER_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), PaymentMethod[].class))
        .thenReturn(PAYMENT_METHOD_ARRAY);
    when(directDebitPaymentClient.getPaymentMethodsByCustomerId(CUSTOMER_ID))
        .thenReturn(PAYMENT_METHOD_ARRAY);

    PaymentMethod[] paymentMethods =
        directDebitPaymentClient.getPaymentMethodsByCustomerId(CUSTOMER_ID);

    assertEquals(PAYMENT_METHOD_ARRAY, paymentMethods);
  }

  @Test(expected = XenditException.class)
  public void getPaymentMethods_ThrowsException_IfCustomerIdIsNotAvailable()
      throws XenditException {
    String NOT_AVAILABLE_CUSTOMER_ID = "not-available-customer-id";
    String url = String.format("%s?customer_id=%s", URL, NOT_AVAILABLE_CUSTOMER_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), PaymentMethod[].class))
        .thenThrow(new XenditException("Payment methods not found"));

    when(directDebitPaymentClient.getPaymentMethodsByCustomerId(NOT_AVAILABLE_CUSTOMER_ID))
        .thenThrow(new XenditException("Payment methods not found"));

    directDebitPaymentClient.getPaymentMethodsByCustomerId(NOT_AVAILABLE_CUSTOMER_ID);
  }
}
