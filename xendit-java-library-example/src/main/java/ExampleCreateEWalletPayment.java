import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.EWalletPayment;
import com.xendit.model.EWalletLinkajaItem;

public class ExampleCreateEWalletPayment {
    private static void createOvo() {
        try {
            String externalId = "ovo-ewallet";
            String ewalletType = "OVO";
            Number amount = 10000;
            String phone = "081298498259";
            EWalletPayment payment = EWalletPayment.create(externalId, amount, phone, ewalletType);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void createLinkaja() {
        try {
            EWalletLinkajaItem item0 = new EWalletLinkajaItem("123123", "Phone Case", 10000, 1);
            EWalletLinkajaItem item1 = new EWalletLinkajaItem("345678", "Powerbank", 200000, 1);
            EWalletLinkajaItem[] array = new EWalletLinkajaItem[2];
            array[0] = item0;
            array[1] = item1;
            String externalId = "linkaja-ewallet";
            String ewalletType = "LINKAJA";
            Number amount = 10000;
            String phone = "081298498259";
            String callbackUrl = "https://yourwebsite.com/callback";
            String redirectUrl = "https://yourwebsite.com/order/123";
            EWalletPayment payment = EWalletPayment.create(externalId, amount, phone, array, ewalletType, callbackUrl, redirectUrl);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_O46JfOtygef9kMNsK+ZPGT+ZZ9b3ooF4w3Dn+R1k+2fT/7GlCAN3jg==:";
//        createOvo();
        createLinkaja();
    }
}

