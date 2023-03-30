package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DisbursementClient extends AbstractResponseHeaders {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public Disbursement create(Map<String, Object> params) throws XenditException {
    return createRequest(new HashMap<>(), params);
  }

  public Disbursement create(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return createRequest(headers, params);
  }

  public Disbursement create(
      String externalId,
      String bankCode,
      String accountHolderName,
      String accountNumber,
      String description,
      BigInteger amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("account_holder_name", accountHolderName);
    params.put("account_number", accountNumber);
    params.put("description", description);
    params.put("amount", amount);
    return createRequest(new HashMap<>(), params);
  }

  public Disbursement create(
      String externalId,
      String bankCode,
      String accountHolderName,
      String accountNumber,
      String description,
      BigInteger amount,
      String[] emailTo)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("account_holder_name", accountHolderName);
    params.put("account_number", accountNumber);
    params.put("description", description);
    params.put("amount", amount);
    params.put("email_to", emailTo);
    return createRequest(new HashMap<>(), params);
  }

  public Disbursement create(
      String externalId,
      String bankCode,
      String accountHolderName,
      String accountNumber,
      String description,
      BigInteger amount,
      String[] emailTo,
      String[] emailCc)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("account_holder_name", accountHolderName);
    params.put("account_number", accountNumber);
    params.put("description", description);
    params.put("amount", amount);
    params.put("email_to", emailTo);
    params.put("email_cc", emailCc);
    return createRequest(new HashMap<>(), params);
  }

  public Disbursement create(
      String externalId,
      String bankCode,
      String accountHolderName,
      String accountNumber,
      String description,
      BigInteger amount,
      String[] emailTo,
      String[] emailCc,
      String[] emailBcc)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("bank_code", bankCode);
    params.put("account_holder_name", accountHolderName);
    params.put("account_number", accountNumber);
    params.put("description", description);
    params.put("amount", amount);
    params.put("email_to", emailTo);
    params.put("email_cc", emailCc);
    params.put("email_bcc", emailBcc);
    return createRequest(new HashMap<>(), params);
  }

  public Disbursement createRequest(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/disbursements");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), Disbursement.class);
  }

  public AvailableBank[] getAvailableBanks() throws XenditException {
    return getAvailableBanks(new HashMap<>());
  }

  public AvailableBank[] getAvailableBanks(Map<String, String> headers) throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/available_disbursements_banks");
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), AvailableBank[].class);
  }

  public Disbursement[] getByExternalId(String externalId) throws XenditException {
    return getByExternalId(new HashMap<>(), externalId);
  }

  public Disbursement[] getByExternalId(Map<String, String> headers, String externalId)
      throws XenditException {
    String url =
        String.format("%s%s%s", opt.getXenditURL(), "/disbursements?external_id=", externalId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), Disbursement[].class);
  }

  public Disbursement getById(String id) throws XenditException {
    return getById(new HashMap<>(), id);
  }

  public Disbursement getById(Map<String, String> headers, String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/disbursements/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), Disbursement.class);
  }
}
