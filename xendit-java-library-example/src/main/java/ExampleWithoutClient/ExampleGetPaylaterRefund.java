package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.PaylaterRefund;

public class ExampleGetPaylaterRefund {
    public static void main(String[] args) {
        // access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        // access static variable (same as old code )
        // Xendit.apiKey = "xnd_development_...";

        try {
            PaylaterRefund refund = PaylaterRefund.getPaylaterRefundStatus("plc_8cb12305-9bcf-4441-a087-ee0d446e297b",
                    "plr_2f2aa47f-2764-4b42-8712-c3fb1270b09e");
            System.out.println(refund.getRefundId());
            System.out.println(refund.getStatus());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
