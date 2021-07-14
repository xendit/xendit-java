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
public class UnbindedLinkedAccount {
  @SerializedName("id")
  private String id;

  @SerializedName("is_deleted")
  private Boolean isDeleted;

  /**
   * Unbind a linked account token
   *
   * @param linkedAccountTokenId Linked account token id received from Initialize Account
   *     Authorization.
   * @return UnbindedLinkedAccount
   * @throws XenditException XenditException
   */
  public static UnbindedLinkedAccount unbindLinkedAccountToken(String linkedAccountTokenId)
      throws XenditException {
    String url =
        String.format("%s%s%s", Xendit.getUrl(), "/linked_account_tokens/", linkedAccountTokenId);
    return Xendit.requestClient.request(
        RequestResource.Method.DELETE, url, null, UnbindedLinkedAccount.class);
  }
}
