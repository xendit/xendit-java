package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.CardlessCreditCustomer;
import com.xendit.model.CardlessCreditItem;
import com.xendit.model.CardlessCreditShippingAddress;
import com.xendit.XenditClient;
import com.xendit.model.CardlessCredit;

public class ExampleCreateCardlessCredit {
  public static void main(String[] args) {
    //create xendit client which holds value of apikey
    XenditClient xenditClient = new XenditClient.Builder()
            .setApikey("xnd_development_...")
            .build();


    try {
      CardlessCreditItem item =
          CardlessCreditItem.builder()
          .id("123")
          .name("Phone Case")
          .price(200000)
          .type("Smartphone")
          .url("https://www.example.org")
          .quantity(1)
          .build();
      CardlessCreditItem[] items = new CardlessCreditItem[]{item};

      CardlessCreditCustomer customer =
          CardlessCreditCustomer.builder()
          .firstName("Lorem")
          .lastName("Ipsum")
          .email("email@example.com")
          .phone("08129748247684")
          .build();

      CardlessCreditShippingAddress address =
          CardlessCreditShippingAddress.builder()
          .firstName("Lorem")
          .lastName("Ipsum")
          .address("Jalan teknologi")
          .city("Jakarta")
          .postalCode("12345")
          .countryCode("IDN")
          .phone("08129748247684")
          .build();

      CardlessCredit cardlessCredit = xenditClient.cardlessCredit.create(
          "KREDIVO",
          "external_id",
          200000,
          CardlessCredit.PaymentType.THREE_MONTHS.getVal(),
          items,
          customer,
          address,
          "google.com",
          "google.com"
      );

      System.out.println(cardlessCredit.getCardlessCreditType());
      System.out.println(cardlessCredit.getRedirectUrl());
      System.out.println(cardlessCredit.getOrderId());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
