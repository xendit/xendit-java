package withoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.Xendit;
import com.xenditclient.ewallet.EWalletCharge;

public class ExampleGetEWalletCharge {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";


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
