package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AccessibleLinkedAccount extends AbstractResponseHeaders {
  @SerializedName("id")
  private String id;

  @SerializedName("channel_code")
  private LinkedAccountEnum.ChannelCode channelCode;

  @SerializedName("type")
  private LinkedAccountEnum.AccountType type;

  @SerializedName("properties")
  private Map<String, Object> properties;

  private static DirectDebitPaymentClient directDebitPaymentClient;

  /**
   * Get accessible accounts by linked account token
   *
   * @param linkedAccountTokenId Linked account token id received from Initialize Account
   *     Authorization.
   * @return AccessibleLinkedAccount
   * @throws XenditException XenditException
   */
  public static AccessibleLinkedAccount[] retrieveAccessibleLinkedAccounts(
      String linkedAccountTokenId) throws XenditException {
    DirectDebitPaymentClient client = DirectDebitPayment.getClient();
    return client.retrieveAccessibleLinkedAccounts(linkedAccountTokenId);
  }
}
