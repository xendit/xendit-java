import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.CreditCard;
import com.xendit.model.CreditCardCharge;

public class ExampleCaptureChargeCard {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      CreditCardCharge creditCardCharge = CreditCard.captureCharge("12345678", 55000);
      System.out.println(creditCardCharge.getId());
      System.out.println(creditCardCharge.getStatus());
      System.out.println(creditCardCharge.getCaptureAmount());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
