package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardRefund;
import com.xendit.XenditClient;

public class ExampleCreateRefundCard {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .setApikey("xnd_development_...")
            .build();


    try {
      CreditCardRefund creditCardRefund = xenditClient.creditCard.createRefund("1234567", 50000, "external_id");
      System.out.println(creditCardRefund.getStatus());
      System.out.println(creditCardRefund.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
