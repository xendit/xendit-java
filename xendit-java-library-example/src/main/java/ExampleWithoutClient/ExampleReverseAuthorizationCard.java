package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardReverseAuth;
import com.xendit.Xendit;
import com.xendit.model.CreditCard;

public class ExampleReverseAuthorizationCard {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";

    try {
      CreditCardReverseAuth creditCardReverseAuth = CreditCard.reverseAuthorization("1234567","external_id");
      System.out.println(creditCardReverseAuth.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
