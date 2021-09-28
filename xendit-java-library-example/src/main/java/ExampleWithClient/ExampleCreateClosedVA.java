package ExampleWithClient;

import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.virtualAccount.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateClosedVA {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();

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
            FixedVirtualAccount virtualAccount = xenditClient.fixedVirtualAccount.createClosed(closedVAMap);

            /**
             * Second option. Create with individual value of required params.
             */
            FixedVirtualAccount virtualAccount2 = xenditClient.fixedVirtualAccount.createClosed("my_external_id",
                    BankCode.PERMATA.getText(), "John Doe", 100000L);

            /**
             * Third option. Create with individual value of required params plus added additional params at the end.
             */
            FixedVirtualAccount virtualAccount3 = xenditClient.fixedVirtualAccount.createClosed("my_external_id",
                    BankCode.MANDIRI.getText(), "John Doe", 100000L, closedVAMap);

            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
