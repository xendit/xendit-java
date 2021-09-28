package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.virtualAccount.FixedVirtualAccount;

public class ExampleGetVA {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();

        String virtualAccountId = "61513f1341de11279b3388bd";

        try {
            /**
             * Get VA from its ID
             */
            FixedVirtualAccount virtualAccount = xenditClient.fixedVirtualAccount.getFixedVA(virtualAccountId);

            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
