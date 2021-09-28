package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.directDebit.PaymentMethod;

import java.util.Arrays;

public class ExampleGetPaymentMethodByCustomerId {
	public static void main(String[] args) {
		//access key with Option
		Xendit.Opt.setApiKey("xnd_development_..");

		//access static variable (same as old code )
		//Xendit.apiKey = "xnd_development_...";
		try {
			PaymentMethod[] paymentMethods = PaymentMethod.getPaymentMethodsByCustomerId("4b7b6050-0830-440a-903b-37d527dbbaa9");
			System.out.println(Arrays.toString(paymentMethods));
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}
}
