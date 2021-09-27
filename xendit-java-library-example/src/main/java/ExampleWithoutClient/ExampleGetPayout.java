package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.payout.Payout;

public class ExampleGetPayout {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

        try {
            Payout payout = Payout.getPayout("7ecf7953-01e8-41db-a6fe-471b1c65df5b");
            System.out.println(payout);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
