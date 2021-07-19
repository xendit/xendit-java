import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.ValidatedLinkedAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleValidateOTPforLinkedAccountToken {
	private static void validateOTP() {
		try {
			String tokenId = "lat-960e709c-bdd6-4b4a-a361-243186379c45";
			String otpCode = "333000";

			ValidatedLinkedAccount linkedAccount = ValidatedLinkedAccount.validateOTP(
					tokenId,
					otpCode
			);
			System.out.println(linkedAccount.getId());
			System.out.println(linkedAccount.getCustomerId());
			System.out.println(linkedAccount.getChannelCode());
			System.out.println(linkedAccount.getStatus());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	private static void validateOTP_withAllParametersAsHashmap() {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("otp_code", "333000");

			String tokenId = "lat-ba3c5645-f134-432a-b4f4-f8972685aa03";

			ValidatedLinkedAccount linkedAccount = ValidatedLinkedAccount.validateOTP(tokenId, params);
			System.out.println(linkedAccount.getId());
			System.out.println(linkedAccount.getCustomerId());
			System.out.println(linkedAccount.getChannelCode());
			System.out.println(linkedAccount.getStatus());
		} catch (XenditException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Xendit.apiKey = "xnd_development_...";
		validateOTP();
		validateOTP_withAllParametersAsHashmap();
	}
}
