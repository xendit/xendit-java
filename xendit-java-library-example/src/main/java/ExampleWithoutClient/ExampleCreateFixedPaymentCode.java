package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;
import com.xendit.Xendit;
import com.xendit.model.RetailOutlet;

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
            FixedPaymentCode fpc = RetailOutlet.createFixedPaymentCode(params);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void createWithoutParamObject() {
        try {
            FixedPaymentCode fpc = RetailOutlet.createFixedPaymentCode("test", FixedPaymentCode.RetailOutletName.ALFAMART, "Rika Sutanto", 10000);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";
        createWithoutParamObject();
    }
}
