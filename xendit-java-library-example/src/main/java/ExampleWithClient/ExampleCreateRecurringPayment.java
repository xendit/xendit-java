package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.RecurringPayment;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateRecurringPayment {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .setApikey("xnd_development_...")
            .build();

    try {
      Map<String , Object> params = new HashMap<>();
      params.put("external_id", "recurring_31451441");
      params.put("payer_email", "sample_email@xendit.co");
      params.put("interval", "MONTH");
      params.put("interval_count", 1);
      params.put("description", "Test desc");
      params.put("amount", 100000);
      params.put("currency", "IDR");

      RecurringPayment recurringPayment = xenditClient.recurringPayment.create(params);
      System.out.println(recurringPayment.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
