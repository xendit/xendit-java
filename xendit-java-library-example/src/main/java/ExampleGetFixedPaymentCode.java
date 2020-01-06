import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;

public class ExampleGetFixedPaymentCode {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            FixedPaymentCode fpc = FixedPaymentCode.getById("5e12d60b7bc384e60435ec92");
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
