import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Payout;

public class ExampleVoidPayout {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Payout payout = Payout.voidPayout("ffd90468-c4c2-4b49-bd5d-e11d6db549f2");
            System.out.println(payout.getId());
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
