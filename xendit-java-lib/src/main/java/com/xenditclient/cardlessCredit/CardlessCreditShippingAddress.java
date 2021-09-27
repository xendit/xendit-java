package com.xenditclient.cardlessCredit;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CardlessCreditShippingAddress {
  @SerializedName("first_name")
  private String firstName;

  @SerializedName("last_name")
  private String lastName;

  @SerializedName("address")
  private String address;

  @SerializedName("city")
  private String city;

  @SerializedName("postal_code")
  private String postalCode;

  @SerializedName("phone")
  private String phone;

  @SerializedName("country_code")
  private String countryCode;
}
