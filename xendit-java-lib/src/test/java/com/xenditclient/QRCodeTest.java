package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.QRCode;
import com.xendit.model.QRCodeClient;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class QRCodeTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/qr_codes");
  private static String TEST_ID = "test_id";
  private static String TEST_REFERENCE_ID = "test_reference_id";
  private static String TEST_QR_STRING = "this_is_qr_string";
  private static String TEST_QR_TYPE = QRCode.QRCodeType.DYNAMIC.toString();
  private static String TEST_QR_STATUS = QRCode.QRCodeStatus.ACTIVE.toString();
  private static String TEST_QR_CURRENCY = "IDR";
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  private static QRCode VALID_PAYMENT =
      QRCode.builder()
          .id(TEST_ID)
          .externalId(TEST_REFERENCE_ID)
          .currency(TEST_QR_CURRENCY)
          .qrString(TEST_QR_STRING)
          .status(TEST_QR_STATUS)
          .amount(10000)
          .build();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  QRCodeClient qrCodeClient = mock(QRCodeClient.class);

  @Before
  public void initMocks() {
    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);

    PARAMS.clear();
  }

  private void initCreateParams() {
    PARAMS.put("reference_id", TEST_REFERENCE_ID);
    PARAMS.put("type", TEST_QR_TYPE);
    PARAMS.put("currency", TEST_QR_CURRENCY);
    PARAMS.put("amount", 10000);
  }

  private void initCreateHeaders() {
    HEADERS.put("api-version", "2022-07-31");
  }

  @Test
  public void createQRCode_Success_IfMethodCalledCorrectly() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), QRCode.class))
        .thenReturn(VALID_PAYMENT);
    when(qrCodeClient.createQRCode(
            TEST_REFERENCE_ID, QRCode.QRCodeType.DYNAMIC, TEST_QR_CURRENCY, 10000))
        .thenReturn(VALID_PAYMENT);
    QRCode qrCode =
        qrCodeClient.createQRCode(
            TEST_REFERENCE_ID, QRCode.QRCodeType.DYNAMIC, TEST_QR_CURRENCY, 10000);

    assertEquals(qrCode, VALID_PAYMENT);
  }

  @Test
  public void createQRCode_Success_IfParamsIsValid() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
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
            HEADERS,
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
    String url = String.format("%s/%s", URL, TEST_REFERENCE_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), QRCode.class))
        .thenReturn(VALID_PAYMENT);
    when(qrCodeClient.getQRCode(TEST_REFERENCE_ID)).thenReturn(VALID_PAYMENT);
    QRCode qrCode = qrCodeClient.getQRCode(TEST_REFERENCE_ID);

    assertEquals(qrCode, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void GetQRCode_ThrowsException_OnExternalIDNotFound() throws XenditException {
    String NOT_VALID_REFERENCE_ID = "not_valid_reference_id";
    String url = String.format("%s/%s", URL, NOT_VALID_REFERENCE_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), QRCode.class))
        .thenThrow(new XenditException("not found"));
    when(qrCodeClient.getQRCode(NOT_VALID_REFERENCE_ID)).thenThrow(new XenditException("not found"));

    qrCodeClient.getQRCode(NOT_VALID_REFERENCE_ID);
  }

  @Test
  public void GetQRCode_Success_WithQRId() throws XenditException {
    String url = String.format("%s/%s", URL, TEST_ID);

    Map<String, Object> headers = new HashMap<>();
    headers.put("api-version", "2022-07-31");

    when(this.requestClient.request(
            RequestResource.Method.GET, url, headers, opt.getApiKey(), QRCode.class))
        .thenReturn(VALID_PAYMENT);
    when(qrCodeClient.getQRCodeByQRId(TEST_ID)).thenReturn(VALID_PAYMENT);
    QRCode qrCode = qrCodeClient.getQRCodeByQRId(TEST_ID);

    assertEquals(qrCode, VALID_PAYMENT);
  }

  @Test(expected = XenditException.class)
  public void GetQRCode_ThrowsException_OnExternalIDNotFound() throws XenditException {
    String NOT_VALID_QR_ID = "not_valid_qr_id";
    String url = String.format("%s/%s", URL, NOT_VALID_QR_ID);

    Map<String, Object> headers = new HashMap<>();
    headers.put("api-version", "2022-07-31");

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), QRCode.class))
        .thenThrow(new XenditException("not found"));
    when(qrCodeClient.getQRCodeByQRId(NOT_VALID_QR_ID)).thenThrow(new XenditException("not found"));

    qrCodeClient.getQRCodeByQRId(NOT_VALID_QR_ID);
  }
}
