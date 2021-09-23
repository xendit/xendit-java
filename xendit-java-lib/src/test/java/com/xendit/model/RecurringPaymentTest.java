package com.xendit.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.BaseRequest;
import com.xendit.network.RequestResource;
import com.xenditclient.invoice.Invoice;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class RecurringPaymentTest {
  private static String URL = String.format("%s%s", Xendit.getUrl(), "/recurring_payments");
  private static Map<String, String> HEADERS = new HashMap<>();
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static String TEST_ID = "5e2dd55ef8a4d24146f59775";
  private static String TEST_EXTERNAL_ID = "test_id";
  private static String TEST_PAYER_EMAIL = "sample_email@xendit.co";
  private static String TEST_INTERVAL = "MONTH";
  private static Number TEST_INTERVAL_COUNT = 1;
  private static String TEST_DESCRIPTION = "Test desc";
  private static Number TEST_AMOUNT = 100000;
  private static RecurringPayment VALID_PAYMENT =
      RecurringPayment.builder()
          .id(TEST_ID)
          .externalId(TEST_EXTERNAL_ID)
          .payerEmail(TEST_PAYER_EMAIL)
          .interval(TEST_INTERVAL)
          .intervalCount(TEST_INTERVAL_COUNT)
          .description(TEST_DESCRIPTION)
          .amount(TEST_AMOUNT)
          .build();

  @Before
  public void initMocks() {
    Xendit.requestClient = mock(BaseRequest.class);
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("payer_email", TEST_PAYER_EMAIL);
    PARAMS.put("interval", TEST_INTERVAL);
    PARAMS.put("interval_count", TEST_INTERVAL_COUNT);
    PARAMS.put("description", TEST_DESCRIPTION);
    PARAMS.put("amount", TEST_AMOUNT);
  }

  @Test
  public void create_Success_IfParamsAreValid() throws XenditException {
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, RecurringPayment.class))
        .thenReturn(VALID_PAYMENT);
    RecurringPayment recurringPayment = RecurringPayment.create(PARAMS);
    assertEquals(recurringPayment, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.remove("external_id");
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, RecurringPayment.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    RecurringPayment.create(PARAMS);
  }

  @Test
  public void edit_Success_IfIdIsAvailable() throws XenditException {
    RecurringPayment result = RecurringPayment.builder().amount(98765).build();
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    Map<String, Object> params = new HashMap<>();
    params.put("amount", 98765);
    when(Xendit.requestClient.request(
            RequestResource.Method.PATCH, url, params, RecurringPayment.class))
        .thenReturn(result);
    RecurringPayment recurringPayment = RecurringPayment.edit(TEST_ID, params);
    assertEquals(result, recurringPayment);
  }

  @Test(expected = XenditException.class)
  public void edit_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id");
    Map<String, Object> params = new HashMap<>();
    when(Xendit.requestClient.request(
            RequestResource.Method.PATCH, url, params, RecurringPayment.class))
        .thenThrow(
            new XenditException(
                "\"Something unexpected happened, we are investigating this issue right now\""));
    RecurringPayment.edit("fake_id", params);
  }

  @Test
  public void get_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    when(Xendit.requestClient.request(
            RequestResource.Method.GET, url, null, RecurringPayment.class))
        .thenReturn(VALID_PAYMENT);
    RecurringPayment recurringPayment = RecurringPayment.get(TEST_ID);
    assertEquals(recurringPayment, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void get_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id");
    when(Xendit.requestClient.request(
            RequestResource.Method.GET, url, null, RecurringPayment.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    RecurringPayment.get("fake_id");
  }

  @Test
  public void stop_Success_IfIdIsAvailable() throws XenditException {
    RecurringPayment result = RecurringPayment.builder().id(TEST_ID).status("STOPPED").build();
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/stop!");
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, null, RecurringPayment.class))
        .thenReturn(result);
    RecurringPayment recurringPayment = RecurringPayment.stop(TEST_ID);
    assertEquals(recurringPayment, result);
  }

  @Test(expected = XenditException.class)
  public void stop_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/stop!");
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, null, RecurringPayment.class))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    RecurringPayment.stop("fake_id");
  }

  @Test
  public void pause_Success_IfIdIsAvailable() throws XenditException {
    RecurringPayment result = RecurringPayment.builder().id(TEST_ID).status("PAUSED").build();
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/pause!");
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, null, RecurringPayment.class))
        .thenReturn(result);
    RecurringPayment recurringPayment = RecurringPayment.pause(TEST_ID);
    assertEquals(recurringPayment, result);
  }

  @Test(expected = XenditException.class)
  public void pause_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/pause!");
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, null, RecurringPayment.class))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    RecurringPayment.pause("fake_id");
  }

  @Test
  public void resume_Success_IfIdIsAvailable() throws XenditException {
    RecurringPayment result = RecurringPayment.builder().id(TEST_ID).status("ACTIVE").build();
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/resume!");
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, null, RecurringPayment.class))
        .thenReturn(result);
    RecurringPayment recurringPayment = RecurringPayment.resume(TEST_ID);
    assertEquals(recurringPayment, result);
  }

  @Test(expected = XenditException.class)
  public void resume_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/resume!");
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, null, RecurringPayment.class))
        .thenThrow(
            new XenditException(
                "Something unexpected happened, we are investigating this issue right now"));
    RecurringPayment.resume("fake_id");
  }

  @Test
  public void getPaymentsById_Success_IfIdIsAvailable() throws XenditException {
    String url =
        String.format("%s%s%s", Xendit.getUrl(), "/v2/invoices?recurring_payment_id=", TEST_ID);
    Invoice invoice = new Invoice();
    invoice.setRecurringPaymentId(TEST_ID);
    Invoice[] invoices = {invoice};
    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Invoice[].class))
        .thenReturn(invoices);
    Invoice[] result = RecurringPayment.getPaymentsById(TEST_ID);
    assertArrayEquals(result, invoices);
  }
}
