package ExampleWithoutClient;

import com.xendit.exception.XenditException;

import java.util.Arrays;

import com.xendit.Xendit;
import com.xendit.model.DisbursementPHP;

public class ExampleGetPHPDisbursement {
    public static void main(String[] args) {
        // access key with Option
        // you must replace api key with actual api key
        Xendit.Opt.setApiKey("xnd_development_...");

        try {
            /**
             * Get disbursement object by ID.
             */
            String disbursementId = "disb-12345678abcdef";
            DisbursementPHP disbursement = DisbursementPHP.getPHPById(disbursementId);

            System.out.println(disbursement);

            /**
             * Get array of php disbursement object by refernce ID.
             */
            String referenceId = "my_reference_id";
            DisbursementPHP[] disbursements = DisbursementPHP.getPHPByReferenceId(referenceId);

            System.out.println(Arrays.toString(disbursements));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}