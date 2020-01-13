package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableEwalletInvoice {
    @SerializedName("ewallet_type")
    private String ewalletType;
}
