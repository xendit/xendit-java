package com.xendit.model;

import com.google.gson.annotations.SerializedName;

import lombok.*;

@Getter
@Setter
@Builder
public class Beneficiary {
  @SerializedName("type")
  private String type;

  @SerializedName("given_names")
  private String givenNames;

  @SerializedName("middle_name")
  private String middleName;

  @SerializedName("surname")
  private String surname;

  @SerializedName("business_name")
  private String businessName;

  @SerializedName("street_line1")
  private String streetLine1;

  @SerializedName("street_line2")
  private String streetLine2;

  @SerializedName("city")
  private String city;

  @SerializedName("province")
  private String province;

  @SerializedName("state")
  private String state;

  @SerializedName("country")
  private String country;

  @SerializedName("zip_code")
  private String zipCode;

  @SerializedName("mobile_number")
  private String mobileNumber;

  @SerializedName("phone_number")
  private String phoneNumber;

  @SerializedName("email")
  private String email;
}
