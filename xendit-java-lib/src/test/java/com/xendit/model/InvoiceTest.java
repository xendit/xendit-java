package com.xendit.model;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.BaseRequest;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class InvoiceTest {
  private static final String URL_V2 = String.format("%s%s", Xendit.getUrl(), "/v2/invoices");
  private static final String URL_V1 = String.format("%s%s", Xendit.getUrl(), "/invoices");
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static String TEST_ID = "5e0cb0bbf4d38b20d5421b72";
  private static String TEST_EXTERNAL_ID = "test_id";
  private static Number TEST_AMOUNT = 100000;
  private static Number TEST_AMOUNT_LESS = 1000;
  private static Number TEST_AMOUNT_GREATER = 1000000001;
  private static String TEST_EMAIL = "test@email.com";
  private static String TEST_DESC = "Testing";
  private static Invoice VALID_INVOICE =
      Invoice.builder()
          .id(TEST_ID)
          .externalId(TEST_EXTERNAL_ID)
          .amount(TEST_AMOUNT)
          .payerEmail(TEST_EMAIL)
          .description(TEST_DESC)
          .build();

  @Before
  public void initMocks() {
    Xendit.requestClient = Mockito.mock(BaseRequest.class);
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("amount", TEST_AMOUNT);
    PARAMS.put("payer_email", TEST_EMAIL);
    PARAMS.put("description", TEST_DESC);
  }

  @Test
  public void create_Success_IfParamsAreValid() throws Exception {
    when(Xendit.requestClient.request(RequestResource.Method.POST, URL_V2, PARAMS, Invoice.class))
        .thenReturn(VALID_INVOICE);
    Invoice invoice = Invoice.create(TEST_EXTERNAL_ID, TEST_AMOUNT, TEST_EMAIL, TEST_DESC);
    assertEquals(invoice, VALID_INVOICE);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfAmountIsLessThanMinimum() throws XenditException {
    PARAMS.put("amount", TEST_AMOUNT_LESS);
    when(Xendit.requestClient.request(RequestResource.Method.POST, URL_V2, PARAMS, Invoice.class))
        .thenThrow(XenditException.class);
    Invoice.create(TEST_EXTERNAL_ID, TEST_AMOUNT_LESS, TEST_EMAIL, TEST_DESC);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfAmountIsGreaterThanMaximum() throws XenditException {
    PARAMS.put("amount", TEST_AMOUNT_GREATER);
    when(Xendit.requestClient.request(RequestResource.Method.POST, URL_V2, PARAMS, Invoice.class))
        .thenThrow(XenditException.class);
    Invoice.create(TEST_EXTERNAL_ID, TEST_AMOUNT_GREATER, TEST_EMAIL, TEST_DESC);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfInvalidParams() throws XenditException {
    Map<String, Object> params = new HashMap<>();
    when(Xendit.requestClient.request(RequestResource.Method.POST, URL_V2, params, Invoice.class))
        .thenThrow(XenditException.class);
    Invoice.create(params);
  }

  @Test
  public void getById_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL_V2, "/", TEST_ID);
    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Invoice.class))
        .thenReturn(VALID_INVOICE);
    Invoice invoice = Invoice.getById(TEST_ID);
    assertEquals(invoice.getId(), VALID_INVOICE.getId());
  }

  @Test(expected = XenditException.class)
  public void getById_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL_V2, "/", "fake_id");
    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Invoice.class))
        .thenThrow(XenditException.class);
    Invoice.getById("fake_id");
  }

  @Test
  public void getAll_Success_IfParamsAreValid() throws XenditException {
    Number limit = 2;
    String statuses = "[\"SETTLED\",\"EXPIRED\"]";
    String clientTypes = "[\"DASHBOARD\",\"API_GATEWAY\"]";
    String lastInvoiceId = "5ca186e407f3b83e34176eac";

    Map<String, Object> params = new HashMap<>();
    params.put("limit", limit);
    params.put("statuses", statuses);
    params.put("client_types", clientTypes);
    params.put("last_invoice_id", lastInvoiceId);

    String urlParams =
        String.format(
            "%s%s%s%s",
            String.format("%s%s", "&statuses=", statuses),
            String.format("%s%s", "&limit=", limit.toString()),
            String.format("%s%s", "&last_invoice_id=", lastInvoiceId),
            String.format("%s%s", "&client_types=", clientTypes));
    String url = String.format("%s%s%s", URL_V2, "?", urlParams);

    Invoice[] invoiceResult = {VALID_INVOICE};

    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Invoice[].class))
        .thenReturn(invoiceResult);
    Invoice[] invoices = Invoice.getAll(params);
    assertEquals(invoices[0], VALID_INVOICE);
    assertThat(invoices, instanceOf(Invoice[].class));
  }

  @Test
  public void getAll_Success_IfNoGivenParams() throws XenditException {
    Invoice[] invoiceResult = {VALID_INVOICE};
    String url = String.format("%s%s", URL_V2, "?");

    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Invoice[].class))
        .thenReturn(invoiceResult);
    Invoice[] invoices = Invoice.getAll(new HashMap<>());
    assertEquals(invoices[0], VALID_INVOICE);
    assertThat(invoices, instanceOf(Invoice[].class));
  }

  @Test
  public void expire_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s%s", URL_V1, "/", TEST_ID, "/expire!");
    when(Xendit.requestClient.request(RequestResource.Method.POST, url, null, Invoice.class))
        .thenReturn(VALID_INVOICE);
    Invoice invoice = Invoice.expire("5e0cb0bbf4d38b20d5421b72");
    assertEquals(invoice, VALID_INVOICE);
  }

  @Test(expected = XenditException.class)
  public void expire_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL_V1, "/fake_id/expire!");
    when(Xendit.requestClient.request(RequestResource.Method.POST, url, null, Invoice.class))
        .thenThrow(XenditException.class);
    Invoice.expire("fake_id");
  }
}
