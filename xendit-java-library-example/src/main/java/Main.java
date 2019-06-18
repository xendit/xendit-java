import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.VirtualAccount;

public class Main {
    public static void main(String[] args) {
        Xendit.apiUrl = "http://localhost:8040";
        Xendit.apiKey = "xnd_development_k0rPl6UtKBXvy35axIYUwZjP8KP4Dxla5pwuAvVJfhNSaArq9XSg94nJMycPOd";

        try {
            VirtualAccount.AvailableBank[] banks = VirtualAccount.getAvailableBank();
            System.out.println(banks);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}