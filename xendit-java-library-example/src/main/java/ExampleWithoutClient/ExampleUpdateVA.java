package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateVA {
    public static void main(String[] args) {
        // access key with Option
        Xendit.Opt.setApiKey("xnd_development...");

        // access static variable (same as old code )
        // Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("is_single_use", true);

            FixedVirtualAccount fixedVirtualAccount = FixedVirtualAccount.update("6413cdfaca7651511ae7f604", params);
            System.out.println(fixedVirtualAccount.getIsSingleUse());

            // You can find this value in the response headers, under Request-ID. You can
            // use Request-ID to find logs in API Logs in Dashboard
            // (https://dashboard.xendit.co/api-logs).
            System.out.println(fixedVirtualAccount.getResponseHeaders().get("Request-Id"));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
