package ExampleWithClient;

import com.xendit.exception.XenditException;

import java.util.Arrays;

import com.xendit.XenditClient;
import com.xendit.model.DisbursementPHP;

public class ExampleGetPHPDisbursement {
    public static void main(String[] args) {
        // create xendit client which holds value of apikey
        // you must replace api key with actual api key
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_dummy_...")
                .build();

        try {
            /**
             * Get disbursement object by ID.
             */
            String disbursementId = "disb-12345678abcdef";
            DisbursementPHP disbursement = xenditClient.disbursementPHP.getPHPById(disbursementId);

            System.out.println(disbursement);

            /**
             * Get array of php disbursement object by refernce ID.
             */
            String referenceId = "my_reference_id";
            DisbursementPHP[] disbursements = xenditClient.disbursementPHP.getPHPByReferenceId(referenceId);

            System.out.println(Arrays.toString(disbursements));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}