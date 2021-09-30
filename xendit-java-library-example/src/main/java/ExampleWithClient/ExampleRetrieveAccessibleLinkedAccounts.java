package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.AccessibleLinkedAccount;

import java.util.Arrays;

public class ExampleRetrieveAccessibleLinkedAccounts {
	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.setApikey("xnd_development_...")
				.build();
		try {
			AccessibleLinkedAccount[] linkedAccounts = xenditClient.directDebitPayment.retrieveAccessibleLinkedAccounts(
					"lat-960e709c-bdd6-4b4a-a361-243186379c45");
			System.out.println(Arrays.toString(linkedAccounts));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
