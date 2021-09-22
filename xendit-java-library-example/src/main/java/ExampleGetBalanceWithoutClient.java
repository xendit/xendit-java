import com.xendit.exception.XenditException;
import com.xenditclient.Xendit;
import com.xenditclient.balance.Balance;

/**
 * Example to get balance without client or having only single apikey
 */
public class ExampleGetBalanceWithoutClient {
    public static void main(String[] args) {

        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

        try {
            Balance balance = Balance.get();
            System.out.println(balance);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
