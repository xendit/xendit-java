import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.QRCode;

public class ExampleGetQRCode {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      QRCode qrCode = QRCode.getQRCode("external_id_1");
      System.out.println(qrCode.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
