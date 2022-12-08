package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.QRCode;

public class ExampleGetQRCodeByQRId {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .setApikey("xnd_development_...")
            .build();

    try {
      QRCode qrCode = xenditClient.qrCode.getQRCodeByQRId("qr_004a0427-b194-49be-9439-657ef77ee4f3");
      System.out.println(qrCode.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
