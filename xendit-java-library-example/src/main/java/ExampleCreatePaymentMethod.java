import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.LinkedAccountEnum.AccountType;
import com.xendit.model.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePaymentMethod {
	private static void createPaymentMethod() {
		try {
			Map<String, Object> properties = new HashMap<>();
			properties.put("id", "la-fd5b2a84-9b7e-40b3-be40-b09fd522a091");
			Map<String, Object> metadata = new HashMap<>();
			metadata.put("tes", "123");
			String customerId = "4b7b6050-0830-440a-903b-37d527dbbaa9";
			AccountType type = AccountType.DEBIT_CARD;

			PaymentMethod paymentMethod = PaymentMethod.createPaymentMethod(
					customerId,
					type,
					properties,
					metadata
			);
			System.out.println(paymentMethod.getId());
			System.out.println(paymentMethod.getType());
			System.out.println(paymentMethod.getStatus());
			System.out.println(paymentMethod.getProperties());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	private static void createPaymentMethod_withAllParametersAsHashmap() {
		try {
			Map<String, Object> properties = new HashMap<>();
			properties.put("id", "la-052d3e2d-bc4d-4c98-8072-8d232a552299");
			Map<String, Object> metadata = new HashMap<>();
			metadata.put("halo", "hello");
			metadata.put("tes", "123");
			Map<String, Object> params = new HashMap<>();
			params.put("customer_id", "4b7b6050-0830-440a-903b-37d527dbbaa9");
			params.put("type", "DEBIT_CARD");
			params.put("properties", properties);
			params.put("metadata", metadata);

			PaymentMethod paymentMethod = PaymentMethod.createPaymentMethod(params);
			System.out.println(paymentMethod.getId());
			System.out.println(paymentMethod.getType());
			System.out.println(paymentMethod.getStatus());
			System.out.println(paymentMethod.getProperties());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Xendit.apiKey = "xnd_development_...";
		createPaymentMethod();
		createPaymentMethod_withAllParametersAsHashmap();
	}
}
