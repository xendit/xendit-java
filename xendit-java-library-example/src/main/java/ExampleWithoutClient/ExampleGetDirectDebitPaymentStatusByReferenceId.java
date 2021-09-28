package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.directDebit.DirectDebitPayment;

import java.util.Arrays;

public class ExampleGetDirectDebitPaymentStatusByReferenceId {
	public static void main(String[] args) {
		//access key with Option
		Xendit.Opt.setApiKey("xnd_development_...");

		//access static variable (same as old code )
		//Xendit.apiKey = "xnd_development_...";
		try {
			DirectDebitPayment[] directDebitPayments = DirectDebitPayment.getDirectDebitPaymentStatusByReferenceId("test-direct-debit-ref-4");
			System.out.println(Arrays.toString(directDebitPayments));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
