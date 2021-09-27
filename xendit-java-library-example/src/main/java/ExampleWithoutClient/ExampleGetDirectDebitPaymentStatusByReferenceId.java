package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.directDebit.DirectDebitPayment;

import java.util.Arrays;

public class ExampleGetDirectDebitPaymentStatusByReferenceId {
	public static void main(String[] args) {
		//access key with Option
		Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

		//access static variable (same as old code )
		//Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";
		try {
			DirectDebitPayment[] directDebitPayments = DirectDebitPayment.getDirectDebitPaymentStatusByReferenceId("test-direct-debit-ref-4");
			System.out.println(Arrays.toString(directDebitPayments));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
