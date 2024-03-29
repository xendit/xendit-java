package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardCharge;
import com.xendit.XenditClient;

public class ExampleCreateAuthorizationCard {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .setApikey("xnd_development_...")
            .build();

    try {
      CreditCardCharge creditCardCharge = xenditClient.creditCard.createAuthorization("token_id", "external_id", 75000, "auth_id", "123", false);
      System.out.println(creditCardCharge.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
