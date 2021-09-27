package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.creditCard.CreditCardCharge;
import com.xendit.Xendit;
import com.xenditclient.creditCard.CreditCard;

public class ExampleCreateChargeCard {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

    try {
      CreditCardCharge creditCardCharge = CreditCard.createCharge("token_id", "external_id", 75000, "auth_id", "123", "lorem ipsum");
      System.out.println(creditCardCharge.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
