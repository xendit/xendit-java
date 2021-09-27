package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xenditclient.ewallet.EWalletBasketItem;
import com.xendit.XenditClient;
import com.xenditclient.ewallet.EWalletCharge;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateEWalletCharge {
  private static void createCharge(XenditClient xenditClient) {
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


      EWalletCharge charge = xenditClient.eWallet.createEWalletCharge(
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

  private static void createCharge2(XenditClient xenditClient) {
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

      EWalletCharge charge = xenditClient.eWallet.createEWalletCharge(params);
      System.out.println(charge.getId());
      System.out.println(charge.getBusinessId());
      System.out.println(charge.getReferenceId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
            .build();

    createCharge(xenditClient);
    createCharge2(xenditClient);
  }
}
