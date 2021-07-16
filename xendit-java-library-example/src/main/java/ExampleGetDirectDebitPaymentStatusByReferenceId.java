import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.DirectDebitPayment;

import java.util.Arrays;

public class ExampleGetDirectDebitPaymentStatusByReferenceId {
	public static void main(String[] args) {
		Xendit.apiKey = "xnd_development_...";

		try {
			DirectDebitPayment[] directDebitPayments = DirectDebitPayment.getDirectDebitPaymentStatusByReferenceId("test-direct-debit-ref-4");
			System.out.println(Arrays.toString(directDebitPayments));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
