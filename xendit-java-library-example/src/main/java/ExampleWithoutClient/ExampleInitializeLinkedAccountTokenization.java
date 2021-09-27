package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.directDebit.InitializedLinkedAccount;
import com.xenditclient.directDebit.LinkedAccountEnum.ChannelCode;

import java.util.HashMap;
import java.util.Map;

public class ExampleInitializeLinkedAccountTokenization {
	private static void initializeLinkedAccountTokenization() {
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

			InitializedLinkedAccount linkedAccount = InitializedLinkedAccount.initializeLinkedAccountTokenization(
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

	private static void initializeLinkedAccountTokenization_withAllParametersAsHashmap() {
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

			InitializedLinkedAccount linkedAccount = InitializedLinkedAccount.initializeLinkedAccountTokenization(params);
			System.out.println(linkedAccount.getCustomerId());
			System.out.println(linkedAccount.getChannelCode());
			System.out.println(linkedAccount.getStatus());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//access key with Option
		Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

		//access static variable (same as old code )
		//Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";
		initializeLinkedAccountTokenization();
		initializeLinkedAccountTokenization_withAllParametersAsHashmap();
	}
}
