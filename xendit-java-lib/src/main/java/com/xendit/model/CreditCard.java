package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditCard {

  private static CreditCardClient creditCardClient;

  /**
   * Create authorization with given parameters
   *
   * @param tokenId The token ID used to charge the card.
   * @param externalId A unique identifier of your choice. Max 64 characters.
   * @param amount The charge amount
   * @param authenticationId Authentication ID for authenticating charge. Optional only if charge
   *     was already authenticated with a single-use token, or if optional authentication is enabled
   *     for your account.
   * @param cardCVN 3 or 4 digit CVN (CVC) code. Optional but highly recommended. Required for cards
   *     issued in Europe.
   * @param capture Whether or not to capture immediately.
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createAuthorization(
      String tokenId,
      String externalId,
      Number amount,
      String authenticationId,
      String cardCVN,
      Boolean capture)
      throws XenditException {
    CreditCardClient client = getClient();
    return client.createAuthorization(
        tokenId, externalId, amount, authenticationId, cardCVN, capture);
  }

  /**
   * Create authorization with parameters in a HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-charge
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createAuthorization(Map<String, Object> params)
      throws XenditException {
    return createAuthorization(new HashMap<>(), params);
  }

  /**
   * Create authorization with parameters in a HashMap
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#create-charge
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createAuthorization(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    CreditCardClient client = getClient();
    return client.createAuthorization(headers, params);
  }

  /**
   * Create charge with given parameters
   *
   * @param tokenId The token ID used to charge the card.
   * @param externalId A unique identifier of your choice. Max 64 characters.
   * @param amount The charge amount
   * @param authenticationId Authentication ID for authenticating charge. Optional only if charge
   *     was already authenticated with a single-use token, or if optional authentication is enabled
   *     for your account.
   * @param cardCVN 3 or 4 digit CVN (CVC) code. Optional but highly recommended. Required for cards
   *     issued in Europe.
   * @param descriptor Spesific descriptor to define merchant's identity
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createCharge(
      String tokenId,
      String externalId,
      Number amount,
      String authenticationId,
      String cardCVN,
      String descriptor)
      throws XenditException {
    CreditCardClient client = getClient();
    return client.createCharge(tokenId, externalId, amount, authenticationId, cardCVN, descriptor);
  }

  /**
   * Create charge with parameters in a HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-charge
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createCharge(Map<String, Object> params) throws XenditException {
    return createCharge(new HashMap<>(), params);
  }
  /**
   * Create charge with parameters in a HashMap
   *
   * @param headers
   * @param params listed here https://xendit.github.io/apireference/#create-charge
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createCharge(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    CreditCardClient client = getClient();
    return client.createCharge(headers, params);
  }

  /**
   * Reverse authorization by external ID
   *
   * @param externalId Charge reference
   * @return CreditCardReverseAuth
   * @throws XenditException XenditException
   */
  public static CreditCardReverseAuth reverseAuthorization(String chargeId, String externalId)
      throws XenditException {
    CreditCardClient client = getClient();
    return client.reverseAuthorization(chargeId, externalId);
  }

  /**
   * Reverse authorization by external ID
   *
   * @param headers
   * @param externalId Charge reference
   * @return CreditCardReverseAuth
   * @throws XenditException XenditException
   */
  public static CreditCardReverseAuth reverseAuthorization(
      Map<String, String> headers, String chargeId, String externalId) throws XenditException {
    CreditCardClient client = getClient();
    return client.reverseAuthorization(headers, chargeId, externalId);
  }

  /**
   * Capture a charge by charge ID
   *
   * @param chargeId Charge ID of authorization
   * @param amount Amount to be captured. Can be up to amount of authorization but not more
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge captureCharge(String chargeId, Number amount)
      throws XenditException {
    return captureCharge(new HashMap<>(), chargeId, amount);
  }

  /**
   * Capture a charge by charge ID
   *
   * @param headers
   * @param chargeId Charge ID of authorization
   * @param amount Amount to be captured. Can be up to amount of authorization but not more
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge captureCharge(
      Map<String, String> headers, String chargeId, Number amount) throws XenditException {
    CreditCardClient client = getClient();
    return client.captureCharge(headers, chargeId, amount);
  }

  /**
   * Get a charge by ID
   *
   * @param id Charge ID of the payment that have been authorized
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge getCharge(String id) throws XenditException {
    CreditCardClient client = getClient();
    return client.getCharge(id);
  }

  /**
   * Create a refund
   *
   * @param id Charge ID of the payment that will be refunded
   * @param amount The amount to be refunded
   * @param externalId A unique identifier of your choice. Max 64 characters.
   * @return CreditCardRefund
   * @throws XenditException XenditException
   */
  public static CreditCardRefund createRefund(String id, Number amount, String externalId)
      throws XenditException {
    return createRefund(new HashMap<>(), id, amount, externalId);
  }

  /**
   * Create a refund
   *
   * @param headers
   * @param id Charge ID of the payment that will be refunded
   * @param amount The amount to be refunded
   * @param externalId A unique identifier of your choice. Max 64 characters.
   * @return CreditCardRefund
   * @throws XenditException XenditException
   */
  public static CreditCardRefund createRefund(
      Map<String, String> headers, String id, Number amount, String externalId)
      throws XenditException {
    CreditCardClient client = getClient();
    return client.createRefund(headers, id, amount, externalId);
  }

  /**
   * Its create a client for CreditCard
   *
   * @return CreditCardClient
   */
  private static CreditCardClient getClient() {
    if (isApiKeyExist()) {
      if (creditCardClient == null
          || !creditCardClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return creditCardClient =
            new CreditCardClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (creditCardClient == null
          || !creditCardClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
        return creditCardClient = new CreditCardClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return creditCardClient;
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
