import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.EWalletPayment;

public class ExampleGetEWalletPayment {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            String externalId = "ovo-ewallet";
            EWalletPayment.EWalletType ewalletType = EWalletPayment.EWalletType.OVO;
            
            EWalletPayment payment = EWalletPayment.getPaymentStatus(externalId, ewalletType);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
