package com.xenditclient.ewallet;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class EWalletBasketItem {
  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("name")
  private String name;

  @SerializedName("category")
  private String category;

  @SerializedName("currency")
  private String currency;

  @SerializedName("price")
  private Number price;

  @SerializedName("quantity")
  private Number quantity;

  @SerializedName("type")
  private String type;

  @SerializedName("url")
  private String url;

  @SerializedName("description")
  private String description;

  @SerializedName("sub_category")
  private String subCategory;

  @SerializedName("metadata")
  private Map<String, Object> metadata;
}
