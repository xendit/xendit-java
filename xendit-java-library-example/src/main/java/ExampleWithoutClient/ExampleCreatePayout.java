package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.payout.Payout;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePayout {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "12");
            params.put("amount", 100000);
            params.put("email","test@gmail.com");

            Payout payout = Payout.createPayout(params);
            System.out.println(payout.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
