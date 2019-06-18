package com.xendit.resources;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CreateClosedVirtualAccount {
    @SerializedName("external_id")
    Long externalId;

    @SerializedName("bank_code")
    String bankCode;

    @SerializedName("name")
    String name;

    @SerializedName("virtual_account_number")
    String virtualAccountNumber;

    @SerializedName("suggested_amount")
    Long suggestedAmount;

    @SerializedName("is_closed")
    final Boolean isClosed = true;

    @SerializedName("expected_amount")
    Long expectedAmount;

    @SerializedName("expiration_date")
    Date expirationDate;

    @SerializedName("is_single_use")
    Boolean isSingleUse;

    @SerializedName("description")
    String description;
}
