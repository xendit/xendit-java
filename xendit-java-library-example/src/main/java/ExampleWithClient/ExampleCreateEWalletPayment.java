package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.model.EWalletLinkajaItem;
import com.xendit.XenditClient;
import com.xendit.model.EWalletPayment;

public class ExampleCreateEWalletPayment {
    private static void createOvo(XenditClient xenditClient) {
        try {
            String externalId = "ovo-ewallet";
            Number amount = 4444;
            String phone = "081298498259";

            EWalletPayment payment = xenditClient.eWallet.createOvoPayment(externalId, amount, phone);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void createLinkaja(XenditClient xenditClient) {
        try {
            EWalletLinkajaItem item0 =
                EWalletLinkajaItem.builder().id("123123").name("Phone Case").price(10000).quantity(1).build();
            EWalletLinkajaItem item1 =
                EWalletLinkajaItem.builder().id("345678").name("Powerbank").price(200000).quantity(1).build();
            EWalletLinkajaItem[] array = new EWalletLinkajaItem[]{item0, item1};
            String externalId = "linkaja-ewallet";
            Number amount = 10000;
            String phone = "081298498259";
            String callbackUrl = "https://yourwebsite.com/callback";
            String redirectUrl = "https://yourwebsite.com/order/123";

            EWalletPayment payment = xenditClient.eWallet.createLinkajaPayment(externalId, amount, phone, array, callbackUrl, redirectUrl);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void createDana(XenditClient xenditClient) {
        try {
            String externalId = "dana-ewallet1";
            Number amount = 100000;
            String phone = "081298498259";
            String expirationDate = "2020-02-20T00:00:00.000Z";
            String callbackUrl = "https://my-shop.com/callbacks";
            String redirectUrl = "https://my-shop.com/home";

            EWalletPayment payment = xenditClient.eWallet.createDanaPayment(externalId, amount, phone, expirationDate, callbackUrl, redirectUrl);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //create xendit client which holds value of apikey
        XenditClient xenditClient = new XenditClient.Builder()
                .setApikey("xnd_development_...")
                .build();
        createOvo(xenditClient);
        /*createLinkaja(xenditClient);
        createDana(xenditClient);*/
    }
}

