package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;
import com.xendit.Xendit;
import com.xendit.model.RetailOutlet;

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
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        updateWithParamObject("614c652131464262440e3d06");
    }
}
