# Xendit Java Library

## Table of Contents

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [API Documentation](#api-documentation)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Disbursement Services](#disbursement-services)
    - [Create a disbursement](#create-a-disbursement)
- [Examples](#examples)
- [Publish](#publish)
- [Contributing](#contributing)

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
  <version>1.3.0</version>
  <type>pom</type>
</dependency>
```

Gradle:
```
compile 'com.xendit:xendit-java-lib:1.3.0'
```

More information: https://bintray.com/xendit/android/xendit-java-lib

## Usage
You need to use secret API key in order to use functionality in this library. The key can be obtained from your [Xendit's Dasboard](https://dashboard.xendit.co/settings/developers#api-keys).

```java
import com.xendit.Xendit;

public class Example {
    public static void main(String[] args) {
        Xendit.apiKey = "PUT YOUR API KEY HERE";
    }
}
```

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

## Examples
Example.java
```
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.model.FixedVirtualAccount;

import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            AvailableBank[] banks = FixedVirtualAccount.getAvailableBank();
            System.out.println(Arrays.toString(banks));
        } catch (XenditException e) {
            e.printStackTrace();
        }
    }
}
```

See other examples [here](https://github.com/xendit/xendit-java-library/tree/master/xendit-java-library-example/src/main/java).

## Contributing
TBD
