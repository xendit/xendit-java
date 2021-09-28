package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.invoice.Invoice;

public class ExampleListRecurringPayments {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_...")
            .build();


    try {
      Invoice[] invoices = xenditClient.recurringPayment.getPaymentsById("61518f673280a45cdcf9852a");
      System.out.println(invoices.length);
      System.out.println(invoices[0].getRecurringPaymentId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
