package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xenditclient.payout.Payout;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePayout {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "my_test_id");
            params.put("amount", 100000);
            params.put("email","test@gmail.com");

            Payout payout = xenditClient.payout.createPayout(params);
            System.out.println(payout.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
