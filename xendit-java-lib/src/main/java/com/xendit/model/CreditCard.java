package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditCard {
  /**
   * Create authorization with given parameters
   *
   * @param tokenId The token ID used to charge the card.
   * @param externalId A unique identifier of your choice. Max 64 characters.
   * @param amount The charge amount
   * @param authenticationId Authentication ID for authenticating charge. Optional only if charge
   *     was already authenticated with a single-use token, or if optional authentication is enabled
   *     for your account.
   * @param cardCvn 3 or 4 digit CVN (CVC) code. Optional but highly recommended. Required for cards
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
      String cardCvn,
      Boolean capture)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("token_id", tokenId);
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("authentication_id", authenticationId);
    params.put("card_cvn", cardCvn);
    params.put("capture", capture);
    String url = String.format("%s%s", Xendit.getUrl(), "/credit_card_charges");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardCharge.class);
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
    String url = String.format("%s%s", Xendit.getUrl(), "/credit_card_charges");
    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardCharge.class);
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
    String url =
        String.format(
            "%s%s%s%s", Xendit.getUrl(), "/credit_card_charges/", chargeId, "/auth_reversal");
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardReverseAuth.class);
  }
}
