package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.ewallet.EWalletPayment;

public class ExampleGetEWalletPayment {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();

        try {
            String externalId = "ovo-ewallet";
            EWalletPayment.EWalletType ewalletType = EWalletPayment.EWalletType.OVO;
            
            EWalletPayment payment = xenditClient.eWallet.getPaymentStatus(externalId, ewalletType);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
