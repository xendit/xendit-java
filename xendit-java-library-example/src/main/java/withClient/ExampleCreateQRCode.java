package withClient;

import com.xendit.exception.XenditException;
import com.xenditclient.XenditClient;
import com.xenditclient.qrCode.QRCode;

public class ExampleCreateQRCode {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
            .build();
    try {
      QRCode qrCode = xenditClient.qrCode.createQRCode("external_id", QRCode.QRCodeType.DYNAMIC, "https://webhook.site", 10000);
      System.out.println(qrCode.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
