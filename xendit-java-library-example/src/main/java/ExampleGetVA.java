import com.xendit.Xendit;
import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.model.VirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleGetVA {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        String virtualAccountId = "5d10f954fcbd4d3b6c1fcf2e";

        try {
            /**
             * Get VA from its ID
             */
            VirtualAccount virtualAccount = VirtualAccount.get(virtualAccountId);

            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
