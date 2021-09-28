package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.retailOutlet.FixedPaymentCode;
import com.xendit.XenditClient;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateFixedPaymentCode {
    private static void createWithParamObject(XenditClient xenditClient) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "test");
            params.put("retail_outlet_name", "ALFAMART");
            params.put("name", "Rika Sutanto");
            params.put("expected_amount", 10000);
            FixedPaymentCode fpc = xenditClient.retailOutlet.createFixedPaymentCode(params);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void createWithoutParamObject(XenditClient xenditClient) {
        try {
            FixedPaymentCode fpc = xenditClient.retailOutlet.createFixedPaymentCode("test", FixedPaymentCode.RetailOutletName.ALFAMART, "Rika Sutanto", 10000);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();
        createWithoutParamObject(xenditClient);
    }
}
