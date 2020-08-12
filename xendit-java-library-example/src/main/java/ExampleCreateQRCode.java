import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.QRCode;

public class ExampleCreateQRCode {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      QRCode qrCode = QRCode.createQRCode("external_id", QRCode.QRCodeType.DYNAMIC, "https://webhook.site", 10000);
      System.out.println(qrCode.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
