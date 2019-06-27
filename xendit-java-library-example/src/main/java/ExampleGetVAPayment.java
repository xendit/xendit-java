import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedVirtualAccount;
import com.xendit.model.FixedVirtualAccountPayment;

public class ExampleGetVAPayment {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        String virtualAccountPaymentId = "random_1560763705544";

        try {
            /**
             * Get VA payment from payment ID
             */
            FixedVirtualAccountPayment virtualAccountPayment = FixedVirtualAccount.getPayment(virtualAccountPaymentId);

            System.out.println(virtualAccountPayment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
