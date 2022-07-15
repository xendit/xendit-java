package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.DisbursementPH;

public class ExampleGetPHDisbursement {
    public static void main(String[] args) {
        // create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        try {
            /**
             * Get disbursement object by ID.
             */
            String disbursementId = "614acbe0c0041e00247ad195";
            DisbursementPH disbursement = xenditClient.disbursement.getPHById(disbursementId);

            System.out.println(disbursement);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
