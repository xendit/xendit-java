package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.PaymentMethod;

import java.util.Arrays;

public class ExampleGetPaymentMethodByCustomerId {
	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.setApikey("xnd_development_...")
				.build();
		try {
			PaymentMethod[] paymentMethods = xenditClient.directDebitPayment.getPaymentMethodsByCustomerId("4b7b6050-0830-440a-903b-37d527dbbaa9");
			System.out.println(Arrays.toString(paymentMethods));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
