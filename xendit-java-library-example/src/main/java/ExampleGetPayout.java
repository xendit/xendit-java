import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Payout;

public class ExampleGetPayout {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Payout payout = Payout.get("190feb8f-a4f4-4db2-b93a-851c90a73a74");
            System.out.println(payout.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
