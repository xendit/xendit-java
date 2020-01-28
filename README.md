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
  - [Recurring Payment Services](#recurring-payment-services)
    - [Create a recurring payment](#create-a-recurring-payment)
    - [Get a recurring payment](#get-a-recurring-payment)
    - [Edit a recurring payment](#edit-a-recurring-payment)
    - [Stop a recurring payment](#stop-a-recurring-payment)
    - [Pause a recurring payment](#pause-a-recurring-payment)
    - [Resume a recurring payment](#resume-a-recurring-payment)
    - [List recurring payments by ID](#list-recurring-payments-by-id)
  - [Balance Service](#balance-service)
    - [Get balance](#get-balance)
  - [Payout Services](#payout-services)
    - [Create a payout](#create-a-payout)
    - [Get a payout by ID](#get-a-payout-by-id)
    - [Void a payout](#void-a-payout)
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

There are some examples provided for you [here](https://github.com/xendit/xendit-java-library/tree/master/xendit-java-library-example/src/main/java).

### Disbursement Services

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

#### Create a disbursement

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

```java
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
```

```java
Disbursement.create(
    Map<String, Object> params
);
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

### Invoice services

Example: Create an invoice

```java
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateInvoice {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "my_external_id");
            params.put("amount", 1800000);
            params.put("payer_email", "customer@domain.com");
            params.put("description", "Invoice Demo #123");

            Invoice invoice = Invoice.create(params);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}

```

#### Create an invoice

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

```java
Invoice.create(
    String externalId,
    Number amount,
    String payerEmail,
    String description
);
```

```java
Invoice.create(
    Map<String, Object> params
);
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

### Virtual Account Services

Example: Create a opened fixed virtual account

```java
import com.xendit.Xendit;
import com.xendit.enums.BankCode;
import com.xendit.exception.XenditException;
import com.xendit.model.FixedVirtualAccount;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateOpenVA {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            Map<String, Object> openVAMap = new HashMap<>();
            openVAMap.put("external_id", "my_external_id");
            openVAMap.put("bank_code", BankCode.BNI.getText());
            openVAMap.put("name", "John Doe");

            FixedVirtualAccount virtualAccount = FixedVirtualAccount.createOpen(openVAMap);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}

```

#### Create a fixed virtual account

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

##### Closed virtual account

```java
FixedVirtualAccount.createClosed(
    String externalId,
    String bankCode,
    String name,
    Long expectedAmount,
    Map<String, Object> additionalParam
);
```

```java
FixedVirtualAccount.createClosed(
    Map<String, Object> params
);
```

##### Opened virtual account

```java
FixedVirtualAccount.createOpen(
    String externalId,
    String bankCode,
    String name,
    Map<String, Object> additionalParam
);
```

```java
FixedVirtualAccount.createOpen(
    Map<String, Object> params
);
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

### Recurring Payment Services

#### Create a recurring payment

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
RecurringPayment.create(
   String externalId,
   String payerEmail,
   String interval,
   Number intervalCount,
   String description,
   Number amount
);
</pre>
</td>
<td>
<pre>
RecurringPayment.create(
   Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
Map<String , Object> params = new HashMap<>();
params.put("external_id", "recurring_31451441");
params.put("payer_email", "sample_email@xendit.co");
params.put("interval", "MONTH");
params.put("interval_count", 1);
params.put("description", "Test desc");
params.put("amount", 100000);

RecurringPayment recurringPayment = RecurringPayment.create(params);
```

#### Get a recurring payment

```java
RecurringPayment recurringPayment = RecurringPayment.get("5e2dd160f8a4d24146f5974c");
```

#### Edit a recurring payment

```java
Map<String, Object> params = new HashMap<>();
params.put("amount", 987654);
params.put("interval", "WEEK");

RecurringPayment recurringPayment = RecurringPayment.edit("5e2dd55ef8a4d24146f59775", params);
```

#### Stop a recurring payment

```java
RecurringPayment recurringPayment = RecurringPayment.stop("5e2dd160f8a4d24146f5974c");
```

#### Pause a recurring payment

```java
RecurringPayment recurringPayment = RecurringPayment.pause("5e2dd55ef8a4d24146f59775");
```

#### Resume a recurring payment

```java
RecurringPayment recurringPayment = RecurringPayment.resume("5e2dd55ef8a4d24146f59775");
```

#### List recurring payments by ID

```java
Invoice[] invoices = RecurringPayment.getPaymentsById("5e2dd55ef8a4d24146f59775");
```

[Back to top](#table-of-contents)

### Balance Service

#### Get balance

The `accountType` parameter is optional.

```java
Balance.get();

Balance.get(String accountType);
```

```java
Balance balance = Balance.get("CASH");
```

### Payout Services

#### Create a payout

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
Payout.createPayout(
    String externalId,
    Number amount
);
</pre>
</td>
<td>
<pre>
Payout.createPayout(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
params.put("external_id", "my_test_id");
params.put("amount", 100000);

Payout payout = Payout.createPayout(params);
```

#### Get a payout by ID

```java
Payout payout = Payout.getPayout("EXAMPLE_ID");
```

#### Void a payout

```java
Payout payout = Payout.voidPayout("EXAMPLE_ID");
```

## Contributing

### Tests

Make sure the the code passes all tests.

```
./gradlew test
```

### Precommit

Before making any commits, please install pre-commit. To install pre-commit, follow the [installation steps](https://pre-commit.com/#install).

For any requests, bugs, or comments, please [open an issue](https://github.com/xendit/xendit-java-library/issues) or [submit a pull request](https://github.com/xendit/xendit-java-library/pulls).