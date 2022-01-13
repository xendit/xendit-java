package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.PaylaterCharge;

public class ExampleGetPaylaterCharge {
    public static void main(String[] args) {
        // access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        // access static variable (same as old code )
        // Xendit.apiKey = "xnd_development_...";

        try {
            PaylaterCharge charge = PaylaterCharge.getPaylaterChargeStatus("plc_8cb12305-9bcf-4441-a087-ee0d446e297b");
            System.out.println(charge.getChargeId());
            System.out.println(refund.getStatus());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
