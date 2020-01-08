package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class AvailableBankInvoice extends BaseModel {
    @SerializedName("bank_code")
    @Getter
    private String bankCode;

    @SerializedName("collection_type")
    @Getter
    private String collectionType;

    @SerializedName("bank_account_number")
    @Getter
    private String bankAccountNumber;

    @SerializedName("transfer_amount")
    @Getter
    private Number transferAmount;

    @SerializedName("bank_branch")
    @Getter
    private String bankBranch;

    @SerializedName("account_holder_name")
    @Getter
    private String accountHolderName;

    @SerializedName("identity_amount")
    @Getter
    private Number identityAmount;
}
