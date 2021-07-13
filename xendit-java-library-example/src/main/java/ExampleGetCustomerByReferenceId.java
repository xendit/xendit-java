import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Customer;

import java.util.Arrays;

public class ExampleGetCustomerByReferenceId {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Customer[] customers = Customer.getCustomerByReferenceId("test-reference-id");
            System.out.println(Arrays.toString(customers));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
