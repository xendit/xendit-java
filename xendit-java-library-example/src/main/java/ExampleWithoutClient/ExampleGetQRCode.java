package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.QRCode;

public class ExampleGetQRCode {
  public static void main(String[] args) {
    // access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    // access static variable (same as old code )
    // Xendit.apiKey = "xnd_development_...";

    try {
      QRCode qrCode = QRCode.getQRCode("12");
      System.out.println(qrCode.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
