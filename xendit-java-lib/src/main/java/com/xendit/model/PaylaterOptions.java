package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PaylaterOptions {
  @SerializedName("downpayment_amount")
  private Number downpaymentAmount;

  @SerializedName("installment_amount")
  private Number isntallmentAmount;

  @SerializedName("interest_rate")
  private Number interestRate;

  @SerializedName("total_amount")
  private Number totalAmount;

  @SerializedName("interval")
  private String interval;

  @SerializedName("interval_count")
  private Number intervalCount;

  @SerializedName("total_recurrence")
  private Number totalRecurrence;

  @SerializedName("description")
  private String description;
}
