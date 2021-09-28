package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.recurringPayment.RecurringPayment;

public class ExampleStopRecurringPayment {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_...")
            .build();

    try {
      RecurringPayment recurringPayment = xenditClient.recurringPayment.stop("615187d21b45cae990abde32");
      System.out.println(recurringPayment.getId());
      System.out.println(recurringPayment.getStatus());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
