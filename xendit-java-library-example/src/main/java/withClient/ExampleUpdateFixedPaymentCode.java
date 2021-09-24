package withClient;

import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;
import com.xenditclient.XenditClient;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateFixedPaymentCode {
    private static void updateWithParamObject(String id, XenditClient xenditClient) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("name", "LOREM IPSUM");
            params.put("expected_amount", 1234567);
            FixedPaymentCode fpc = xenditClient.retailOutlet.updateFixedPaymentCode(id, params);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void updateWithoutParamObject(String id, XenditClient xenditClient) {
        try {
            FixedPaymentCode fpc = xenditClient.retailOutlet.updateFixedPaymentCode(id, "LOREM IPSUM 2", 98765, null);
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();
        updateWithParamObject("614c650b018a613ac8f066bd", xenditClient);
    }
}
