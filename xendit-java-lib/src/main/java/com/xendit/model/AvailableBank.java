package com.xendit.model;

import com.google.gson.annotations.SerializedName;

public class AvailableBank extends BaseModel {
    @SerializedName("name")
    String name;

    @SerializedName("code")
    String code;
}
