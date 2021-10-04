package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Balance;
import com.xendit.model.BalanceClient;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class BalanceTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/balance");
  private static Map<String, String> HEADERS = new HashMap<>();
  Balance VALID_BALANCE = Balance.builder().balance(10000000).build();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  BalanceClient balanceClient = mock(BalanceClient.class);

  @Before
  public void initMocks() {
    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
  }

  @Test
  public void get_Success_IfNoGivenParam() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.GET, URL, HEADERS, null, opt.getApiKey(), Balance.class))
        .thenReturn(VALID_BALANCE);
    when(balanceClient.get()).thenReturn(VALID_BALANCE);
    Balance balance = balanceClient.get();
    assertEquals(balance, VALID_BALANCE);
  }

  @Test
  public void get_Success_IfGivenParam() throws XenditException {
    String url = String.format("%s%s", URL, "?account_type=CASH");
    when(this.requestClient.request(
            RequestResource.Method.GET, url, HEADERS, null, opt.getApiKey(), Balance.class))
        .thenReturn(VALID_BALANCE);
    when(balanceClient.get(Balance.AccountType.CASH)).thenReturn(VALID_BALANCE);
    Balance balance = balanceClient.get(Balance.AccountType.CASH);
    assertEquals(balance, VALID_BALANCE);
  }
}
