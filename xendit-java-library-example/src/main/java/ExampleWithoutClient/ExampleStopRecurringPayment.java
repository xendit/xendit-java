package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.recurringPayment.RecurringPayment;

public class ExampleStopRecurringPayment {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";

    try {
      RecurringPayment recurringPayment = RecurringPayment.stop("615187d21b45cae990abde32");
      System.out.println(recurringPayment.getId());
      System.out.println(recurringPayment.getStatus());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
