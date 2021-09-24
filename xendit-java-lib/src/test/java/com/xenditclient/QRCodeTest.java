package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.network.NetworkClient;
import com.xenditclient.qrCode.QRCode;
import com.xenditclient.qrCode.QRCodeClient;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class QRCodeTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/qr_codes");
  private static String TEST_ID = "test_id";
  private static String TEST_EXTERNAL_ID = "test_external_id";
  private static String TEST_QR_STRING = "this_is_qr_string";
  private static String TEST_CALLBACK_URL = "https://callback.url";
  private static String TEST_QR_TYPE = QRCode.QRCodeType.DYNAMIC.toString();
  private static String TEST_QR_STATUS = QRCode.QRCodeStatus.ACTIVE.toString();
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  private static QRCode VALID_PAYMENT = new QRCode();
  NetworkClient requestClient = mock(com.xenditclient.network.BaseRequest.class);
  Xendit.Option opt = mock(com.xenditclient.Xendit.Option.class);
  QRCodeClient qrCodeClient = mock(QRCodeClient.class);

  @Before
  public void initMocks() {
    VALID_PAYMENT.setId(TEST_ID);
    VALID_PAYMENT.setExternalId(TEST_EXTERNAL_ID);
    VALID_PAYMENT.setQrString(TEST_QR_STRING);
    VALID_PAYMENT.setCallbackUrl(TEST_CALLBACK_URL);
    VALID_PAYMENT.setStatus(TEST_QR_STATUS);
    VALID_PAYMENT.setAmount(10000);

    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);

    PARAMS.clear();
  }

  private void initCreateParams() {
    PARAMS.put("external_id", TEST_EXTERNAL_ID);
    PARAMS.put("type", TEST_QR_TYPE);
    PARAMS.put("callback_url", TEST_CALLBACK_URL);
    PARAMS.put("amount", 10000);
  }

  @Test
  public void createQRCode_Success_IfMethodCalledCorrectly() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST, URL, PARAMS, opt.getApiKey(), QRCode.class))
        .thenReturn(VALID_PAYMENT);
    when(qrCodeClient.createQRCode(
            TEST_EXTERNAL_ID, QRCode.QRCodeType.DYNAMIC, TEST_CALLBACK_URL, 10000))
        .thenReturn(VALID_PAYMENT);
    QRCode qrCode =
        qrCodeClient.createQRCode(
            TEST_EXTERNAL_ID, QRCode.QRCodeType.DYNAMIC, TEST_CALLBACK_URL, 10000);

    assertEquals(qrCode, VALID_PAYMENT);
  }

  @Test
  public void createQRCode_Success_IfParamsIsValid() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            new HashMap<>(),
            PARAMS,
            opt.getApiKey(),
            QRCode.class))
        .thenReturn(VALID_PAYMENT);
    when(qrCodeClient.createQRCode(PARAMS)).thenReturn(VALID_PAYMENT);
    QRCode qrCode = qrCodeClient.createQRCode(PARAMS);

    assertEquals(qrCode, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void createQRCode_ThrowsException_IfParamsIsInvalid() throws XenditException {
    initCreateParams();
    PARAMS.put("type", "NOT_DYNAMIC");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            new HashMap<>(),
            PARAMS,
            opt.getApiKey(),
            QRCode.class))
        .thenThrow(new XenditException("QR Code type is invalid"));
    when(qrCodeClient.createQRCode(PARAMS))
        .thenThrow(new XenditException("QR Code type is invalid"));

    qrCodeClient.createQRCode(PARAMS);
  }

  @Test
  public void createQRCode_Success_WithHeaderProvided() throws XenditException {
    initCreateParams();
    HEADERS.put("for-user-id", "user-id");

    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), QRCode.class))
        .thenReturn(VALID_PAYMENT);
    when(qrCodeClient.createQRCode(HEADERS, PARAMS)).thenReturn(VALID_PAYMENT);

    QRCode qrCode = qrCodeClient.createQRCode(HEADERS, PARAMS);

    assertEquals(qrCode, VALID_PAYMENT);
  }

  @Test
  public void GetQRCode_Success_WithExternalId() throws XenditException {
    String url = String.format("%s/%s", URL, TEST_EXTERNAL_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), QRCode.class))
        .thenReturn(VALID_PAYMENT);
    when(qrCodeClient.getQRCode(TEST_EXTERNAL_ID)).thenReturn(VALID_PAYMENT);
    QRCode qrCode = qrCodeClient.getQRCode(TEST_EXTERNAL_ID);

    assertEquals(qrCode, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void GetQRCode_ThrowsException_OnExternalIDNotFound() throws XenditException {
    String NOT_VALID_EXTERNAL_ID = "not_valid_external_id";
    String url = String.format("%s/%s", URL, NOT_VALID_EXTERNAL_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), QRCode.class))
        .thenThrow(new XenditException("not found"));
    when(qrCodeClient.getQRCode(NOT_VALID_EXTERNAL_ID)).thenThrow(new XenditException("not found"));

    qrCodeClient.getQRCode(NOT_VALID_EXTERNAL_ID);
  }
}
