package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.ewallet.EWalletCharge;

public class ExampleGetEWalletCharge {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";


    try {
      EWalletCharge charge = EWalletCharge.getEWalletChargeStatus("ewc_29af0db6-fc4f-4c1f-bbdb-9194724b9c00");
      System.out.println(charge.getId());
      System.out.println(charge.getBusinessId());
      System.out.println(charge.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
