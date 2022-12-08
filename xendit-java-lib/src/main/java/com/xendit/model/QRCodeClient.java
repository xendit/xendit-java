package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QRCodeClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public QRCode createQRCode(
      String referenceId, QRCode.QRCodeType type, String currency, Number amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("reference_id", referenceId);
    params.put("type", type.toString());
    params.put("currency", currency);
    params.put("amount", amount);

    //Use new API version
    Map<String, String> headers = new HashMap<>();
    headers.put("api-version", "2022-07-31");
    String url = String.format("%s%s", opt.getXenditURL(), "/qr_codes");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), QRCode.class);
  }

  public QRCode createQRCode(Map<String, Object> params) throws XenditException {
    return createQRCode(new HashMap<>(), params);
  }

  public QRCode createQRCode(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/qr_codes");
    //Use new API version
    headers.put("api-version", "2022-07-31");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), QRCode.class);
  }

  public QRCode getQRCode(String externalId) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/qr_codes/", externalId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), QRCode.class);
  }

  public QRCode getQRCodeByQRId(String qrId) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/qr_codes/", qrId);

    Map<String, String> headers = new HashMap<>();
    headers.put("api-version", "2022-07-31");

    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), QRCode.class);
  }
}
