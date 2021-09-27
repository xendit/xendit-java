package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.XenditClient;

import java.util.Arrays;

public class ExampleGetAvailableVABank {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();

        try {
            AvailableBank[] banks = xenditClient.fixedVirtualAccount.getAvailableBanks();
            System.out.println(Arrays.toString(banks));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
