package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.Customer;

import java.util.Arrays;

public class ExampleGetCustomerByReferenceId {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            Customer[] customers = Customer.getCustomerByReferenceId("test-reference-id");
            System.out.println(Arrays.toString(customers));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
