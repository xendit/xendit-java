package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xenditclient.creditCard.CreditCardRefund;
import com.xendit.XenditClient;

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
