package com.xendit.model.invoice;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class ItemInvoice {
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
