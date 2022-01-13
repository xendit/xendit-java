package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.PaylaterCharge;

public class ExampleGetPaylaterCharge {
    public static void main(String[] args) {
        // create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        try {
            PaylaterCharge charge = xenditClient.paylater
                    .getPaylaterChargeStatus("plc_8cb12305-9bcf-4441-a087-ee0d446e297b");
            System.out.println(charge.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
