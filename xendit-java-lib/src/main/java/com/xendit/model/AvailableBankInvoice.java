package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class AvailableBankInvoice extends BaseModel {
    @SerializedName("bank_code")
    @Getter
    String bankCode;

    @SerializedName("collection_type")
    @Getter
    String collectionType;

    @SerializedName("bank_account_number")
    @Getter
    String bankAccountNumber;

    @SerializedName("transfer_amount")
    @Getter
    Number transferAmount;

    @SerializedName("bank_branch")
    @Getter
    String bankBranch;

    @SerializedName("account_holder_name")
    @Getter
    String accountHolderName;

    @SerializedName("identity_amount")
    @Getter
    Number identityAmount;
}
