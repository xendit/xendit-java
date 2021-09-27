package com.xenditclient.batchDisbursements;

import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.network.RequestResource;
import com.xendit.Xendit;
import com.xendit.network.NetworkClient;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class BatchDisbursementClient {
    private Xendit.Option opt;

    private NetworkClient requestClient;

    public Xendit.Option getOpt() {
        return opt;
    }

    public BatchDisbursement create(String reference, BatchDisbursementItem[] disbursements)
            throws XenditException {
        return create(new HashMap<>(), reference, disbursements);
    }

    public BatchDisbursement create(
            Map<String, String> headers, String reference, BatchDisbursementItem[] disbursements)
            throws XenditException {
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/batch_disbursements");
        Map<String, Object> params = new HashMap<>();
        params.put("reference", reference);
        params.put("disbursements", disbursements);

        return this.requestClient.request(
                RequestResource.Method.POST, url, headers, params,opt.getApiKey(), BatchDisbursement.class);
    }

    public AvailableBank[] getAvailableBanks() throws XenditException {
        String url = String.format("%s%s", Xendit.Opt.getXenditURL(), "/available_disbursements_banks");
        return this.requestClient.request(
                RequestResource.Method.GET, url, null,opt.getApiKey(), AvailableBank[].class);
    }

}
