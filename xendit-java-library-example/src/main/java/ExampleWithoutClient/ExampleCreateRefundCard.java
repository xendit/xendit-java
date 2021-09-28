package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.creditCard.CreditCardRefund;
import com.xendit.Xendit;
import com.xendit.model.creditCard.CreditCard;

public class ExampleCreateRefundCard {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";

    try {
      CreditCardRefund creditCardRefund = CreditCard.createRefund("1234567", 50000, "external_id");
      System.out.println(creditCardRefund.getStatus());
      System.out.println(creditCardRefund.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
