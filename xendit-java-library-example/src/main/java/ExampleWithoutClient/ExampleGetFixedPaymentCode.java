package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;
import com.xendit.Xendit;
import com.xendit.model.RetailOutlet;

public class ExampleGetFixedPaymentCode {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            FixedPaymentCode fpc = RetailOutlet.getFixedPaymentCode("614c652131464262440e3d06");
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
