package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.recurringPayment.RecurringPayment;

import java.util.HashMap;
import java.util.Map;

public class ExampleEditRecurringPayment {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

    try {
      Map<String, Object> params = new HashMap<>();
      params.put("amount", 987654);
      params.put("interval", "WEEK");

      RecurringPayment recurringPayment = RecurringPayment.edit("615187d21b45cae990abde32", params);
      System.out.println(recurringPayment.getAmount());
      System.out.println(recurringPayment.getInterval());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
