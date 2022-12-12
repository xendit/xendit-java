package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.QRCode;

public class ExampleGetQRCodeByQRId {
  public static void main(String[] args) {
    // access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    // access static variable (same as old code )
    // Xendit.apiKey = "xnd_development_...";

    try {
      QRCode qrCode = QRCode.getQRCodeByQRId("qr_004a0427-b194-49be-9439-657ef77ee4f3");
      System.out.println(qrCode.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
