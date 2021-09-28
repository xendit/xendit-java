package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.invoice.Invoice;

public class ExampleExpireInvoice {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            Invoice invoice = Invoice.expire("614b035fc3153d1712c0a902");
            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
