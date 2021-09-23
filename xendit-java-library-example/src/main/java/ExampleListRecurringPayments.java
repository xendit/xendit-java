import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.RecurringPayment;
import com.xenditclient.invoice.Invoice;

public class ExampleListRecurringPayments {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      Invoice[] invoices = RecurringPayment.getPaymentsById("5e2dd55ef8a4d24146f59775");
      System.out.println(invoices.length);
      System.out.println(invoices[0].getRecurringPaymentId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
