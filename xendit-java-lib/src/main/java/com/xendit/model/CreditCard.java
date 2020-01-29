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

  public static CreditCardCharge createAuthorization(Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/credit_card_charges");
    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardCharge.class);
  }
}
