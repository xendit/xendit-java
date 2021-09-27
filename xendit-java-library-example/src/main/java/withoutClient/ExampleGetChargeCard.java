package withoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardCharge;
import com.xenditclient.Xendit;
import com.xenditclient.creditCard.CreditCard;

public class ExampleGetChargeCard {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

    try {
      CreditCardCharge creditCardCharge = CreditCard.getCharge("1234567");
      System.out.println(creditCardCharge.getId());
      System.out.println(creditCardCharge.getStatus());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
