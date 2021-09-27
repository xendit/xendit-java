package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xenditclient.virtualAccount.FixedVirtualAccountPayment;
import com.xendit.XenditClient;

public class ExampleGetVAPayment {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
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
