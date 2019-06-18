import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.VirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateClosedVA {
    public static void main(String[] args) {
        Xendit.apiUrl = "http://localhost:8040";
        Xendit.apiKey = "xnd_development_k0rPl6UtKBXvy35axIYUwZjP8KP4Dxla5pwuAvVJfhNSaArq9XSg94nJMycPOd";

        Map<String, Object> closedVAMap = new HashMap<String, Object>();
        closedVAMap.put("external_id", "random");
        closedVAMap.put("bank_code", VirtualAccount.BankCode.BNI.getText());
        closedVAMap.put("name", "HAKIEMM");
        closedVAMap.put("expected_amount", 20000);

        try {
//            VirtualAccount virtualAccount = VirtualAccount.createClosed(closedVAMap);
            VirtualAccount virtualAccount = VirtualAccount.createClosed("random",
                    VirtualAccount.BankCode.PERMATA.getText(), "Name Saya Hakiem", new Long(100000));
//            VirtualAccount virtualAccount = VirtualAccount.createClosed("random",
//                    VirtualAccount.BankCode.MANDIRI.getText(), "Name Saya Hakiem", new Long(100000),
//                    closedVAMap);
            System.out.println(virtualAccount);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
