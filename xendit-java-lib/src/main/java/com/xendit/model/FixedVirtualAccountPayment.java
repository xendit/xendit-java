package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FixedVirtualAccountPayment {
  @SerializedName("id")
  String id;

  @SerializedName("payment_id")
  String paymentId;

  @SerializedName("callback_virtual_account_id")
  String callbackVirtualAccountId;

  @SerializedName("external_id")
  String externalId;

  @SerializedName("account_number")
  String accountNumber;

  @SerializedName("merchant_code")
  String merchantCode;

  @SerializedName("bank_code")
  String bankCode;

  @SerializedName("amount")
  Number amount;

  @SerializedName("transaction_timestamp")
  String transactionTimeStamp;

  @SerializedName("currency")
  String currency;

  @SerializedName("sender_name")
  String senderName;
}
