package withoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xenditclient.Xendit;
import com.xenditclient.virtualAccount.FixedVirtualAccount;

import java.util.Arrays;

public class ExampleGetAvailableVABank {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

        try {
            AvailableBank[] banks = FixedVirtualAccount.getAvailableBanks();
            System.out.println(Arrays.toString(banks));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
