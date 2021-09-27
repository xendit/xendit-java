package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xenditclient.balance.Balance;

/**
 * Example to get balance with client while having multiple apikey
 */
public class ExampleGetBalance {
    public static void main(String[] args) {

        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();

        //create second object of xendit client with different apikey
        XenditClient xenditClient2 = new XenditClient.Builder()
                .apikey("xnd_development_fBbgLpGo7ZdGqOIg4YIphntfNcFOE0ZdhqyfgeQcYaqoR3erPvVrozgtJY4EZb")
                .build();

        try {
            Balance balance1 = xenditClient.balance.get();
            Balance balance2 = xenditClient2.balance.get();
            System.out.println(balance1);
            System.out.println(balance2);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
