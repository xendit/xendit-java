package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xenditclient.directDebit.DirectDebitPayment;

import java.util.Arrays;

public class ExampleGetDirectDebitPaymentStatusByReferenceId {
	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
				.build();
		try {
			DirectDebitPayment[] directDebitPayments = xenditClient.directDebitPayment.getDirectDebitPaymentStatusByReferenceId("test-direct-debit-ref-4");
			System.out.println(Arrays.toString(directDebitPayments));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
