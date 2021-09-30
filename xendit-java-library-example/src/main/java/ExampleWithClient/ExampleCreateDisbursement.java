package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.XenditClient;
import com.xendit.model.Disbursement;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ExampleCreateDisbursement {
    public static void main(String[] args) {

        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        try {
            /**
             * [OPTIONAL]
             * Before requesting disbursement to Xendit, you should get the supported bank code via getAvailableBank
             * method. You can skip this step if your system is already familiar with our standard code.
             * In this example, we call available disbursement bank function.
             */
            AvailableBank[] availableBanks = xenditClient.disbursement.getAvailableBanks();

            /**
             * Let's say that we want to disburse to the first bank in that list.
             */
            AvailableBank destinationBank = availableBanks[0];

            /**
             * There are several options to create disbursement.
             * First option. Create directly from a properly named hashmap key value pair.
             * Check https://xendit.github.io/apireference/#create-disbursement for field name.
             */
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("external_id", "my_external_id");
            params.put("bank_code", destinationBank.getCode());
            params.put("account_holder_name", "John Doe");
            params.put("account_number", "123456789");
            params.put("description", "My Description");
            params.put("amount", "90000");

            Disbursement disbursement = xenditClient.disbursement.create(params);

            /**
             * Second option. Create with individual value of required params.
             */
            Disbursement disbursement2 = xenditClient.disbursement.create(
                    "my_external_id",
                    destinationBank.getCode(),
                    "John Doe",
                    "1234567890",
                    "description",
                    new BigInteger("90000")
            );

            /**
             * Third option. Create with individual value of required params + email to.
             */
            Disbursement disbursement3 = xenditClient.disbursement.create(
                    "my_external_id",
                    destinationBank.getCode(),
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
            Disbursement disbursement4 = xenditClient.disbursement.create(
                    "my_external_id",
                    destinationBank.getCode(),
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
            Disbursement disbursement5 = xenditClient.disbursement.create(
                    "my_external_id",
                    destinationBank.getCode(),
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
