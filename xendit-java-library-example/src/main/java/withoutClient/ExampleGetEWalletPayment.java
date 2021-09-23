package withoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.Xendit;
import com.xenditclient.ewallet.EWalletPayment;

public class ExampleGetEWalletPayment {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

        try {
            String externalId = "ovo-ewallet";
            EWalletPayment.EWalletType ewalletType = EWalletPayment.EWalletType.OVO;
            
            EWalletPayment payment = EWalletPayment.getPaymentStatus(externalId, ewalletType);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
