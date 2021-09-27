package com.xenditclient.batchDisbursements;

import com.google.gson.annotations.SerializedName;
import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.model.BatchDisbursementItem;
import com.xendit.network.RequestResource;
import com.xenditclient.Xendit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

    private static BatchDisbursementClient batchDisbursementClient;

    /**
     * Create a batch disbursement.
     *
     * @param reference     ID of the batch disbursement in your system, used to reconcile disbursements
     *                      after they have been completed
     * @param disbursements List of disbursements in the batch
     * @return BatchDisbursement
     * @throws XenditException XenditException
     */
    public static BatchDisbursement create(String reference, BatchDisbursementItem[] disbursements)
            throws XenditException {
        return create(new HashMap<>(), reference, disbursements);
    }

    /**
     * Create a batch disbursement.
     *
     * @param headers
     * @param reference     ID of the batch disbursement in your system, used to reconcile disbursements
     *                      after they have been completed
     * @param disbursements List of disbursements in the batch
     * @return BatchDisbursement
     * @throws XenditException XenditException
     */
    public static BatchDisbursement create(
            Map<String, String> headers, String reference, BatchDisbursementItem[] disbursements)
            throws XenditException {
        BatchDisbursementClient client = getClient();
        return client.create(headers, reference, disbursements);
    }

    /**
     * Get disbursement available bank
     *
     * @return AvailableBank[]
     * @throws XenditException XenditException
     */
    public static AvailableBank[] getAvailableBanks() throws XenditException {
        BatchDisbursementClient client = getClient();
        return client.getAvailableBanks();
    }

    /**
     * Its create a client for BatchDisbursement
     *
     * @return BatchDisbursementClient
     */
    private static BatchDisbursementClient getClient() {
        if (isApiKeyExist()) {
            if (batchDisbursementClient == null
                    || !batchDisbursementClient.getOpt().getApiKey().trim().equals(Xendit.apiKey.trim())) {
                return batchDisbursementClient =
                        new BatchDisbursementClient(Xendit.Opt.setApiKey(Xendit.apiKey), Xendit.getRequestClient());
            }
        } else {
            if (batchDisbursementClient == null
                    || !batchDisbursementClient.getOpt().getApiKey().trim().equals(Xendit.Opt.getApiKey().trim())) {
                return batchDisbursementClient = new BatchDisbursementClient(Xendit.Opt, Xendit.getRequestClient());
            }
        }
        return batchDisbursementClient;
    }

    /**
     * check if api-key is exist or not
     *
     * @return boolean
     */
    private static boolean isApiKeyExist() {
        return Xendit.apiKey != null;
    }
}
