package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.*;
import com.xendit.model.LinkedAccountEnum.AccountType;
import com.xendit.model.LinkedAccountEnum.ChannelCode;
import com.xendit.network.BaseRequest;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class LinkedAccountTest {
  private static String TOKEN_ID = "lat-f9dc34e7-153a-444e-b337-cd2599e7f670";
  private static String INITIALIZE_URL =
      String.format("%s%s", Xendit.Opt.getXenditURL(), "/linked_account_tokens/auth");
  private static String VALIDATE_URL =
      String.format(
          "%s%s%s%s",
          Xendit.Opt.getXenditURL(), "/linked_account_tokens/", TOKEN_ID, "/validate_otp");
  private static String GET_ACCESSIBLE_URL =
      String.format(
          "%s%s%s%s", Xendit.Opt.getXenditURL(), "/linked_account_tokens/", TOKEN_ID, "/accounts");
  private static String UNBIND_URL =
      String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/linked_account_tokens/", TOKEN_ID);
  private static String CUSTOMER_ID = "6778b829-1936-4c4a-a321-9a0178840571";
  private static ChannelCode CHANNEL_CODE = ChannelCode.DC_BRI;
  private static AccountType ACCOUNT_TYPE = AccountType.DEBIT_CARD;
  private static String PENDING_STATUS = "PENDING";
  private static String SUCCESS_STATUS = "SUCCESS";
  private static String OTP_CODE = "333000";
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(Xendit.Option.class);
  DirectDebitPaymentClient directDebitPaymentClient = mock(DirectDebitPaymentClient.class);
  private static Map<String, Object> PROPERTIES =
      new HashMap<String, Object>() {
        {
          put("account_mobile_number", "+62818555988");
          put("card_last_four", "8888");
          put("card_expiry", "06/24");
          put("account_email", "test.email@xendit.co");
        }
      };
  private static InitializedLinkedAccount VALID_INITIALIZED_LINKED_ACCOUNT =
      InitializedLinkedAccount.builder()
          .id(TOKEN_ID)
          .customerId(CUSTOMER_ID)
          .channelCode(CHANNEL_CODE)
          .status(PENDING_STATUS)
          .build();
  private static ValidatedLinkedAccount VALID_VALIDATED_LINKED_ACCOUNT =
      ValidatedLinkedAccount.builder()
          .id(TOKEN_ID)
          .customerId(CUSTOMER_ID)
          .channelCode(CHANNEL_CODE)
          .status(SUCCESS_STATUS)
          .build();
  private static AccessibleLinkedAccount ACCESSIBLE_LINKED_ACCOUNT =
      AccessibleLinkedAccount.builder()
          .id(TOKEN_ID)
          .channelCode(CHANNEL_CODE)
          .type(ACCOUNT_TYPE)
          .properties(PROPERTIES)
          .build();
  private static AccessibleLinkedAccount[] ACCESSIBLE_LINKED_ACCOUNT_ARRAY =
      new AccessibleLinkedAccount[] {ACCESSIBLE_LINKED_ACCOUNT};
  private static UnbindedLinkedAccount VALID_UNBINDED_LINKED_ACCOUNT =
      UnbindedLinkedAccount.builder().id(TOKEN_ID).isDeleted(true).build();

  @Before
  public void initMocks() {
    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);

    HEADERS.clear();
    PARAMS.clear();
  }

  private void initInitializeLinkedAccountParams() {
    PARAMS.put("customer_id", CUSTOMER_ID);
    PARAMS.put("channel_code", CHANNEL_CODE);
    PARAMS.put("properties", PROPERTIES);
    PARAMS.put("metadata", null);
  }

  private void initValidateOTPParams() {
    PARAMS.put("otp_code", OTP_CODE);
  }

  @Test
  public void initializeLinkedAccount_Success_IfMethodCalledCorrectly() throws XenditException {
    initInitializeLinkedAccountParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            INITIALIZE_URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            InitializedLinkedAccount.class))
        .thenReturn(VALID_INITIALIZED_LINKED_ACCOUNT);
    when(directDebitPaymentClient.initializeLinkedAccountTokenization(
            CUSTOMER_ID, CHANNEL_CODE, PROPERTIES, null))
        .thenReturn(VALID_INITIALIZED_LINKED_ACCOUNT);

    InitializedLinkedAccount initializedLinkedAccount =
        directDebitPaymentClient.initializeLinkedAccountTokenization(
            CUSTOMER_ID, CHANNEL_CODE, PROPERTIES, null);

    assertEquals(VALID_INITIALIZED_LINKED_ACCOUNT, initializedLinkedAccount);
  }

  @Test
  public void initializeLinkedAccount_Success_IfParamsIsValid() throws XenditException {
    initInitializeLinkedAccountParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            INITIALIZE_URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            InitializedLinkedAccount.class))
        .thenReturn(VALID_INITIALIZED_LINKED_ACCOUNT);

    when(directDebitPaymentClient.initializeLinkedAccountTokenization(PARAMS))
        .thenReturn(VALID_INITIALIZED_LINKED_ACCOUNT);

    InitializedLinkedAccount initializedLinkedAccount =
        directDebitPaymentClient.initializeLinkedAccountTokenization(PARAMS);

    assertEquals(VALID_INITIALIZED_LINKED_ACCOUNT, initializedLinkedAccount);
  }

  @Test
  public void initializeLinkedAccount_Success_WithHeaderProvided() throws XenditException {
    initInitializeLinkedAccountParams();
    HEADERS.put("for-user-id", "user-id");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            INITIALIZE_URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            InitializedLinkedAccount.class))
        .thenReturn(VALID_INITIALIZED_LINKED_ACCOUNT);

    when(directDebitPaymentClient.initializeLinkedAccountTokenization(HEADERS, PARAMS))
        .thenReturn(VALID_INITIALIZED_LINKED_ACCOUNT);

    InitializedLinkedAccount initializedLinkedAccount =
        directDebitPaymentClient.initializeLinkedAccountTokenization(HEADERS, PARAMS);

    assertEquals(VALID_INITIALIZED_LINKED_ACCOUNT, initializedLinkedAccount);
  }

  @Test(expected = XenditException.class)
  public void initializeLinkedAccount_ThrowsException_IfParamsIsInvalid() throws XenditException {
    initInitializeLinkedAccountParams();
    PARAMS.put("channel_code", "NOT_DC_BRI");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            INITIALIZE_URL,
            new HashMap<>(),
            PARAMS,
            opt.getApiKey(),
            InitializedLinkedAccount.class))
        .thenThrow(new XenditException("Channel code is invalid"));
    when(directDebitPaymentClient.initializeLinkedAccountTokenization(PARAMS))
        .thenThrow(new XenditException("Channel code is invalid"));

    directDebitPaymentClient.initializeLinkedAccountTokenization(PARAMS);
  }

  @Test
  public void validateOTP_Success_IfMethodCalledCorrectly() throws XenditException {
    initValidateOTPParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            VALIDATE_URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            ValidatedLinkedAccount.class))
        .thenReturn(VALID_VALIDATED_LINKED_ACCOUNT);

    when(directDebitPaymentClient.validateOTPWithToken(TOKEN_ID, OTP_CODE))
        .thenReturn(VALID_VALIDATED_LINKED_ACCOUNT);

    ValidatedLinkedAccount validatedLinkedAccount =
        directDebitPaymentClient.validateOTPWithToken(TOKEN_ID, OTP_CODE);

    assertEquals(VALID_VALIDATED_LINKED_ACCOUNT, validatedLinkedAccount);
  }

  @Test
  public void validateOTP_Success_IfParamsIsValid() throws XenditException {
    initValidateOTPParams();

    when(this.requestClient.request(
            RequestResource.Method.POST,
            VALIDATE_URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            ValidatedLinkedAccount.class))
        .thenReturn(VALID_VALIDATED_LINKED_ACCOUNT);

    when(directDebitPaymentClient.validateOTPWithToken(TOKEN_ID, PARAMS))
        .thenReturn(VALID_VALIDATED_LINKED_ACCOUNT);

    ValidatedLinkedAccount validatedLinkedAccount =
        directDebitPaymentClient.validateOTPWithToken(TOKEN_ID, PARAMS);

    assertEquals(VALID_VALIDATED_LINKED_ACCOUNT, validatedLinkedAccount);
  }

  @Test
  public void validateOTP_Success_WithHeaderProvided() throws XenditException {
    initValidateOTPParams();
    HEADERS.put("for-user-id", "user-id");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            VALIDATE_URL,
            HEADERS,
            PARAMS,
            opt.getApiKey(),
            ValidatedLinkedAccount.class))
        .thenReturn(VALID_VALIDATED_LINKED_ACCOUNT);

    when(directDebitPaymentClient.validateOTPWithToken(TOKEN_ID, HEADERS, PARAMS))
        .thenReturn(VALID_VALIDATED_LINKED_ACCOUNT);

    ValidatedLinkedAccount validatedLinkedAccount =
        directDebitPaymentClient.validateOTPWithToken(TOKEN_ID, HEADERS, PARAMS);

    assertEquals(VALID_VALIDATED_LINKED_ACCOUNT, validatedLinkedAccount);
  }

  @Test(expected = XenditException.class)
  public void validateOTP_ThrowsException_IfParamsIsInvalid() throws XenditException {
    initValidateOTPParams();
    PARAMS.put("otp_code", "000000");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            VALIDATE_URL,
            new HashMap<>(),
            PARAMS,
            opt.getApiKey(),
            ValidatedLinkedAccount.class))
        .thenThrow(new XenditException("OTP code is invalid"));
    when(directDebitPaymentClient.validateOTPWithToken(TOKEN_ID, PARAMS))
        .thenThrow(new XenditException("OTP code is invalid"));

    directDebitPaymentClient.validateOTPWithToken(TOKEN_ID, PARAMS);
  }

  @Test
  public void retrieveAccessibleAccounts_Success_IfTokenIdIsAvailable() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.GET,
            GET_ACCESSIBLE_URL,
            null,
            opt.getApiKey(),
            AccessibleLinkedAccount[].class))
        .thenReturn(ACCESSIBLE_LINKED_ACCOUNT_ARRAY);

    when(directDebitPaymentClient.retrieveAccessibleLinkedAccounts(TOKEN_ID))
        .thenReturn(ACCESSIBLE_LINKED_ACCOUNT_ARRAY);

    AccessibleLinkedAccount[] accessibleLinkedAccounts =
        directDebitPaymentClient.retrieveAccessibleLinkedAccounts(TOKEN_ID);

    assertEquals(ACCESSIBLE_LINKED_ACCOUNT_ARRAY, accessibleLinkedAccounts);
  }

  @Test(expected = XenditException.class)
  public void retrieveAccessibleAccounts_ThrowsException_IfTokenIdIsNotAvailable()
      throws XenditException {
    String NOT_AVAILABLE_TOKEN_ID = "not-available-token-id";
    GET_ACCESSIBLE_URL =
        String.format(
            "%s%s%s%s",
            Xendit.Opt.getXenditURL(),
            "/linked_account_tokens/",
            NOT_AVAILABLE_TOKEN_ID,
            "/accounts");

    when(this.requestClient.request(
            RequestResource.Method.GET,
            GET_ACCESSIBLE_URL,
            null,
            opt.getApiKey(),
            AccessibleLinkedAccount[].class))
        .thenThrow(new XenditException("Linked accounts not found"));
    when(directDebitPaymentClient.retrieveAccessibleLinkedAccounts(NOT_AVAILABLE_TOKEN_ID))
        .thenThrow(new XenditException("Linked accounts not found"));

    directDebitPaymentClient.retrieveAccessibleLinkedAccounts(NOT_AVAILABLE_TOKEN_ID);
  }

  @Test
  public void unbindLinkedAccount_Success_IfTokenIdIsAvailable() throws XenditException {
    when(this.requestClient.request(
            RequestResource.Method.DELETE,
            UNBIND_URL,
            null,
            opt.getApiKey(),
            UnbindedLinkedAccount.class))
        .thenReturn(VALID_UNBINDED_LINKED_ACCOUNT);

    when(directDebitPaymentClient.unbindLinkedAccountToken(TOKEN_ID))
        .thenReturn(VALID_UNBINDED_LINKED_ACCOUNT);

    UnbindedLinkedAccount unbindedLinkedAccount =
        directDebitPaymentClient.unbindLinkedAccountToken(TOKEN_ID);

    assertEquals(VALID_UNBINDED_LINKED_ACCOUNT, unbindedLinkedAccount);
  }

  @Test(expected = XenditException.class)
  public void unbindLinkedAccount_ThrowsException_IfTokenIdIsNotAvailable() throws XenditException {
    String NOT_AVAILABLE_TOKEN_ID = "not-available-token-id";
    UNBIND_URL =
        String.format(
            "%s%s%s", Xendit.Opt.getXenditURL(), "/linked_account_tokens/", NOT_AVAILABLE_TOKEN_ID);

    when(this.requestClient.request(
            RequestResource.Method.DELETE,
            UNBIND_URL,
            null,
            opt.getApiKey(),
            UnbindedLinkedAccount.class))
        .thenThrow(new XenditException("Linked account not found"));

    when(directDebitPaymentClient.unbindLinkedAccountToken(NOT_AVAILABLE_TOKEN_ID))
        .thenThrow(new XenditException("Linked account not found"));

    directDebitPaymentClient.unbindLinkedAccountToken(NOT_AVAILABLE_TOKEN_ID);
  }
}
