package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterEnum;
import com.xendit.model.PaylaterRefund;
import com.xendit.Xendit;

public class ExampleCreatePaylaterRefund {
    public static void main(String[] args) {
        // access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        // access static variable (same as old code )
        // Xendit.apiKey = "xnd_development_...";

        try {
            PaylaterRefund paylaterRefund = PaylaterRefund.createPaylaterRefund(
                    "plc_8cb12305-9bcf-4441-a087-ee0d446e297b", 1000,
                    PaylaterEnum.RefundReasons.OTHERS);
            System.out.println(paylaterRefund.getStatus());
            System.out.println(paylaterRefund.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
