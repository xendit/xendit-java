import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.RecurringPayment;

public class ExampleResumeRecurringPayment {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      RecurringPayment recurringPayment = RecurringPayment.resume("5e2dd55ef8a4d24146f59775");
      System.out.println(recurringPayment.getStatus());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
