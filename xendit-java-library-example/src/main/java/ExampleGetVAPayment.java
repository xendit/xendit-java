import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.VirtualAccount;
import com.xendit.model.VirtualAccountPayment;

public class ExampleGetVAPayment {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        String virtualAccountPaymentId = "my_external_id_1561393634704";

        try {
            /**
             * Get VA payment from payment ID
             */
            VirtualAccountPayment virtualAccountPayment = VirtualAccount.getPayment(virtualAccountPaymentId);

            System.out.println(virtualAccountPayment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
