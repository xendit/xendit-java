package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class EWalletLinkajaItem extends BaseModel {
    @SerializedName("id")
    @Getter
    private String id;

    @SerializedName("name")
    @Getter
    private String name;

    @SerializedName("price")
    @Getter
    private Number price;

    @SerializedName("quantity")
    @Getter
    private Number quantity;

    public EWalletLinkajaItem(String id, String name, Number price, Number quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
