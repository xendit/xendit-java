package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.directDebit.AccessibleLinkedAccount;

import java.util.Arrays;

public class ExampleRetrieveAccessibleLinkedAccounts {
	public static void main(String[] args) {
		//access key with Option
		Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

		//access static variable (same as old code )
		//Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";
		try {
			AccessibleLinkedAccount[] linkedAccounts = AccessibleLinkedAccount.retrieveAccessibleLinkedAccounts(
					"lat-960e709c-bdd6-4b4a-a361-243186379c45");
			System.out.println(Arrays.toString(linkedAccounts));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
