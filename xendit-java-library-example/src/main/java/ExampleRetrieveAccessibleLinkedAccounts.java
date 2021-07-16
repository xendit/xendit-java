import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.AccessibleLinkedAccount;

import java.util.Arrays;

public class ExampleRetrieveAccessibleLinkedAccounts {
	public static void main(String[] args) {
		Xendit.apiKey = "xnd_development_...";

		try {
			AccessibleLinkedAccount[] linkedAccounts = AccessibleLinkedAccount.retrieveAccessibleLinkedAccounts(
					"lat-960e709c-bdd6-4b4a-a361-243186379c45");
			System.out.println(Arrays.toString(linkedAccounts));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
