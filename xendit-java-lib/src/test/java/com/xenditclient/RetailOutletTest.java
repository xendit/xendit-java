package com.xenditclient;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xenditclient.retailOutlet.FixedPaymentCode;
import com.xendit.network.RequestResource;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xenditclient.retailOutlet.RetailOutletClient;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RetailOutletTest {
    private static final String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/fixed_payment_code");
    private static Map<String, String> HEADERS = new HashMap<>();
    private static Map<String, Object> PARAMS = new HashMap<>();
    private static String TEST_ID = "5e0cb0bbf4d38b20d5421b72";
    private static String TEST_EXTERNAL_ID = "test_id";
    NetworkClient requestClient = mock(BaseRequest.class);
    Xendit.Option opt = mock(Xendit.Option.class);
    RetailOutletClient retailOutletClient = mock(RetailOutletClient.class);
    private static FixedPaymentCode VALID_FPC = new FixedPaymentCode();

    @Before
    public void initMocks() {
        VALID_FPC.setId(TEST_ID);
        VALID_FPC.setExternalId(TEST_EXTERNAL_ID);

        Xendit.Opt.setApiKey(
                "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
        Xendit.setRequestClient(requestClient);
    }

    @Test
    public void createFixedPaymentCode_Success_IfParamsAreValid() throws XenditException {
        PARAMS.put("external_id", TEST_EXTERNAL_ID);
        PARAMS.put("retail_outlet_name", FixedPaymentCode.RetailOutletName.ALFAMART);
        PARAMS.put("name", "Rika Sutanto");
        PARAMS.put("expected_amount", 10000);

        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), FixedPaymentCode.class))
                .thenReturn(VALID_FPC);
        when(retailOutletClient.createFixedPaymentCode(PARAMS)).thenReturn(VALID_FPC);
        FixedPaymentCode fpc = retailOutletClient.createFixedPaymentCode(PARAMS);
        assertEquals(fpc, VALID_FPC);
    }

    @Test(expected = XenditException.class)
    public void createFixedPaymentCode_ThrowsException_IfInvalidParams() throws XenditException {
        when(this.requestClient.request(
                RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), FixedPaymentCode.class))
                .thenThrow(
                        new XenditException("There was an error with the format submitted to the server."));
        when(retailOutletClient.createFixedPaymentCode(PARAMS)).thenThrow(new XenditException("There was an error with the format submitted to the server."));
        retailOutletClient.createFixedPaymentCode(PARAMS);
    }

    @Test
    public void getFixedPaymentCode_Success_IfIdIsAvailable() throws XenditException {
        String url = String.format("%s%s%s", URL, "/", TEST_ID);
        when(this.requestClient.request(
                RequestResource.Method.GET, url, HEADERS, null, opt.getApiKey(), FixedPaymentCode.class))
                .thenReturn(VALID_FPC);
        when(retailOutletClient.getFixedPaymentCode(TEST_ID)).thenReturn(VALID_FPC);
        FixedPaymentCode fpc = retailOutletClient.getFixedPaymentCode(TEST_ID);
        assertEquals(fpc.getId(), VALID_FPC.getId());
    }

    @Test(expected = XenditException.class)
    public void getFixedPaymentCode_ThrowsException_IfIdIsNotAvailable() throws XenditException {
        String url = String.format("%s%s%s", URL, "/", "fake_id");
        when(this.requestClient.request(
                RequestResource.Method.GET, url, HEADERS, null, opt.getApiKey(), FixedPaymentCode.class))
                .thenThrow(new XenditException("Fixed payment code not found"));
        when(retailOutletClient.getFixedPaymentCode("fake_id")).thenThrow(new XenditException("Fixed payment code not found"));
        retailOutletClient.getFixedPaymentCode("fake_id");
    }

    @Test
    public void updateFixedPaymentCode_Success_IfIdIsAvailable() throws XenditException {
        PARAMS.put("name", "New Name");
        String url = String.format("%s%s%s", URL, "/", TEST_ID);
        FixedPaymentCode result = new FixedPaymentCode();
        result.setId(TEST_ID);
        result.setExternalId(TEST_EXTERNAL_ID);
        result.setName("New Name");

        when(this.requestClient.request(
                RequestResource.Method.PATCH, url, HEADERS, PARAMS,opt.getApiKey(), FixedPaymentCode.class))
                .thenReturn(result);
        when(retailOutletClient.updateFixedPaymentCode(TEST_ID,PARAMS)).thenReturn(result);
        FixedPaymentCode fpc = retailOutletClient.updateFixedPaymentCode(TEST_ID, PARAMS);
        assertEquals(fpc, result);
    }

    @Test(expected = XenditException.class)
    public void updateFixedPaymentCode_ThrowsException_IfIdIsNotAvailable() throws XenditException {
        String url = String.format("%s%s%s", URL, "/", "fake_id");
        when(this.requestClient.request(
                RequestResource.Method.PATCH, url, HEADERS, PARAMS,opt.getApiKey(), FixedPaymentCode.class))
                .thenThrow(new XenditException("Fixed payment code not found"));
        when(retailOutletClient.updateFixedPaymentCode("fake_id",PARAMS)).thenThrow(new XenditException("Fixed payment code not found"));
        retailOutletClient.updateFixedPaymentCode("fake_id", PARAMS);
    }
}
