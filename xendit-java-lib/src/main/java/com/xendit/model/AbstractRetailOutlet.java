package com.xendit.model;

import com.xendit.Xendit;

public class AbstractRetailOutlet extends AbstractResponseHeaders {
  protected static RetailOutletClient retailOutletClient;
  /**
   * Its create a client for RetailOutlet
   *
   * @return RetailOutletClient
   */
  protected static RetailOutletClient getClient() {
    if (isApiKeyExist()) {
      if (retailOutletClient == null
          || !retailOutletClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
        return retailOutletClient =
            new RetailOutletClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
      }
    } else {
      if (retailOutletClient == null
          || !retailOutletClient
              .getOpt()
              .getApiKey()
              .trim()
              .equals(Xendit.Opt.getApiKey().trim())) {
        return retailOutletClient = new RetailOutletClient(Xendit.Opt, Xendit.getRequestClient());
      }
    }
    return retailOutletClient;
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
