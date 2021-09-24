package com.xenditclient.customer;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xendit.model.CustomerAddress;
import com.xenditclient.Xendit;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer {
  @SerializedName("id")
  private String id;

  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("mobile_number")
  private String mobileNumber;

  @SerializedName("email")
  private String email;

  @SerializedName("given_names")
  private String givenNames;

  @SerializedName("middle_name")
  private String middleName;

  @SerializedName("surname")
  private String surname;

  @SerializedName("description")
  private String description;

  @SerializedName("phone_number")
  private String phoneNumber;

  @SerializedName("nationality")
  private String nationality;

  @SerializedName("addresses")
  private CustomerAddress[] addresses;

  @SerializedName("date_of_birth")
  private String dateOfBirth;

  @SerializedName("metadata")
  private Map<String, Object> metadata;

  private static CustomerClient customerClient;

  /**
   * Create Customer
   *
   * @param referenceId Merchant-provided identifier for the customer.
   * @param mobileNumber Mobile number of the customer in E.164 international standard.
   * @param email Email address of the customer.
   * @param givenNames Primary of first name/s of the customer.
   * @param middleName Middle name of the customer.
   * @param surname Surname of the customer.
   * @param description Merchant-provided description for the customer object.
   * @param phoneNumber Alternate or landline phone number in E.164 international standard.
   * @param nationality 2-letter ISO 3166-2 country code for the customer's nationality.
   * @param addresses Array of objects containing the specific customer's address infromation.
   * @param dateOfBirth Date of birth of the customer in YYYY-MM-DD format.
   * @param metadata Object of additional information the user may use.
   * @return Customer model.
   * @throws XenditException XenditException
   */
  public static Customer createCustomer(
      String referenceId,
      String mobileNumber,
      String email,
      String givenNames,
      String middleName,
      String surname,
      String description,
      String phoneNumber,
      String nationality,
      CustomerAddress[] addresses,
      String dateOfBirth,
      Map<String, Object> metadata)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("reference_id", referenceId);
    params.put("mobile_number", mobileNumber);
    params.put("email", email);
    params.put("given_names", givenNames);
    params.put("middle_name", middleName);
    params.put("surname", surname);
    params.put("description", description);
    params.put("phone_number", phoneNumber);
    params.put("nationality", nationality);
    params.put("addresses", addresses);
    params.put("date_of_birth", dateOfBirth);
    params.put("metadata", metadata);
    return createCustomerRequest(new HashMap<>(), params);
  }

  /**
   * Create customer with all parameter as HashMap
   *
   * @param params listed here https://developers.xendit.co/api-reference/#create-customer.
   * @return Customer
   * @throws XenditException
   */
  public static Customer createCustomer(Map<String, Object> params) throws XenditException {
    return createCustomerRequest(new HashMap<>(), params);
  }

  /**
   * Create customer with headers and all parameter as HashMap
   *
   * @param headers
   * @param params listed here https://developers.xendit.co/api-reference/#create-customer.
   * @return Customer
   * @throws XenditException
   */
  public static Customer createCustomer(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return createCustomerRequest(headers, params);
  }

  /**
   * Get customer by reference id
   *
   * @param referenceId Customer reference ID
   * @return Customer
   * @throws XenditException XenditException
   */
  public static Customer[] getCustomerByReferenceId(String referenceId) throws XenditException {
    CustomerClient client = getClient();
    return client.getCustomerByReferenceId(referenceId);
  }

  private static Customer createCustomerRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    CustomerClient client = getClient();
    return client.createCustomer(headers, params);
  }

  /**
   * Its create a client for Customer
   *
   * @return CustomerClient
   */
  private static CustomerClient getClient() {
    if (isApiKeyExist()) {
      if (customerClient == null
          || !customerClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return customerClient =
            new CustomerClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (customerClient == null
          || !customerClient
              .getOpt()
              .getApiKey()
              .trim()
              .equals(com.xenditclient.Xendit.Opt.getApiKey().trim())) {
        return customerClient = new CustomerClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return customerClient;
  }

  /**
   * check if api-key is exist or not
   *
   * @return boolean
   */
  private static boolean isApiKeyExist() {
    return Xendit.apiKey != null;
  }
}
