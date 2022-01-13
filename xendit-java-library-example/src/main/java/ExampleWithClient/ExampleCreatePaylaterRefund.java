package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterEnum;
import com.xendit.model.PaylaterRefund;
import com.xendit.XenditClient;

public class ExampleCreatePaylaterRefund {
    public static void main(String[] args) {
        // create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        try {
            PaylaterRefund paylaterRefund = xenditClient.paylater.createPaylaterRefund(
                    "plc_8cb12305-9bcf-4441-a087-ee0d446e297b", 1000,
                    PaylaterEnum.RefundReasons.OTHERS);
            System.out.println(paylaterRefund.getRefundId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
