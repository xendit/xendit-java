package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class QRCode {
  public enum QRCodeType {
    DYNAMIC,
    STATIC
  }

  public enum QRCodeStatus {
    ACTIVE,
    INACTIVE
  }

  @SerializedName("id")
  private String id;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("amount")
  private Number amount;

  @SerializedName("qr_string")
  private String qrString;

  @SerializedName("callback_url")
  private String callbackUrl;

  @SerializedName("type")
  private String type;

  @SerializedName("status")
  private String status;

  @SerializedName("created")
  private String created;

  @SerializedName("updated")
  private String updated;

  /**
   * Create QR Code with given parameters
   *
   * @param externalId An ID of your choice. Often it is unique identifier in your system like
   *     customer ID or order ID
   * @param type DYNAMIC or STATIC. DYNAMIC QR code contains the payment value upon scanning and can
   *     be paid multiple times. STATIC QR code requires end user to input the payment value and can
   *     be paid multiple times
   * @param callbackUrl The URL to receive payment notification after payment has been made by end
   *     user
   * @param amount The payment value embedded in the QR code, end user can only pay the specified
   *     amount after scanning the QR code. For STATIC QR code, amount parameter will be ignored.
   * @return QRCode
   * @throws XenditException XenditException
   */
  public static QRCode createQRCode(
      String externalId, QRCodeType type, String callbackUrl, Number amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("type", type.toString());
    params.put("callback_url", callbackUrl);
    params.put("amount", amount);
    String url = String.format("%s%s", Xendit.getUrl(), "/qr_codes");

    return Xendit.requestClient.request(RequestResource.Method.POST, url, params, QRCode.class);
  }

  /**
   * Create QR Code with parameters in a HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-qr-code
   * @return QRCode
   * @throws XenditException XenditException
   */
  public static QRCode createQRCode(Map<String, Object> params) throws XenditException {
    return createQRCode(new HashMap<>(), params);
  }

  /**
   * Create QR Code with headers and parameters in a HashMap
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#create-qr-code
   * @return QRCode
   * @throws XenditException XenditException
   */
  public static QRCode createQRCode(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/qr_codes");
    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, headers, params, QRCode.class);
  }

  /**
   * Get QR Code by external id
   *
   * @param externalId Merchant provided unique ID used to create QR code
   * @return QRCode
   * @throws XenditException XenditException
   */
  public static QRCode getQRCode(String externalId) throws XenditException {
    String url = String.format("%s%s%s", Xendit.getUrl(), "/qr_codes/", externalId);
    return Xendit.requestClient.request(RequestResource.Method.GET, url, null, QRCode.class);
  }
}
