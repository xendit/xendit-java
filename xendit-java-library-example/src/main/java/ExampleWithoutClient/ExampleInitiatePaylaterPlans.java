package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterOrderItem;
import com.xendit.model.PaylaterPlans;
import com.xendit.Xendit;

public class ExampleInitiatePaylaterPlans {
  private static void initiatePlans() {
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

      PaylaterPlans initiatePlan = PaylaterPlans.initiatePaylaterPlans(
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
    // access key with Option
    Xendit.Opt.setApiKey("xnd_development_...");

    // access static variable (same as old code )
    // Xendit.apiKey = "xnd_development_...";
    initiatePlans();
  }
}
