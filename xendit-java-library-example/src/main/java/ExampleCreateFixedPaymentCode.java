import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateFixedPaymentCode {
    private static void createWithParamObject() {
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

    private static void createWithoutParamObject() {
        try {
            FixedPaymentCode fpc = FixedPaymentCode.create("test", "ALFAMART", "Rika Sutanto", 10000);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";
        createWithoutParamObject();
    }
}
