package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.directDebit.DirectDebitBasketItem;
import com.xenditclient.directDebit.DirectDebitDevice;
import com.xenditclient.directDebit.DirectDebitPayment;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateDirectDebitPayment {
	private static void createDirectDebitPayment() {
		try {
			DirectDebitBasketItem basketItem =  DirectDebitBasketItem.builder()
					.referenceId("basket-product-ref-id")
					.name("product-name")
					.category("mechanics")
					.market("ID")
					.price(50000)
					.quantity(5)
					.type("product type")
					.subCategory("product sub category")
					.description("product description")
					.url("https://product.url")
					.build();
			DirectDebitBasketItem[] basketItemArray = new DirectDebitBasketItem[]{basketItem};

			DirectDebitDevice device = DirectDebitDevice.builder()
					.id("device-id")
					.ipAddress("0.0.0.0")
					.userAgent("user-agent")
					.adId("ad-id")
					.imei("123a456b789c")
					.build();

			Map<String, Object> metadata = new HashMap<>();
			metadata.put("tes", "123");

			String referenceId = "test-direct-debit-ref-3";
			String paymentMethodId = "pm-ebb1c863-c7b5-4f20-b116-b3071b1d3aef";
			String currency = "IDR";
			Number amount = 15000;
			String callbackUrl = "http://webhook.site/";
			Boolean enableOtp = true;
			String description = "test description";
			String successRedirectUrl = "https://success-redirect.url";
			String failureRedirectUrl = "https://failure-redirect.url";
			String idempotencyKey = "idempotency-key-3";

			DirectDebitPayment directDebitPayment = DirectDebitPayment.createDirectDebitPayment(
					referenceId,
					paymentMethodId,
					currency,
					amount,
					callbackUrl,
					enableOtp,
					description,
					basketItemArray,
					device,
					successRedirectUrl,
					failureRedirectUrl,
					metadata,
					idempotencyKey
			);
			System.out.println(directDebitPayment.getId());
			System.out.println(directDebitPayment.getReferenceId());
			System.out.println(directDebitPayment.getPaymentMethodId());
			System.out.println(directDebitPayment);
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	private static void createDirectDebitPayment_withAllParametersAsHashmap() {
		try {
			DirectDebitBasketItem basketItem =  DirectDebitBasketItem.builder()
					.referenceId("basket-product-ref-id")
					.name("product-name")
					.category("mechanics")
					.market("ID")
					.price(50000)
					.quantity(5)
					.type("product type")
					.subCategory("product sub category")
					.description("product description")
					.url("https://product.url")
					.build();
			DirectDebitBasketItem[] basketItemArray = new DirectDebitBasketItem[]{basketItem};

			DirectDebitDevice device = DirectDebitDevice.builder()
					.id("device-id")
					.ipAddress("0.0.0.0")
					.userAgent("user-agent")
					.adId("ad-id")
					.imei("123a456b789c")
					.build();

			Map<String, Object> metadata = new HashMap<>();
			metadata.put("halo", "hello");
			metadata.put("tes", "123");

			Map<String, Object> params = new HashMap<>();
			params.put("reference_id", "test-direct-debit-ref-4");
			params.put("payment_method_id", "pm-ebb1c863-c7b5-4f20-b116-b3071b1d3aef");
			params.put("currency", "IDR");
			params.put("amount", 15000);
			params.put("callback_url", "http://webhook.site/");
			params.put("enable_otp", true);
			params.put("description", "test description");
			params.put("basket", basketItemArray);
			params.put("success_redirect_url", "https://success-redirect.url");
			params.put("failure_redirect_url", "https://failure-redirect.url");
			params.put("device", device);
			params.put("metadata", metadata);

			String idempotencyKey = "idempotency-key-4";

			DirectDebitPayment directDebitPayment = DirectDebitPayment.createDirectDebitPayment(params, idempotencyKey);
			System.out.println(directDebitPayment.getId());
			System.out.println(directDebitPayment.getReferenceId());
			System.out.println(directDebitPayment.getPaymentMethodId());
			System.out.println(directDebitPayment);
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//access key with Option
		Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

		//access static variable (same as old code )
		//Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";
		createDirectDebitPayment();
		createDirectDebitPayment_withAllParametersAsHashmap();
	}
}
