package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Beneficiary;
import com.xendit.model.DisbursementClient;
import com.xendit.model.DisbursementPH;
import com.xendit.model.ReceiptNotification;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class DisbursementPHTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/disbursement");
  private static String ID = "test-id";
  private static String REFERNCEID = "test-refernce-id";
  private static String CURRENCY = "PHP";
  private static Integer AMOUNT = 50000;
  private static String CHANNEL_CODE = "PH_CIMB";
  private static String DESCRIPTION = "test description";
  private static String STATUS = "PENDING";
  private static Date CREATED = new Date();
  private static Date UPDATED = new Date();

  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  DisbursementClient disbursementClient = mock(DisbursementClient.class);

  private static ReceiptNotification RECEIPTNOTIFICATION =
      ReceiptNotification.builder()
          .emailTo(new String[] {"test@emailTo.com"})
          .emailCC(new String[] {"test@emailCC.com"})
          .emailBcc(new String[] {"test@emailBcc.com"})
          .build();
  private static Beneficiary BENIFICIARY =
      Beneficiary.builder()
          .type("test-type")
          .givenNames("Test Name")
          .middleName("Middle Name")
          .surname("Sur Name")
          .businessName("Test")
          .streetLine1("Jl. 123")
          .streetLine2("Jl. 456")
          .city("Jakarta Selatan")
          .province("DKI Jakarta")
          .state("Test")
          .country("Test")
          .zipCode("12345")
          .mobileNumber("123456789")
          .phoneNumber("123456789")
          .email("email@test.com")
          .build();
  private static DisbursementPH VALID_PH_DISBURSEMENT =
      DisbursementPH.builder()
          .id(ID)
          .referenceId(REFERNCEID)
          .currency(CURRENCY)
          .amount(AMOUNT)
          .channelCode(CHANNEL_CODE)
          .description(DESCRIPTION)
          .status(STATUS)
          .created(CREATED)
          .updated(UPDATED)
          .receiptNotification(RECEIPTNOTIFICATION)
          .build();

  @Before
  public void initMocks() {
    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
    HEADERS.clear();
    PARAMS.clear();
  }

  @Test
  public void getById_Success_IfMethodCalledCorrectly() throws XenditException {
    String url = String.format("%s/", URL, ID);
    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementPH.class))
        .thenReturn(VALID_PH_DISBURSEMENT);
    when(disbursementClient.getPHById(HEADERS, ID)).thenReturn(VALID_PH_DISBURSEMENT);

    DisbursementPH disbursement = disbursementClient.getPHById(HEADERS, ID);

    assertEquals(VALID_PH_DISBURSEMENT, disbursement);
  }

  @Test(expected = XenditException.class)
  public void getById_ThrowsException_IfServerError() throws XenditException {
    String url = String.format("%s/", URL, ID);
    when(this.requestClient.request(
            RequestResource.Method.GET,
            url,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementPH.class))
        .thenThrow(new XenditException("Something went wrong"));
    when(disbursementClient.getPHById(HEADERS, ID))
        .thenThrow(new XenditException("Something went wrong"));
    disbursementClient.getPHById(HEADERS, ID);
  }

  @Test(expected = XenditException.class)
  public void getById_ThrowsException_IfInvalidArgs() throws XenditException {
    String url = String.format("%s/", URL, "");

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), DisbursementPH.class))
        .thenThrow(new XenditException("Invalid Arguments"));
    when(disbursementClient.getPHById(HEADERS, ""))
        .thenThrow(new XenditException("Invalid Arguments"));

    disbursementClient.getPHById(HEADERS, "");
  }

  private void initCreateParams() {
    PARAMS.put("xendit_idempotency_key", "xendit_idempotency_key");
    PARAMS.put("reference_id", REFERNCEID);
    PARAMS.put("currency", CURRENCY);
    PARAMS.put("channel_code", CHANNEL_CODE);
    PARAMS.put("account_name", "account_name");
    PARAMS.put("account_number", "account_number");
    PARAMS.put("description", DESCRIPTION);
    PARAMS.put("amount", AMOUNT);
  }

  @Test
  public void createDisbursementPH_Success_IfOnlyRequiredParamsAreProvided()
      throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementPH.class))
        .thenReturn(VALID_PH_DISBURSEMENT);
    when(disbursementClient.createPHRequest(HEADERS, PARAMS)).thenReturn(VALID_PH_DISBURSEMENT);
    DisbursementPH disbursement = disbursementClient.createPHRequest(HEADERS, PARAMS);

    assertEquals(disbursement, VALID_PH_DISBURSEMENT);
  }

  @Test
  public void createDisbursementPH_Success_IfOptionalParamsAreProvided() throws XenditException {
    initCreateParams();
    PARAMS.put("beneficiary", BENIFICIARY);
    PARAMS.put("receipt_notification", RECEIPTNOTIFICATION);
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementPH.class))
        .thenReturn(VALID_PH_DISBURSEMENT);
    when(disbursementClient.createPHRequest(HEADERS, PARAMS)).thenReturn(VALID_PH_DISBURSEMENT);
    DisbursementPH disbursement = disbursementClient.createPHRequest(HEADERS, PARAMS);

    assertEquals(disbursement, VALID_PH_DISBURSEMENT);
  }

  @Test
  public void createDisbursementPH_Success_IfOptionalBenificiaryIsValid() throws XenditException {
    initCreateParams();
    PARAMS.put("beneficiary", BENIFICIARY);
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementPH.class))
        .thenReturn(VALID_PH_DISBURSEMENT);
    when(disbursementClient.createPHRequest(HEADERS, PARAMS)).thenReturn(VALID_PH_DISBURSEMENT);
    DisbursementPH disbursement = disbursementClient.createPHRequest(HEADERS, PARAMS);

    assertEquals(disbursement, VALID_PH_DISBURSEMENT);
  }

  @Test
  public void createDisbursementPH_Success_IfOptionalRecepIsValid() throws XenditException {
    initCreateParams();
    PARAMS.put("receipt_notification", RECEIPTNOTIFICATION);
    System.out.println(PARAMS);
    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementPH.class))
        .thenReturn(VALID_PH_DISBURSEMENT);
    when(disbursementClient.createPHRequest(HEADERS, PARAMS)).thenReturn(VALID_PH_DISBURSEMENT);
    DisbursementPH disbursement = disbursementClient.createPHRequest(HEADERS, PARAMS);

    assertEquals(disbursement, VALID_PH_DISBURSEMENT);
  }

  @Test(expected = XenditException.class)
  public void createDisbursementPH_ThrowsException_IfParamsAreInvalid() throws XenditException {
    PARAMS.put("amount", "amount");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            DisbursementPH.class))
        .thenThrow(new XenditException("Amount is invalid"));
    when(disbursementClient.createPHRequest(HEADERS, PARAMS))
        .thenThrow(new XenditException("Amount is invalid"));
    disbursementClient.createPHRequest(HEADERS, PARAMS);
  }
}
