package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class AvailableBank {
  @SerializedName("name")
  @Getter
  String name;

  @SerializedName("code")
  @Getter
  String code;

  @SerializedName("can_disburse")
  @Getter
  Boolean canDisburse;

  @SerializedName("can_name_validate")
  @Getter
  Boolean canNameValidate;
}
