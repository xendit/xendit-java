package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.RegionalRetailOutletPaymentCode;
import com.xendit.XenditClient;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePaymentCode {
    private static void createWithParamObject(XenditClient xenditClient) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("reference_id", "test");
            params.put("channel_code", RegionalRetailOutletPaymentCode.ChannelCode.SEVENELEVENCLIQQ);
            params.put("customer_name", "test-customer");
            params.put("amount", 10);
            params.put("currency",  RegionalRetailOutletPaymentCode.Currency.PHP);
            params.put("market", RegionalRetailOutletPaymentCode.Market.PH);
            RegionalRetailOutletPaymentCode pc = xenditClient.retailOutlet.createPaymentCode(params);
            System.out.println(pc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void createWithoutParamObject(XenditClient xenditClient) {
        try {
            RegionalRetailOutletPaymentCode pc = xenditClient.retailOutlet.createPaymentCode("test", RegionalRetailOutletPaymentCode.ChannelCode.SEVENELEVENCLIQQ, 
                "test-customer", 10, RegionalRetailOutletPaymentCode.Currency.PHP, RegionalRetailOutletPaymentCode.Market.PH);
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
        createWithoutParamObject(xenditClient);
    }
}
