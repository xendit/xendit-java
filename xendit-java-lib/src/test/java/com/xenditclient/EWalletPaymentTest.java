package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.ewallet.EWalletClient;
import com.xendit.model.ewallet.EWalletLinkajaItem;
import com.xendit.model.ewallet.EWalletPayment;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class EWalletPaymentTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/ewallets");
  private static String TEST_ID = "test_id";
  private static String TEST_EXTERNAL_ID = "test_external_id";
  private static String TEST_PHONE = "081298498259";
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  EWalletClient eWalletClient = mock(EWalletClient.class);
  private static EWalletPayment VALID_PAYMENT = new EWalletPayment();

  @Before
  public void initMocks() {
    VALID_PAYMENT.setId(TEST_ID);
    VALID_PAYMENT.setExternalId(TEST_EXTERNAL_ID);

    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);

    PARAMS.clear();
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("phone", TEST_PHONE);
  }

  @Test
  public void createLinkajaPayment_Success_IfParamsAreValid() throws XenditException {
    Number amount = 10000;
    String callbackUrl = "https://yourwebsite.com/callback";
    String redirectUrl = "https://yourwebsite.com/order/123";
    EWalletLinkajaItem item1 =
        EWalletLinkajaItem.builder().id("test").name("Phone Case").price(10000).quantity(1).build();
    EWalletLinkajaItem item2 =
        EWalletLinkajaItem.builder().id("test1").name("Charger").price(20000).quantity(1).build();
    EWalletLinkajaItem[] array = new EWalletLinkajaItem[] {item1, item2};
    PARAMS.put("amount", amount);
    PARAMS.put("items", array);
    PARAMS.put("ewallet_type", EWalletPayment.EWalletType.LINKAJA);
    PARAMS.put("callback_url", callbackUrl);
    PARAMS.put("redirect_url", redirectUrl);

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            EWalletPayment.class))
        .thenReturn(VALID_PAYMENT);
    when(eWalletClient.createLinkajaPayment(
            TEST_EXTERNAL_ID, amount, TEST_PHONE, array, callbackUrl, redirectUrl))
        .thenReturn(VALID_PAYMENT);
    EWalletPayment result =
        eWalletClient.createLinkajaPayment(
            TEST_EXTERNAL_ID, amount, TEST_PHONE, array, callbackUrl, redirectUrl);

    assertEquals(result, VALID_PAYMENT);
  }

  @Test
  public void createOvoPayment_Success_IfParamsAreValid() throws XenditException {
    PARAMS.put("amount", 4444);
    PARAMS.put("ewallet_type", EWalletPayment.EWalletType.OVO);

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            EWalletPayment.class))
        .thenReturn(VALID_PAYMENT);
    when(eWalletClient.createOvoPayment(TEST_EXTERNAL_ID, 4444, TEST_PHONE))
        .thenReturn(VALID_PAYMENT);
    EWalletPayment result = eWalletClient.createOvoPayment(TEST_EXTERNAL_ID, 4444, TEST_PHONE);
    assertEquals(result, VALID_PAYMENT);
  }

  @Test
  public void createDanaPayment_Success_IfParamsAreValid() throws XenditException {
    Number amount = 100000;
    String expirationDate = "2020-02-20T00:00:00.000Z";
    String callbackUrl = "https://my-shop.com/callbacks";
    String redirectUrl = "https://my-shop.com/home";
    PARAMS.put("amount", amount);
    PARAMS.put("expiration_date", expirationDate);
    PARAMS.put("callback_url", callbackUrl);
    PARAMS.put("redirect_url", redirectUrl);
    PARAMS.put("ewallet_type", EWalletPayment.EWalletType.DANA);

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            EWalletPayment.class))
        .thenReturn(VALID_PAYMENT);
    when(eWalletClient.createDanaPayment(
            TEST_EXTERNAL_ID, amount, TEST_PHONE, expirationDate, callbackUrl, redirectUrl))
        .thenReturn(VALID_PAYMENT);
    EWalletPayment result =
        eWalletClient.createDanaPayment(
            TEST_EXTERNAL_ID, amount, TEST_PHONE, expirationDate, callbackUrl, redirectUrl);

    assertEquals(result.getId(), VALID_PAYMENT.getId());
  }

  @Test
  public void getPaymentStatus_Success_IfIdIsAvailable() throws XenditException {
    String url =
        String.format(
            "%s%s%s%s%s",
            URL,
            "/?external_id=",
            TEST_EXTERNAL_ID,
            "&ewallet_type=",
            EWalletPayment.EWalletType.DANA);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), EWalletPayment.class))
        .thenReturn(VALID_PAYMENT);
    when(eWalletClient.getPaymentStatus(TEST_EXTERNAL_ID, EWalletPayment.EWalletType.DANA))
        .thenReturn(VALID_PAYMENT);
    EWalletPayment eWalletPayment =
        eWalletClient.getPaymentStatus(TEST_EXTERNAL_ID, EWalletPayment.EWalletType.DANA);

    assertEquals(eWalletPayment, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void getPaymentStatus_ThrowsException_IfIdIsNotAvailable() throws XenditException {
    String url =
        String.format(
            "%s%s%s", URL, "/?external_id=fake_id&ewallet_type=", EWalletPayment.EWalletType.DANA);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), EWalletPayment.class))
        .thenThrow(new XenditException("Payment not found"));
    when(eWalletClient.getPaymentStatus("fake_id", EWalletPayment.EWalletType.DANA))
        .thenThrow(new XenditException("Payment not found"));

    eWalletClient.getPaymentStatus("fake_id", EWalletPayment.EWalletType.DANA);
  }
}
