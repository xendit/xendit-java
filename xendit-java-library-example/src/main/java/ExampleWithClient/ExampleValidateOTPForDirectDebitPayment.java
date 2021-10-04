package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.DirectDebitPayment;

import java.util.HashMap;
import java.util.Map;

public class ExampleValidateOTPForDirectDebitPayment {
	private static void validateOTP(XenditClient xenditClient) {
		try {
			String directDebitPaymentId = "ddpy-7e61b0a7-92f9-4762-a994-c2936306f44c";
			String otpCode = "222000";

			DirectDebitPayment directDebitPayment = xenditClient.directDebitPayment.validateOTP(
					directDebitPaymentId,
					otpCode
			);
			System.out.println(directDebitPayment.getId());
			System.out.println(directDebitPayment.getPaymentMethodId());
			System.out.println(directDebitPayment.getChannelCode());
			System.out.println(directDebitPayment.getStatus());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	private static void validateOTP_withAllParametersAsHashmap(XenditClient xenditClient) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("otp_code", "222000");

			String directDebitPaymentId = "ddpy-b150da90-2121-44a6-a836-5eebf0d7ab55";

			DirectDebitPayment directDebitPayment = xenditClient.directDebitPayment.validateOTP(directDebitPaymentId, params);
			System.out.println(directDebitPayment.getId());
			System.out.println(directDebitPayment.getPaymentMethodId());
			System.out.println(directDebitPayment.getChannelCode());
			System.out.println(directDebitPayment.getStatus());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.setApikey("xnd_development_...")
				.build();
		validateOTP(xenditClient);
		validateOTP_withAllParametersAsHashmap(xenditClient);
	}
}
