# Xendit Java Library

This library is the abstraction of Xendit API for access from applications written with Java.

## Table of Contents

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [API Documentation](#api-documentation)
- [Requirements](#requirements)
- [Installation](#installation)
    - [Maven](#maven)
    - [Gradle](#gradle)
- [Usage](#usage)
  - [Disbursement Services](#disbursement-services)
    - [Create an IDR disbursement](#create-an-idr-disbursement)
    - [Create a PHP disbursement](#create-a-php-disbursement)
    - [Get banks with available IDR disbursement service](#get-banks-with-available-idr-disbursement-service)
    - [Get disbursements channels](#get-disbursements-channels)
    - [Get disbursements channels by channel category](#get-disbursement-channels-by-channel-category)
    - [Get disbursements channels by channel code](#get-disbursement-channels-by-channel-code)
    - [Get an IDR disbursement by external ID](#get-an-idr-disbursement-by-external-id)
    - [Get a PHP disbursement by reference ID](#get-a-php-disbursement-by-reference-id)
    - [Get an IDR disbursement by ID](#get-an-idr-disbursement-by-id)
    - [Get a PHP disbursement by ID](#get-a-php-disbursement-by-id)
  - [Invoice services](#invoice-services)
    - [Create an invoice](#create-an-invoice)
    - [Get an invoice by ID](#get-an-invoice-by-id)
    - [Get all invoices](#get-all-invoices)
    - [Expire an invoice](#expire-an-invoice)
  - [Virtual Account Services](#virtual-account-services)
    - [Create a fixed virtual account](#create-a-fixed-virtual-account)
      - [Closed virtual account](#closed-virtual-account)
      - [Opened virtual account](#opened-virtual-account)
    - [Update a fixed virtual account by ID](#update-a-fixed-virtual-account-by-id)
    - [Get banks with available virtual account service](#get-banks-with-available-virtual-account-service)
    - [Get a fixed virtual account by ID](#get-a-fixed-virtual-account-by-id)
    - [Get a fixed virtual account payment by payment ID](#get-a-fixed-virtual-account-payment-by-payment-id)
  - [Retail Outlet Services - Indo](#retail-outlet-services-ID)
    - [Create fixed payment code](#create-fixed-payment-code)
    - [Get fixed payment code](#get-fixed-payment-code)
    - [Update fixed payment code](#update-fixed-payment-code)
  - [Retail Outlet Services - PH](#retail-outlet-services-PH)
    - [Create payment code](#create-payment-code)
    - [Get payment code](#get-payment-code)
    - [Update payment code](#update-payment-code)
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
  - [E-Wallet Services](#e-wallet-services)
    - [Create an e-wallet charge](#create-an-e-wallet-charge)
    - [Get an e-wallet charge status](#get-an-e-wallet-charge-status)
  - [Credit Card Services](#credit-card-services)
    - [Create an authorization](#create-an-authorization)
    - [Create a charge](#create-a-charge)
    - [Reverse an authorization](#reverse-an-authorization)
    - [Capture a charge](#capture-a-charge)
    - [Get a charge by ID](#get-a-charge-by-id)
    - [Create a refund](#create-a-refund)
  - [Batch Disbursement Services](#batch-disbursement-services)
    - [Batch disbursement item](#batch-disbursement-item)
    - [Create a batch disbursement](#create-a-batch-disbursement)
    - [Get banks with available disbursement service](#get-banks-with-available-disbursement-service-1)
  - [Cardless Credit Services](#cardless-credit-services)
    - [Cardless credit item](#cardless-credit-item)
    - [Cardless credit customer details](#cardless-credit-customer-details)
    - [Cardless credit shipping address](#cardless-credit-shipping-address)
    - [Create a cardless credit payment](#create-a-cardless-credit-payment)
  - [QR Code](#qr-code)
    - [Create QR Code](#create-qr-code)
    - [Get QR Code](#get-qr-code)
  - [Customer](#customer)
    - [Create Customer](#create-customer)
    - [Get Customer by Reference ID](#get-customer-by-reference-id)
  - [Direct Debit](#direct-debit)
    - [Initialize linked account tokenization](#initialize-linked-account-tokenization)
    - [Validate OTP for Linked Account Token](#validate-otp-for-linked-account-token)
    - [Retrieve accessible accounts by linked account token](#retrieve-accessible-accounts-by-linked-account-token)
    - [Unbind linked account token](#unbind-linked-account-token)
    - [Create payment method](#create-payment-method)
    - [Get payment methods by customer ID](#get-payment-methods-by-customer-id)
    - [Create recurring payment](#create-recurring-payment)
    - [Create direct debit payment](#create-direct-debit-payment)
    - [Validate OTP for direct debit payment](#validate-otp-for-direct-debit-payment)
    - [Get direct debit payment status by ID](#get-direct-debit-payment-status-by-id)
    - [Get direct debit payment status by reference ID](#get-direct-debit-payment-status-by-reference-id)
  - [Paylater](#paylater)
    - [Initiate Paylater Plans](#initiate-paylater-plans)
    - [Create Paylater Charges](#create-paylater-charges)
    - [Get Paylater Charge by ID](#get-paylater-charge-by-id)
    - [Refund Paylater Charge](#refund-paylater-charge)
    - [Get Refund by Refund ID](#get-refund-by-refund-id)
  - [How to get Request Id](#how-to-get-request-id)
- [Contributing](#contributing)
  - [Lint](#lint)
  - [Tests](#tests)
  - [Precommit](#precommit)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## API Documentation
Please check [Xendit API Reference](https://xendit.github.io/apireference/).

## Requirements
JDK 1.7 or later.

## Installation
#### Maven

Add these lines of code in your `pom.xml`
```
<dependency>
    <groupId>com.xendit</groupId>
    <artifactId>xendit-java-lib</artifactId>
    <version>SELECTED_VERSION</version>
</dependency>
```

#### Gradle

Add these lines in your `build.gradle`
```
compile 'com.xendit:xendit-java-lib:{SELECTED_VERSION}'
```

More information: https://search.maven.org/artifact/com.xendit/xendit-java-lib

## Usage
You need to use secret API key in order to use functionality in this library. The key can be obtained from your [Xendit Dashboard](https://dashboard.xendit.co/settings/developers#api-keys).

### Without Client
If you're only dealing with a single secret key, you can simply import the packages required for the products you're interacting with without the need to create a client. Xendit Disbursement class is being used for IDR Disbursement.

There is another way to set secret key using **Xendit.Opt.setApiKey(")** which is recommended way to use instead of **Xendit.apiKey**.

```java
import com.xendit.Xendit;

public class Example {
    public static void main(String[] args) {
        Xendit.Opt.setApiKey("PUT YOUR API KEY HERE");
        // OR    
        Xendit.apiKey = "PUT YOUR API KEY HERE";               
    }
}
```
### With Client
If you're dealing with multiple secret keys, it is recommended that you use **XenditClient**. This allows you to create as many clients as needed, each with their own individual key. Xendit Disbursement Client is being used for IDR Disbursements.
```java
import com.xendit.XenditClient;

public class Example {
    public static void main(String[] args) {
        XenditClient xenditClient = new XenditClient.Builder()
                        .setApikey("PUT YOUR API KEY HERE")
                        .build();

        XenditClient xenditClient2 = new XenditClient.Builder()
                        .setApikey("PUT YOUR API KEY HERE")
                        .build();

    }
}
```
Example: Create an IDR disbursement

###### Without Client
```java
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Disbursement;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateDisbursement {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_..."; 
        //OR 
        Xendit.Opt.setApiKey("xnd_development_...");

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
###### With Client
Xendit Disbursement Client is being used for IDR Disbursement.
```java
import com.xendit.exception.XenditException;
import com.xendit.XenditClient;
import com.xendit.model.Disbursement;

import java.util.HashMap;
import java.util.Map;

public class ExampleCreateDisbursement {
    public static void main(String[] args) {
        XenditClient xenditClient = new XenditClient.Builder()
                      .setApikey("xnd_development_...")
                      .build();
        
        XenditClient xenditClient2 = new XenditClient.Builder()
                      .setApikey("xnd_development_...")
                      .build();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", "my_external_id");
            params.put("bank_code", "BCA");
            params.put("account_holder_name", "John Doe");
            params.put("account_number", "123456789");
            params.put("description", "My Description");
            params.put("amount", "90000");

            Disbursement disbursement = xenditClient.disbursement.create(params);
            Disbursement disbursement2 = xenditClient2.disbursement.create(params);
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
```

There are some examples provided for you [here](https://github.com/xendit/xendit-java-library/tree/master/xendit-java-library-example/src/main/java).

### Disbursement Services

#### Create an IDR disbursement

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

/* Without client */
Disbursement disbursement = Disbursement.create(params); 

/* With client */
Disbursement disbursement = xenditClient.disbursement.create(params);
```

#### Get banks with available IDR disbursement service

```java
/* Without client */
AvailableBank[] banks = Disbursement.getAvailableBanks();
/* With client */
AvailableBank[] banks = xenditClient.disbursement.getAvailableBanks();
```

#### Get an IDR disbursement by external ID

```java
/* Without client */
Disbursement disbursement = Disbursement.getByExternalId("EXAMPLE_ID");
/* With client */
Disbursement disbursement = xenditClient.disbursement.getByExternalId("EXAMPLE_ID");
```

#### Get an IDR disbursement by ID

```java
/* Without client */
Disbursement disbursement = Disbursement.getById("EXAMPLE_ID");
/* With client */
Disbursement disbursement = xenditClient.disbursement.getById("EXAMPLE_ID");
```

#### Create a PHP disbursement

You can choose whether want to put the attributes as parameters or to put in inside a Map object. 

<table>
<tr>
<td>
<pre>
DisbursementPHP.createPHPDisbursement(
    String xendit_idempotency_key,
    String reference_id,
    String currency,
    String channel_code,
    String account_name,
    String account_number,
    String description,
    Integer amount,
    ReceiptNotification receiptNotification,
    Beneficiary beneficiary
);
ReceiptNotification receiptNotification = ReceiptNotification.builder()
    .emailTo(new String[] { "test@emailTo.com" })
    .emailCC(new String[] { "test@emailCC.com" })
    .emailBcc(new String[] { "test@emailBcc.com" })
    .build();
Beneficiary beneficiary =
    Beneficiary.builder()
        .type("test-type")
        .givenNames("Test Name")
        .middleName("Middle Name")
        .surname("Sur Name")
        .businessName("Test")
        .streetLine1("Jl. 123")
        .streetLine2("Jl. 456")
        .city("Jakarta Selatan")
        .province("DKI Jakarta")
        .state("Test")
        .country("Test")
        .zipCode("12345")
        .mobileNumber("123456789")
        .phoneNumber("12345678")
        .email("email@test.com")
        .build();
</pre>
</td>
<td>
<pre>
DisbursementPHP.createPHPDisbursement(
    Map&lt;String, String&gt; headers, Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
Map<String, String> headers = new HashMap<>();
headers.put("xendit-idempotency-key", "xendit-idempotency-key");
params.put("reference_id", "reference_id_value");
params.put("currency", "PHP");
params.put("channel_code", "required_channel_code");
params.put("account_name", "John etc");
params.put("account_number", "123456");
params.put("description", "Disbursement description");
params.put("amount", 50000);
params.put("receipt_notification", receiptNotification);

/* Without client */
DisbursementPHP disbursement = DisbursementPHP.createPHPDisbursement(headers, params); 

/* With client */
DisbursementPHP disbursement = xenditClient.disbursementPHP.createPHPDisbursement(headers, params);
```

#### Get disbursements Channels

```java
/* Without client */
DisbursementChannel[] disbursementChannels = DisbursementChannel.getDisbursementChannels();
/* With client */
DisbursementChannel[] disbursementChannels = xenditClient.disbursementPHP.getDisbursementChannels();
```
#### Get disbursement channels by channel category

```java
/* Without client */
DisbursementChannel[] disbursementChannels = DisbursementChannel.getByChannelCategory("channel-category");
/* With client */
DisbursementChannel[] disbursementChannels = xenditClient.disbursementPHP.getByChannelCategory("channel-category");
```
#### Get disbursement channels by channel code

```java
/* Without client */
DisbursementChannel[] disbursementChannels = DisbursementChannel.getByChannelCode("channel-code");
/* With client */
DisbursementChannel[] disbursementChannels = xenditClient.disbursementPHP.getByChannelCode("channel-code");
```
#### Get a PHP disbursement by reference ID

```java
/* Without client */
DisbursementPHP disbursement = DisbursementPHP.getPHPByReferenceId("EXAMPLE_ID");
/* With client */
DisbursementPHP disbursement = xenditClient.disbursementPHP.getPHPByReferenceId("EXAMPLE_ID");
```

#### Get a PHP disbursement by ID

```java
/* Without client */
DisbursementPHP disbursement = Disbursement.getPHPById("EXAMPLE_ID");
/* With client */
DisbursementPHP disbursement = xenditClient.disbursementPHP.getPHPById("EXAMPLE_ID");
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
/* Without client */
Invoice invoice = Invoice.create(params);
/* With client */
Invoice invoice = xenditClient.invoice.create(params);
```

#### Get an invoice by ID

```java
/* Without client */
Invoice invoice = Invoice.getById("EXAMPLE_ID");
/* With client */
Invoice invoice = xenditClient.invoice.getById("EXAMPLE_ID");
```

#### Get all invoices

```java
Map<String, Object> params = new HashMap<>();
params.put("limit", 3);
params.put("statuses", "[\"SETTLED\",\"EXPIRED\"]");
/* Without client */
Invoice[] invoices = Invoice.getAll(params);
/* With client */
Invoice[] invoices = xenditClient.invoice.getAll(params);

```

#### Expire an invoice

```java
/* Without client */
Invoice invoice = Invoice.expire("EXAMPLE_ID");
/* With client */
Invoice invoice = xenditClient.invoice.expire("EXAMPLE_ID");
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

/* Optional for xenPlatform */
params.put("for-user-id", "<Sub Account User ID>");



/* Without client */
FixedVirtualAccount virtualAccount = FixedVirtualAccount.createOpen(params);
/* With client */
FixedVirtualAccount virtualAccount = xenditClient.fixedVirtualAccount.createOpen(params);

```

#### Update a fixed virtual account by ID

```java
Map<String, Object> params = new HashMap<>();
params.put("is_single_use", true);

/* Optional for xenPlatform */
params.put("for-user-id", "<Sub Account User ID>");

/* Without client */
FixedVirtualAccount fixedVirtualAccount = FixedVirtualAccount.update("EXAMPLE_ID", params);
/* With client */
FixedVirtualAccount fixedVirtualAccount = xenditClient.fixedVirtualAccount.update("EXAMPLE_ID", params);
```

#### Get banks with available virtual account service

```java
/* Without client */
AvailableBank[] availableBanks = FixedVirtualAccount.getAvailableBanks();
/* With client */
AvailableBank[] availableBanks = xenditClient.fixedVirtualAccount.getAvailableBanks();
```

#### Get a fixed virtual account by ID

```java
/* Without client */
FixedVirtualAccount fpa = FixedVirtualAccount.getFixedVA("EXAMPLE_ID");
/* With client */
FixedVirtualAccount fpa = xenditClient.fixedVirtualAccount.getFixedVA("EXAMPLE_ID");
```

#### Get a fixed virtual account payment by payment ID

```java
/* Without client */
FixedVirtualAccountPayment payment = FixedVirtualAccount.getPayment("EXAMPLE_PAYMENT_ID");
/* With client */
FixedVirtualAccountPayment payment = xenditClient.fixedVirtualAccount.getPayment("EXAMPLE_PAYMENT_ID");
```

[Back to top](#table-of-contents)

### Retail Outlet Services ID

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

```
params.put("external_id", "test");
params.put("retail_outlet_name", "ALFAMART");
params.put("name", "Rika Sutanto");
params.put("expected_amount", 10000);
/* Without client */
FixedPaymentCode fpc = RetailOutlet.createFixedPaymentCode(params);
/* With client */
FixedPaymentCode fpc = xenditClient.retailOutlet.createFixedPaymentCode(params);
```

#### Get fixed payment code

```java
/* Without client */
FixedPaymentCode fpc = RetailOutlet.getFixedPaymentCode("EXAMPLE_ID");
/* With client */
FixedPaymentCode fpc = xenditClient.retailOutlet.getFixedPaymentCode("EXAMPLE_ID");
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

/* Without client */
FixedPaymentCode fpc = RetailOutlet.updateFixedPaymentCode("EXAMPLE_ID", params);
/* With client */
FixedPaymentCode fpc = xenditClient.retailOutlet.updateFixedPaymentCode("EXAMPLE_ID", params);
```

[Back to top](#table-of-contents)
### Retail Outlet Services PH

#### Create payment code

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
RegionalRetailOutlet.createPaymentCode(
    String referenceId,
    RegionalRetailOutletPaymentCode.ChannelCode channelCode,
    String customerName,
    Number amount,
    RegionalRetailOutletPaymentCode.Currency currency,
    RegionalRetailOutletPaymentCode.Market market
);
</pre>
</td>
<td>
<pre>
RegionalRetailOutlet.createPaymentCode(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
params.put("reference_id", "test");
params.put("channel_code", RegionalRetailOutletPaymentCode.ChannelCode.SEVENELEVENCLIQQ);
params.put("customer_name", "test-customer");
params.put("amount", 10);
params.put("currency",  RegionalRetailOutletPaymentCode.Currency.PHP);
params.put("market", RegionalRetailOutletPaymentCode.Market.PH);
/* Without client */
RegionalRetailOutletPaymentCode pc = RegionalRetailOutlet.createPaymentCode(params);
/* With client */
RegionalRetailOutletPaymentCode pc = xenditClient.retailOutlet.createPaymentCode(params);
```

#### Get payment code

```java
/* Without client */
RegionalRetailOutletPaymentCode pc = RegionalRetailOutlet.getPaymentCode("EXAMPLE_ID");
/* With client */
RegionalRetailOutletPaymentCode pc = xenditClient.retailOutlet.getPaymentCode("EXAMPLE_ID");
```

#### Update payment code

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
RegionalRetailOutlet.updatePaymentCode(
    String id,
    String customerName,
    Number amount,
    RegionalRetailOutletPaymentCode.Currency currency,
    String expiresAt,
    String description
);
</pre>
</td>
<td>
<pre>
RegionalRetailOutlet.updatePaymentCode(
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

/* Without client */
RegionalRetailOutletPaymentCode pc = RegionalRetailOutlet.updatePaymentCode("EXAMPLE_ID", params);
/* With client */
RegionalRetailOutletPaymentCode pc = xenditClient.retailOutlet.updatePaymentCode("EXAMPLE_ID", params);
```

#### Get payments by payment code ID

```java
/* Without client */
RegionalRetailOutletPaymentCode pc = RegionalRetailOutlet.getPaymentsByPaymentCodeId("EXAMPLE_ID");
/* With client */
RegionalRetailOutletPaymentCode pc = xenditClient.retailOutlet.getPaymentsByPaymentCodeId("EXAMPLE_ID");
```

[Back to top](#table-of-contents)

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
params.put("currency", "IDR"); //Optional param

/* Without client */
RecurringPayment recurringPayment = RecurringPayment.create(params);
/* With client */
RecurringPayment recurringPayment = xenditClient.recurringPayment.create(params);
```

#### Get a recurring payment

```java
/* Without client */
RecurringPayment recurringPayment = RecurringPayment.get("5e2dd160f8a4d24146f5974c");
/* With client */
RecurringPayment recurringPayment = xenditClient.recurringPayment.get("5e2dd160f8a4d24146f5974c");
```

#### Edit a recurring payment

```java
Map<String, Object> params = new HashMap<>();
params.put("amount", 987654);
params.put("interval", "WEEK");

/* Without client */
RecurringPayment recurringPayment = RecurringPayment.edit("5e2dd55ef8a4d24146f59775", params);
/* With client */
RecurringPayment recurringPayment = xenditClient.recurringPayment.edit("5e2dd55ef8a4d24146f59775", params);
```

#### Stop a recurring payment

```java
/* Without client */
RecurringPayment recurringPayment = RecurringPayment.stop("5e2dd160f8a4d24146f5974c");
/* With client */
RecurringPayment recurringPayment = xenditClient.recurringPayment.stop("5e2dd160f8a4d24146f5974c");
```

#### Pause a recurring payment

```java
/* Without client */
RecurringPayment recurringPayment = RecurringPayment.pause("5e2dd55ef8a4d24146f59775");
/* With client */
RecurringPayment recurringPayment = xenditClient.recurringPayment.pause("5e2dd55ef8a4d24146f59775");
```

#### Resume a recurring payment

```java
/* Without client */
RecurringPayment recurringPayment = RecurringPayment.resume("5e2dd55ef8a4d24146f59775");
/* With client */
RecurringPayment recurringPayment = xenditClient.recurringPayment.resume("5e2dd55ef8a4d24146f59775");
```

#### List recurring payments by ID

```java
/* Without client */
Invoice[] invoices = RecurringPayment.getPaymentsById("5e2dd55ef8a4d24146f59775");
/* With client */
Invoice[] invoices = xenditClient.recurringPayment.getPaymentsById("5e2dd55ef8a4d24146f59775");
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
/* Without client */
Balance balance = Balance.get("CASH");
/* With client */
Balance balance = xenditClient.balance.get("CASH");
```

[Back to top](#table-of-contents)

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

/* Without client */
Payout payout = Payout.createPayout(params);
/* Without client */
Payout payout = xenditClient.payout.createPayout(params);
```

#### Get a payout by ID

```java
/* Without client */
Payout payout = Payout.getPayout("EXAMPLE_ID");
/* With client */
Payout payout = xenditClient.payout.getPayout("EXAMPLE_ID");
```

#### Void a payout

```java
/* Without client */
Payout payout = Payout.voidPayout("EXAMPLE_ID");
/* With client */
Payout payout = xenditClient.payout.voidPayout("EXAMPLE_ID");
```

[Back to top](#table-of-contents)

### E-Wallet Services

#### Create an e-wallet charge

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
EWalletCharge.createEWalletCharge(
    String referenceId,
    String currency,
    Number amount,
    String checkoutMethod,
    String channelCode,
    Map&lt;String, String&gt; channelProperties,
    String customerId,
    EWalletBasketItem[] basket,
    Map&lt;String, Object&gt; metadata
);
</pre>
</td>
<td>
<pre>
EWalletCharge.createEWalletCharge(Map&lt;String, Object&gt; params);
</pre>
</td>
</tr>
</table>

```java
Map<String, String> channelProperties = new HashMap<>();
channelProperties.put("success_redirect_url", "https://yourwebsite.com/order/123");

Map<String, Object> params = new HashMap<>();
params.put("reference_id", "test-reference-id");
params.put("currency", "IDR");
params.put("amount", 50000);
params.put("checkout_method", "ONE_TIME_PAYMENT");
params.put("channel_code", "ID_SHOPEEPAY");
params.put("channel_properties", channelProperties);

/* Without client */
EWalletCharge charge = EWalletCharge.createEWalletCharge(params);
/* With client */
EWalletCharge charge = xenditClient.eWallet.createEWalletCharge(params);
```

#### Get an e-wallet charge status

```java
/* Without client */
EWalletCharge charge = EWalletCharge.getEWalletChargeStatus("ewc_c8630205-3e7a-4511-8250-26a084480c4c");
/* With client */
EWalletCharge charge = xenditClient.eWallet.getEWalletChargeStatus("ewc_c8630205-3e7a-4511-8250-26a084480c4c");
```

[Back to top](#table-of-contents)

### Credit Card Services

#### Create an authorization

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
CreditCardCharge.createAuthorization(
    String tokenId,
    String externalId,
    Number amount,
    String authenticationId,
    String cardCVN,
    Boolean capture
);
</pre>
</td>
<td>
<pre>
CreditCardCharge.createAuthorization(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
/* Without client */
CreditCardCharge creditCardCharge = CreditCard.createAuthorization("...", "test_id", 75000, "...", "123", false);
/* With client */
CreditCardCharge creditCardCharge = xenditClient.creditCard.createAuthorization("...", "test_id", 75000, "...", "123", false);

```

#### Create a charge

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
CreditCardCharge.createCharge(
    String tokenId,
    String externalId,
    Number amount,
    String authenticationId,
    String cardCVN,
    String descriptor
);
</pre>
</td>
<td>
<pre>
CreditCardCharge.createCharge(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
/* Without client */
CreditCardCharge creditCardCharge = CreditCard.createCharge("...", "test_id", 75000, "...", "123", "lorem ipsum");
/* With client */
CreditCardCharge creditCardCharge = xenditClient.creditCard.createCharge("...", "test_id", 75000, "...", "123", "lorem ipsum");
```

#### Reverse an authorization

```java

CreditCard.reverseAuthorization(String chargeId, String externalId);

/* Without client */
CreditCardReverseAuth creditCardReverseAuth = CreditCard.reverseAuthorization("1234567", "external_id");
/* With client */
CreditCardReverseAuth creditCardReverseAuth = xenditClient.creditCard.reverseAuthorization("1234567", "external_id");
```

#### Capture a charge

```java

CreditCard.captureCharge(String chargeId, Number amount);
/* Without client */
CreditCardCharge creditCardCharge = CreditCard.captureCharge("12345678", 55000);
/* With client */
CreditCardCharge creditCardCharge = xenditClient.creditCard.captureCharge("12345678", 55000);
```

#### Get a charge by ID

```java
/* Without client */
CreditCardCharge creditCardCharge = CreditCard.getCharge("1234567");
/* With client */
CreditCardCharge creditCardCharge = xenditClient.creditCard.getCharge("1234567");
```

#### Create a refund

```java

CreditCard.createRefund(String id, Number amount, String externalId);
/* Without client */
CreditCardRefund creditCardRefund = CreditCard.createRefund("1234567", 50000, "external_id");
/* With client */
CreditCardRefund creditCardRefund = xenditClient.creditCard.createRefund("1234567", 50000, "external_id");
```

[Back to top](#table-of-contents)

### Batch Disbursement Services

#### Batch disbursement item

```java
BatchDisbursementItem item =
    BatchDisbursementItem.builder()
        .amount(10000)
        .bankCode("ABC")
        .bankAccountName("Lorem Ipsum")
        .bankAccountNumber("1234567890")
        .description("Lorem ipsum dolor sit amet")
        .externalId("test_id")
        .emailTo(["email1", "email2"])
        .emailCC(["email1", "email2"])
        .emailBcc(["email1", "email2"])
        .build();
```

#### Create a batch disbursement

```java
/* Without client */
BatchDisbursement.create(
    String reference,
    BatchDisbursementItem[] disbursements
);
/* With client */
xenditClient.batchDisbursement.create(
    String reference,
    BatchDisbursementItem[] disbursements
);
```

#### Get banks with available disbursement service

```java
/* Without client */
AvailableBank[] banks = BatchDisbursement.getAvailableBanks();
/* With client */
AvailableBank[] banks = xenditClient.batchDisbursement.getAvailableBanks();
```

[Back to top](#table-of-contents)

### Cardless Credit Services

#### Cardless credit item

```java
CardlessCreditItem item =
    CardlessCreditItem.builder()
        .id("123")
        .name("Phone Case")
        .price(200000)
        .type("Smartphone")
        .url("https://www.example.org")
        .quantity(1)
        .build();
```

#### Cardless credit customer details

```java
CardlessCreditCustomer customer =
    CardlessCreditCustomer.builder()
        .firstName("Lorem")
        .lastName("Ipsum")
        .email("email@example.com")
        .phone("08129748247684")
        .build();
```

#### Cardless credit shipping address

```java
CardlessCreditShippingAddress address =
    CardlessCreditShippingAddress.builder()
        .firstName("Lorem")
        .lastName("Ipsum")
        .address("Jalan teknologi")
        .city("Jakarta")
        .postalCode("12345")
        .countryCode("IDN")
        .phone("08129748247684")
        .build();
```

#### Create a cardless credit payment

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
CardlessCredit.create(
    String cardlessCreditType,
    String externalId,
    Number amount,
    String paymentType,
    CardlessCreditItem[] items,
    CardlessCreditCustomer customerDetails,
    CardlessCreditShippingAddress shippingAddress,
    String redirectUrl,
    String callbackUrl
);
</pre>
</td>
<td>
<pre>
CardlessCredit.create(
    Map&lt;String, Object&gt; params
);
</pre>
</td>
</tr>
</table>

```java
/* Without client */
CardlessCredit cardlessCredit = CardlessCredit.create(
    "KREDIVO",
    "external_id",
    200000,
    CardlessCredit.PaymentType.THREE_MONTHS.getVal(),
    items,
    customer,
    address,
    "www.example.com",
    "www.example.com"
);
/* With client */
CardlessCredit cardlessCredit = xenditClient.cardlessCredit.create(
    "KREDIVO",
    "external_id",
    200000,
    CardlessCredit.PaymentType.THREE_MONTHS.getVal(),
    items,
    customer,
    address,
    "www.example.com",
    "www.example.com"
);
```

### QR Code

#### Create QR Code

```java
/* Without client */
QRCode qrCode = QRCode.create(
    "reference_id",
    QRCode.QRCodeType.DYNAMIC,
    "IDR",
    10000
);
/* With client */
QRCode qrCode = xenditClient.qrCode.create(
    "reference_id",
    QRCode.QRCodeType.DYNAMIC,
    "IDR",
    10000
);
```

#### Get QR Code

```java
/* Without client */
QRCode qrCode = QRCode.getQRCodeByQRId("qr_004a0427-b194-49be-9439-657ef77ee4f3");
/* With client */
QRCode qrCode = xenditClient.qrCode.getQRCodeByQRId("qr_004a0427-b194-49be-9439-657ef77ee4f3");
```

### Customer

#### Create Customer

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
Customer.createCustomer(
    String referenceId,
    String mobileNumber,
    String email,
    String givenNames,
    String middleName,
    String surname,
    String description,
    String phoneNumber,
    String nationality,
    CustomerAddress[] addresses,
    String dateOfBirth,
    Map&lt;String, Object&gt; metadata
);
</pre>
</td>
<td>
<pre>
Customer.createCustomer(Map&lt;String, Object&gt; params);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> metadata = new HashMap<>();
metadata.put("halo", "hello");
metadata.put("tes", "123");

Map<String, Object> params = new HashMap<>();
params.put("reference_id", "test-reference-id");
params.put("email", "tes@tes.com");
params.put("given_names", "Given Names");
params.put("nationality", "ID");
params.put("date_of_birth", "1995-12-30");
params.put("metadata", metadata);

/* Without client */
Customer customer = Customer.createCustomer(params);
/* With client */
Customer customer = xenditClient.customer.createCustomer(params);
```

#### Get Customer by Reference ID

```java
/* Without client */
Customer[] customers = Customer.getCustomerByReferenceId("test-reference-id");
/* With client */
Customer[] customers = xenditClient.customer.getCustomerByReferenceId("test-reference-id");
```

### Direct Debit

#### Initialize linked account tokenization

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
InitializedLinkedAccount.initializeLinkedAccountTokenization(
    String customerId,
    LinkedAccountEnum.ChannelCode channelCode,
    Map&lt;String, Object&gt; properties,
    Map&lt;String, Object&gt; metadata
);
</pre>
</td>
<td>
<pre>
InitializedLinkedAccount.initializeLinkedAccountTokenization(Map&lt;String, Object&gt; params);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> properties = new HashMap<>();
properties.put("account_mobile_number", "+62818555988");
properties.put("card_last_four", "8888");
properties.put("card_expiry", "06/24");
properties.put("account_email", "test.email@xendit.co");

Map<String, Object> metadata = new HashMap<>();
metadata.put("tes", "123");

String customerId = "791ac956-397a-400f-9fda-4958894e61b5";
ChannelCode channelCode = ChannelCode.DC_BRI;

/* Without client */
InitializedLinkedAccount linkedAccount = InitializedLinkedAccount.initializeLinkedAccountTokenization(
    customerId,
    channelCode,
    properties,
    metadata
);
/* With client */
InitializedLinkedAccount linkedAccount = xenditClient.directDebitPayment.initializeLinkedAccountTokenization(
    customerId,
    channelCode,
    properties,
    metadata
);
```

#### Validate OTP for Linked Account Token

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
ValidatedLinkedAccount.validateOTP(
    String tokenId,
    String otpCode
);
</pre>
</td>
<td>
<pre>
ValidatedLinkedAccount.validateOTP(String tokenId, Map&lt;String, Object&gt; params);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
params.put("otp_code", "333000");

String tokenId = "lat-ba3c5645-f134-432a-b4f4-f8972685aa03";

/* Without client */
ValidatedLinkedAccount linkedAccount = ValidatedLinkedAccount.validateOTP(tokenId, params);
/* With client */
ValidatedLinkedAccount linkedAccount = xenditClient.directDebitPayment.validateOTP(tokenId, params);
```

#### Retrieve accessible accounts by linked account token

```java
/* Without client */
AccessibleLinkedAccount[] linkedAccounts = AccessibleLinkedAccount.retrieveAccessibleLinkedAccounts("lat-960e709c-bdd6-4b4a-a361-243186379c45");
/* With client */
AccessibleLinkedAccount[] linkedAccounts = xenditClient.directDebitPayment.retrieveAccessibleLinkedAccounts("lat-960e709c-bdd6-4b4a-a361-243186379c45");
System.out.println(Arrays.toString(linkedAccounts));
```

#### Unbind linked account token

```java
/* Without client */
UnbindedLinkedAccount linkedAccount = UnbindedLinkedAccount.unbindLinkedAccountToken("lat-a08fba1b-100c-445b-b788-aaeaf8215e8f");
/* With client */
UnbindedLinkedAccount linkedAccount = xenditClient.directDebitPayment.unbindLinkedAccountToken("lat-a08fba1b-100c-445b-b788-aaeaf8215e8f");
```

#### Create payment method

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
PaymentMethod.createPaymentMethod(
    String customerId,
    LinkedAccountEnum.AccountType type,
    Map&lt;String, Object&gt; properties,
    Map&lt;String, Object&gt; metadata
);
</pre>
</td>
<td>
<pre>
PaymentMethod.createPaymentMethod(Map&lt;String, Object&gt; params);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> properties = new HashMap<>();
properties.put("id", "la-052d3e2d-bc4d-4c98-8072-8d232a552299");
Map<String, Object> metadata = new HashMap<>();
metadata.put("halo", "hello");
metadata.put("tes", "123");
Map<String, Object> params = new HashMap<>();
params.put("customer_id", "4b7b6050-0830-440a-903b-37d527dbbaa9");
params.put("type", "DEBIT_CARD");
params.put("properties", properties);
params.put("metadata", metadata);
/* Without client */
PaymentMethod paymentMethod = PaymentMethod.createPaymentMethod(params);
/* With client */
PaymentMethod paymentMethod = xenditClient.directDebitPayment.createPaymentMethod(params);
```

#### Get payment methods by customer ID

```java
/* Without client */
PaymentMethod[] paymentMethods = PaymentMethod.getPaymentMethodsByCustomerId("4b7b6050-0830-440a-903b-37d527dbbaa9");
/* With client */
PaymentMethod[] paymentMethods = xenditClient.directDebitPayment.getPaymentMethodsByCustomerId("4b7b6050-0830-440a-903b-37d527dbbaa9");
System.out.println(Arrays.toString(paymentMethods));
```

#### Create recurring payment

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
RecurringPayment.create(Map&lt;String, Object&gt; params);
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
params.put("currency", "IDR");

/* Without client */
RecurringPayment recurringPayment = RecurringPayment.create(params);
/* With client */
RecurringPayment recurringPayment = xenditClient.directDebitPayment.create(params);
```

#### Create direct debit payment

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
DirectDebitPayment.createDirectDebitPayment(
    String referenceId,
    String paymentMethodId,
    String currency,
    Number amount,
    String callbackUrl,
    Boolean enableOtp,
    String description,
    DirectDebitBasketItem[] basket,
    DirectDebitDevice device,
    String successRedirectUrl,
    String failureRedirectUrl,
    Map&lt;String, Object&gt; metadata,
    String idempotencyKey
);
</pre>
</td>
<td>
<pre>
DirectDebitPayment.createDirectDebitPayment(Map&lt;String, Object&gt; params, String idempotencyKey);
</pre>
</td>
</tr>
</table>

```java
DirectDebitBasketItem basketItem =  DirectDebitBasketItem.builder()
        .referenceId("basket-product-ref-id")
        .name("product-name")
        .category("mechanics")
        .market("ID")
        .price(50000)
        .quantity(5)
        .type("product type")
        .subCategory("product sub category")
        .description("product description")
        .url("https://product.url")
        .build();
DirectDebitBasketItem[] basketItemArray = new DirectDebitBasketItem[]{basketItem};

DirectDebitDevice device = DirectDebitDevice.builder()
        .id("device-id")
        .ipAddress("0.0.0.0")
        .userAgent("user-agent")
        .adId("ad-id")
        .imei("123a456b789c")
        .build();

Map<String, Object> metadata = new HashMap<>();
metadata.put("halo", "hello");
metadata.put("tes", "123");

Map<String, Object> params = new HashMap<>();
params.put("reference_id", "test-direct-debit-ref-4");
params.put("payment_method_id", "pm-ebb1c863-c7b5-4f20-b116-b3071b1d3aef");
params.put("currency", "IDR");
params.put("amount", 15000);
params.put("callback_url", "http://webhook.site/");
params.put("enable_otp", true);
params.put("description", "test description");
params.put("basket", basketItemArray);
params.put("success_redirect_url", "https://success-redirect.url");
params.put("failure_redirect_url", "https://failure-redirect.url");
params.put("device", device);
params.put("metadata", metadata);

String idempotencyKey = "idempotency-key-4";

/* Without client */
DirectDebitPayment directDebitPayment = DirectDebitPayment.createDirectDebitPayment(params, idempotencyKey);
/* With client */
DirectDebitPayment directDebitPayment = xenditClient.directDebitPayment.createDirectDebitPayment(params, idempotencyKey);
```

#### Validate OTP for direct debit payment

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
DirectDebitPayment.validateOTP(
    String directDebitPaymentId,
    String otpCode
);
</pre>
</td>
<td>
<pre>
DirectDebitPayment.validateOTP(String directDebitPaymentId, Map&lt;String, Object&gt; params);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> params = new HashMap<>();
params.put("otp_code", "222000");

String directDebitPaymentId = "ddpy-b150da90-2121-44a6-a836-5eebf0d7ab55";

/* Without client */
DirectDebitPayment directDebitPayment = DirectDebitPayment.validateOTP(directDebitPaymentId, params);
/* With client */
DirectDebitPayment directDebitPayment = xenditClient.directDebitPayment.validateOTP(directDebitPaymentId, params);
```

#### Get direct debit payment status by ID

```java
/* Without client */
DirectDebitPayment directDebitPayment = DirectDebitPayment.getDirectDebitPaymentStatusById("ddpy-7e61b0a7-92f9-4762-a994-c2936306f44c");
/* With client */
DirectDebitPayment directDebitPayment = xenditClient.directDebitPayment.getDirectDebitPaymentStatusById("ddpy-7e61b0a7-92f9-4762-a994-c2936306f44c");
```

#### Get direct debit payment status by reference ID

```java
/* Without client */
DirectDebitPayment[] directDebitPayments = DirectDebitPayment.getDirectDebitPaymentStatusByReferenceId("test-direct-debit-ref-4");
/* With client */
DirectDebitPayment[] directDebitPayments = xenditClient.directDebitPayment.getDirectDebitPaymentStatusByReferenceId("test-direct-debit-ref-4");
System.out.println(Arrays.toString(directDebitPayments));
```

[Back to top](#table-of-contents)

### Paylater Services

#### Initiate Paylater Plans

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
PaylaterPlans.initiatePaylaterPlans(
    String customerId,
    String channelCode,
    String currency,
    Number Amount,
    PaylaterOrderItem[] orderItems
);
</pre>
</td>
<td>
<pre>
PaylaterPlans.initiatePaylaterPlans(Map&lt;String, Object&gt; params);
</pre>
</td>
</tr>
</table>

```java
PaylaterOrderItem orderItems = PaylaterOrderItem.builder()
    .type("type")
    .referenceId("reference_id")
    .name("name")
    .netUnitAmount("net_unit_amount")
    .quantity(1)
    .url("https://www.google.com")
    .category("category")
    .subCategory("subCategory")
    .description("description")
    .build();
PaylaterOrderItem[] orderItemsArray = new PaylaterOrderItem[] { orderItem };

Map<String, Object> params = new HashMap<>();
params.put("customer_id", "test-customer-id");
params.put("channel_code", "ID_KREDIVO");
params.put("currency", "IDR");
params.put("amount", 50000);
params.put("order_items", orderItemsArray);

/* Without client */
PaylaterPlans initiatePlan = PaylaterPlans.initiatePaylaterPlans(params);
/* With client */
PaylaterPlans initiatePlan = xenditClient.paylater.initiatePaylaterPlans(params);
```

#### Create Paylater Charges

You can choose whether want to put the attributes as parameters or to put in inside a Map object.

<table>
<tr>
<td>
<pre>
PaylaterCharges.createPaylaterCharges(
    String planId,
    String referenceId,
    String checkoutMethod,
    String successRedirectUrl,
    String failureRedirectUrl,
    String paymentMethodId,
    Map&lt;String, Object&gt; metadata
);
</pre>
</td>
<td>
<pre>
PaylaterCharges.createPaylaterCharges(Map&lt;String, Object&gt; params);
</pre>
</td>
</tr>
</table>

```java
Map<String, Object> metadata = new HashMap<>();
metadata.put("halo", "hello");
metadata.put("tes", "123");

Map<String, Object> params = new HashMap<>();
params.put("plan_id", "test-plan-id");
params.put("reference_id", "test-reference-id");
params.put("checkout_method", "ONE_TIME_PAYMENT");
params.put("success_redirect_url", "https://success-redirect.url");
params.put("failure_redirect_url", "https://failure-redirect.url");
params.put("payment_method_id", null);
params.put("metadata", metadata);

/* Without client */
PaylaterCharge charge = PaylaterCharge.createPaylaterCharges(params);
/* With client */
PaylaterCharge charge = xenditClient.paylater.createPaylaterCharges(params);
```

#### Get Paylater Charge by Charge ID

```java
/* Without client */
PaylaterCharge paylaterCharge = PaylaterCharge.getPaylaterChargeStatus("charge-id");
/* With client */
PaylaterCharge paylaterCharge = xenditclient.paylater.getPaylaterChargeStatus("charge-id");
```

#### Refund Paylater Charge

```java
PaylaterRefund.createPaylaterRefund(String chargeId, Number amount, PaylaterEnum.RefundReasons);
/* Without client */
PaylaterRefund paylaterRefund = PaylaterRefund.createPaylaterRefund("charge-id", 1000, "OTHERS");
/* With client */
PaylaterRefund paylaterRefund = xenditClient.paylater.createPaylaterRefund("charge-id", 1000, "OTHERS");
```

#### Get Paylater Refund by Refund ID

```java
/* Without client */
PaylaterRefund paylaterRefund = PaylaterRefund.getPaylaterRefundStatus("charge-id", "refund-id");
/* With client */
PaylaterRefund paylaterRefund = xenditclient.paylater.getPaylaterRefundStatus("charge-id", "refund-id");
```

### How to get Request Id

Each API request has an asssociated request identifier. You can find this value in the response headers, under Request-ID. You can use Request-ID to find logs in [API Logs](https://dashboard.xendit.co/api-logs) in Dashboard. Learn more about Searching API Logs using Request-ID in [API Logs Docs](https://docs.xendit.co/api-integration/api-logs).

The following example will show how to obtain Request-ID when creating QRCode

```java
/* Without client */
QRCode qrCode = QRCode.createQRCode("12", QRCode.QRCodeType.DYNAMIC, "IDR", 10000);
/* Xendit.getResponseHeaders() will contain all response headers after your request is completed, hence you can obtain Request-Id from header by doing the following:*/
System.out.println(Xendit.getResponseHeaders().get("Request-Id"));

/* With client */
QRCode qrCode = xenditClient.qrCode.createQRCode("external_id", QRCode.QRCodeType.DYNAMIC, "IDR", 10000);
/* Xendit.getResponseHeaders() will contain all response headers after your request is completed, hence you can obtain Request-Id from header by doing the following:*/
System.out.println(Xendit.getResponseHeaders().get("Request-Id"));
```
Full Example can be found [here](https://github.com/xendit/xendit-java/blob/9bd69bd6f4061307a5dee30287a1f7712d060527/xendit-java-library-example/src/main/java/ExampleWithClient/ExampleCreateQRCode.java)


## Contributing
You can go to the [contributing guidelines](https://github.com/xendit/xendit-java/blob/master/CONTRIBUTING.md) to learn on how to contribute this project.

### Lint
Run `./gradlew spotlessApply` to apply linter.

### Tests

Make sure the the code passes all tests.

```
./gradlew test
```

### Precommit

Before making any commits, please install pre-commit. To install pre-commit, follow the [installation steps](https://pre-commit.com/#install).

For any requests, bugs, or comments, please [open an issue](https://github.com/xendit/xendit-java-library/issues) or [submit a pull request](https://github.com/xendit/xendit-java-library/pulls).
