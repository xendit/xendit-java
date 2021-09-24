package com.xenditclient.qrCode;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.Xendit;
import com.xenditclient.network.NetworkClient;
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
      String externalId, QRCode.QRCodeType type, String callbackUrl, Number amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("type", type.toString());
    params.put("callback_url", callbackUrl);
    params.put("amount", amount);
    String url = String.format("%s%s", opt.getXenditURL(), "/qr_codes");
    return this.requestClient.request(
        RequestResource.Method.POST, url, params, opt.getApiKey(), QRCode.class);
  }

  public QRCode createQRCode(Map<String, Object> params) throws XenditException {
    return createQRCode(new HashMap<>(), params);
  }

  public QRCode createQRCode(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/qr_codes");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), QRCode.class);
  }

  public QRCode getQRCode(String externalId) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/qr_codes/", externalId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), QRCode.class);
  }
}
