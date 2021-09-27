package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.invoice.Invoice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExampleGetInvoice {

    private static void getById() {
        try {
            Invoice invoice = Invoice.getById("614871cfa4c8311b21a97ad7");
            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void getAll() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("limit", 2);
            params.put("last_invoice_id", "614b035fc3153d1712c0a902");
            Invoice[] invoices = Invoice.getAll(params);
            System.out.println(Arrays.toString(invoices));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";
        getById();
    }
}
