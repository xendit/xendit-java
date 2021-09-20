import com.xenditclient.XenditClient;
import com.xenditclient.invoice.Invoice;

import java.util.HashMap;
import java.util.Map;

/**
 * Example to implement create Invoice with client while having multiple apikey
 */
public class ExampleCreateInvoiceWithClient {
    public static void main(String[] args) {

        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();

        //create second object of xendit client with different apikey
        XenditClient xenditClient2 = new XenditClient.Builder()
                .apikey("xnd_development_fBbgLpGo7ZdGqOIg4YIphntfNcFOE0ZdhqyfgeQcYaqoR3erPvVrozgtJY4EZb")
                .build();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "100");
            params.put("amount", 2500);
            params.put("payer_email", "customer@domain.com");
            params.put("description", "Invoice Demo");

            Invoice invoice1 = xenditClient.invoice.create(params);
            Invoice invoice2 = xenditClient2.invoice.create(params);

            System.out.println(invoice1);
            System.out.println(invoice2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
