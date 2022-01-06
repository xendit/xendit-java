package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PaylaterOrderItem {
  @SerializedName("type")
  private String type;

  @SerializedName("reference_id")
  private String referenceId;

  @SerializedName("name")
  private String name;

  @SerializedName("net_unit_amount")
  private String netUnitAmount;

  @SerializedName("quantity")
  private Number quantity;

  @SerializedName("url")
  private String url;

  @SerializedName("category")
  private String category;

  @SerializedName("subcategory")
  private String subCategory;

  @SerializedName("description")
  private String description;

  @SerializedName("metadata")
  private Map<String, Object> metadata;
}
