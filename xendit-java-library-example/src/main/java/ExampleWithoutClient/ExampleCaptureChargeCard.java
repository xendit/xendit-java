package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.creditCard.CreditCardCharge;
import com.xendit.Xendit;
import com.xenditclient.creditCard.CreditCard;

public class ExampleCaptureChargeCard {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

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
