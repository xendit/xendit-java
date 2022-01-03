package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.EWalletBasketItem;
import com.xendit.XenditClient;
import com.xendit.model.EWalletCharge;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePaylaterCharge {
  private static void createCharge(XenditClient xenditClient) {
    try {
      String planId = "test-plan-id";
      String referenceId = "order-id-456";
      String checkoutMethod = "ONE_TIME_PAYMENT";
      String successRedirectUrl = "https://yourwebsite.com/order/123";

      PaylaterCharge charge = xenditClient.paylater.createPaylaterCharges(
          planId,
          referenceId,
          checkoutMethod,
          successRedirectUrl,
          null,
          null,
          null,
          null
      );
      System.out.println(charge.getId());
      System.out.println(charge.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_...";

    createCharge();
  }
}
