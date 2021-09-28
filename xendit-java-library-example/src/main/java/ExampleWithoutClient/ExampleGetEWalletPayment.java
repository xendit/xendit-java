package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.ewallet.EWalletPayment;

public class ExampleGetEWalletPayment {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

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
