import com.xendit.Xendit;
import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.model.VirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateOpenVA {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        Map<String, Object> openVAMap = new HashMap<String, Object>();
        openVAMap.put("external_id", "my_external_id");
        openVAMap.put("bank_code", BankCode.BNI.getText());
        openVAMap.put("name", "John Doe");

        try {
            /**
             * First option. Create directly from a properly named hashmap key value pair.
             * Check https://xendit.github.io/apireference/#create-fixed-virtual-accounts for field name.
             */
            VirtualAccount virtualAccount = VirtualAccount.createOpen(openVAMap);

            /**
             * Second option. Create with individual value of required params.
             */
            VirtualAccount virtualAccount2 = VirtualAccount.createOpen("my_external_id",
                    BankCode.BRI.getText(), "John Doe");

            /**
             * Third option. Create with individual value of required params plus added additional params at the end.
             */
            VirtualAccount virtualAccount3 = VirtualAccount.createOpen("my_external_id",
                    BankCode.MANDIRI.getText(), "John Doe", openVAMap);

            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
