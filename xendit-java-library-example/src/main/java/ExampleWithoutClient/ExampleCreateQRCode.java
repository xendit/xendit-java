package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.QRCode;

public class ExampleCreateQRCode {
  public static void main(String[] args) {
    // access key with Option
    Xendit.Opt.setApiKey("xnd_development_HhEaDg8BL32lxtHdNaABWgQ3LvnIEtFtwXIDdK1M9v2imuMwpLWPyAJNMVKq58");

    // access static variable (same as old code )
    // Xendit.apiKey = "xnd_development_...";

    try {
      QRCode qrCode = QRCode.createQRCode("12", QRCode.QRCodeType.DYNAMIC, "https://webhook.site", 10000);
      System.out.println(qrCode.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
