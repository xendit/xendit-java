import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.EWalletPayment;

public class ExampleGetEWalletPayment {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_O46JfOtygef9kMNsK+ZPGT+ZZ9b3ooF4w3Dn+R1k+2fT/7GlCAN3jg==:";

        try {
            String externalId = "ovo-ewallet";
            String ewalletType = "OVO";
            EWalletPayment payment = EWalletPayment.getPaymentStatus(externalId, ewalletType);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
