package com.xenditclient;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.invoice.Invoice;
import com.xenditclient.invoice.InvoiceClient;
import com.xenditclient.network.BaseRequest;
import com.xenditclient.network.NetworkClient;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class InvoiceTest {
  private static final String URL_V2 =
      String.format("%s%s", com.xendit.Xendit.getUrl(), "/v2/invoices");
  private static final String URL_V1 =
      String.format("%s%s", com.xendit.Xendit.getUrl(), "/invoices");
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static String TEST_ID = "5e0cb0bbf4d38b20d5421b72";
  private static String TEST_EXTERNAL_ID = "test_id";
  private static Number TEST_AMOUNT = 100000;
  private static Number TEST_AMOUNT_LESS = 1000;
  private static Number TEST_AMOUNT_GREATER = 1000000001;
  private static String TEST_EMAIL = "test@email.com";
  private static String TEST_DESC = "Testing";
  Invoice VALID_INVOICE = new Invoice();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  InvoiceClient invoiceClient = mock(InvoiceClient.class);

  @Before
  public void initMocks() {
    VALID_INVOICE.setId(TEST_ID);
    VALID_INVOICE.setExternalId(TEST_EXTERNAL_ID);
    VALID_INVOICE.setAmount(TEST_AMOUNT);
    VALID_INVOICE.setPayerEmail(TEST_EMAIL);
    VALID_INVOICE.setDescription(TEST_DESC);

    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("amount", TEST_AMOUNT);
    PARAMS.put("payer_email", TEST_EMAIL);
    PARAMS.put("description", TEST_DESC);

    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
  }

  @Test
  public void create_Success_IfParamsAreValid() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.POST, URL_V2, PARAMS, opt.getApiKey(), Invoice.class))
        .thenReturn(VALID_INVOICE);
    when(invoiceClient.create(TEST_EXTERNAL_ID, TEST_AMOUNT, TEST_EMAIL, TEST_DESC))
        .thenReturn(VALID_INVOICE);
    Invoice invoice = invoiceClient.create(TEST_EXTERNAL_ID, TEST_AMOUNT, TEST_EMAIL, TEST_DESC);
    assertEquals(invoice, VALID_INVOICE);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfAmountIsLessThanMinimum() throws XenditException {
    PARAMS.put("amount", TEST_AMOUNT_LESS);
    when(this.requestClient.request(
            RequestResource.Method.POST, URL_V2, PARAMS, opt.getApiKey(), Invoice.class))
        .thenThrow(XenditException.class);
    when(invoiceClient.create(TEST_EXTERNAL_ID, TEST_AMOUNT, TEST_EMAIL, TEST_DESC))
        .thenThrow(XenditException.class);
    invoiceClient.create(TEST_EXTERNAL_ID, TEST_AMOUNT, TEST_EMAIL, TEST_DESC);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfAmountIsGreaterThanMaximum() throws XenditException {
    PARAMS.put("amount", TEST_AMOUNT_GREATER);
    when(this.requestClient.request(
            RequestResource.Method.POST, URL_V2, PARAMS, opt.getApiKey(), Invoice.class))
        .thenThrow(XenditException.class);
    when(invoiceClient.create(TEST_EXTERNAL_ID, TEST_AMOUNT, TEST_EMAIL, TEST_DESC))
        .thenThrow(XenditException.class);
    invoiceClient.create(TEST_EXTERNAL_ID, TEST_AMOUNT, TEST_EMAIL, TEST_DESC);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfInvalidParams() throws XenditException {
    Map<String, Object> params = new HashMap<>();
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL_V2,
            new HashMap<>(),
            params,
            opt.getApiKey(),
            Invoice.class))
        .thenThrow(XenditException.class);
    when(invoiceClient.create(params)).thenThrow(XenditException.class);
    invoiceClient.create(params);
  }

  @Test
  public void getById_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL_V2, "/", TEST_ID);
    when(this.requestClient.request(
            RequestResource.Method.GET, url, new HashMap<>(), null, opt.getApiKey(), Invoice.class))
        .thenReturn(VALID_INVOICE);
    when(invoiceClient.getById(TEST_ID)).thenReturn(VALID_INVOICE);
    Invoice invoice = invoiceClient.getById(TEST_ID);
    assertEquals(invoice.getId(), VALID_INVOICE.getId());
  }

  @Test(expected = XenditException.class)
  public void getById_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL_V2, "/", "fake_id");
    when(this.requestClient.request(
            RequestResource.Method.GET, url, new HashMap<>(), null, opt.getApiKey(), Invoice.class))
        .thenThrow(XenditException.class);

    when(invoiceClient.getById("fake_id")).thenThrow(XenditException.class);
    invoiceClient.getById("fake_id");
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

    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            new HashMap<>(),
            null,
            opt.getApiKey(),
            Invoice[].class))
        .thenReturn(invoiceResult);
    when(invoiceClient.getAll(params)).thenReturn(invoiceResult);
    Invoice[] invoices = invoiceClient.getAll(params);
    assertEquals(invoices[0], VALID_INVOICE);
    assertThat(invoices, instanceOf(Invoice[].class));
  }

  @Test
  public void getAll_Success_IfNoGivenParams() throws XenditException {
    Invoice[] invoiceResult = {VALID_INVOICE};
    String url = String.format("%s%s", URL_V2, "?");

    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            new HashMap<>(),
            null,
            opt.getApiKey(),
            Invoice[].class))
        .thenReturn(invoiceResult);
    when(invoiceClient.getAll(new HashMap<>())).thenReturn(invoiceResult);
    Invoice[] invoices = invoiceClient.getAll(new HashMap<>());
    assertEquals(invoices[0], VALID_INVOICE);
    assertThat(invoices, instanceOf(Invoice[].class));
  }

  @Test
  public void expire_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s%s", URL_V1, "/", TEST_ID, "/expire!");
    when(this.requestClient.request(
            RequestResource.Method.POST,
            url,
            new HashMap<>(),
            null,
            opt.getApiKey(),
            Invoice.class))
        .thenReturn(VALID_INVOICE);
    when(invoiceClient.expire("5e0cb0bbf4d38b20d5421b72")).thenReturn(VALID_INVOICE);
    Invoice invoice = invoiceClient.expire("5e0cb0bbf4d38b20d5421b72");
    assertEquals(invoice, VALID_INVOICE);
  }

  @Test(expected = XenditException.class)
  public void expire_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL_V1, "/fake_id/expire!");
    when(this.requestClient.request(
            RequestResource.Method.POST,
            url,
            new HashMap<>(),
            null,
            opt.getApiKey(),
            Invoice.class))
        .thenThrow(XenditException.class);
    when(invoiceClient.expire("fake_id")).thenThrow(XenditException.class);
    invoiceClient.expire("fake_id");
    Invoice.expire("fake_id");
  }
}
