package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.FixedVirtualAccountPayment;
import com.xendit.XenditClient;

public class ExampleGetVAPayment {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        String virtualAccountPaymentId = "random_1560763705544";

        try {
            /**
             * Get VA payment from payment ID
             */
            FixedVirtualAccountPayment virtualAccountPayment = xenditClient.fixedVirtualAccount.getPayment(virtualAccountPaymentId);

            System.out.println(virtualAccountPayment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
