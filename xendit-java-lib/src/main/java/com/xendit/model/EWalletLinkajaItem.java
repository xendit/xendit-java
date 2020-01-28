package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EWalletLinkajaItem {
  @SerializedName("id")
  private String id;

  @SerializedName("name")
  private String name;

  @SerializedName("price")
  private Number price;

  @SerializedName("quantity")
  private Number quantity;
}
