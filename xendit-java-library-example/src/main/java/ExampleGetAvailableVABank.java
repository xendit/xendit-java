import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.model.VirtualAccount;

import java.util.Arrays;

public class ExampleGetAvailableVABank {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            AvailableBank[] banks = VirtualAccount.getAvailableBank();
            System.out.println(Arrays.toString(banks));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}