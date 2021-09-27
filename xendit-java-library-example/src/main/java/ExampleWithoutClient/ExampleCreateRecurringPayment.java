package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.recurringPayment.RecurringPayment;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateRecurringPayment {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

    try {
      Map<String , Object> params = new HashMap<>();
      params.put("external_id", "recurring_31451441");
      params.put("payer_email", "sample_email@xendit.co");
      params.put("interval", "MONTH");
      params.put("interval_count", 1);
      params.put("description", "Test desc");
      params.put("amount", 100000);
      params.put("currency", "IDR");

      RecurringPayment recurringPayment = RecurringPayment.create(params);
      System.out.println(recurringPayment.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
