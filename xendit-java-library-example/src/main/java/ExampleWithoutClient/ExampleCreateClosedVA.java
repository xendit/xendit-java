package ExampleWithoutClient;

import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateClosedVA {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        Map<String, Object> closedVAMap = new HashMap<String, Object>();
        closedVAMap.put("external_id", "my_external_id");
        closedVAMap.put("bank_code", BankCode.BNI.getText());
        closedVAMap.put("name", "John Doe");
        closedVAMap.put("expected_amount", "100");

        try {
            /**
             * First option. Create directly from a properly named hashmap key value pair.
             * Check https://xendit.github.io/apireference/#create-fixed-virtual-accounts for field name.
             */
            FixedVirtualAccount virtualAccount = FixedVirtualAccount.createClosed(closedVAMap);

            /**
             * Second option. Create with individual value of required params.
             */
            FixedVirtualAccount virtualAccount2 = FixedVirtualAccount.createClosed("my_external_id",
                    BankCode.PERMATA.getText(), "John Doe", 100000L);

            /**
             * Third option. Create with individual value of required params plus added additional params at the end.
             */
            FixedVirtualAccount virtualAccount3 = FixedVirtualAccount.createClosed("my_external_id",
                    BankCode.MANDIRI.getText(), "John Doe", 100000L, closedVAMap);

            System.out.println(virtualAccount.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
