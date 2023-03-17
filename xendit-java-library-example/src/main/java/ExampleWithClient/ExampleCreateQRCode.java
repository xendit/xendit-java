package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.XenditClient;
import com.xendit.model.QRCode;

public class ExampleCreateQRCode {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .setApikey("xnd_development_...")
            .build();
    try {
      QRCode qrCode = xenditClient.qrCode.createQRCode("external_id", QRCode.QRCodeType.DYNAMIC, "IDR", 10000);
      System.out.println(qrCode.getId());

      //You can find this value in the response headers, under Request-ID. You can use Request-ID to find logs in API Logs in Dashboard (https://dashboard.xendit.co/api-logs).
      System.out.println(Xendit.getResponseHeaders().get("Request-Id"));
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
