package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
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
    DirectDebitPaymentClient client = DirectDebitPayment.getClient();
    return client.unbindLinkedAccountToken(linkedAccountTokenId);
  }
}
