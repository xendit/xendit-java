# Xendit Java Library

This library is the abstraction of Xendit API for access from applications written with Java.

## Table of Contents

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [API Documentation](#api-documentation)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Disbursement Services](#disbursement-services)
    - [Create a disbursement](#create-a-disbursement)
    - [Get banks with available disbursement service](#get-banks-with-available-disbursement-service)
    - [Get a disbursement by external ID](#get-a-disbursement-by-external-id)
    - [Get a disbursement by ID](#get-a-disbursement-by-id)
  - [Invoice services](#invoice-services)
    - [Create an invoice](#create-an-invoice)
    - [Get an invoice by ID](#get-an-invoice-by-id)
    - [Get all invoices](#get-all-invoices)
    - [Expire an invoice](#expire-an-invoice)
  - [Virtual Account Services](#virtual-account-services)
    - [Create a fixed virtual account](#create-a-fixed-virtual-account)
      - [Closed virtual account](#closed-virtual-account)
      - [Opened virtual account](#opened-virtual-account)
    - [Get banks with available virtual account service](#get-banks-with-available-virtual-account-service)
    - [Get a fixed virtual account by ID](#get-a-fixed-virtual-account-by-id)
    - [Get a fixed virtual account payment by payment ID](#get-a-fixed-virtual-account-payment-by-payment-id)
  - [Retail Outlet Services](#retail-outlet-services)
    - [Create fixed payment code](#create-fixed-payment-code)
    - [Get fixed payment code](#get-fixed-payment-code)
    - [Update fixed payment code](#update-fixed-payment-code)
- [Contributing](#contributing)
  - [Tests](#tests)
  - [Precommit](#precommit)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## API Documentation
Please check [Xendit API Reference](https://xendit.github.io/apireference/).

## Requirements
JDK 1.7 or later.

## Installation
Maven:
```
<dependency>
  <groupId>com.xendit</groupId>
  <artifactId>xendit-java-lib</artifactId>
  <version>SELECTED_VERSION</version>
  <type>pom</type>
</dependency>
```

Gradle:
```
compile 'com.xendit:xendit-java-lib:{SELECTED_VERSION}'
```

More information: https://bintray.com/xendit/android/xendit-java-lib

## Usage
You need to use secret API key in order to use functionality in this library. The key can be obtained from your [Xendit Dasboard](https://dashboard.xendit.co/settings/developers#api-keys).

```java
import com.xendit.Xendit;

public class Example {
    public static void main(String[] args) {
        Xendit.apiKey = "PUT YOUR API KEY HERE";
    }
}
```

Example: Create a disbursement

```java
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Disbursement;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateDisbursement {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "my_external_id");
            params.put("bank_code", "BCA");
            params.put("account_holder_name", "John Doe");
            params.put("account_number", "123456789");
            params.put("description", "My Description");
            params.put("amount", "90000");

            Disbursement disbursement = Disbursement.create(params);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
```

There are some examples provided for you [here](https://github.com/xendit/xendit-java-library/tree/master/xendit-java-library-example/src/main/java).

### Disbursement Services

#### Create a disbursement

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
Disbursement.create(
    String externalId,
    String bankCode,
    String accountHolderName,
    String accountNumber,
    String description,
    BigInteger amount,
    String[] emailTo,
    String[] emailCc,
    String[] emailBcc
);
</pre>
</td>
<td>
<pre>
Disbursement.create(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
params.put("external_id", "my_external_id");
params.put("bank_code", "BCA");
params.put("account_holder_name", "John Doe");
params.put("account_number", "123456789");
params.put("description", "My Description");
params.put("amount", "90000");

Disbursement disbursement = Disbursement.create(params);
```

#### Get banks with available disbursement service

```java
AvailableBank[] banks = Disbursement.getAvailableBanks();
```

#### Get a disbursement by external ID

```java
Disbursement disbursement = Disbursement.getByExternalId("EXAMPLE_ID");
```

#### Get a disbursement by ID

```java
Disbursement disbursement = Disbursement.getById("EXAMPLE_ID");
```

[Back to top](#table-of-contents)

### Invoice services

#### Create an invoice

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
Invoice.create(
    String externalId,
    Number amount,
    String payerEmail,
    String description
);
</pre>
</td>
<td>
<pre>
Invoice.create(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
params.put("external_id", "my_external_id");
params.put("amount", 1800000);
params.put("payer_email", "customer@domain.com");
params.put("description", "Invoice Demo #123");

Invoice invoice = Invoice.create(params);
```

#### Get an invoice by ID

```java
Invoice invoice = Invoice.getById("EXAMPLE_ID");
```

#### Get all invoices

```java
Map<String, Object> params = new HashMap<>();
params.put("limit", 3);
params.put("statuses", "[\"SETTLED\",\"EXPIRED\"]");

Invoice[] invoices = Invoice.getAll(params);
```

#### Expire an invoice

```java
Invoice invoice = Invoice.expire("EXAMPLE_ID");
```

[Back to top](#table-of-contents)

### Virtual Account Services

#### Create a fixed virtual account

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

##### Closed virtual account

<table>
<tr>
<td>
<pre>
FixedVirtualAccount.createClosed(
    String externalId,
    String bankCode,
    String name,
    Long expectedAmount,
    Map&lt;String, Object&gt; additionalParam
);
</pre>
</td>
<td>
<pre>
FixedVirtualAccount.createClosed(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

##### Opened virtual account

<table>
<tr>
<td>
<pre>
FixedVirtualAccount.createOpen(
    String externalId,
    String bankCode,
    String name,
    Map&lt;String, Object&gt; additionalParam
);
</pre>
</td>
<td>
<pre>
FixedVirtualAccount.createOpen(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
params.put("external_id", "my_external_id");
params.put("bank_code", BankCode.BNI.getText());
params.put("name", "John Doe");

FixedVirtualAccount virtualAccount = FixedVirtualAccount.createOpen(params);
```

#### Get banks with available virtual account service

```java
AvailableBank[] availableBanks = FixedVirtualAccount.getAvailableBanks();
```

#### Get a fixed virtual account by ID

```java
FixedVirtualAccount fpa = FixedVirtualAccount.getFixedVA("EXAMPLE_ID");
```

#### Get a fixed virtual account payment by payment ID

```java
FixedVirtualAccountPayment payment = FixedVirtualAccount.getPayment("EXAMPLE_PAYMENT_ID");
```

[Back to top](#table-of-contents)

### Retail Outlet Services

#### Create fixed payment code

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
RetailOutlet.createFixedPaymentCode(
    String externalId,
    String retailOutletName,
    String name,
    Number expectedAmount
);
</pre>
</td>
<td>
<pre>
RetailOutlet.createFixedPaymentCode(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
params.put("external_id", "test");
params.put("retail_outlet_name", "ALFAMART");
params.put("name", "Rika Sutanto");
params.put("expected_amount", 10000);

FixedPaymentCode fpc = RetailOutlet.createFixedPaymentCode(params);
```

#### Get fixed payment code

```java
FixedPaymentCode fpc = RetailOutlet.getFixedPaymentCode("EXAMPLE_ID");
```

#### Update fixed payment code

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
RetailOutlet.updateFixedPaymentCode(
    String id,
    String name,
    Number expectedAmount,
    String expirationDate
);
</pre>
</td>
<td>
<pre>
RetailOutlet.updateFixedPaymentCode(
    String id,
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
params.put("name", "Lorem Ipsum");

FixedPaymentCode fpc = RetailOutlet.updateFixedPaymentCode("EXAMPLE_ID", params);
```

[Back to top](#table-of-contents)

## Contributing

### Tests

Make sure the the code passes all tests.

```
./gradlew test
```

### Precommit

Before making any commits, please install pre-commit. To install pre-commit, follow the [installation steps](https://pre-commit.com/#install).

For any requests, bugs, or comments, please [open an issue](https://github.com/xendit/xendit-java-library/issues) or [submit a pull request](https://github.com/xendit/xendit-java-library/pulls).