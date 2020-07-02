package com.xendit.model;

import static org.junit.Assert.assertArrayEquals;
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

public class BatchDisbursementTest {
  private static String TEST_REFERENCE = "test_ref";
  private static BatchDisbursement VALID_BATCH =
      BatchDisbursement.builder().reference(TEST_REFERENCE).build();

  @Before
  public void initMocks() {
    Xendit.requestClient = mock(BaseRequest.class);
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
    BatchDisbursementItem[] items = new BatchDisbursementItem[] {item};
    Map<String, Object> params = new HashMap<>();
    params.put("reference", TEST_REFERENCE);
    params.put("disbursements", items);
    String url = String.format("%s%s", Xendit.getUrl(), "/batch_disbursements");

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, new HashMap<>(), params, BatchDisbursement.class))
        .thenReturn(VALID_BATCH);
    BatchDisbursement batchDisbursement = BatchDisbursement.create(TEST_REFERENCE, items);

    assertEquals(batchDisbursement, VALID_BATCH);
  }

  @Test(expected = XenditException.class)
  public void create_ThrowsException_IfParamsAreInvalid() throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("reference", TEST_REFERENCE);
    params.put("disbursements", null);
    String url = String.format("%s%s", Xendit.getUrl(), "/batch_disbursements");

    when(Xendit.requestClient.request(
            RequestResource.Method.POST, url, new HashMap<>(), params, BatchDisbursement.class))
        .thenThrow(
            new XenditException("There was an error with the format submitted to the server."));
    BatchDisbursement.create(TEST_REFERENCE, null);
  }

  @Test
  public void getAvailableBanks_Success() throws XenditException {
    AvailableBank bank = AvailableBank.builder().build();
    AvailableBank[] banks = new AvailableBank[] {bank};
    String url = String.format("%s%s", Xendit.getUrl(), "/available_disbursements_banks");

    when(Xendit.requestClient.request(RequestResource.Method.GET, url, null, AvailableBank[].class))
        .thenReturn(banks);
    AvailableBank[] result = BatchDisbursement.getAvailableBanks();

    assertArrayEquals(result, banks);
  }
}
