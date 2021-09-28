package com.xendit.model.customer;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CustomerAddress {
  @SerializedName("category")
  private String category;

  @SerializedName("country")
  private String country;

  @SerializedName("state")
  private String state;

  @SerializedName("province")
  private String province;

  @SerializedName("city")
  private String city;

  @SerializedName("postal_code")
  private String postalCode;

  @SerializedName("street_line1")
  private String streetLine1;

  @SerializedName("street_line2")
  private String streetLine2;

  @SerializedName("is_preferred")
  private Boolean isPreferred;
}
