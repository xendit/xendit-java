import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;

public class ExampleExpireInvoice {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Invoice invoice = Invoice.expire("5e0cbbdbf4d38b20d5421b96");
            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
