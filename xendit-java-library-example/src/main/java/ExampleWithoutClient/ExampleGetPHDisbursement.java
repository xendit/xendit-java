package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.DisbursementPH;

public class ExampleGetPHDisbursement {
    public static void main(String[] args) {
        // access key with Option
        Xendit.Opt.setApiKey("xnd_development_CnXTAQWxG5qax2csldXWa65PvRAH3XOnqrtTMi5RPyQBUhdqfLcj2vEaFHGUk7Zv");

        try {
            /**
             * Get disbursement object by ID.
             */
            String disbursementId = "614acbe0c0041e00247ad195";
            DisbursementPH disbursement = DisbursementPH.getPHById(disbursementId);

            System.out.println(disbursement);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
