package com.xendit.model;

import com.xendit.exception.XenditException;
import java.util.HashMap;
import java.util.Map;

public class RegionalRetailOutlet extends AbstractRetailOutlet {
  /**
   * Create payment code with required parameters
   *
   * @param referenceId An ID of your choice. Often it is unique identifier like a phone number,
   *     email or transaction ID.
   *     <p>Characters Special and alphanumeric Minimum length 1 character Maximum length 1000
   *     characters
   * @param channelCode Channel code of selected payment method.
   *     <p>Available Channel Codes: SEVENELEVEN SEVENELEVEN_CLIQQ CEBUANA ECPAY
   * @param customerName Name of user - this might be used by the Retail Outlets cashier to validate
   *     the end user.
   *     <p>Characters Only alphanumeric Special Characters: # / . " - , ' _ @ ( ) &amp; ] [ :
   *     Minimum length 1 character Maximum length 250 characters
   * @param amount The amount that is expected to be paid by end customer the minimum is PHP 50 and
   *     the maximum is PHP 10,000 for SEVENELEVEN the minimum is PHP 100 and the maximum is PHP
   *     50,000 for SEVENELEVEN_CLIQQ the minimum is PHP 1 and the maximum is PHP 30,000 for CEBUANA
   *     the minimum is PHP 1 and the maximum is PHP 50,000 for ECPAY
   * @param currency ISO 4217 Currency code
   *     <p>Supported currency code: PHP
   * @param market 2-letter ISO 3166-2 country code indicating country of transaction.
   *     <p>Supported market: PH
   * @return PaymentCode
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPaymentCode createPaymentCode(
      String referenceId,
      RegionalRetailOutletPaymentCode.ChannelCode channelCode,
      String customerName,
      Number amount,
      RegionalRetailOutletPaymentCode.Currency currency,
      RegionalRetailOutletPaymentCode.Market market)
      throws XenditException {

    String channelCodeParam = channelCode.getChannelCode();

    Map<String, Object> params = new HashMap<>();
    params.put("reference_id", referenceId);
    params.put("channel_code", channelCodeParam);
    params.put("customer_name", customerName);
    params.put("amount", amount);
    params.put("currency", currency);
    params.put("market", market);
    RetailOutletClient client = getClient();
    return client.createPaymentCode(params);
  }

  /**
   * Create payment code with all parameters as HashMap
   *
   * @param params listed here https://developers.xendit.co/api-reference/#create-payment-code
   * @return PaymentCodeRetailOutlet
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPaymentCode createPaymentCode(Map<String, Object> params)
      throws XenditException {
    return createPaymentCode(new HashMap<>(), params);
  }

  /**
   * Create payment code with all parameters as HashMap
   *
   * @param headers
   * @param params listed here https://developers.xendit.co/api-reference/#create-payment-code
   * @return PaymentCodeRetailOutlet
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPaymentCode createPaymentCode(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    RetailOutletClient client = getClient();
    for (Map.Entry<String, Object> param : params.entrySet()) {
      if (param.getKey() == "channel_code") {
        RegionalRetailOutletPaymentCode.ChannelCode channelCode =
            (RegionalRetailOutletPaymentCode.ChannelCode) (param.getValue());
        param.setValue(channelCode.getChannelCode());
      }
    }
    return client.createPaymentCode(headers, params);
  }

  /**
   * Get payment code by ID
   *
   * @param id ID of the payment code to retrieve
   * @return PaymentCode
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPaymentCode getPaymentCode(String id) throws XenditException {
    return getPaymentCode(id, new HashMap<>());
  }

  /**
   * Get payment code by ID
   *
   * @param id ID of the payment code to retrieve
   * @param headers
   * @return PaymentCode
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPaymentCode getPaymentCode(
      String id, Map<String, String> headers) throws XenditException {
    RetailOutletClient client = getClient();
    return client.getPaymentCode(id, headers);
  }

  /**
   * Update payment code by ID and with all parameters as HashMap
   *
   * @param id ID of the payment code to be updated
   * @param params listed here https://developers.xendit.co/api-reference/#update-payment-code
   * @return PaymentCode
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPaymentCode updatePaymentCode(
      String id, Map<String, Object> params) throws XenditException {
    RetailOutletClient client = getClient();
    return client.updatePaymentCode(id, params);
  }

  /**
   * Update payment code by ID and with all parameters as HashMap
   *
   * @param id ID of the payment code to be updated
   * @param headers
   * @param params listed here https://developers.xendit.co/api-reference/#update-payment-code
   * @return PaymentCode
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPaymentCode updatePaymentCode(
      String id, Map<String, String> headers, Map<String, Object> params) throws XenditException {
    RetailOutletClient client = getClient();
    return client.updatePaymentCode(id, headers, params);
  }

  /**
   * Update payment code by ID and with parameters
   *
   * @param id ID of the payment code to be updated
   * @param customerName Name of user - this might be used by the Retail Outlets cashier to validate
   *     the end user.
   *     <p>Characters Only alphanumeric Minimum length 1 character Maximum length 250 characters
   * @param amount The amount that is expected to be paid by end customer
   *     <p>updating of amount is not allowed for 7ELEVEN the minimum is PHP 100 and the maximum is
   *     PHP 50,000 for 7ELEVEN_CLIQQ the minimum is PHP 1 and the maximum is PHP 30,000 for CEBUANA
   *     the minimum is PHP 1 and the maximum is PHP 50,000 for ECPAY
   * @param currency ISO 4217 Currency code
   * @param expiresAt The time when the payment code will be expired. You can set it to be days in
   *     the past to expire payment code immediately
   *     <p>updating of expiration days is not allowed for 7ELEVEN Timezone: UTC+0
   * @param description Description of the payment code.
   *     <p>Minimum length 1 character Maximum length 250 characters
   * @return PaymentCode
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPaymentCode updatePaymentCode(
      String id,
      String customerName,
      Number amount,
      RegionalRetailOutletPaymentCode.Currency currency,
      String expiresAt,
      String description)
      throws XenditException {
    RetailOutletClient client = getClient();
    return client.updatePaymentCode(id, customerName, amount, currency, expiresAt, description);
  }

  /**
   * get payments by payment code ID
   *
   * @param id ID of the payment code to be retrieved
   * @return RegionalRetailOutletPayments
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPayments[] getPaymentsByPaymentCodeId(String id)
      throws XenditException {
    RetailOutletClient client = getClient();
    return client.getPaymentsByPaymentCodeId(id);
  }

  /**
   * get payments by payment code ID
   *
   * @param id ID of the payment code to be retrieved
   * @param headers
   * @return RegionalRetailOutletPayments
   * @throws XenditException XenditException
   */
  public static RegionalRetailOutletPayments[] getPaymentsByPaymentCodeId(
      String id, Map<String, String> headers) throws XenditException {
    RetailOutletClient client = getClient();
    return client.getPaymentsByPaymentCodeId(id, headers);
  }
}
