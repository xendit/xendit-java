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
          .url("https://google.com")
          .category("category")
          .subCategory("subCategory")
          .description("description")
          .build();
      PaylaterOrderItem[] orderItemsArray = new PaylaterOrderItem[] { orderItems };

      String customerId = "test-customer-id";
      String channelCode = "ID_KREDIVO";
      String currency = "IDR";
      Number amount = new Integer(50000);

      PaylaterPlans initiatePlan = PaylaterPlans.initiatePaylaterPlans(
          customerId,
          channelCode,
          currency,
          amount,
          orderItemsArray);
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // access key with Option
    Xendit.Opt.setApiKey("xnd_development_HhEaDg8BL32lxtHdNaABWgQ3LvnIEtFtwXIDdK1M9v2imuMwpLWPyAJNMVKq58");

    // access static variable (same as old code )
    // Xendit.apiKey = "xnd_development_...";
    initiatePlans();
  }
}
