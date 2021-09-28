package com.xenditclient;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.invoice.Invoice;
import com.xendit.model.recurringPayment.RecurringPayment;
import com.xendit.model.recurringPayment.RecurringPaymentClient;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class RecurringPaymentTest {
  private static String URL =
      String.format("%s%s", Xendit.Opt.getXenditURL(), "/recurring_payments");
  private static Map<String, String> HEADERS = new HashMap<>();
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static String TEST_ID = "5e2dd55ef8a4d24146f59775";
  private static String TEST_EXTERNAL_ID = "test_id";
  private static String TEST_PAYER_EMAIL = "sample_email@xendit.co";
  private static String TEST_INTERVAL = "MONTH";
  private static Number TEST_INTERVAL_COUNT = 1;
  private static String TEST_DESCRIPTION = "Test desc";
  private static Number TEST_AMOUNT = 100000;
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  RecurringPaymentClient recurringPaymentClient = mock(RecurringPaymentClient.class);
  private static RecurringPayment VALID_PAYMENT = new RecurringPayment();

  @Before
  public void initMocks() {
    VALID_PAYMENT.setId(TEST_ID);
    VALID_PAYMENT.setExternalId(TEST_EXTERNAL_ID);
    VALID_PAYMENT.setPayerEmail(TEST_PAYER_EMAIL);
    VALID_PAYMENT.setInterval(TEST_INTERVAL);
    VALID_PAYMENT.setIntervalCount(TEST_INTERVAL_COUNT);
    VALID_PAYMENT.setDescription(TEST_DESCRIPTION);
    VALID_PAYMENT.setAmount(TEST_AMOUNT);

    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);

    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("payer_email", TEST_PAYER_EMAIL);
    PARAMS.put("interval", TEST_INTERVAL);
    PARAMS.put("interval_count", TEST_INTERVAL_COUNT);
    PARAMS.put("description", TEST_DESCRIPTION);
    PARAMS.put("amount", TEST_AMOUNT);
  }

  @Test
  public void create_Success_IfParamsAreValid() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            RecurringPayment.class))
        .thenReturn(VALID_PAYMENT);
    when(recurringPaymentClient.create(PARAMS)).thenReturn(VALID_PAYMENT);
    RecurringPayment recurringPayment = recurringPaymentClient.create(PARAMS);
    assertEquals(recurringPayment, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.remove("external_id");
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            RecurringPayment.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    when(recurringPaymentClient.create(PARAMS))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    recurringPaymentClient.create(PARAMS);
  }

  @Test
  public void edit_Success_IfIdIsAvailable() throws XenditException {
    RecurringPayment result = new RecurringPayment();
    result.setAmount(98765);
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    Map<String, Object> params = new HashMap<>();
    params.put("amount", 98765);
    when(this.requestClient.request(
            RequestResource.Method.PATCH, url, params, opt.getApiKey(), RecurringPayment.class))
        .thenReturn(result);
    when(recurringPaymentClient.edit(TEST_ID, params)).thenReturn(result);
    RecurringPayment recurringPayment = recurringPaymentClient.edit(TEST_ID, params);
    assertEquals(result, recurringPayment);
  }

  @Test(expected = XenditException.class)
  public void edit_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id");
    Map<String, Object> params = new HashMap<>();
    when(this.requestClient.request(
            RequestResource.Method.PATCH, url, params, opt.getApiKey(), RecurringPayment.class))
        .thenThrow(
            new XenditException(
                "\"Something unexpected happened, we are investigating this issue right now\""));
    when(recurringPaymentClient.edit("fake_id", params))
        .thenThrow(
            new XenditException(
                "\"Something unexpected happened, we are investigating this issue right now\""));
    recurringPaymentClient.edit("fake_id", params);
  }

  @Test
  public void get_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), RecurringPayment.class))
        .thenReturn(VALID_PAYMENT);
    when(recurringPaymentClient.get(TEST_ID)).thenReturn(VALID_PAYMENT);
    RecurringPayment recurringPayment = recurringPaymentClient.get(TEST_ID);
    assertEquals(recurringPayment, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void get_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id");
    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), RecurringPayment.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    when(recurringPaymentClient.get("fake_id"))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    recurringPaymentClient.get("fake_id");
  }

  @Test
  public void stop_Success_IfIdIsAvailable() throws XenditException {
    RecurringPayment result = new RecurringPayment();
    result.setId(TEST_ID);
    result.setStatus("STOPPED");
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/stop!");
    when(this.requestClient.request(
            RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class))
        .thenReturn(result);
    when(recurringPaymentClient.stop(TEST_ID)).thenReturn(result);
    RecurringPayment recurringPayment = recurringPaymentClient.stop(TEST_ID);
    assertEquals(recurringPayment, result);
  }

  @Test(expected = XenditException.class)
  public void stop_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/stop!");
    when(this.requestClient.request(
            RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    when(recurringPaymentClient.stop("fake_id"))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    recurringPaymentClient.stop("fake_id");
  }

  @Test
  public void pause_Success_IfIdIsAvailable() throws XenditException {
    RecurringPayment result = new RecurringPayment();
    result.setId(TEST_ID);
    result.setStatus("PAUSED");
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/pause!");
    when(this.requestClient.request(
            RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class))
        .thenReturn(result);
    when(recurringPaymentClient.pause(TEST_ID)).thenReturn(result);
    RecurringPayment recurringPayment = recurringPaymentClient.pause(TEST_ID);
    assertEquals(recurringPayment, result);
  }

  @Test(expected = XenditException.class)
  public void pause_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/pause!");
    when(this.requestClient.request(
            RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    when(recurringPaymentClient.pause("fake_id"))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    recurringPaymentClient.pause("fake_id");
  }

  @Test
  public void resume_Success_IfIdIsAvailable() throws XenditException {
    RecurringPayment result = new RecurringPayment();
    result.setId(TEST_ID);
    result.setStatus("ACTIVE");
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/resume!");
    when(this.requestClient.request(
            RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class))
        .thenReturn(result);
    when(recurringPaymentClient.resume(TEST_ID)).thenReturn(result);
    RecurringPayment recurringPayment = recurringPaymentClient.resume(TEST_ID);
    assertEquals(recurringPayment, result);
  }

  @Test(expected = XenditException.class)
  public void resume_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/resume!");
    when(this.requestClient.request(
            RequestResource.Method.POST, url, null, opt.getApiKey(), RecurringPayment.class))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    when(recurringPaymentClient.resume("fake_id"))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    recurringPaymentClient.resume("fake_id");
  }

  @Test
  public void getPaymentsById_Success_IfIdIsAvailable() throws XenditException {
    String url =
        String.format(
            "%s%s%s", Xendit.Opt.getXenditURL(), "/v2/invoices?recurring_payment_id=", TEST_ID);
    Invoice invoice = new Invoice();
    invoice.setRecurringPaymentId(TEST_ID);
    Invoice[] invoices = {invoice};
    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), Invoice[].class))
        .thenReturn(invoices);
    when(recurringPaymentClient.getPaymentsById(TEST_ID)).thenReturn(invoices);
    Invoice[] result = recurringPaymentClient.getPaymentsById(TEST_ID);
    assertArrayEquals(result, invoices);
  }
}
