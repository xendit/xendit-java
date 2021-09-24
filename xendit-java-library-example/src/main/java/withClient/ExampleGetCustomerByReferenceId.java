package withClient;

import com.xendit.exception.XenditException;
import com.xenditclient.XenditClient;
import com.xenditclient.customer.Customer;

import java.util.Arrays;

public class ExampleGetCustomerByReferenceId {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
                .build();


        try {
            Customer[] customers = xenditClient.customer.getCustomerByReferenceId("test-reference-id");
            System.out.println(Arrays.toString(customers));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
