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
public class InitializedLinkedAccount {
  @SerializedName("id")
  private String id;

  @SerializedName("customer_id")
  private String customerId;

  @SerializedName("channel_code")
  private LinkedAccountEnum.ChannelCode channelCode;

  @SerializedName("authorizer_url")
  private String authorizerUrl;

  @SerializedName("status")
  private String status;

  @SerializedName("metadata")
  private Map<String, Object> metadata;

  /**
   * Initialize linked account tokenization
   *
   * @param customerId ID of customer object to which the account token will be linked to.
   * @param channelCode Specifies which e-Wallet will be used by end-customer.
   * @param properties Specific information based on type of account.
   * @param metadata Object of additional information the user may use.
   * @return InitializedLinkedAccount model.
   * @throws XenditException XenditException
   */
  public static InitializedLinkedAccount initializeLinkedAccountTokenization(
      String customerId,
      LinkedAccountEnum.ChannelCode channelCode,
      Map<String, Object> properties,
      Map<String, Object> metadata)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
    params.put("channel_code", channelCode);
    params.put("properties", properties);
    params.put("metadata", metadata);
    return initializeLinkedAccountTokenizationRequest(new HashMap<>(), params);
  }

  /**
   * Initialize linked account tokenization with all parameter as HashMap
   *
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#initialize-linked-account-tokenization.
   * @return InitializedLinkedAccount
   * @throws XenditException
   */
  public static InitializedLinkedAccount initializeLinkedAccountTokenization(
      Map<String, Object> params) throws XenditException {
    return initializeLinkedAccountTokenizationRequest(new HashMap<>(), params);
  }

  /**
   * Initialize linked account tokenization with headers and all parameter as HashMap
   *
   * @param headers
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#initialize-linked-account-tokenization.
   * @return InitializedLinkedAccount
   * @throws XenditException
   */
  public static InitializedLinkedAccount initializeLinkedAccountTokenization(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return initializeLinkedAccountTokenizationRequest(headers, params);
  }

  private static InitializedLinkedAccount initializeLinkedAccountTokenizationRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/linked_account_tokens/auth");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, headers, params, InitializedLinkedAccount.class);
  }
}
