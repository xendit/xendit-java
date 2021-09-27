package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.retailOutlet.FixedPaymentCode;
import com.xendit.Xendit;
import com.xenditclient.retailOutlet.RetailOutlet;

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
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";
        createWithoutParamObject();
    }
}
