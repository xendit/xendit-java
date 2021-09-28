package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.directDebit.DirectDebitPayment;

public class ExampleGetDirectDebitPaymentStatusById {
	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.apikey("xnd_development_...")
				.build();

		try {
			DirectDebitPayment directDebitPayment = xenditClient.directDebitPayment.getDirectDebitPaymentStatusById("ddpy-7e61b0a7-92f9-4762-a994-c2936306f44c");
			System.out.println(directDebitPayment.getId());
			System.out.println(directDebitPayment.getReferenceId());
			System.out.println(directDebitPayment);
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
