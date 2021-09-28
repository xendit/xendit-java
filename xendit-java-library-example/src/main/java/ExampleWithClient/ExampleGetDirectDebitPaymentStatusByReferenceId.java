package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.directDebit.DirectDebitPayment;

import java.util.Arrays;

public class ExampleGetDirectDebitPaymentStatusByReferenceId {
	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.apikey("xnd_development_...")
				.build();
		try {
			DirectDebitPayment[] directDebitPayments = xenditClient.directDebitPayment.getDirectDebitPaymentStatusByReferenceId("test-direct-debit-ref-4");
			System.out.println(Arrays.toString(directDebitPayments));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
