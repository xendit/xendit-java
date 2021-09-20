package com.xenditclient;

import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xenditclient.balance.Balance;
import com.xenditclient.balance.BalanceClient;
import com.xenditclient.network.BaseRequest;
import com.xenditclient.network.NetworkClient;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BalanceTest {
    private static String URL = String.format("%s%s", com.xendit.Xendit.getUrl(), "/balance");
    private static Map<String, String> HEADERS = new HashMap<>();
    Balance VALID_BALANCE = new Balance();
    NetworkClient requestClient = mock(BaseRequest.class);
    Xendit.Option opt = mock(Xendit.Option.class);
    BalanceClient balanceClient = mock(BalanceClient.class);

    @Before
    public void initMocks() {
        VALID_BALANCE.setBalance(10000000);
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
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
