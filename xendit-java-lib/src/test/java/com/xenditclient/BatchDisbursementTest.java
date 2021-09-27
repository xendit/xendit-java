package com.xenditclient;

import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.model.BatchDisbursementItem;
import com.xendit.network.RequestResource;
import com.xenditclient.batchDisbursements.BatchDisbursement;
import com.xenditclient.batchDisbursements.BatchDisbursementClient;
import com.xenditclient.network.BaseRequest;
import com.xenditclient.network.NetworkClient;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatchDisbursementTest {
    private static String TEST_REFERENCE = "test_ref";
    NetworkClient requestClient = mock(BaseRequest.class);
    Xendit.Option opt = mock(Xendit.Option.class);
    BatchDisbursementClient batchDisbursementClient = mock(BatchDisbursementClient.class);
    private static BatchDisbursement VALID_BATCH = new BatchDisbursement();

    @Before
    public void initMocks() {
        VALID_BATCH.setReference(TEST_REFERENCE);
        Xendit.Opt.setApiKey(
                "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
        Xendit.setRequestClient(requestClient);
    }

    @Test
    public void create_Success_IfParamsAreValid() throws XenditException {
        BatchDisbursementItem item =
                BatchDisbursementItem.builder()
                        .amount(15000)
                        .bankAccountName("Joe")
                        .bankCode("BCA")
                        .bankAccountNumber("1234567890")
                        .description("Example")
                        .build();
        BatchDisbursementItem[] items = new BatchDisbursementItem[]{item};
        Map<String, Object> params = new HashMap<>();
        params.put("reference", TEST_REFERENCE);
        params.put("disbursements", items);
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/batch_disbursements");

        when(this.requestClient.request(
                RequestResource.Method.POST, url, new HashMap<>(), params, opt.getApiKey(), BatchDisbursement.class))
                .thenReturn(VALID_BATCH);
        when(batchDisbursementClient.create(TEST_REFERENCE,items)).thenReturn(VALID_BATCH);
        BatchDisbursement batchDisbursement = batchDisbursementClient.create(TEST_REFERENCE, items);

        assertEquals(batchDisbursement, VALID_BATCH);
    }

    @Test(expected = XenditException.class)
    public void create_ThrowsException_IfParamsAreInvalid() throws XenditException {
        Map<String, Object> params = new HashMap<>();
        params.put("reference", TEST_REFERENCE);
        params.put("disbursements", null);
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/batch_disbursements");

        when(this.requestClient.request(
                RequestResource.Method.POST, url, new HashMap<>(), params, opt.getApiKey(), BatchDisbursement.class))
                .thenThrow(
                        new XenditException("There was an error with the format submitted to the server."));
        when(batchDisbursementClient.create(TEST_REFERENCE,null)).thenThrow(new XenditException("There was an error with the format submitted to the server."));
      batchDisbursementClient.create(TEST_REFERENCE, null);
    }

    @Test
    public void getAvailableBanks_Success() throws XenditException {
        AvailableBank bank = AvailableBank.builder().build();
        AvailableBank[] banks = new AvailableBank[]{bank};
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/available_disbursements_banks");

        when(this.requestClient.request(RequestResource.Method.GET, url, null, opt.getApiKey(), AvailableBank[].class))
                .thenReturn(banks);
        when(batchDisbursementClient.getAvailableBanks()).thenReturn(banks);
        AvailableBank[] result = batchDisbursementClient.getAvailableBanks();

        assertArrayEquals(result, banks);
    }
}
