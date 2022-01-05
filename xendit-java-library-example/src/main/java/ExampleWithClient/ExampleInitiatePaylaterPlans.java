package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterOrderItem;
import com.xendit.model.PaylaterPlans;
import com.xendit.XenditClient;

import java.util.HashMap;
import java.util.Map;

public class ExampleInitiatePaylaterPlans {
  private static void initiatePlans(XenditClient xenditClient) {
    try {
      PaylaterOrderItem orderItems = PaylaterOrderItem.builder()
          .type("type")
          .referenceId("reference_id")
          .name("name")
          .netUnitAmount("net_unit_amount")
          .quantity(1)
          .url("url")
          .category("category")
          .subCategory("subCategory")
          .description("description")
          .build();
      PaylaterOrderItem[] orderItemsArray = new PaylaterOrderItem[] { orderItems };

      String customerId = "test-customer-id";
      String channelCode = "ID_KREDIVO";
      String currency = "IDR";
      String amount = "50000";

      PaylaterPlans initiatePlan = xenditClient.paylater.initiatePaylaterPlans(
          customerId,
          channelCode,
          currency,
          amount,
          orderItemsArray);
      System.out.println(initiatePlan.getId());
      System.out.println(initiatePlan.getBusinessId());
      System.out.println(initiatePlan.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
        .setApikey("xnd_development_...")
        .build();

    initiatePlans(xenditClient);
  }
}
