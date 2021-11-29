package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.RegionalRetailOutletPaymentCode;
import com.xendit.XenditClient;

public class ExampleGetPaymentCode {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        try {
            RegionalRetailOutletPaymentCode fpc = xenditClient.retailOutlet.getPaymentCode("614c652131464262440e3d06");
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
