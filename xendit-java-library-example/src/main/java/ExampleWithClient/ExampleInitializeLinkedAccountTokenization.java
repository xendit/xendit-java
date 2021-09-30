package ExampleWithClient;

import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.InitializedLinkedAccount;
import com.xendit.model.LinkedAccountEnum.ChannelCode;

import java.util.HashMap;
import java.util.Map;

public class ExampleInitializeLinkedAccountTokenization {
	private static void initializeLinkedAccountTokenization(XenditClient xenditClient) {
		try {
			Map<String, Object> properties = new HashMap<>();
			properties.put("account_mobile_number", "+62818555988");
			properties.put("card_last_four", "8888");
			properties.put("card_expiry", "06/24");
			properties.put("account_email", "test.email@xendit.co");

			Map<String, Object> metadata = new HashMap<>();
			metadata.put("tes", "123");

			String customerId = "791ac956-397a-400f-9fda-4958894e61b5";
			ChannelCode channelCode = ChannelCode.DC_BRI;

			InitializedLinkedAccount linkedAccount = xenditClient.directDebitPayment.initializeLinkedAccountTokenization(
					customerId,
					channelCode,
					properties,
					metadata
			);
			System.out.println(linkedAccount.getCustomerId());
			System.out.println(linkedAccount.getChannelCode());
			System.out.println(linkedAccount.getStatus());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	private static void initializeLinkedAccountTokenization_withAllParametersAsHashmap(XenditClient xenditClient) {
		try {
			Map<String, Object> properties = new HashMap<>();
			properties.put("success_redirect_url", "https://success-redirect-url.com");
			properties.put("failure_redirect_url", "https://failure-redirect-url.com");
			properties.put("callback_url", "https://callback-url.com");

			Map<String, Object> metadata = new HashMap<>();
			metadata.put("tes", "123");

			Map<String, Object> params = new HashMap<>();
			params.put("customer_id", "791ac956-397a-400f-9fda-4958894e61b5");
			params.put("channel_code", "BA_UBP");
			params.put("properties", properties);
			params.put("metadata", metadata);

			InitializedLinkedAccount linkedAccount = xenditClient.directDebitPayment.initializeLinkedAccountTokenization(params);
			System.out.println(linkedAccount.getCustomerId());
			System.out.println(linkedAccount.getChannelCode());
			System.out.println(linkedAccount.getStatus());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		XenditClient xenditClient = new XenditClient.Builder()
				.setApikey("xnd_development_...")
				.build();
		initializeLinkedAccountTokenization(xenditClient);
		initializeLinkedAccountTokenization_withAllParametersAsHashmap(xenditClient);
	}
}
