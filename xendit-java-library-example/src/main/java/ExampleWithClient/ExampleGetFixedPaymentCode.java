package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xenditclient.retailOutlet.FixedPaymentCode;
import com.xendit.XenditClient;

public class ExampleGetFixedPaymentCode {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();

        try {
            FixedPaymentCode fpc = xenditClient.retailOutlet.getFixedPaymentCode("614c652131464262440e3d06");
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
