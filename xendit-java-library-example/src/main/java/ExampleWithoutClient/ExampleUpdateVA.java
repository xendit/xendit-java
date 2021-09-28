package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.virtualAccount.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateVA {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("is_single_use", true);

            FixedVirtualAccount fixedVirtualAccount = FixedVirtualAccount.update("614dbfea41de115d0d338602", params);
            System.out.println(fixedVirtualAccount.getIsSingleUse());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
