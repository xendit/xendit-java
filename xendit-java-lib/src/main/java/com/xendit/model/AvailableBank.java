package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AvailableBank {
  @SerializedName("name")
  String name;

  @SerializedName("code")
  String code;

  @SerializedName("can_disburse")
  Boolean canDisburse;

  @SerializedName("can_name_validate")
  Boolean canNameValidate;
}
