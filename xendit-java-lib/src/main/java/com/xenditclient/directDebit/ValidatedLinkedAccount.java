package com.xenditclient.directDebit;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;

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
  public static ValidatedLinkedAccount validateOTPWithToken(String tokenId, String otpCode)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("otp_code", otpCode);
    return validateOTPRequestWithToken(tokenId, new HashMap<>(), params);
  }

  /**
   * Validate OTP for linked account token with all parameter as HashMap
   *
   * @param params listed here
   *     https://developers.xendit.co/api-reference/#validate-otp-for-linked-account-token.
   * @return ValidatedLinkedAccount
   * @throws XenditException
   */
  public static ValidatedLinkedAccount validateOTPWithToken(String tokenId, Map<String, Object> params)
      throws XenditException {
    return validateOTPRequestWithToken(tokenId, new HashMap<>(), params);
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
  public static ValidatedLinkedAccount validateOTPWithToken(
      String tokenId, Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    return validateOTPRequestWithToken(tokenId, headers, params);
  }

  private static ValidatedLinkedAccount validateOTPRequestWithToken(
      String tokenId, Map<String, String> headers, Map<String, Object> params)
      throws XenditException {
    DirectDebitPaymentClient client = DirectDebitPayment.getClient();
    return client.validateOTPRequestWithToken(tokenId, headers, params);
  }
}
