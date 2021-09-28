package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.Xendit;
import com.xendit.model.virtualAccount.FixedVirtualAccount;

import java.util.Arrays;

public class ExampleGetAvailableVABank {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            AvailableBank[] banks = FixedVirtualAccount.getAvailableBanks();
            System.out.println(Arrays.toString(banks));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
