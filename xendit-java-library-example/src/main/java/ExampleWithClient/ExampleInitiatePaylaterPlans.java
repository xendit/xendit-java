package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.PaylaterPlans;
import com.xendit.Xendit;
import com.xendit.model.Paylater;

import java.util.HashMap;
import java.util.Map;

public class ExampleInitiatePaylaterPlans {
  private static void createPlans() {
    try {
      PaylaterOrderItem orderItems =  PaylaterOrderItem.builder()
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
      PaylaterOrderItem[] orderItemsArray = new PaylaterOrderItem[]{orderItems};

      String customerId = "test-customer-id";
      String channelCode = "ID_KREDIVO";
      String currency = "IDR";
      Number amount = new Integer("50000");

      PaylaterPlans initiatePlan = PaylaterPlans.initiatePaylaterPlans(
          customerId,
          channelCode,
          currency,
          amount,
          orderItemsArray
      );
      System.out.println(initiatePlan.getId());
      System.out.println(initiatePlan.getBusinessId());
      System.out.println(initiatePlan.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .setApiKey("xnd_development_...")
            .build();

    createPlans(xenditClient);
  }
}
