package withClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardRefund;
import com.xenditclient.Xendit;
import com.xenditclient.XenditClient;
import com.xenditclient.creditCard.CreditCard;

public class ExampleCreateRefundCard {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
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
