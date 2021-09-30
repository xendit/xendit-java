package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.Balance;

/**
 * Example to get balance without client or having only single apikey
 */
public class ExampleGetBalance {
    public static void main(String[] args) {

        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            Balance balance = Balance.get();
            System.out.println(balance);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
