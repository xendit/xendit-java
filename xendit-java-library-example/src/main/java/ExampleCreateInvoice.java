import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;
import com.xendit.network.BaseRequest;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateInvoice {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";
        Xendit.requestClient = new BaseRequest();

        try {
            /**
             * There are several options to create invoice.
             * First option. Create directly from a properly named hashmap key value pair.
             * Check https://xendit.github.io/apireference/#create-invoice for field name.
             */
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "my_external_id");
            params.put("amount", 1800000);
            params.put("payer_email", "customer@domain.com");
            params.put("description", "Invoice Demo #123");

            Invoice invoice = Invoice.create(params);

            /**
             * Second option. Create with individual value of required params.
             */

            Invoice invoice1 = Invoice.create("my_external_id", 1800000, "customer@domain.com", "Invoice Demo #123");

            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
