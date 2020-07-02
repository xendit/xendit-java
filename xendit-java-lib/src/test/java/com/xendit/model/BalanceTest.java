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

public class BalanceTest {
  private static String URL = String.format("%s%s", Xendit.getUrl(), "/balance");
  private static Map<String, String> HEADERS = new HashMap<>();
  private static Balance VALID_BALANCE = Balance.builder().balance(10000000).build();

  @Before
  public void initMocks() {
    Xendit.requestClient = mock(BaseRequest.class);
  }

  @Test
  public void get_Success_IfNoGivenParam() throws XenditException {
    when(Xendit.requestClient.request(
            RequestResource.Method.GET, URL, HEADERS, null, Balance.class))
        .thenReturn(VALID_BALANCE);
    Balance balance = Balance.get();
    assertEquals(balance, VALID_BALANCE);
  }

  @Test
  public void get_Success_IfGivenParam() throws XenditException {
    String url = String.format("%s%s", URL, "?account_type=CASH");
    when(Xendit.requestClient.request(
            RequestResource.Method.GET, url, HEADERS, null, Balance.class))
        .thenReturn(VALID_BALANCE);
    Balance balance = Balance.get(Balance.AccountType.CASH);
    assertEquals(balance, VALID_BALANCE);
  }
}
