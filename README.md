# Xendit Java Library
Java library to be used with Xendit Payment Gateway. Register [here](http://dashboard.xendit.co).

## Features
- Create Open Amount VA
- Create Closed Amount VA
- Get List of Available Banks

## Requirements
JDK 1.7 or later.

## Installation
Maven:
```
<dependency>
  <groupId>com.xendit</groupId>
  <artifactId>xendit-java-lib</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

Gradle:
```
compile 'com.xendit:xendit-java-lib:1.0.0'
```

More information: https://bintray.com/xendit/android/xendit-java-lib

## Usage
You need to use secret API key in order to use functionality in this library.

## Examples
Example.java
```
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.AvailableBank;
import com.xendit.model.VirtualAccount;

import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        Xendit.apiKey = "xnd_development_...";

        try {
            AvailableBank[] banks = VirtualAccount.getAvailableBank();
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