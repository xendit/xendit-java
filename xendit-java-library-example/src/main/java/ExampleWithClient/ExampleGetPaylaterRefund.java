package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.PaylaterRefund;

public class ExampleGetPaylaterRefund {
    public static void main(String[] args) {
        // create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        try {
            PaylaterRefund refund = xenditClient.paylater
                    .getPaylaterRefundStatus("plc_8cb12305-9bcf-4441-a087-ee0d446e297b",
                            "plr_2f2aa47f-2764-4b42-8712-c3fb1270b09e");
            System.out.println(refund.getRefundId());
            System.out.println(refund.getStatus());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
