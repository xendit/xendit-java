package com.xendit.model;

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

public class PayoutTest {
  private static String URL = String.format("%s%s", Xendit.getUrl(), "/payouts");
  private static String TEST_ID = "5e0cb0bbf4d38b20d5421b72";
  private static String TEST_EXTERNAL_ID = "test_id";
  private static Number TEST_AMOUNT = 100000;
  private static String TEST_PASSCODE = "827995";
  private static String TEST_STATUS = "ISSUED";
  private static String TEST_STATUS_VOIDED = "VOIDED";
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  private static Payout VALID_PAYOUT =
      Payout.builder()
          .id(TEST_ID)
          .externalId(TEST_EXTERNAL_ID)
          .amount(TEST_AMOUNT)
          .passcode(TEST_PASSCODE)
          .status(TEST_STATUS)
          .build();
  private static Payout VOIDED_PAYOUT =
      Payout.builder()
          .id(TEST_ID)
          .externalId(TEST_EXTERNAL_ID)
          .amount(TEST_AMOUNT)
          .passcode(TEST_PASSCODE)
          .status(TEST_STATUS_VOIDED)
          .build();

  @Before
  public void initMocks() {
    Xendit.requestClient = mock(BaseRequest.class);
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("amount", TEST_AMOUNT);
  }

  @Test
  public void createPayout_Success_IfParamsAreValid() throws XenditException {
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, Payout.class))
        .thenReturn(VALID_PAYOUT);
    Payout payout = Payout.createPayout(PARAMS);
    assertEquals(payout, VALID_PAYOUT);
  }

  @Test(expected = XenditException.class)
  public void createPayout_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.remove("amount");
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, Payout.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    Payout.createPayout(PARAMS);
  }

  @Test
  public void getPayout_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Payout.class))
        .thenReturn(VALID_PAYOUT);
    Payout payout = Payout.getPayout(TEST_ID);
    assertEquals(payout, VALID_PAYOUT);
  }

  @Test(expected = XenditException.class)
  public void getPayout_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", "fake_id");
    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Payout.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    Payout.getPayout("fake_id");
  }

  @Test
  public void voidPayout_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/void");
    when(Xendit.requestClient.request(RequestResource.Method.POST, url, null, Payout.class))
        .thenReturn(VOIDED_PAYOUT);
    Payout payout = Payout.voidPayout(TEST_ID);
    assertEquals(payout, VOIDED_PAYOUT);
  }

  @Test(expected = XenditException.class)
  public void voidPayout_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id/void");
    when(Xendit.requestClient.request(RequestResource.Method.POST, url, null, Payout.class))
        .thenThrow(new XenditException("Payout does not exist"));
    Payout.voidPayout("fake_id");
  }
}
