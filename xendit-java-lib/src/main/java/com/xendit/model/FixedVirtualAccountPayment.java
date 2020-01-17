package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class FixedVirtualAccountPayment {
  @SerializedName("id")
  @Getter
  String id;

  @SerializedName("payment_id")
  @Getter
  String paymentId;

  @SerializedName("callback_virtual_account_id")
  @Getter
  String callbackVirtualAccountId;

  @SerializedName("external_id")
  @Getter
  String externalId;

  @SerializedName("account_number")
  @Getter
  String accountNumber;

  @SerializedName("merchant_code")
  @Getter
  String merchantCode;

  @SerializedName("bank_code")
  @Getter
  String bankCode;

  @SerializedName("amount")
  @Getter
  Number amount;

  @SerializedName("transaction_timestamp")
  @Getter
  String transactionTimeStamp;

  @SerializedName("currency")
  @Getter
  String currency;
}
