package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class AvailableEwalletInvoice extends BaseModel {
    @SerializedName("ewallet_type")
    @Getter
    String ewalletType;
}
