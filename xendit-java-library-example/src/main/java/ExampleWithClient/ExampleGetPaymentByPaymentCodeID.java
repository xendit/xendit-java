package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.RegionalRetailOutletPayments;
import com.xendit.XenditClient;

public class ExampleGetPaymentByPaymentCodeID {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();

        try {
            RegionalRetailOutletPayments[] payments = xenditClient.retailOutlet.getPaymentsByPaymentCodeId("614c652131464262440e3d06");
            System.out.println(payments);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
