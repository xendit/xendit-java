import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleUpdateVA {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      Map<String, Object> params = new HashMap<>();
      params.put("is_single_use", true);
      FixedVirtualAccount fixedVirtualAccount = FixedVirtualAccount.update("5e2fd16370279c782d5f56a3", params);
      System.out.println(fixedVirtualAccount.getIsSingleUse());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
