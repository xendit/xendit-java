package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Balance {
  @SerializedName("balance")
  private Number balance;

  /**
   * Get balance from your account
   *
   * @return Balance
   * @throws XenditException XenditException
   */
  public static Balance get() throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/balance");
    return Xendit.requestClient.request(RequestResource.Method.GET, url, null, Balance.class);
  }

  /**
   * Get balance from your account based on given account type
   *
   * @param accountType The selected balance type
   * @return Balance
   * @throws XenditException XenditException
   */
  public static Balance get(String accountType) throws XenditException {
    String url = String.format("%s%s%s", Xendit.getUrl(), "/balance?account_type=", accountType);
    return Xendit.requestClient.request(RequestResource.Method.GET, url, null, Balance.class);
  }
}
