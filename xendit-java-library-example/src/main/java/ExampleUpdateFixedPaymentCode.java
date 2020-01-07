import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateFixedPaymentCode {
    private static void updateWithParamObject(String id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("name", "LOREM IPSUM");
            params.put("expected_amount", 1234567);
            FixedPaymentCode fpc = FixedPaymentCode.update(id, params);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void updateWithoutParamObject(String id) {
        try {
            FixedPaymentCode fpc = FixedPaymentCode.update(id, "LOREM IPSUM 2", 98765, null);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";
        updateWithParamObject("5e12d60b7bc384e60435ec92");
    }
}
