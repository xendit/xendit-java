package withoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.Xendit;
import com.xenditclient.customer.Customer;

import java.util.Arrays;

public class ExampleGetCustomerByReferenceId {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

        try {
            Customer[] customers = Customer.getCustomerByReferenceId("test-reference-id");
            System.out.println(Arrays.toString(customers));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
