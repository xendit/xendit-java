import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.VirtualAccount;
import com.xendit.model.VirtualAccountPayment;

public class ExampleGetVAPayment {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        String virtualAccountPaymentId = "5d072b6dfcbd4d3b6c1fca32";

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
