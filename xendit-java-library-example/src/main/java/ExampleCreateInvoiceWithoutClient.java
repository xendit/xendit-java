import com.xenditclient.Xendit;
import com.xenditclient.invoice.Invoice;

import java.util.HashMap;
import java.util.Map;

/**
 * Example to implement create Invoice without client or having only single apikey
 */
public class ExampleCreateInvoiceWithoutClient {
    public static void main(String[] args) {

        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";


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
