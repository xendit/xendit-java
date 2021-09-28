package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.ewallet.EWalletBasketItem;
import com.xendit.Xendit;
import com.xendit.model.ewallet.EWalletCharge;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateEWalletCharge {
  private static void createCharge() {
    try {
      Map<String, String> channelProperties = new HashMap<>();
      channelProperties.put("success_redirect_url", "https://yourwebsite.com/order/123");

      EWalletBasketItem basketItem =  EWalletBasketItem.builder()
          .referenceId("basket-product-ref-id")
          .name("product-name")
          .category("mechanics")
          .currency("IDR")
          .price(50000)
          .quantity(5)
          .type("product type")
          .subCategory("product sub category")
          .build();
      EWalletBasketItem[] basketItemArray = new EWalletBasketItem[]{basketItem};

      String referenceId = "test-reference-id";
      String currency = "IDR";
      Number amount = new Integer("1688");
      String checkoutMethod = "ONE_TIME_PAYMENT";
      String channelCode = "ID_SHOPEEPAY";


      EWalletCharge charge = EWalletCharge.createEWalletCharge(
          referenceId,
          currency,
          amount,
          checkoutMethod,
          channelCode,
          channelProperties,
          null,
          basketItemArray,
          null
      );
      System.out.println(charge.getId());
      System.out.println(charge.getBusinessId());
      System.out.println(charge.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  private static void createCharge2() {
    try {
      Map<String, String> channelProperties = new HashMap<>();
      channelProperties.put("success_redirect_url", "https://yourwebsite.com/order/123");

      Map<String, Object> params = new HashMap<>();
      params.put("reference_id", "test-reference-id");
      params.put("currency", "IDR");
      params.put("amount", 50000);
      params.put("checkout_method", "ONE_TIME_PAYMENT");
      params.put("channel_code", "ID_SHOPEEPAY");
      params.put("channel_properties", channelProperties);

      EWalletCharge charge = EWalletCharge.createEWalletCharge(params);
      System.out.println(charge.getId());
      System.out.println(charge.getBusinessId());
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
    createCharge2();
  }
}
