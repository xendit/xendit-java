package com.xendit.model;

import com.google.gson.annotations.SerializedName;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BatchDisbursement {
  @SerializedName("id")
  private String id;

  @SerializedName("created")
  private String created;

  @SerializedName("reference")
  private String reference;

  @SerializedName("total_uploaded_count")
  private Number totalUploadedCount;

  @SerializedName("total_uploaded_amount")
  private Number totalUploadedAmount;

  @SerializedName("status")
  private String status;

  /**
   * Create a batch disbursement.
   *
   * @param reference ID of the batch disbursement in your system, used to reconcile disbursements
   *     after they have been completed
   * @param disbursements List of disbursements in the batch
   * @return BatchDisbursement
   * @throws XenditException XenditException
   */
  public static BatchDisbursement create(String reference, BatchDisbursementItem[] disbursements)
      throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/batch_disbursements");
    Map<String, Object> params = new HashMap<>();
    params.put("reference", reference);
    params.put("disbursements", disbursements);

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, BatchDisbursement.class);
  }

  /**
   * Get disbursement available bank
   *
   * @return AvailableBank[]
   * @throws XenditException XenditException
   */
  public static AvailableBank[] getAvailableBanks() throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/available_disbursements_banks");
    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, null, AvailableBank[].class);
  }
}
