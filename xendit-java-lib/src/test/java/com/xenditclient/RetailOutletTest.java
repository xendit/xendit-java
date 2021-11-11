package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;
import com.xendit.model.RetailOutletClient;
import com.xendit.model.RegionalRetailOutletPaymentCode;
import com.xendit.model.RegionalRetailOutletPayments;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class RetailOutletTest {
  private static final String URL =
      String.format("%s%s", Xendit.Opt.getXenditURL(), "/fixed_payment_code");
  private static Map<String, String> HEADERS = new HashMap<>();
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static String TEST_ID = "5e0cb0bbf4d38b20d5421b72";
  private static String TEST_EXTERNAL_ID = "test_id";
  private static String TEST_REFERENCE_ID = "test_reference_id";
  private static String TEST_REMARKS = "test_remarks";
  private static RegionalRetailOutletPaymentCode.ChannelCode TEST_CHANNEL_CODE =
      RegionalRetailOutletPaymentCode.ChannelCode.LBC;
  private static String TEST_CUSTOMER_NAME = "test_customer_name";
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  RetailOutletClient retailOutletClient = mock(RetailOutletClient.class);
  private static FixedPaymentCode VALID_FPC =
      FixedPaymentCode.builder().id(TEST_ID).externalId(TEST_EXTERNAL_ID).build();
  private static RegionalRetailOutletPaymentCode VALID_PC =
      RegionalRetailOutletPaymentCode.builder().id(TEST_ID).referenceId(TEST_REFERENCE_ID).build();
  private static RegionalRetailOutletPayments VALID_Payment =
      RegionalRetailOutletPayments.builder().id(TEST_ID).remarks(TEST_REMARKS).build();

  @Before
  public void initMocks() {
    VALID_FPC.setId(TEST_ID);
    VALID_FPC.setExternalId(TEST_EXTERNAL_ID);
    VALID_PC.setId(TEST_ID);
    VALID_PC.setReferenceId(TEST_REFERENCE_ID);
    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
  }

  @Test
  public void createFixedPaymentCode_Success_IfParamsAreValid() throws XenditException {
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("retail_outlet_name", FixedPaymentCode.RetailOutletName.ALFAMART);
    PARAMS.put("name", "Rika Sutanto");
    PARAMS.put("expected_amount", 10000);

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            FixedPaymentCode.class))
        .thenReturn(VALID_FPC);
    when(retailOutletClient.createFixedPaymentCode(PARAMS)).thenReturn(VALID_FPC);
    FixedPaymentCode fpc = retailOutletClient.createFixedPaymentCode(PARAMS);
    assertEquals(fpc, VALID_FPC);
  }

  @Test(expected = XenditException.class)
  public void createFixedPaymentCode_ThrowsException_IfInvalidParams() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            FixedPaymentCode.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    when(retailOutletClient.createFixedPaymentCode(PARAMS))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    retailOutletClient.createFixedPaymentCode(PARAMS);
  }

  @Test
  public void getFixedPaymentCode_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            HEADERS,
            null,
            opt.getApiKey(),
            FixedPaymentCode.class))
        .thenReturn(VALID_FPC);
    when(retailOutletClient.getFixedPaymentCode(TEST_ID)).thenReturn(VALID_FPC);
    FixedPaymentCode fpc = retailOutletClient.getFixedPaymentCode(TEST_ID);
    assertEquals(fpc.getId(), VALID_FPC.getId());
  }

  @Test(expected = XenditException.class)
  public void getFixedPaymentCode_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", "fake_id");
    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            HEADERS,
            null,
            opt.getApiKey(),
            FixedPaymentCode.class))
        .thenThrow(new XenditException("Fixed payment code not found"));
    when(retailOutletClient.getFixedPaymentCode("fake_id"))
        .thenThrow(new XenditException("Fixed payment code not found"));
    retailOutletClient.getFixedPaymentCode("fake_id");
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
    when(this.requestClient.request(
            RequestResource.Method.PATCH,
            url,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            FixedPaymentCode.class))
        .thenReturn(result);
    when(retailOutletClient.updateFixedPaymentCode(TEST_ID, PARAMS)).thenReturn(result);
    FixedPaymentCode fpc = retailOutletClient.updateFixedPaymentCode(TEST_ID, PARAMS);
    assertEquals(fpc, result);
  }

  @Test(expected = XenditException.class)
  public void updateFixedPaymentCode_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", "fake_id");
    when(this.requestClient.request(
            RequestResource.Method.PATCH,
            url,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            FixedPaymentCode.class))
        .thenThrow(new XenditException("Fixed payment code not found"));
    when(retailOutletClient.updateFixedPaymentCode("fake_id", PARAMS))
        .thenThrow(new XenditException("Fixed payment code not found"));
    retailOutletClient.updateFixedPaymentCode("fake_id", PARAMS);
  }


  @Test
  public void createPaymentCode_Success_IfParamsAreValidAndChannelCodeTransferred()
      throws XenditException {
    VALID_PC.setChannelCode("7ELEVEN");
    PARAMS.put("reference_id", TEST_REFERENCE_ID);
    PARAMS.put("channel_code", RegionalRetailOutletPaymentCode.ChannelCode.SEVENELEVEN);
    PARAMS.put("customer_name", TEST_CUSTOMER_NAME);
    PARAMS.put("amount", 10000);
    PARAMS.put("currency", RegionalRetailOutletPaymentCode.Currency.PHP);
    PARAMS.put("market", RegionalRetailOutletPaymentCode.Market.PH);

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            RegionalRetailOutletPaymentCode.class))
        .thenReturn(VALID_PC);
    when(retailOutletClient.createPaymentCode(PARAMS)).thenReturn(VALID_PC);
    RegionalRetailOutletPaymentCode pc = retailOutletClient.createPaymentCode(PARAMS);
    assertEquals(pc, VALID_PC);
    assertEquals(pc.getChannelCode().toString(), "7ELEVEN");

    // test channel code SEVENELEVENCLIQQ is transferred
    VALID_PC.setChannelCode("7ELEVEN_CLIQQ");
    PARAMS.put("channel_code", RegionalRetailOutletPaymentCode.ChannelCode.SEVENELEVENCLIQQ);

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            RegionalRetailOutletPaymentCode.class))
        .thenReturn(VALID_PC);
    when(retailOutletClient.createPaymentCode(PARAMS)).thenReturn(VALID_PC);
    pc = retailOutletClient.createPaymentCode(PARAMS);
    assertEquals(pc, VALID_PC);
    assertEquals(pc.getChannelCode().toString(), "7ELEVEN_CLIQQ");
  }

  @Test(expected = XenditException.class)
  public void createPaymentCode_ThrowsException_IfInvalidParams() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            RegionalRetailOutletPaymentCode.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    when(retailOutletClient.createPaymentCode(PARAMS))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    retailOutletClient.createPaymentCode(PARAMS);
  }

  @Test
  public void getPaymentCode_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            HEADERS,
            null,
            opt.getApiKey(),
            RegionalRetailOutletPaymentCode.class))
        .thenReturn(VALID_PC);
    when(retailOutletClient.getPaymentCode(TEST_ID)).thenReturn(VALID_PC);
    RegionalRetailOutletPaymentCode fpc = retailOutletClient.getPaymentCode(TEST_ID);
    assertEquals(fpc.getId(), VALID_PC.getId());
  }

  @Test(expected = XenditException.class)
  public void getPaymentCode_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", "fake_id");
    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            HEADERS,
            null,
            opt.getApiKey(),
            RegionalRetailOutletPaymentCode.class))
        .thenThrow(new XenditException("Fixed payment code not found"));
    when(retailOutletClient.getPaymentCode("fake_id"))
        .thenThrow(new XenditException("Fixed payment code not found"));
    retailOutletClient.getPaymentCode("fake_id");
  }

  @Test
  public void updatePaymentCode_Success_IfIdIsAvailable() throws XenditException {
    PARAMS.put("name", "New Name");
    String url = String.format("%s%s%s", URL, "/", TEST_ID);
    RegionalRetailOutletPaymentCode result =
        RegionalRetailOutletPaymentCode.builder()
            .id(TEST_ID)
            .referenceId(TEST_REFERENCE_ID)
            .customerName("New Name")
            .build();
    when(this.requestClient.request(
            RequestResource.Method.PATCH,
            url,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            RegionalRetailOutletPaymentCode.class))
        .thenReturn(result);
    when(retailOutletClient.updatePaymentCode(TEST_ID, PARAMS)).thenReturn(result);
    RegionalRetailOutletPaymentCode pc = retailOutletClient.updatePaymentCode(TEST_ID, PARAMS);
    assertEquals(pc, result);
  }

  @Test(expected = XenditException.class)
  public void updatePaymentCode_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", "fake_id");
    when(this.requestClient.request(
            RequestResource.Method.PATCH,
            url,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            RegionalRetailOutletPaymentCode.class))
        .thenThrow(new XenditException("Fixed payment code not found"));
    when(retailOutletClient.updatePaymentCode("fake_id", PARAMS))
        .thenThrow(new XenditException("Fixed payment code not found"));
    retailOutletClient.updatePaymentCode("fake_id", PARAMS);
  }

  @Test
  public void getPaymentByPaymentCodeId_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s%s", URL, "/", TEST_ID, "/payments");
    RegionalRetailOutletPayments[] result = {VALID_Payment};
    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            HEADERS,
            null,
            opt.getApiKey(),
            RegionalRetailOutletPayments[].class))
        .thenReturn(result);
    when(retailOutletClient.getPaymentsByPaymentCodeId(TEST_ID)).thenReturn(result);
    RegionalRetailOutletPayments[] payments =
  retailOutletClient.getPaymentsByPaymentCodeId(TEST_ID);
    assertEquals(payments[0].getId(), VALID_PC.getId());
  }

  @Test(expected = XenditException.class)
  public void getPaymentByPaymentCodeId_ThrowsException_IfIdIsNotAvailable() throws
  XenditException {
    String url = String.format("%s%s%s%s", URL, "/", "fake_id", "/payments");
    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            HEADERS,
            null,
            opt.getApiKey(),
            RegionalRetailOutletPaymentCode.class))
        .thenThrow(new XenditException("Fixed payment code not found"));
    when(retailOutletClient.getPaymentCode("fake_id"))
        .thenThrow(new XenditException("Fixed payment code not found"));
    retailOutletClient.getPaymentCode("fake_id");
  }
}
