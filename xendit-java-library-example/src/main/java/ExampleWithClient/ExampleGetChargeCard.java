package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardCharge;
import com.xendit.XenditClient;

public class ExampleGetChargeCard {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .setApikey("xnd_development_...")
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
