import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Payout;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePayout {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "my_test_id");
            params.put("amount", 100000);

            Payout payout = Payout.create(params);
            System.out.println(payout.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
