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

public class RetailOutletTest {
  private static final String URL = String.format("%s%s", Xendit.getUrl(), "/fixed_payment_code");
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static String TEST_ID = "5e0cb0bbf4d38b20d5421b72";
  private static String TEST_EXTERNAL_ID = "test_id";
  private static FixedPaymentCode VALID_FPC =
      FixedPaymentCode.builder().id(TEST_ID).externalId(TEST_EXTERNAL_ID).build();

  @Before
  public void initMocks() {
    Xendit.requestClient = mock(BaseRequest.class);
  }

  @Test
  public void createFixedPaymentCode_Success_IfParamsAreValid() throws XenditException {
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("retail_outlet_name", "ALFAMART");
    PARAMS.put("name", "Rika Sutanto");
    PARAMS.put("expected_amount", 10000);

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, PARAMS, FixedPaymentCode.class))
        .thenReturn(VALID_FPC);
    FixedPaymentCode fpc = RetailOutlet.createFixedPaymentCode(PARAMS);
    assertEquals(fpc, VALID_FPC);
  }

  @Test(expected = XenditException.class)
  public void createFixedPaymentCode_ThrowsException_IfInvalidParams() throws XenditException {
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, PARAMS, FixedPaymentCode.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    RetailOutlet.createFixedPaymentCode(PARAMS);
  }

  @Test
  public void getFixedPaymentCode_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    when(Xendit.requestClient.request(
            RequestResource.Method.GET, url, null, FixedPaymentCode.class))
        .thenReturn(VALID_FPC);
    FixedPaymentCode fpc = RetailOutlet.getFixedPaymentCode(TEST_ID);
    assertEquals(fpc.getId(), VALID_FPC.getId());
  }

  @Test(expected = XenditException.class)
  public void getFixedPaymentCode_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", "fake_id");
    when(Xendit.requestClient.request(
            RequestResource.Method.GET, url, null, FixedPaymentCode.class))
        .thenThrow(new XenditException("Fixed payment code not found"));
    RetailOutlet.getFixedPaymentCode("fake_id");
  }

  @Test
  public void updateFixedPaymentCode_Success_IfIdIsAvailable() throws XenditException {
    PARAMS.put("name", "New Name");
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    FixedPaymentCode result =
        FixedPaymentCode.builder()
            .id(TEST_ID)
            .externalId(TEST_EXTERNAL_ID)
            .name("New Name")
            .build();

    when(Xendit.requestClient.request(
            RequestResource.Method.PATCH, url, PARAMS, FixedPaymentCode.class))
        .thenReturn(result);
    FixedPaymentCode fpc = RetailOutlet.updateFixedPaymentCode(TEST_ID, PARAMS);
    assertEquals(fpc, result);
  }

  @Test(expected = XenditException.class)
  public void updateFixedPaymentCode_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", "fake_id");
    when(Xendit.requestClient.request(
            RequestResource.Method.PATCH, url, PARAMS, FixedPaymentCode.class))
        .thenThrow(new XenditException("Fixed payment code not found"));
    RetailOutlet.updateFixedPaymentCode("fake_id", PARAMS);
  }
}
