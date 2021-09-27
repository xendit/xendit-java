package withoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.Xendit;
import com.xenditclient.virtualAccount.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateVA {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

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
