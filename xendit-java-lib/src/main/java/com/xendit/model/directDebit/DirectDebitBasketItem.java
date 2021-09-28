package com.xendit.model.directDebit;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DirectDebitBasketItem {
  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("name")
  private String name;

  @SerializedName("market")
  private String market;

  @SerializedName("type")
  private String type;

  @SerializedName("description")
  private String description;

  @SerializedName("category")
  private String category;

  @SerializedName("sub-category")
  private String subCategory;

  @SerializedName("price")
  private Number price;

  @SerializedName("url")
  private String url;

  @SerializedName("metadata")
  private Map<String, Object> metadata;

  @SerializedName("quantity")
  private Number quantity;
}
