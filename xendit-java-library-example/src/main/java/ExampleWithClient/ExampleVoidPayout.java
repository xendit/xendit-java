package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xenditclient.payout.Payout;

public class ExampleVoidPayout {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();

        try {
            Payout payout = xenditClient.payout.voidPayout("7ecf7953-01e8-41db-a6fe-471b1c65df5b");
            System.out.println(payout.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
