import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.BatchDisbursement;
import com.xendit.model.BatchDisbursementItem;

public class ExampleCreateBatchDisbursement {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

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
