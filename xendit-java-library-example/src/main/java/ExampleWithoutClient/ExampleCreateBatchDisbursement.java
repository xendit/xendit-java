package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.BatchDisbursementItem;
import com.xendit.Xendit;
import com.xendit.model.BatchDisbursement;

public class ExampleCreateBatchDisbursement {
    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_...");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_...";

        try {
            BatchDisbursementItem item =
                    BatchDisbursementItem.builder()
                            .amount(15000)
                            .bankAccountName("Joe")
                            .bankCode("BCA")
                            .bankAccountNumber("1234567890")
                            .description("Example")
                            .build();
            BatchDisbursementItem[] items = new BatchDisbursementItem[]{item};

            BatchDisbursement batchDisbursement = BatchDisbursement.create("reference", items);

            System.out.println(batchDisbursement.getId());
            System.out.println(batchDisbursement.getReference());
            System.out.println(batchDisbursement.getCreated());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
