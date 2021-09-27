package withClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardCharge;
import com.xenditclient.Xendit;
import com.xenditclient.XenditClient;
import com.xenditclient.creditCard.CreditCard;

public class ExampleGetChargeCard {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
            .build();


    try {
      CreditCardCharge creditCardCharge = xenditClient.creditCard.getCharge("1234567");
      System.out.println(creditCardCharge.getId());
      System.out.println(creditCardCharge.getStatus());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
