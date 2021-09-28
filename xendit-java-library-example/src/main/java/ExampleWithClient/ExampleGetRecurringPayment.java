package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.recurringPayment.RecurringPayment;

public class ExampleGetRecurringPayment {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_...")
            .build();

    try {
      RecurringPayment recurringPayment = xenditClient.recurringPayment.get("61518f673280a45cdcf9852a");
      System.out.println(recurringPayment.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
