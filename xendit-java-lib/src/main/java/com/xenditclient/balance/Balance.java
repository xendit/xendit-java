package com.xenditclient.balance;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xenditclient.Xendit;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Balance {
  @SerializedName("balance")
  private Number balance;

  private static BalanceClient balanceClient;

  public enum AccountType {
    CASH,
    HOLDING,
    TAX
  }

  public void setBalance(Number balance) {
    this.balance = balance;
  }

  public static Balance get() throws XenditException {
    return getBalance(new HashMap<>(), null);
  }

  public static Balance get(Balance.AccountType accountType) throws XenditException {
    return getBalance(new HashMap<>(), accountType);
  }

  public static Balance get(Map<String, String> headers, Balance.AccountType accountType)
      throws XenditException {
    return getBalance(headers, accountType);
  }

  private static Balance getBalance(Map<String, String> headers, Balance.AccountType accountType)
      throws XenditException {
    BalanceClient client = getClient();
    return client.getBalance(headers, accountType);
  }

  /**
   * Its create a client for Invoice
   *
   * @return InvoiceClient
   */
  private static BalanceClient getClient() {
    if (isApiKeyExist()) {
      if (balanceClient == null
          || !balanceClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return balanceClient =
            new BalanceClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (balanceClient == null
          || !balanceClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
        return balanceClient = new BalanceClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return balanceClient;
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
