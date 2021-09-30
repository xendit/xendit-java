package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.RecurringPayment;

public class ExampleGetRecurringPayment {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";

    try {
      RecurringPayment recurringPayment = RecurringPayment.get("615187d21b45cae990abde32");
      System.out.println(recurringPayment.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
