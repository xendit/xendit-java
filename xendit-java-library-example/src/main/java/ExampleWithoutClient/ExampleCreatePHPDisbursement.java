package ExampleWithoutClient;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Beneficiary;
import com.xendit.model.DisbursementChannel;
import com.xendit.model.DisbursementPHP;
import com.xendit.model.ReceiptNotification;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExampleCreatePHPDisbursement {
        public static void main(String[] args) {
                // access key with Option
                // you must replace api key with actual api key
                Xendit.Opt.setApiKey("xnd_development_...");

                try {
                        /**
                         * [OPTIONAL]
                         * Before requesting disbursement to Xendit, you should get the supported
                         * channels
                         * code via getDisbursementChannels or if you know the channel category or code
                         * you can getByChannelCategory or getByChannelCode
                         * method. You can skip this step if your system is already familiar with our
                         * standard code.
                         * In this example, we call available disbursement bank function.
                         */
                        DisbursementChannel[] disbursementChannels = DisbursementChannel.getDisbursementChannels();
                        /**
                         * Let's say that we want to use first channel in that list.
                         */
                        DisbursementChannel disbursementChannel = disbursementChannels[0];
                        /**
                         * There are several options to create disbursement.
                         * First option. Create directly from a properly named hashmap key value pair.
                         * Check https://xendit.github.io/apireference/#create-a-ph-disbursement for
                         * field
                         * name.
                         */
                        Map<String, Object> disbursementMap = new HashMap<String, Object>();
                        Map<String, String> headers = new HashMap<>();
                        headers.put("xendit-idempotency-key",
                                        "xendit_idempotency_key".concat(new Date().toString()));
                        disbursementMap.put("reference_id", "reference_id_value");
                        disbursementMap.put("currency", "PHP");
                        disbursementMap.put("channel_code", disbursementChannel.getChannelCode());
                        disbursementMap.put("account_name", "John etc");
                        disbursementMap.put("account_number", "123456");
                        disbursementMap.put("description", "Disbursement description");
                        disbursementMap.put("amount", 50000);

                        DisbursementPHP disbursement = DisbursementPHP.createPHPDisbursement(headers, disbursementMap);
                        System.out.print(disbursement);

                        /**
                         * Second option. Create with individual value of required params.
                         */
                        DisbursementPHP disbursement2 = DisbursementPHP.createPHPDisbursement(
                                        "xendit_idempotency_key".concat(new Date().toString()), "reference_id_value",
                                        "PHP",
                                        disbursementChannel.getChannelCode(), "John etc", "123456",
                                        "Disbursement description", 50000);
                        System.out.print(disbursement2);
                        /**
                         * Third option. Create with individual value of required params + optional
                         * ReceiptNotification.
                         */
                        ReceiptNotification receiptNotification = ReceiptNotification.builder()
                                        .emailTo(new String[] { "test@emailTo.com" })
                                        .emailCC(new String[] { "test@emailCC.com" })
                                        .emailBcc(new String[] { "test@emailBcc.com" })
                                        .build();
                        DisbursementPHP disbursement3 = DisbursementPHP.createPHPDisbursement(
                                        "xendit_idempotency_key".concat(new Date().toString()), "reference_id_value",
                                        "PHP",
                                        disbursementChannel.getChannelCode(), "John etc", "123456",
                                        "Disbursement description", 50000, receiptNotification);
                        System.out.print(disbursement3);
                        /**
                         * Fourth option. Create with individual value of required params + optional
                         * Beneficiary.
                         */
                        Beneficiary beneficiary = Beneficiary.builder()
                                        .type("INDIVIDUAL")
                                        .givenNames("Test Name")
                                        .middleName("Middle Name")
                                        .surname("Sur Name")
                                        .businessName("Test")
                                        .streetLine1("Jl. 123")
                                        .streetLine2("Jl. 456")
                                        .city("City")
                                        .province("Province")
                                        .state("Test")
                                        .country("Test")
                                        .zipCode("12345")
                                        .mobileNumber("+639876543210")
                                        .phoneNumber("+63987654321")
                                        .email("email@test.com")
                                        .build();
                        DisbursementPHP disbursement4 = DisbursementPHP.createPHPDisbursement(
                                        "xendit_idempotency_key".concat(new Date().toString()), "reference_id_value",
                                        "PHP",
                                        disbursementChannel.getChannelCode(), "John etc", "123456",
                                        "Disbursement description", 50000, beneficiary);
                        System.out.print(disbursement4);
                        /**
                         * Fifth option. Create with individual value of required params + optional
                         * Beneficiary
                         */
                        DisbursementPHP disbursement5 = DisbursementPHP.createPHPDisbursement(
                                        "xendit_idempotency_key".concat(new Date().toString()), "reference_id_value",
                                        "PHP",
                                        disbursementChannel.getChannelCode(), "John etc", "123456",
                                        "Disbursement description", 50000, receiptNotification, beneficiary);
                        System.out.print(disbursement5);
                } catch (XenditException e) {
                        System.out.print(e);
                        e.printStackTrace();
                }
        }
}