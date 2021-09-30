package ExampleWithoutClient;

import com.xendit.Xendit;
import com.xendit.model.Invoice;

import java.util.HashMap;
import java.util.Map;

/**
 * Example to implement create Invoice without client or having only single apikey
 */
public class ExampleCreateInvoice {
    public static void main(String[] args) {

        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";


        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "12");
            params.put("amount", 1800000);
            params.put("payer_email", "customer@domain.com");
            params.put("description", "Invoice Demo #123");

            Invoice invoice1 = Invoice.create(params);
            System.out.println(invoice1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
