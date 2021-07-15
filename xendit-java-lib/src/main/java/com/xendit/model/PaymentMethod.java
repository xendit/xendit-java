package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.LinkedAccountEnum.AccountType;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentMethod {
  @SerializedName("id")
  private String id;

  @SerializedName("type")
  private AccountType type;

  @SerializedName("properties")
  private Map<String, Object> properties;

  @SerializedName("customer_id")
  private String customerId;

  @SerializedName("status")
  private String status;

  @SerializedName("created")
  private String created;

  @SerializedName("updated")
  private String updated;

  @SerializedName("metadata")
  private Map<String, Object> metadata;

  /**
   * Create payment method
   *
   * @param customerId ID of the customer object to which the account token will be linked to
   * @param type Type of payment method
   * @param properties Specific information that identifies the payment method
   * @param metadata Object of additional information the user may use.
   * @return PaymentMethod model.
   * @throws XenditException XenditException
   */
  public static PaymentMethod createPaymentMethod(
      String customerId,
      AccountType type,
      Map<String, Object> properties,
      Map<String, Object> metadata)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
    params.put("type", type);
    params.put("properties", properties);
    params.put("metadata", metadata);
    return createPaymentMethodRequest(new HashMap<>(), params);
  }

  /**
   * Create payment method with all parameter as HashMap
   *
   * @param params listed here https://developers.xendit.co/api-reference/#create-payment-method.
   * @return PaymentMethod
   * @throws XenditException
   */
  public static PaymentMethod createPaymentMethod(Map<String, Object> params)
      throws XenditException {
    return createPaymentMethodRequest(new HashMap<>(), params);
  }

  /**
   * Create payment method with headers and all parameter as HashMap
   *
   * @param headers
   * @param params listed here https://developers.xendit.co/api-reference/#create-payment-method.
   * @return PaymentMethod
   * @throws XenditException
   */
  public static PaymentMethod createPaymentMethod(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    return createPaymentMethodRequest(headers, params);
  }

  /**
   * Get payment methods by customer id
   *
   * @param customerId Customer ID
   * @return PaymentMethod
   * @throws XenditException XenditException
   */
  public static PaymentMethod[] getPaymentMethodsByCustomerId(String customerId)
      throws XenditException {
    String url =
        String.format("%s%s%s", Xendit.getUrl(), "/payment_methods?customer_id=", customerId);
    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, null, PaymentMethod[].class);
  }

  public static PaymentMethod createPaymentMethodRequest(
      Map<String, String> headers, Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/payment_methods");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, headers, params, PaymentMethod.class);
  }
}
