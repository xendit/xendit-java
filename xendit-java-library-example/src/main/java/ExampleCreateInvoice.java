import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateInvoice {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "my_external_id");
            params.put("amount", 1800000);
            params.put("payer_email", "customer@domain.com");
            params.put("description", "Invoice Demo #123");

            Invoice invoice = Invoice.create(params);
            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
