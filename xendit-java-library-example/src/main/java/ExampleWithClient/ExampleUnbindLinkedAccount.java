package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xenditclient.directDebit.UnbindedLinkedAccount;

public class ExampleUnbindLinkedAccount {
	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
				.build();

		try {
			UnbindedLinkedAccount linkedAccount = xenditClient.directDebitPayment.unbindLinkedAccountToken("lat-a08fba1b-100c-445b-b788-aaeaf8215e8f");
			System.out.println(linkedAccount.getId());
			System.out.println(linkedAccount.getIsDeleted());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
