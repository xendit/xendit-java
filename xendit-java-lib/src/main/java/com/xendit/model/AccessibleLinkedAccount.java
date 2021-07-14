package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AccessibleLinkedAccount {
  @SerializedName("id")
  private String id;

  @SerializedName("channel_code")
  private LinkedAccountEnum.ChannelCode channelCode;

  @SerializedName("type")
  private LinkedAccountEnum.AccountType type;

  @SerializedName("properties")
  private Map<String, Object> properties;

  /**
   * Get accessible accounts by linked account token
   *
   * @param linkedAccountTokenId Linked account token id received from Initialize Account
   *     Authorization.
   * @return AccessibleLinkedAccount
   * @throws XenditException XenditException
   */
  public static AccessibleLinkedAccount[] getAccessibleLinkedAccounts(String linkedAccountTokenId)
      throws XenditException {
    String url =
        String.format(
            "%s%s%s%s",
            Xendit.getUrl(), "/linked_account_tokens/", linkedAccountTokenId, "/accounts");
    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, null, AccessibleLinkedAccount[].class);
  }
}
