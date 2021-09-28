package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.invoice.Invoice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExampleGetInvoice {
    private static void getById(XenditClient xenditClient) {
        try {
            Invoice invoice = xenditClient.invoice.getById("614871cfa4c8311b21a97ad7");
            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void getAll(XenditClient xenditClient) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("limit", 2);
            params.put("last_invoice_id", "614b035fc3153d1712c0a902");
            Invoice[] invoices = xenditClient.invoice.getAll(params);
            System.out.println(Arrays.toString(invoices));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();
        getAll(xenditClient);
    }
}
