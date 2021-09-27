package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xenditclient.directDebit.PaymentMethod;

import java.util.Arrays;

public class ExampleGetPaymentMethodByCustomerId {
	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.apikey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB")
				.build();
		try {
			PaymentMethod[] paymentMethods = xenditClient.directDebitPayment.getPaymentMethodsByCustomerId("4b7b6050-0830-440a-903b-37d527dbbaa9");
			System.out.println(Arrays.toString(paymentMethods));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
