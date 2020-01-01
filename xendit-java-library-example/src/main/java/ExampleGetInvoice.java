import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;

public class ExampleGetInvoice {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Invoice invoice = Invoice.retrieve("5e0cb0bbf4d38b20d5421b72");
            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
