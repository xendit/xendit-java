import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.UnbindedLinkedAccount;

public class ExampleUnbindLinkedAccount {
	public static void main(String[] args) {
		Xendit.apiKey = "xnd_development_...";

		try {
			UnbindedLinkedAccount linkedAccount = UnbindedLinkedAccount.unbindLinkedAccountToken("lat-a08fba1b-100c-445b-b788-aaeaf8215e8f");
			System.out.println(linkedAccount.getId());
			System.out.println(linkedAccount.getIsDeleted());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
