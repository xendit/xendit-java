package com.xenditclient.ewallet;

import com.xendit.exception.ParamException;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import com.xendit.Xendit;
import com.xendit.network.NetworkClient;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EWalletClient {

  private Xendit.Option opt;

  private NetworkClient requestClient;

  public Xendit.Option getOpt() {
    return opt;
  }

  private static final BigInteger MINIMUM_AMOUNT = new BigInteger("1");
  private static final BigInteger MAXIMUM_AMOUNT = new BigInteger("10000000");

  public EWalletPayment createLinkajaPayment(
      String externalId,
      Number amount,
      String phone,
      EWalletLinkajaItem[] items,
      String callbackUrl,
      String redirectUrl)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("phone", phone);
    params.put("items", items);
    params.put("ewallet_type", EWalletPayment.EWalletType.LINKAJA);
    params.put("callback_url", callbackUrl);
    params.put("redirect_url", redirectUrl);
    return createPaymentRequest(new HashMap<>(), params);
  }

  public EWalletPayment createOvoPayment(String externalId, Number amount, String phone)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("phone", phone);
    params.put("ewallet_type", EWalletPayment.EWalletType.OVO);
    return createPaymentRequest(new HashMap<>(), params);
  }

  public EWalletPayment createDanaPayment(
      String externalId,
      Number amount,
      String phone,
      String expirationDate,
      String callbackUrl,
      String redirectUrl)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("phone", phone);
    params.put("expiration_date", expirationDate);
    params.put("callback_url", callbackUrl);
    params.put("redirect_url", redirectUrl);
    params.put("ewallet_type", EWalletPayment.EWalletType.DANA);
    return createPaymentRequest(new HashMap<>(), params);
  }

  private static void amountValidation(String amount) throws ParamException {
    try {
      BigInteger bigInteger = new BigInteger(amount);

      if (bigInteger.compareTo(MINIMUM_AMOUNT) == -1) {
        throw new ParamException(String.format("Minimum amount is %s", MINIMUM_AMOUNT));
      }

      if (bigInteger.compareTo(MAXIMUM_AMOUNT) == 1) {
        throw new ParamException(String.format("Maximum amount is %s", MAXIMUM_AMOUNT));
      }
    } catch (NumberFormatException e) {
      throw new ParamException("Invalid amount format");
    }
  }

  public EWalletPayment createPaymentRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/ewallets");
    String amount = params.get("amount").toString();

    amountValidation(amount);

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), EWalletPayment.class);
  }

  public EWalletPayment getPaymentStatus(String externalId, EWalletPayment.EWalletType ewalletType)
      throws XenditException {
    String url =
        String.format(
            "%s%s%s%s%s",
            opt.getXenditURL(),
            "/ewallets/?external_id=",
            externalId,
            "&ewallet_type=",
            ewalletType);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), EWalletPayment.class);
  }

  public EWalletCharge createEWalletCharge(
      String referenceId,
      String currency,
      Number amount,
      String checkoutMethod,
      String channelCode,
      Map<String, String> channelProperties,
      String customerId,
      EWalletBasketItem[] basket,
      Map<String, Object> metadata)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("reference_id", referenceId);
    params.put("currency", currency);
    params.put("amount", amount);
    params.put("checkout_method", checkoutMethod);
    params.put("channel_code", channelCode);
    params.put("channel_properties", channelProperties);
    params.put("customer_id", customerId);
    params.put("basket", basket);
    params.put("metadata", metadata);
    return createChargeRequest(new HashMap<>(), params);
  }

  public EWalletCharge createEWalletCharge(Map<String, Object> params) throws XenditException {
    return createChargeRequest(new HashMap<>(), params);
  }

  public EWalletCharge createEWalletCharge(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return createChargeRequest(headers, params);
  }

  public EWalletCharge getEWalletChargeStatus(String chargeId) throws XenditException {
    String url = String.format("%s%s%s", opt.getXenditURL(), "/ewallets/charges/", chargeId);
    return this.requestClient.request(
        RequestResource.Method.GET, url, null, opt.getApiKey(), EWalletCharge.class);
  }

  public EWalletCharge createChargeRequest(Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", opt.getXenditURL(), "/ewallets/charges");

    return this.requestClient.request(
        RequestResource.Method.POST, url, headers, params, opt.getApiKey(), EWalletCharge.class);
  }
}
