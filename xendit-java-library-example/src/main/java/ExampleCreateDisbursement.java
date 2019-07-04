import com.xendit.Xendit;
import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.model.Disbursement;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ExampleCreateDisbursement {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        Map<String, Object> disbursementMap = new HashMap<String, Object>();
        disbursementMap.put("external_id", "my_external_id");
        disbursementMap.put("bank_code", BankCode.BNI.getText());
        disbursementMap.put("account_holder_name", "John Doe");
        disbursementMap.put("account_number", "123456789");
        disbursementMap.put("description", "My Description");
        disbursementMap.put("amount", "90000");

        try {
            /**
             * First option. Create directly from a properly named hashmap key value pair.
             * Check https://xendit.github.io/apireference/#create-disbursement for field name.
             */
            Disbursement disbursement = Disbursement.create(disbursementMap);

            /**
             * Second option. Create with individual value of required params.
             */
            Disbursement disbursement2 = Disbursement.create(
                    "my_external_id",
                    BankCode.MANDIRI.getText(),
                    "John Doe",
                    "1234567890",
                    "description",
                    new BigInteger("90000")

            );

            /**
             * Third option. Create with individual value of required params + email to.
             */
            Disbursement disbursement3 = Disbursement.create(
                    "my_external_id",
                    BankCode.MANDIRI.getText(),
                    "John Doe",
                    "1234567890",
                    "description",
                    new BigInteger("90000"),
                    new String[]{
                            "john@doe.com"
                    }
            );

            /**
             * Fourth option. Create with individual value of required params + email to and cc.
             */
            Disbursement disbursement4 = Disbursement.create(
                    "my_external_id",
                    BankCode.MANDIRI.getText(),
                    "John Doe",
                    "1234567890",
                    "description",
                    new BigInteger("90000"),
                    new String[]{
                            "john@doe.com"
                    },
                    new String[]{
                            "doe@john.com"
                    }
            );

            /**
             * Fifth option. Create with individual value of required params + email to, cc and bcc.
             */
            Disbursement disbursement5 = Disbursement.create(
                    "my_external_id",
                    BankCode.MANDIRI.getText(),
                    "John Doe",
                    "1234567890",
                    "description",
                    new BigInteger("90000"),
                    new String[]{
                            "john@doe.com"
                    },
                    new String[]{
                            "doe@john.com"
                    },
                    new String[]{
                            "john@john.com"
                    }
            );

            System.out.println(disbursement);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
