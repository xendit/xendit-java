import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Balance;

public class ExampleGetBalance {
  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";

    try {
      Balance balance = Balance.get();
      System.out.println(balance.getBalance());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }
}
