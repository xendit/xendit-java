import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.RecurringPayment;

import java.util.HashMap;
import java.util.Map;

public class ExampleEditRecurringPayment {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      Map<String, Object> params = new HashMap<>();
      params.put("amount", 987654);
      params.put("interval", "WEEK");

      RecurringPayment recurringPayment = RecurringPayment.edit("5e2dd55ef8a4d24146f59775", params);
      System.out.println(recurringPayment.getAmount());
      System.out.println(recurringPayment.getInterval());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
