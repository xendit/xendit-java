package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.creditCard.CreditCardCharge;
import com.xendit.Xendit;
import com.xendit.model.creditCard.CreditCard;

public class ExampleCreateChargeCard {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";

    try {
      CreditCardCharge creditCardCharge = CreditCard.createCharge("token_id", "external_id", 75000, "auth_id", "123", "lorem ipsum");
      System.out.println(creditCardCharge.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
