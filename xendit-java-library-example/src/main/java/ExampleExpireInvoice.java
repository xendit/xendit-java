import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;
import com.xendit.network.BaseRequest;

public class ExampleExpireInvoice {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";
        Xendit.requestClient = new BaseRequest();

        try {
            Invoice invoice = Invoice.expire("5e15a29ef4d38b20d5423b9f");
            System.out.println(invoice);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
