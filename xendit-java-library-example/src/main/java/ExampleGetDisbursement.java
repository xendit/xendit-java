import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.model.Disbursement;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExampleGetDisbursement {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            /**
             * Get disbursement object by ID. 
             */
            String disbursementId = "5d3e89e1ac22f4000fa45294";
            Disbursement disbursement = Disbursement.getById(disbursementId);

            /**
             * Get array of disbursement object by external ID. 
             */
            String externalId = "my_external_id";
            Disbursement[] disbursements = Disbursement.getByExternalId(externalId);

            System.out.println(disbursement);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
