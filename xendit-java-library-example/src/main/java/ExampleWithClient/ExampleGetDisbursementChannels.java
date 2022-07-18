package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.DisbursementChannel;

public class ExampleGetDisbursementChannels {
    public static void main(String[] args) {
        // create xendit client which holds value of apikey. You must replace api key
        // with actual api key
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_dummy_...")
                .build();

        try {
            /**
             * Get all disbursement channels
             */
            DisbursementChannel[] disbursementChannels = xenditClient.disbursementPHP.getDisbursementChannels();

            System.out.println(disbursementChannels);

            /**
             * Get all disbursement channels by channel category
             */
            String channelCategory = "BANK";
            DisbursementChannel[] disbursementChannels2 = xenditClient.disbursementPHP
                    .getByChannelCategory(channelCategory);

            System.out.println(disbursementChannels2);

            /**
             * Get all disbursement channels by channel code
             */
            String channelCode = "PH_CITI";
            DisbursementChannel[] disbursementChannels3 = xenditClient.disbursementPHP
                    .getByChannelCode(channelCode);

            System.out.println(disbursementChannels3);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}