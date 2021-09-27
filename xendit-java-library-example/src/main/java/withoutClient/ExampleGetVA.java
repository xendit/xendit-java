package withoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.Xendit;
import com.xenditclient.virtualAccount.FixedVirtualAccount;

public class ExampleGetVA {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

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
