import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.VirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateOpenVA {
    public static void main(String[] args) {
        Xendit.apiUrl = "http://localhost:8040";
        Xendit.apiKey = "xnd_development_k0rPl6UtKBXvy35axIYUwZjP8KP4Dxla5pwuAvVJfhNSaArq9XSg94nJMycPOd";

        Map<String, Object> closedVAMap = new HashMap<String, Object>();
        closedVAMap.put("external_id", "random");
        closedVAMap.put("bank_code", VirtualAccount.BankCode.BNI.getText());
        closedVAMap.put("name", "HAKIEMM");

        try {
            VirtualAccount virtualAccount = VirtualAccount.createOpen(closedVAMap);
//            VirtualAccount virtualAccount = VirtualAccount.createOpen("random",
//                    VirtualAccount.BankCode.BRI.getText(), "Name Saya Hakiem");
//            VirtualAccount virtualAccount = VirtualAccount.createOpen("random",
//                    VirtualAccount.BankCode.MANDIRI.getText(), "Name Saya Hakiem",
//                    closedVAMap);
            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
