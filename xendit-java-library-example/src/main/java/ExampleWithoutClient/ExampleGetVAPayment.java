package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.virtualAccount.FixedVirtualAccountPayment;
import com.xendit.Xendit;
import com.xendit.model.virtualAccount.FixedVirtualAccount;

public class ExampleGetVAPayment {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

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
