package com.xenditclient;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xenditclient.cardlessCredit.CardlessCreditCustomer;
import com.xenditclient.cardlessCredit.CardlessCreditItem;
import com.xenditclient.cardlessCredit.CardlessCreditShippingAddress;
import com.xendit.network.RequestResource;
import com.xenditclient.cardlessCredit.CardlessCredit;
import com.xenditclient.cardlessCredit.CardlessCreditClient;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardlessCreditTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/cardless-credit");
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  CardlessCreditClient cardlessCreditClient = mock(CardlessCreditClient.class);
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
  private static CardlessCredit VALID_CREDIT = new CardlessCredit();

  @Before
  public void initMocks() {
    Xendit.Opt.setApiKey(
            "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
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
    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS,opt.getApiKey(), CardlessCredit.class))
        .thenReturn(VALID_CREDIT);
    when(cardlessCreditClient.create(PARAMS)).thenReturn(VALID_CREDIT);
    CardlessCredit cardlessCredit = cardlessCreditClient.create(PARAMS);
    assertEquals(cardlessCredit, VALID_CREDIT);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.clear();

    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS,opt.getApiKey(), CardlessCredit.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    when(cardlessCreditClient.create(PARAMS)).thenThrow(new XenditException("There was an error with the format submitted to the server."));
    cardlessCreditClient.create(PARAMS);
  }
}
