package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.payout.Payout;

public class ExampleGetPayout {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();

        try {
            Payout payout = xenditClient.payout.getPayout("7ecf7953-01e8-41db-a6fe-471b1c65df5b");
            System.out.println(payout);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
