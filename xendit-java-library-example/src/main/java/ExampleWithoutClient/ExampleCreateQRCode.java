package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.QRCode;

public class ExampleCreateQRCode {
  public static void main(String[] args) {
    // access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    // access static variable (same as old code )
    // Xendit.apiKey = "xnd_development_...";

    try {
      QRCode qrCode = QRCode.createQRCode("12", QRCode.QRCodeType.DYNAMIC, "IDR", 10000);
      System.out.println(qrCode.getId());

      // You can find this value in the response headers, under Request-ID. You can
      // use Request-ID to find logs in API Logs in Dashboard
      // (https://dashboard.xendit.co/api-logs).
      System.out.println(qrCode.getResponseHeaders().get("Request-Id"));
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
