import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.CreditCard;
import com.xendit.model.CreditCardRefund;

public class ExampleCreateRefundCard {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      CreditCardRefund creditCardRefund = CreditCard.createRefund("1234567", 50000, "external_id");
      System.out.println(creditCardRefund.getStatus());
      System.out.println(creditCardRefund.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}