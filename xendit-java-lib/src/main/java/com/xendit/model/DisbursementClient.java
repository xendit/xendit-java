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
public class DisbursementClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public DisbursementIDR createIDRDisbursement(Map<String, Object> params) throws XenditException {
    return createIDRRequest(new HashMap<>(), params);
  }

  public DisbursementIDR createIDRDisbursement(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return createIDRRequest(headers, params);
  }

  public DisbursementPH createPHDisbursement(Map<String, Object> params) throws XenditException {
    return createPHDisbursement(new HashMap<>(), params);
  }

  public DisbursementPH createPHDisbursement(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return createPHDisbursement(headers, params);
  }

  public DisbursementIDR createIDRDisbursement(
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
    return createIDRRequest(new HashMap<>(), params);
  }

  public DisbursementIDR createIDRDisbursement(
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
    return createIDRRequest(new HashMap<>(), params);
  }

  public DisbursementIDR createIDRDisbursement(
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
    return createIDRRequest(new HashMap<>(), params);
  }

  public DisbursementIDR createIDRDisbursement(
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
    return createIDRRequest(new HashMap<>(), params);
  }

  public DisbursementIDR createIDRRequest(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/disbursements");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), DisbursementIDR.class);
  }

  public AvailableBank[] getAvailableBanks() throws XenditException {
    return getAvailableBanks(new HashMap<>());
  }

  public AvailableBank[] getAvailableBanks(Map<String, String> headers) throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/available_disbursements_banks");
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), AvailableBank[].class);
  }

  public DisbursementIDR[] getByExternalId(String externalId) throws XenditException {
    return getByExternalId(new HashMap<>(), externalId);
  }

  public DisbursementIDR[] getByExternalId(Map<String, String> headers, String externalId)
      throws XenditException {
    String url =
        String.format("%s%s%s", opt.getXenditURL(), "/disbursements?external_id=", externalId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), DisbursementIDR[].class);
  }

  public DisbursementPH[] getByReferenceId(Map<String, String> headers, String referenceId)
      throws XenditException {
    String url =
        String.format("%s%s%s", opt.getXenditURL(), "/disbursements?reference_id=", referenceId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), DisbursementPH[].class);
  }

  public DisbursementIDR getIDRById(String id) throws XenditException {
    return getIDRById(new HashMap<>(), id);
  }

  public DisbursementIDR getIDRById(Map<String, String> headers, String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/disbursements/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), DisbursementIDR.class);
  }

  public DisbursementPH getPHById(String id) throws XenditException {
    return getPHById(new HashMap<>(), id);
  }

  public DisbursementPH getPHById(Map<String, String> headers, String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/disbursements/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), DisbursementPH.class);
  }

  public DisbursementPH createPHRequest(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/disbursements");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), DisbursementPH.class);
  }

  public DisbursementChannel[] getDisbursementChannels(Map<String, String> headers)
      throws XenditException {
    String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/disbursement-channels");
    return this.requestClient.request(
        RequestResource.Method.GET,
        url,
        headers,
        null,
        opt.getApiKey(),
        DisbursementChannel[].class);
  }

  public DisbursementChannel[] getByChannelCategory(
      Map<String, String> headers, String channelCategory) throws XenditException {
    String url =
        String.format(
            "%s%s%s",
            Xendit.Opt.getXenditURL(), "/disbursement-channels?channel_category=", channelCategory);
    return this.requestClient.request(
        RequestResource.Method.GET,
        url,
        headers,
        null,
        opt.getApiKey(),
        DisbursementChannel[].class);
  }

  public DisbursementChannel[] getByChannelCode(Map<String, String> headers, String channelCode)
      throws XenditException {
    String url =
        String.format(
            "%s%s%s",
            Xendit.Opt.getXenditURL(), "/disbursement-channels?channel_code=", channelCode);
    return this.requestClient.request(
        RequestResource.Method.GET,
        url,
        headers,
        null,
        opt.getApiKey(),
        DisbursementChannel[].class);
  }
}
