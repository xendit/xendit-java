package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.virtualAccount.FixedVirtualAccount;

public class ExampleGetVA {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        String virtualAccountId = "615139d841de117db33388b3";

        try {
            /**
             * Get VA from its ID
             */
            FixedVirtualAccount virtualAccount = FixedVirtualAccount.getFixedVA(virtualAccountId);

            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
