package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableBankInvoice {
  @SerializedName("bank_code")
  private String bankCode;

  @SerializedName("collection_type")
  private String collectionType;

  @SerializedName("bank_account_number")
  private String bankAccountNumber;

  @SerializedName("transfer_amount")
  private Number transferAmount;

  @SerializedName("bank_branch")
  private String bankBranch;

  @SerializedName("account_holder_name")
  private String accountHolderName;

  @SerializedName("identity_amount")
  private Number identityAmount;
}
