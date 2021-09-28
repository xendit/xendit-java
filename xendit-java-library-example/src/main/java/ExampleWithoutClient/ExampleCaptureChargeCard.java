package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.creditCard.CreditCardCharge;
import com.xendit.Xendit;
import com.xendit.model.creditCard.CreditCard;

public class ExampleCaptureChargeCard {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";

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
