package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.DisbursementChannel;

public class ExampleGetDisbursementChannels {
    public static void main(String[] args) {
        // access key with Option
        // you must replace api key with actual api key
        Xendit.Opt.setApiKey("xnd_development_...");

        try {
            /**
             * Get all disbursement channels
             */
            DisbursementChannel[] disbursementChannels = DisbursementChannel.getDisbursementChannels();

            System.out.println(disbursementChannels);

            /**
             * Get all disbursement channels by channel category
             */
            String channelCategory = "BANK";
            DisbursementChannel[] disbursementChannels2 = DisbursementChannel
                    .getByChannelCategory(channelCategory);

            System.out.println(disbursementChannels2);

            /**
             * Get all disbursement channels by channel code
             */
            String channelCode = "PH_CITI";
            DisbursementChannel[] disbursementChannels3 = DisbursementChannel
                    .getByChannelCode(channelCode);

            System.out.println(disbursementChannels3);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}