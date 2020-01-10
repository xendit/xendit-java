package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class AvailableEwalletInvoice {
    @SerializedName("ewallet_type")
    @Getter
    private String ewalletType;
}
