package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.directDebit.UnbindedLinkedAccount;

public class ExampleUnbindLinkedAccount {
	public static void main(String[] args) {
		//access key with Option
		Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

		//access static variable (same as old code )
		//Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

		try {
			UnbindedLinkedAccount linkedAccount = UnbindedLinkedAccount.unbindLinkedAccountToken("lat-a08fba1b-100c-445b-b788-aaeaf8215e8f");
			System.out.println(linkedAccount.getId());
			System.out.println(linkedAccount.getIsDeleted());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
