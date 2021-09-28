package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.recurringPayment.RecurringPayment;

import java.util.HashMap;
import java.util.Map;

public class ExampleEditRecurringPayment {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_...")
            .build();
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("amount", 987654);
      params.put("interval", "WEEK");

      RecurringPayment recurringPayment = xenditClient.recurringPayment.edit("61518f673280a45cdcf9852a", params);
      System.out.println(recurringPayment.getAmount());
      System.out.println(recurringPayment.getInterval());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
