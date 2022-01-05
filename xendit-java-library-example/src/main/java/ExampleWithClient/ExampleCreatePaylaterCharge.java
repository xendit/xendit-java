package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterOrderItem;
import com.xendit.XenditClient;
import com.xendit.model.PaylaterCharge;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePaylaterCharge {
  private static void createCharge(XenditClient xenditClient) {
    try {
      String planId = "test-plan-id";
      String referenceId = "test-reference-id";
      String checkoutMethod = "ONE_TIME_PAYMENT";
      String successRedirectUrl = "https://yourwebsite.com/order/123";

      PaylaterCharge charge = xenditClient.paylater.createPaylaterCharges(
          planId,
          referenceId,
          checkoutMethod,
          successRedirectUrl,
          null,
          null,
          null);
      System.out.println(charge.getId());
      System.out.println(charge.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
        .setApikey("xnd_development_...")
        .build();

    createCharge(xenditClient);
  }
}
