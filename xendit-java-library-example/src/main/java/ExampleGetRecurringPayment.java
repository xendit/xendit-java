import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.RecurringPayment;

public class ExampleGetRecurringPayment {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      RecurringPayment recurringPayment = RecurringPayment.get("5e2dd160f8a4d24146f5974c");
      System.out.println(recurringPayment.getId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
