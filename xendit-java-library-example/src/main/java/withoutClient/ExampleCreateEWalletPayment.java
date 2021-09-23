package withoutClient;

import com.xendit.exception.XenditException;
import com.xendit.model.EWalletLinkajaItem;
import com.xenditclient.Xendit;
import com.xenditclient.ewallet.EWalletPayment;

public class ExampleCreateEWalletPayment {
    private static void createOvo() {
        try {
            String externalId = "ovo-ewallet2";
            Number amount = 4444;
            String phone = "081298498259";

            EWalletPayment payment = EWalletPayment.createOvoPayment(externalId, amount, phone);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void createLinkaja() {
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

            EWalletPayment payment = EWalletPayment.createLinkajaPayment(externalId, amount, phone, array, callbackUrl, redirectUrl);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    private static void createDana() {
        try {
            String externalId = "dana-ewallet2";
            Number amount = 100000;
            String phone = "081298498259";
            String expirationDate = "2021-12-20T00:00:00.000Z";
            String callbackUrl = "https://my-shop.com/callbacks";
            String redirectUrl = "https://my-shop.com/home";

            EWalletPayment payment = EWalletPayment.createDanaPayment(externalId, amount, phone, expirationDate, callbackUrl, redirectUrl);
            System.out.println(payment);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //access key with Option
        Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

        //access static variable (same as old code )
        //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

        createOvo();
       /* createLinkaja();
        createDana();*/
    }
}

