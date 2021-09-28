package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.invoice.Invoice;

public class ExampleExpireInvoice {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();

        try {
            Invoice invoice = xenditClient.invoice.expire("614b035fc3153d1712c0a902");
            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
