package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.NetworkClient;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DisbursementPHPClient {
  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  public DisbursementPHP createPHPDisbursement(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return createPHPRequest(headers, params);
  }

  public DisbursementPHP createPHPDisbursement(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      Integer amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    headers.put("xendit-idempotency-key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    return createPHPRequest(headers, params);
  }

  public DisbursementPHP createPHPDisbursement(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      Integer amount,
      ReceiptNotification receiptNotification)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    headers.put("xendit-idempotency-key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    params.put("receipt_notification", receiptNotification);
    return createPHPRequest(headers, params);
  }

  public DisbursementPHP createPHPDisbursement(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      Integer amount,
      Beneficiary beneficiary)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    headers.put("xendit-idempotency-key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    params.put("beneficiary", beneficiary);
    return createPHPRequest(headers, params);
  }

  public DisbursementPHP createPHPDisbursement(
      String xendit_idempotency_key,
      String reference_id,
      String currency,
      String channel_code,
      String account_name,
      String account_number,
      String description,
      Integer amount,
      ReceiptNotification receiptNotification,
      Beneficiary beneficiary)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    headers.put("xendit-idempotency-key", xendit_idempotency_key);
    params.put("reference_id", reference_id);
    params.put("currency", currency);
    params.put("channel_code", channel_code);
    params.put("account_name", account_name);
    params.put("account_number", account_number);
    params.put("description", description);
    params.put("amount", amount);
    params.put("receipt_notification", receiptNotification);
    params.put("beneficiary", beneficiary);
    return createPHPRequest(headers, params);
  }

  public DisbursementPHP[] getPHPByReferenceId(String referenceId) throws XenditException {
    return getPHPByReferenceId(new HashMap<String, String>(), referenceId);
  }

  public DisbursementPHP[] getPHPByReferenceId(Map<String, String> headers, String referenceId)
      throws XenditException {
    String url =
        String.format("%s%s%s", opt.getXenditURL(), "/disbursements?reference_id=", referenceId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), DisbursementPHP[].class);
  }

  public DisbursementPHP getPHPById(String id) throws XenditException {
    return getPHPById(new HashMap<>(), id);
  }

  public DisbursementPHP getPHPById(Map<String, String> headers, String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.Opt.getXenditURL(), "/disbursements/", id);
    return this.requestClient.request(
        RequestResource.Method.GET, url, headers, null, opt.getApiKey(), DisbursementPHP.class);
  }

  public DisbursementPHP createPHPRequest(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/disbursements");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), DisbursementPHP.class);
  }

  public DisbursementChannel[] getDisbursementChannels() throws XenditException {
    return getDisbursementChannels(new HashMap<>());
  }

  public DisbursementChannel[] getDisbursementChannels(Map<String, String> headers)
      throws XenditException {
    String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/disbursement-channels");
    return this.requestClient.request(
        RequestResource.Method.GET,
        url,
        headers,
        null,
        opt.getApiKey(),
        DisbursementChannel[].class);
  }

  public DisbursementChannel[] getByChannelCategory(String channelCategory) throws XenditException {
    return getByChannelCategory(new HashMap<>(), channelCategory);
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

  public DisbursementChannel[] getByChannelCode(String channelCode) throws XenditException {
    return getByChannelCode(new HashMap<>(), channelCode);
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
