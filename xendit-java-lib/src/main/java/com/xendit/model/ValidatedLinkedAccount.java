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
public class ValidatedLinkedAccount {
  @SerializedName("id")
  private String id;

  @SerializedName("customer_id")
  private String customerId;

  @SerializedName("channel_code")
  private LinkedAccountEnum.ChannelCode channelCode;

  @SerializedName("status")
  private String status;

  /**
   * Validate OTP for linked account token
   *
   * @param otpCode OTP received by the customer from the partner bank for account linking
   * @return ValidatedLinkedAccount model.
   * @throws XenditException XenditException
   */
  public static ValidatedLinkedAccount validateOTP(String tokenId, String otpCode)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("otp_code", otpCode);
    return validateOTPRequest(tokenId, new HashMap<>(), params);
  }

  /**
   * Validate OTP for linked account token with all parameter as HashMap
   *
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#validate-otp-for-linked-account-token.
   * @return ValidatedLinkedAccount
   * @throws XenditException
   */
  public static ValidatedLinkedAccount validateOTP(String tokenId, Map<String, Object> params)
      throws XenditException {
    return validateOTPRequest(tokenId, new HashMap<>(), params);
  }

  /**
   * Validate OTP for linked account token with headers and all parameter as HashMap
   *
   * @param headers
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#validate-otp-for-linked-account-token.
   * @return ValidatedLinkedAccount
   * @throws XenditException
   */
  public static ValidatedLinkedAccount validateOTP(
      String tokenId, Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return validateOTPRequest(tokenId, headers, params);
  }

  private static ValidatedLinkedAccount validateOTPRequest(
      String tokenId, Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    String url =
        String.format(
            "%s%s%s%s", Xendit.getUrl(), "/linked_account_tokens/", tokenId, "/validate_otp");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, headers, params, ValidatedLinkedAccount.class);
  }
}
