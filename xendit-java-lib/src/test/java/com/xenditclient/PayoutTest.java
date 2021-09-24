package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.network.BaseRequest;
import com.xenditclient.network.NetworkClient;
import com.xenditclient.payout.Payout;
import com.xenditclient.payout.PayoutClient;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class PayoutTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/payouts");
  private static String TEST_ID = "5e0cb0bbf4d38b20d5421b72";
  private static String TEST_EXTERNAL_ID = "test_id";
  private static Number TEST_AMOUNT = 100000;
  private static String TEST_PASSCODE = "827995";
  private static String TEST_STATUS = "ISSUED";
  private static String TEST_STATUS_VOIDED = "VOIDED";
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  PayoutClient payoutClient = mock(PayoutClient.class);
  private static Payout VALID_PAYOUT = new Payout();
  private static Payout VOIDED_PAYOUT = new Payout();

  @Before
  public void initMocks() {
    VALID_PAYOUT.setId(TEST_ID);
    VALID_PAYOUT.setExternalId(TEST_EXTERNAL_ID);
    VALID_PAYOUT.setAmount(TEST_AMOUNT);
    VALID_PAYOUT.setPasscode(TEST_PASSCODE);
    VALID_PAYOUT.setStatus(TEST_STATUS);

    VOIDED_PAYOUT.setId(TEST_ID);
    VOIDED_PAYOUT.setExternalId(TEST_EXTERNAL_ID);
    VOIDED_PAYOUT.setAmount(TEST_AMOUNT);
    VOIDED_PAYOUT.setPasscode(TEST_PASSCODE);
    VOIDED_PAYOUT.setStatus(TEST_STATUS);

    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("amount", TEST_AMOUNT);
  }

  @Test
  public void createPayout_Success_IfParamsAreValid() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), Payout.class))
        .thenReturn(VALID_PAYOUT);
    when(payoutClient.createPayout(PARAMS)).thenReturn(VALID_PAYOUT);
    Payout payout = payoutClient.createPayout(PARAMS);
    assertEquals(payout, VALID_PAYOUT);
  }

  @Test(expected = XenditException.class)
  public void createPayout_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.remove("amount");
    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), Payout.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    when(payoutClient.createPayout(PARAMS))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    payoutClient.createPayout(PARAMS);
  }

  @Test
  public void getPayout_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), Payout.class))
        .thenReturn(VALID_PAYOUT);
    when(payoutClient.getPayout(TEST_ID)).thenReturn(VALID_PAYOUT);
    Payout payout = payoutClient.getPayout(TEST_ID);
    assertEquals(payout, VALID_PAYOUT);
  }

  @Test(expected = XenditException.class)
  public void getPayout_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", "fake_id");
    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), Payout.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    when(payoutClient.getPayout("fake_id"))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    payoutClient.getPayout("fake_id");
  }

  @Test
  public void voidPayout_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/void");
    when(this.requestClient.request(
            RequestResource.Method.POST, url, null, opt.getApiKey(), Payout.class))
        .thenReturn(VOIDED_PAYOUT);
    when(payoutClient.voidPayout(TEST_ID)).thenReturn(VOIDED_PAYOUT);
    Payout payout = payoutClient.voidPayout(TEST_ID);
    assertEquals(payout, VOIDED_PAYOUT);
  }

  @Test(expected = XenditException.class)
  public void voidPayout_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/void");
    when(this.requestClient.request(
            RequestResource.Method.POST, url, null, opt.getApiKey(), Payout.class))
        .thenThrow(new XenditException("Payout does not exist"));
    when(payoutClient.voidPayout("fake_id"))
        .thenThrow(new XenditException("Payout does not exist"));
    payoutClient.voidPayout("fake_id");
  }
}
