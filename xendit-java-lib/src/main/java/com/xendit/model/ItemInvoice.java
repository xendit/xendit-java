package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class ItemInvoice extends BaseModel {
    @SerializedName("name")
    @Getter
    String name;

    @SerializedName("price")
    @Getter
    Number price;

    @SerializedName("quantity")
    @Getter
    Number quantity;
}
