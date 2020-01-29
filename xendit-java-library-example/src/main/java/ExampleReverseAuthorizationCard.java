import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.CreditCard;
import com.xendit.model.CreditCardReverseAuth;

public class ExampleReverseAuthorizationCard {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      CreditCardReverseAuth creditCardReverseAuth = CreditCard.reverseAuthorization("1234567","external_id");
      System.out.println(creditCardReverseAuth.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
