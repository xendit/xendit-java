package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xenditclient.virtualAccount.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateVA {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("is_single_use", true);

            FixedVirtualAccount fixedVirtualAccount = xenditClient.fixedVirtualAccount.update("61513f1341de11279b3388bd", params);
            System.out.println(fixedVirtualAccount.getIsSingleUse());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
