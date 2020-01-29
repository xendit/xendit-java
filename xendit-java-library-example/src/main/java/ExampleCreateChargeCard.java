import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.CreditCard;
import com.xendit.model.CreditCardCharge;

public class ExampleCreateChargeCard {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      CreditCardCharge creditCardCharge = CreditCard.createCharge("...", "test_id", 75000, "...", "123", "lorem ipsum");
      System.out.println(creditCardCharge.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
