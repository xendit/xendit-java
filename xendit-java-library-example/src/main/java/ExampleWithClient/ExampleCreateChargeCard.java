package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.creditCard.CreditCardCharge;
import com.xendit.XenditClient;

public class ExampleCreateChargeCard {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_...")
            .build();


    try {
      CreditCardCharge creditCardCharge = xenditClient.creditCard.createCharge("token_id", "external_id", 75000, "auth_id", "123", "lorem ipsum");
      System.out.println(creditCardCharge.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
