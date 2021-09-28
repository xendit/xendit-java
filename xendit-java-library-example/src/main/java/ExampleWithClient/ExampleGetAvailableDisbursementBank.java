package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.XenditClient;

import java.util.Arrays;

public class ExampleGetAvailableDisbursementBank {

    public static void main(String[] args) {

        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();

        //create second object of xendit client with different apikey
        XenditClient xenditClient2 = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();

        try {
            AvailableBank[] banks = xenditClient.disbursement.getAvailableBanks();
            AvailableBank[] banks2 = xenditClient2.disbursement.getAvailableBanks();
            System.out.println(Arrays.toString(banks));
            System.out.println(Arrays.toString(banks2));
        } catch (XenditException e) {
            e.printStackTrace();
        }

    }

}
