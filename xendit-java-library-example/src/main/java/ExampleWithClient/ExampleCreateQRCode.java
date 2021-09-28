package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.qrCode.QRCode;

public class ExampleCreateQRCode {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_...")
            .build();
    try {
      QRCode qrCode = xenditClient.qrCode.createQRCode("external_id", QRCode.QRCodeType.DYNAMIC, "https://webhook.site", 10000);
      System.out.println(qrCode.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
