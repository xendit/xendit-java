package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.directDebit.DirectDebitPayment;

public class ExampleGetDirectDebitPaymentStatusById {
	public static void main(String[] args) {
		//access key with Option
		Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

		//access static variable (same as old code )
		//Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";

		try {
			DirectDebitPayment directDebitPayment = DirectDebitPayment.getDirectDebitPaymentStatusById("ddpy-7e61b0a7-92f9-4762-a994-c2936306f44c");
			System.out.println(directDebitPayment.getId());
			System.out.println(directDebitPayment.getReferenceId());
			System.out.println(directDebitPayment);
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
