package ExampleWithClient;

import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.virtualAccount.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateOpenVA {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();

        Map<String, Object> openVAMap = new HashMap<String, Object>();
        openVAMap.put("external_id", "my_external_id");
        openVAMap.put("bank_code", BankCode.BNI.getText());
        openVAMap.put("name", "John Doe");

        try {
            /**
             * First option. Create directly from a properly named hashmap key value pair.
             * Check https://xendit.github.io/apireference/#create-fixed-virtual-accounts for field name.
             */
            FixedVirtualAccount virtualAccount = xenditClient.fixedVirtualAccount.createOpen(openVAMap);

            /**
             * Second option. Create with individual value of required params.
             */
            FixedVirtualAccount virtualAccount2 = xenditClient.fixedVirtualAccount.createOpen("my_external_id",
                    BankCode.BRI.getText(), "John Doe");

            /**
             * Third option. Create with individual value of required params plus added additional params at the end.
             */
            FixedVirtualAccount virtualAccount3 = xenditClient.fixedVirtualAccount.createOpen("my_external_id",
                    BankCode.MANDIRI.getText(), "John Doe", openVAMap);

            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
