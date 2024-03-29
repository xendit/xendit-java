package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.RecurringPayment;
import com.xendit.model.Invoice;

public class ExampleListRecurringPayments {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";


    try {
      Invoice[] invoices = RecurringPayment.getPaymentsById("615187d21b45cae990abde32");
      System.out.println(invoices.length);
      System.out.println(invoices[0].getRecurringPaymentId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
