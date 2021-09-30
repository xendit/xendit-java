package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardCharge;
import com.xendit.Xendit;
import com.xendit.model.CreditCard;

public class ExampleGetChargeCard {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";

    try {
      CreditCardCharge creditCardCharge = CreditCard.getCharge("1234567");
      System.out.println(creditCardCharge.getId());
      System.out.println(creditCardCharge.getStatus());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
