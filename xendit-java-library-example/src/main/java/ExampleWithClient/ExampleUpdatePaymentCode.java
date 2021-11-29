package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.RegionalRetailOutletPaymentCode;
import com.xendit.XenditClient;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdatePaymentCode {
    private static void updateWithParamObject(String id, XenditClient xenditClient) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("customer_name", "LOREM IPSUM");
            params.put("amount", 1234567);
            RegionalRetailOutletPaymentCode pc = xenditClient.retailOutlet.updatePaymentCode(id, params);
            System.out.println(pc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void updateWithoutParamObject(String id, XenditClient xenditClient) {
        try {
            RegionalRetailOutletPaymentCode pc = xenditClient.retailOutlet.updatePaymentCode(id, "LOREM IPSUM 2", 98765, null, null, null);
            System.out.println(pc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();
        updateWithParamObject("614c650b018a613ac8f066bd", xenditClient);
    }
}
