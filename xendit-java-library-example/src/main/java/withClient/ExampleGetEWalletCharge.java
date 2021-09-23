package withClient;

import com.xendit.exception.XenditException;
import com.xenditclient.XenditClient;
import com.xenditclient.ewallet.EWalletCharge;

public class ExampleGetEWalletCharge {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
            .build();

    try {
      EWalletCharge charge = xenditClient.eWallet.getEWalletChargeStatus("ewc_29af0db6-fc4f-4c1f-bbdb-9194724b9c00");
      System.out.println(charge.getId());
      System.out.println(charge.getBusinessId());
      System.out.println(charge.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
