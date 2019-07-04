package com.xendit.model;

import com.google.gson.annotations.SerializedName;

public class AvailableBank extends BaseModel {
    @SerializedName("name")
    String name;

    @SerializedName("code")
    String code;

    @SerializedName("can_disburse")
    Boolean canDisburse;

    @SerializedName("can_name_validate")
    Boolean canNameValidate;
}
