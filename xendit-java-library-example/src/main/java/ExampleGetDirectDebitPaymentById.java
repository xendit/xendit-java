import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.DirectDebitPayment;

public class ExampleGetDirectDebitPaymentById {
	public static void main(String[] args) {
		Xendit.apiKey = "xnd_development_...";

		try {
			DirectDebitPayment directDebitPayment = DirectDebitPayment.getDirectDebitPaymentById("ddpy-7e61b0a7-92f9-4762-a994-c2936306f44c");
			System.out.println(directDebitPayment.getId());
			System.out.println(directDebitPayment.getReferenceId());
			System.out.println(directDebitPayment);
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
