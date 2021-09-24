package com.xenditclient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.xendit.exception.XenditException;
import com.xendit.model.CustomerAddress;
import com.xendit.network.RequestResource;
import com.xenditclient.customer.Customer;
import com.xenditclient.customer.CustomerClient;
import com.xenditclient.network.BaseRequest;
import com.xenditclient.network.NetworkClient;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
  private static String URL = String.format("%s%s", Xendit.Opt.getXenditURL(), "/customers");
  private static String CUSTOMER_ID = "791ac956-397a-400f-9fda-4958894e61b5";
  private static String REFERENCE_ID = "test-reference-id";
  private static String EMAIL = "tes@tes.com";
  private static String MOBILE_NUMBER = "+6281234567890";
  private static String GIVEN_NAMES = "Given Names";
  private static String NATIONALITY = "ID";
  private static String DATE_OF_BIRTH = "1995-12-30";
  private static Map<String, Object> PARAMS = new HashMap<>();
  private static Map<String, String> HEADERS = new HashMap<>();
  NetworkClient requestClient = mock(BaseRequest.class);
  Xendit.Option opt = mock(com.xenditclient.Xendit.Option.class);
  CustomerClient customerClient = mock(CustomerClient.class);
  private static CustomerAddress CUSTOMER_ADDRESS =
      CustomerAddress.builder()
          .country("ID")
          .streetLine1("Jl. 123")
          .streetLine2("Jl. 456")
          .city("Jakarta Selatan")
          .province("DKI Jakarta")
          .state("-")
          .postalCode("12345")
          .category("None")
          .isPreferred(true)
          .build();
  private static CustomerAddress[] CUSTOMER_ADDRESS_ARRAY =
      new CustomerAddress[] {CUSTOMER_ADDRESS};
  private static Customer VALID_CUSTOMER = new Customer();
  private static Customer[] CUSTOMER_ARRAY = new Customer[] {VALID_CUSTOMER};

  @Before
  public void initMocks() {
    VALID_CUSTOMER.setId(CUSTOMER_ID);
    VALID_CUSTOMER.setReferenceId(REFERENCE_ID);
    VALID_CUSTOMER.setGivenNames(GIVEN_NAMES);
    VALID_CUSTOMER.setEmail(EMAIL);
    VALID_CUSTOMER.setMobileNumber(MOBILE_NUMBER);
    VALID_CUSTOMER.setNationality(NATIONALITY);
    VALID_CUSTOMER.setDateOfBirth(DATE_OF_BIRTH);
    VALID_CUSTOMER.setAddresses(CUSTOMER_ADDRESS_ARRAY);

    Xendit.Opt.setApiKey(
        "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");
    Xendit.setRequestClient(requestClient);
    HEADERS.clear();
    PARAMS.clear();
  }

  private void initCreateParams() {
    PARAMS.put("reference_id", REFERENCE_ID);
    PARAMS.put("given_names", GIVEN_NAMES);
    PARAMS.put("email", EMAIL);
    PARAMS.put("mobile_number", MOBILE_NUMBER);
    PARAMS.put("addresses", CUSTOMER_ADDRESS_ARRAY);
    PARAMS.put("middle_name", null);
    PARAMS.put("surname", null);
    PARAMS.put("description", null);
    PARAMS.put("phone_number", null);
    PARAMS.put("nationality", NATIONALITY);
    PARAMS.put("date_of_birth", DATE_OF_BIRTH);
    PARAMS.put("metadata", null);
  }

  @Test
  public void createCustomer_Success_IfMethodCalledCorrectly() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), Customer.class))
        .thenReturn(VALID_CUSTOMER);
    when(customerClient.createCustomer(
            REFERENCE_ID,
            MOBILE_NUMBER,
            EMAIL,
            GIVEN_NAMES,
            null,
            null,
            null,
            null,
            NATIONALITY,
            CUSTOMER_ADDRESS_ARRAY,
            DATE_OF_BIRTH,
            null))
        .thenReturn(VALID_CUSTOMER);

    Customer customer =
        customerClient.createCustomer(
            REFERENCE_ID,
            MOBILE_NUMBER,
            EMAIL,
            GIVEN_NAMES,
            null,
            null,
            null,
            null,
            NATIONALITY,
            CUSTOMER_ADDRESS_ARRAY,
            DATE_OF_BIRTH,
            null);

    assertEquals(VALID_CUSTOMER, customer);
  }

  @Test
  public void createCustomer_Success_IfParamsIsValid() throws XenditException {
    initCreateParams();

    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), Customer.class))
        .thenReturn(VALID_CUSTOMER);
    when(customerClient.createCustomer(PARAMS)).thenReturn(VALID_CUSTOMER);

    Customer customer = customerClient.createCustomer(PARAMS);

    assertEquals(VALID_CUSTOMER, customer);
  }

  @Test
  public void createCustomer_Success_WithHeaderProvided() throws XenditException {
    initCreateParams();
    HEADERS.put("for-user-id", "user-id");

    when(this.requestClient.request(
            RequestResource.Method.POST, URL, HEADERS, PARAMS, opt.getApiKey(), Customer.class))
        .thenReturn(VALID_CUSTOMER);
    when(customerClient.createCustomer(HEADERS, PARAMS)).thenReturn(VALID_CUSTOMER);

    Customer customer = customerClient.createCustomer(HEADERS, PARAMS);

    assertEquals(VALID_CUSTOMER, customer);
  }

  @Test(expected = XenditException.class)
  public void createCustomer_ThrowsException_IfParamsIsInvalid() throws XenditException {
    initCreateParams();
    PARAMS.put("nationality", "NOT_ID");

    when(this.requestClient.request(
            RequestResource.Method.POST,
            URL,
            new HashMap<>(),
            PARAMS,
            opt.getApiKey(),
            Customer.class))
        .thenThrow(new XenditException("Nationality is invalid"));
    when(customerClient.createCustomer(PARAMS))
        .thenThrow(new XenditException("Nationality is invalid"));

    customerClient.createCustomer(PARAMS);
  }

  @Test
  public void getCustomer_Success_IfReferenceIdIsAvailable() throws XenditException {
    String url = String.format("%s?reference_id=%s", URL, REFERENCE_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), Customer[].class))
        .thenReturn(CUSTOMER_ARRAY);
    when(customerClient.getCustomerByReferenceId(REFERENCE_ID)).thenReturn(CUSTOMER_ARRAY);

    Customer[] customers = customerClient.getCustomerByReferenceId(REFERENCE_ID);

    assertEquals(CUSTOMER_ARRAY, customers);
  }

  @Test(expected = XenditException.class)
  public void getCustomers_ThrowsException_IfReferenceIdIsNotAvailable() throws XenditException {
    String NOT_AVAILABLE_REFERENCE_ID = "not-available-reference-id";
    String url = String.format("%s?reference_id=%s", URL, NOT_AVAILABLE_REFERENCE_ID);

    when(this.requestClient.request(
            RequestResource.Method.GET, url, null, opt.getApiKey(), Customer[].class))
        .thenThrow(new XenditException("Customer not found"));
    when(customerClient.getCustomerByReferenceId(NOT_AVAILABLE_REFERENCE_ID))
        .thenThrow(new XenditException("Customer not found"));
    customerClient.getCustomerByReferenceId(NOT_AVAILABLE_REFERENCE_ID);
  }
}
