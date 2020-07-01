package com.xendit.model;

import com.google.gson.annotations.SerializedName;
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
public class Balance {
  @SerializedName("balance")
  private Number balance;

  public enum AccountType {
    CASH,
    HOLDING,
    TAX
  }

  /**
   * Get balance from your account
   *
   * @return Balance
   * @throws XenditException XenditException
   */
  public static Balance get() throws XenditException {
    return getBalance(new HashMap<>(), null);
  }

  /**
   * Get balance from your account based on given account type
   *
   * @param accountType The selected balance type
   * @return Balance
   * @throws XenditException XenditException
   */
  public static Balance get(AccountType accountType) throws XenditException {
    return getBalance(new HashMap<>(), accountType);
  }

  /**
   * Get balance from your account based on given account type
   *
   * @param headers
   * @param accountType The selected balance type
   * @return Balance
   * @throws XenditException XenditException
   */
  public static Balance get(Map<String, String> headers, AccountType accountType)
      throws XenditException {
    return getBalance(headers, accountType);
  }

  private static Balance getBalance(Map<String, String> headers, AccountType accountType)
      throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/balance");
    if (accountType != null) {
      url = String.format("%s%s%s", url, "?account_type=", accountType);
    }

    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, headers, null, Balance.class);
  }
}
