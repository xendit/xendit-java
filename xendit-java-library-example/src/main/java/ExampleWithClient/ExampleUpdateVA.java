package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateVA {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("is_single_use", true);

            FixedVirtualAccount fixedVirtualAccount = xenditClient.fixedVirtualAccount.update("6413d9622123d8f96721cd1d", params);
            System.out.println(fixedVirtualAccount.getIsSingleUse());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
