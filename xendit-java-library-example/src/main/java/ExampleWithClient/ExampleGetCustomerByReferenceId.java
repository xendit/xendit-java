package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.customer.Customer;

import java.util.Arrays;

public class ExampleGetCustomerByReferenceId {
    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .apikey("xnd_development_...")
                .build();


        try {
            Customer[] customers = xenditClient.customer.getCustomerByReferenceId("test-reference-id");
            System.out.println(Arrays.toString(customers));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
