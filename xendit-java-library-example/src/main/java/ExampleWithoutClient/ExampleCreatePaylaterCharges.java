package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.Paylater;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePaylaterCharges {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("plan_id","plp_3d88d952-9505-4ed7-84d3-e8639e99e9c4");
            params.put("reference_id","order_id_123");
            params.put("checkout_method","ONE_TIME_PAYMENT");
            params.put("success_redirect_url","https://merchant.com/order/confirm");
            params.put("failure_redirect_url","https://merchant.com/order/fail");
            params.put("payment_method_id",null);
            params.put("metadata",null);

            Paylater paylater = Paylater.createPaylaterCharges(params);
            System.out.println(paylater.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
