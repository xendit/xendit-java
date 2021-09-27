package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xenditclient.disbursement.Disbursement;

import java.util.Arrays;

public class ExampleGetDisbursement {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();

        try {
            /**
             * Get disbursement object by ID.
             */
            String disbursementId = "614acbe0c0041e00247ad195";
            Disbursement disbursement = xenditClient.disbursement.getById(disbursementId);

            /**
             * Get array of disbursement object by external ID.
             */
            String externalId = "my_external_id";
            Disbursement[] disbursements = xenditClient.disbursement.getByExternalId(externalId);

            System.out.println(disbursement);
            System.out.println(Arrays.toString(disbursements));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
