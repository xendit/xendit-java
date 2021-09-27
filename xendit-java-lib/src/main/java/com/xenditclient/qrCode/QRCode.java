package com.xenditclient.qrCode;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
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

  private static QRCodeClient qrCodeClient;

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
    QRCodeClient client = getClient();
    return client.createQRCode(externalId, type, callbackUrl, amount);
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
    QRCodeClient client = getClient();
    return client.createQRCode(headers, params);
  }

  /**
   * Get QR Code by external id
   *
   * @param externalId Merchant provided unique ID used to create QR code
   * @return QRCode
   * @throws XenditException XenditException
   */
  public static QRCode getQRCode(String externalId) throws XenditException {
    QRCodeClient client = getClient();
    return client.getQRCode(externalId);
  }

  /**
   * Its create a client for QRCode
   *
   * @return QRCodeClient
   */
  private static QRCodeClient getClient() {
    if (isApiKeyExist()) {
      if (qrCodeClient == null
          || !qrCodeClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return qrCodeClient =
            new QRCodeClient(
                Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (qrCodeClient == null
          || !qrCodeClient
              .getOpt()
              .getApiKey()
              .trim()
              .equals(Xendit.Opt.getApiKey().trim())) {
        return qrCodeClient = new QRCodeClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return qrCodeClient;
  }

  /**
   * check if api-key is exist or not
   *
   * @return boolean
   */
  private static boolean isApiKeyExist() {
    return Xendit.apiKey != null;
  }
}
