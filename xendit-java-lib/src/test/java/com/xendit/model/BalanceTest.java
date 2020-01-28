package com.xendit.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.BaseRequest;
import com.xendit.network.RequestResource;
import org.junit.Before;
import org.junit.Test;

public class BalanceTest {
  private static String URL = String.format("%s%s", Xendit.getUrl(), "/balance");
  private static Balance VALID_BALANCE = Balance.builder().balance(10000000).build();

  @Before
  public void initMocks() {
    Xendit.requestClient = mock(BaseRequest.class);
  }

  @Test
  public void get_Success_IfNoGivenParam() throws XenditException {
    when(Xendit.requestClient.request(RequestResource.Method.GET, URL, null, Balance.class))
        .thenReturn(VALID_BALANCE);
    Balance balance = Balance.get();
    assertEquals(balance, VALID_BALANCE);
  }

  @Test
  public void get_Success_IfGivenParam() throws XenditException {
    String url = String.format("%s%s", URL, "?account_type=CASH");
    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Balance.class))
        .thenReturn(VALID_BALANCE);
    Balance balance = Balance.get("CASH");
    assertEquals(balance, VALID_BALANCE);
  }

  @Test(expected = XenditException.class)
  public void get_ThrowsException_IfParamIsInvalid() throws XenditException {
    String url = String.format("%s%s", URL, "?account_type=WRONG_TYPE");
    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, Balance.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    Balance.get("WRONG_TYPE");
  }
}
