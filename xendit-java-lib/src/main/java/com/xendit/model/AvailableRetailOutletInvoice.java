package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class AvailableRetailOutletInvoice extends BaseModel {
    @SerializedName("retail_outlet_name")
    @Getter
    private String retailOutletName;

    @SerializedName("payment_code")
    @Getter
    private String paymentCode;

    @SerializedName("transfer_amount")
    @Getter
    private Number transferAmount;

    @SerializedName("merchant_name")
    @Getter
    private String merchantName;
}
