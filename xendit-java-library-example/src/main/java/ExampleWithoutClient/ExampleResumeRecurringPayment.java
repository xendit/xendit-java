package ExampleWithoutClient;

import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xenditclient.recurringPayment.RecurringPayment;

public class ExampleResumeRecurringPayment {
  public static void main(String[] args) {
    //access key with Option
    Xendit.Opt.setApiKey("xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB");

    //access static variable (same as old code )
    //Xendit.apiKey = "xnd_development_Z568GecuIH66011GIILkDFNJOoR1wFZdGqOOMFBrRVeX64DISB1o7hnNKB";


    try {
      RecurringPayment recurringPayment = RecurringPayment.resume("615187d21b45cae990abde32");
      System.out.println(recurringPayment.getStatus());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
