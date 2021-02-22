import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.EWalletCharge;

public class ExampleGetEWalletCharge {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      EWalletCharge charge = EWalletCharge.getEWalletChargeStatus("ewc_c8630205-3e7a-4511-8250-26a084480c4c");
      System.out.println(charge.getId());
      System.out.println(charge.getBusinessId());
      System.out.println(charge.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
