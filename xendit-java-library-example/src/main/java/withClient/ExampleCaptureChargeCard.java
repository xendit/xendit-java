package withClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CreditCardCharge;
import com.xenditclient.XenditClient;

public class ExampleCaptureChargeCard {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();


        try {
            CreditCardCharge creditCardCharge = xenditClient.creditCard.captureCharge("12345678", 55000);
            System.out.println(creditCardCharge.getId());
            System.out.println(creditCardCharge.getStatus());
            System.out.println(creditCardCharge.getCaptureAmount());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
