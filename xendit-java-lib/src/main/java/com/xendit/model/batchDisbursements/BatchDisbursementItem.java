package com.xendit.model.batchDisbursements;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BatchDisbursementItem {
  @SerializedName("amount")
  private Number amount;

  @SerializedName("bank_code")
  private String bankCode;

  @SerializedName("bank_account_name")
  private String bankAccountName;

  @SerializedName("bank_account_number")
  private String bankAccountNumber;

  @SerializedName("description")
  private String description;

  @SerializedName("external_id")
  private String externalId;

  @SerializedName("email_to")
  private String[] emailTo;

  @SerializedName("email_cc")
  private String[] emailCC;

  @SerializedName("email_bcc")
  private String[] emailBcc;
}
