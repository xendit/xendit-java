package com.xenditclient.customer;

import com.xendit.exception.XenditException;
import com.xendit.model.CustomerAddress;
import com.xendit.network.RequestResource;
import com.xenditclient.Xendit;
import com.xenditclient.network.NetworkClient;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public Customer createCustomer(
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

  public Customer createCustomer(Map<String, Object> params) throws XenditException {
    return createCustomerRequest(new HashMap<>(), params);
  }

  public Customer createCustomer(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return createCustomerRequest(headers, params);
  }

  public Customer[] getCustomerByReferenceId(String referenceId) throws XenditException {
    String url =
        String.format("%s%s%s", opt.getXenditURL(), "/customers?reference_id=", referenceId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), Customer[].class);
  }

  private Customer createCustomerRequest(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/customers");
    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), Customer.class);
  }
}
