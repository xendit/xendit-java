package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.DisbursementIDR;

import java.util.Arrays;

public class ExampleGetIDRDisbursement {
    public static void main(String[] args) {
        // access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        // access static variable (same as old code )
        // Xendit.apiKey =
        // "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

        try {
            /**
             * Get disbursement object by ID.
             */
            String disbursementId = "614acbe0c0041e00247ad195";
            DisbursementIDR disbursement = DisbursementIDR.getIDRById(disbursementId);

            /**
             * Get array of disbursement object by external ID.
             */
            String externalId = "my_external_id";
            DisbursementIDR[] disbursements = DisbursementIDR.getByExternalId(externalId);

            System.out.println(disbursement);
            System.out.println(Arrays.toString(disbursements));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
