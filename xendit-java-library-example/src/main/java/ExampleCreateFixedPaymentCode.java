import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateFixedPaymentCode {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "test");
            params.put("retail_outlet_name", "ALFAMART");
            params.put("name", "Rika Sutanto");
            params.put("expected_amount", 10000);
            FixedPaymentCode fpc = FixedPaymentCode.create(params);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
