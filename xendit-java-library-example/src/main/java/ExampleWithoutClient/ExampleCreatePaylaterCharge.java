package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.EWalletBasketItem;
import com.xendit.Xendit;
import com.xendit.XenditClient;
import com.xendit.model.PaylaterCharge;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePaylaterCharge {
  private static void createCharge() {
    try {
      String planId = "test-plan-id";
      String referenceId = "order-id-456";
      String checkoutMethod = "ONE_TIME_PAYMENT";
      String successRedirectUrl = "https://yourwebsite.com/order/123";

      PaylaterCharge charge = PaylaterCharge.createPaylaterCharges(
          planId,
          referenceId,
          checkoutMethod,
          successRedirectUrl,
          null,
          null,
          null);
      System.out.println(charge.getPlanId());
      System.out.println(charge.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    // access static variable (same as old code )
    // Xendit.apiKey = "xnd_development_...";

    createCharge();
  }
}
