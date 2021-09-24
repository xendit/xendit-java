package withoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.FixedPaymentCode;
import com.xenditclient.Xendit;
import com.xenditclient.retailOutlet.RetailOutlet;

public class ExampleGetFixedPaymentCode {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

        try {
            FixedPaymentCode fpc = RetailOutlet.getFixedPaymentCode("614c652131464262440e3d06");
            System.out.println(fpc);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
