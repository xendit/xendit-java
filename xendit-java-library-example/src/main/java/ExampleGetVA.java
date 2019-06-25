import com.xendit.Xendit;
import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleGetVA {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        String virtualAccountId = "5d11bebd2e1d322f03de1e7b";

        try {
            /**
             * Get VA from its ID
             */
            FixedVirtualAccount virtualAccount = FixedVirtualAccount.getFixedVA(virtualAccountId);

            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
