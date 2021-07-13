import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.CustomerAddress;
import com.xendit.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateCustomer {
  private static void createCustomer() {
    try {
      CustomerAddress customerAddress =  CustomerAddress.builder()
          .country("ID")
          .streetLine1("Jl. 123")
          .streetLine2("Jl. 456")
          .city("Jakarta Selatan")
          .province("DKI Jakarta")
          .state("-")
          .postalCode("12345")
          .category("None")
          .isPreferred(true)
          .build();
      CustomerAddress[] customerAddressArray = new CustomerAddress[]{customerAddress};

      String referenceId = "test-reference-id-2";
      String email = "tes@tes.com";
      String givenNames = "Given Names";
      String nationality = "ID";
      String dateOfBirth = "1995-12-30";

      Customer customer = Customer.createCustomer(
          referenceId,
          null,
          email,
          givenNames,
          null,
          null,
          null,
          null,
          nationality,
          customerAddressArray,
          dateOfBirth,
          null
      );
      System.out.println(customer.getReferenceId());
      System.out.println(customer.getEmail());
      System.out.println(customer.getGivenNames());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  private static void createCustomer2() {
    try {
      Map<String, Object> metadata = new HashMap<>();
      metadata.put("halo", "hello");
      metadata.put("tes", "123");
      Map<String, Object> params = new HashMap<>();
      params.put("reference_id", "test-reference-id-3");
      params.put("email", "tes@tes.com");
      params.put("given_names", "Given Names");
      params.put("nationality", "ID");
      params.put("date_of_birth", "1995-12-30");
      params.put("metadata", metadata);

      Customer customer = Customer.createCustomer(params);
      System.out.println(customer.getReferenceId());
      System.out.println(customer.getEmail());
      System.out.println(customer.getGivenNames());
    } catch (XenditException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Xendit.apiKey = "xnd_development_...";
    createCustomer();
    createCustomer2();
  }
}
