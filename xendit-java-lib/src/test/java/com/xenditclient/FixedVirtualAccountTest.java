package com.xenditclient;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.model.virtualAccount.FixedVirtualAccount;
import com.xendit.model.virtualAccount.FixedVirtualAccountClient;
import com.xendit.model.virtualAccount.FixedVirtualAccountPayment;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class FixedVirtualAccountTest {
  private static Map<String, String> HEADERS = new HashMap<>();
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static String URL =
      String.format("%s%s", Xendit.Opt.getXenditURL(), "/callback_virtual_accounts");
  private static String TEST_ID = "test_id";
  private static String TEST_EXTERNAL_ID = "test_external_id";
  private static BankCode TEST_BANK_CODE = BankCode.BNI;
  private static String TEST_NAME = "John Doe";
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  FixedVirtualAccountClient fixedVirtualAccountClient = mock(FixedVirtualAccountClient.class);
  private static FixedVirtualAccount VALID_ACCOUNT = new FixedVirtualAccount();

  @Before
  public void initMocks() {
    PARAMS.clear();

    VALID_ACCOUNT.setId(TEST_ID);

    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("bank_code", TEST_BANK_CODE);
    PARAMS.put("name", TEST_NAME);
  }

  @Test
  public void createClosed_Success_IfParamsAreValid() throws XenditException {
    PARAMS.put("expected_amount", 200000000);

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            FixedVirtualAccount.class))
        .thenReturn(VALID_ACCOUNT);
    when(fixedVirtualAccountClient.createClosed(PARAMS)).thenReturn(VALID_ACCOUNT);
    FixedVirtualAccount fixedVirtualAccount = fixedVirtualAccountClient.createClosed(PARAMS);

    assertEquals(fixedVirtualAccount, VALID_ACCOUNT);
  }

  @Test(expected = XenditException.class)
  public void createClosed_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.put("expected_amount", 50000000001L);

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            FixedVirtualAccount.class))
        .thenThrow(new XenditException("Maximum amount is 50000000000"));
    when(fixedVirtualAccountClient.createClosed(PARAMS))
        .thenThrow(new XenditException("Maximum amount is 50000000000"));
    fixedVirtualAccountClient.createClosed(PARAMS);
  }

  @Test
  public void createOpen_Success_IfParamsAreValid() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            FixedVirtualAccount.class))
        .thenReturn(VALID_ACCOUNT);
    when(fixedVirtualAccountClient.createOpen(PARAMS)).thenReturn(VALID_ACCOUNT);
    FixedVirtualAccount fixedVirtualAccount = fixedVirtualAccountClient.createOpen(PARAMS);

    assertEquals(fixedVirtualAccount, VALID_ACCOUNT);
  }

  @Test(expected = XenditException.class)
  public void createOpen_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.put("bank_code", "XYZ");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            FixedVirtualAccount.class))
        .thenThrow(new XenditException("That bank code is not currently supported"));
    when(fixedVirtualAccountClient.createOpen(PARAMS))
        .thenThrow(new XenditException("That bank code is not currently supported"));
    fixedVirtualAccountClient.createOpen(PARAMS);
  }

  @Test
  public void getAvailableBanks_Success() throws XenditException {
    String url =
        String.format("%s%s", Xendit.Opt.getXenditURL(), "/available_virtual_account_banks");
    AvailableBank[] availableBanks = new AvailableBank[] {};

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), AvailableBank[].class))
        .thenReturn(availableBanks);
    when(fixedVirtualAccountClient.getAvailableBanks()).thenReturn(availableBanks);
    AvailableBank[] result = fixedVirtualAccountClient.getAvailableBanks();

    assertArrayEquals(availableBanks, result);
  }

  @Test
  public void getFixedVA_Success_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s%s", URL, "/", TEST_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            new HashMap<>(),
            null,
            opt.getApiKey(),
            FixedVirtualAccount.class))
        .thenReturn(VALID_ACCOUNT);
    when(fixedVirtualAccountClient.getFixedVA(new HashMap<>(), TEST_ID)).thenReturn(VALID_ACCOUNT);
    FixedVirtualAccount fixedVirtualAccount =
        fixedVirtualAccountClient.getFixedVA(new HashMap<>(), TEST_ID);

    assertEquals(fixedVirtualAccount, VALID_ACCOUNT);
  }

  @Test(expected = XenditException.class)
  public void getFixedVA_ThrowsException_IfIdIsAvailable() throws XenditException {
    String url = String.format("%s%s", URL, "/fake_id");

    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            new HashMap<>(),
            null,
            opt.getApiKey(),
            FixedVirtualAccount.class))
        .thenThrow(new XenditException("Callback virtual account not found"));
    when(fixedVirtualAccountClient.getFixedVA("fake_id"))
        .thenThrow(new XenditException("Callback virtual account not found"));
    fixedVirtualAccountClient.getFixedVA("fake_id");
  }

  @Test
  public void update_Success_IfIdIsAvailable() throws XenditException {
    FixedVirtualAccount fixedVirtualAccount = new FixedVirtualAccount();
    fixedVirtualAccount.setId(TEST_ID);
    fixedVirtualAccount.setIsSingleUse(true);

    Map<String, Object> params = new HashMap<>();
    params.put("is_single_use", true);
    String url = String.format("%s%s%s", URL, "/", TEST_ID);

    when(this.requestClient.request(
            RequestResource.Method.PATCH, url, params, opt.getApiKey(), FixedVirtualAccount.class))
        .thenReturn(fixedVirtualAccount);
    when(fixedVirtualAccountClient.update(TEST_ID, params)).thenReturn(fixedVirtualAccount);
    FixedVirtualAccount result = fixedVirtualAccountClient.update(TEST_ID, params);

    assertEquals(result, fixedVirtualAccount);
  }

  @Test(expected = XenditException.class)
  public void update_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    Map<String, Object> params = new HashMap<>();
    String url = String.format("%s%s", URL, "/fake_id");

    when(this.requestClient.request(
            RequestResource.Method.PATCH, url, params, opt.getApiKey(), FixedVirtualAccount.class))
        .thenThrow(new XenditException("Could not find callback virtual account"));
    when(fixedVirtualAccountClient.update("fake_id", params))
        .thenThrow(new XenditException("Could not find callback virtual account"));
    fixedVirtualAccountClient.update("fake_id", params);
  }

  @Test
  public void getPayment_Success_IfIdIsAvailable() throws XenditException {
    FixedVirtualAccountPayment fixedVirtualAccountPayment =
        FixedVirtualAccountPayment.builder().id("test_id").build();
    String url =
        String.format(
            "%s%s",
            Xendit.Opt.getXenditURL(), "/callback_virtual_account_payments/payment_id=test_id");

    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            null,
            opt.getApiKey(),
            FixedVirtualAccountPayment.class))
        .thenReturn(fixedVirtualAccountPayment);
    when(fixedVirtualAccountClient.getPayment("test_id")).thenReturn(fixedVirtualAccountPayment);
    FixedVirtualAccountPayment result = fixedVirtualAccountClient.getPayment("test_id");

    assertEquals(result, fixedVirtualAccountPayment);
  }
}
