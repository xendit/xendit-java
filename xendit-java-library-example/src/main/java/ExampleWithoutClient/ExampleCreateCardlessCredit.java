package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xenditclient.cardlessCredit.CardlessCreditCustomer;
import com.xenditclient.cardlessCredit.CardlessCreditItem;
import com.xenditclient.cardlessCredit.CardlessCreditShippingAddress;
import com.xendit.Xendit;
import com.xenditclient.cardlessCredit.CardlessCredit;

public class ExampleCreateCardlessCredit {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

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

      CardlessCredit cardlessCredit = CardlessCredit.create(
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
