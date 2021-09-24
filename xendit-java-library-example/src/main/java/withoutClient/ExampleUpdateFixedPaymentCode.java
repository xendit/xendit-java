package withoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;
import com.xenditclient.Xendit;
import com.xenditclient.retailOutlet.RetailOutlet;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateFixedPaymentCode {
    private static void updateWithParamObject(String id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("name", "LOREM IPSUM");
            params.put("expected_amount", 1234567);
            FixedPaymentCode fpc = RetailOutlet.updateFixedPaymentCode(id, params);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void updateWithoutParamObject(String id) {
        try {
            FixedPaymentCode fpc = RetailOutlet.updateFixedPaymentCode(id, "LOREM IPSUM 2", 98765, null);
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

        updateWithParamObject("614c652131464262440e3d06");
    }
}
