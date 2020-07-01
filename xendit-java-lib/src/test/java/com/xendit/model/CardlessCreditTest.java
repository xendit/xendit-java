package com.xendit.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.BaseRequest;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class CardlessCreditTest {
  private static String URL = String.format("%s%s", Xendit.getUrl(), "/cardless-credit");
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  private static CardlessCreditItem VALID_ITEM =
      CardlessCreditItem.builder()
          .id("123")
          .name("Phone Case")
          .price(200000)
          .type("Smartphone")
          .url("https://www.example.org")
          .quantity(1)
          .build();
  private static CardlessCreditCustomer VALID_CUSTOMER =
      CardlessCreditCustomer.builder()
          .firstName("Lorem")
          .lastName("Ipsum")
          .email("email@example.com")
          .phone("08129748247684")
          .build();
  private static CardlessCreditShippingAddress VALID_ADDRESS =
      CardlessCreditShippingAddress.builder()
          .firstName("Lorem")
          .lastName("Ipsum")
          .address("Jalan teknologi")
          .city("Jakarta")
          .postalCode("12345")
          .countryCode("IDN")
          .phone("08129748247684")
          .build();
  private static CardlessCredit VALID_CREDIT = CardlessCredit.builder().build();

  @Before
  public void initMocks() {
    Xendit.requestClient = mock(BaseRequest.class);
    PARAMS.clear();
    PARAMS.put("cardless_credit_type", "KREDIVO");
    PARAMS.put("external_id", "external_id");
    PARAMS.put("amount", 200000);
    PARAMS.put("payment_type", CardlessCredit.PaymentType.THREE_MONTHS.getVal());
    PARAMS.put("items", new CardlessCreditItem[] {VALID_ITEM});
    PARAMS.put("customer_details", VALID_CUSTOMER);
    PARAMS.put("shipping_address", VALID_ADDRESS);
    PARAMS.put("redirect_url", "www.example.com");
    PARAMS.put("callback_url", "www.example.com");
  }

  @Test
  public void create_Success_IfParamsAreValid() throws XenditException {
    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, CardlessCredit.class))
        .thenReturn(VALID_CREDIT);
    CardlessCredit cardlessCredit = CardlessCredit.create(PARAMS);

    assertEquals(cardlessCredit, VALID_CREDIT);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.clear();

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, CardlessCredit.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    CardlessCredit.create(PARAMS);
  }
}
