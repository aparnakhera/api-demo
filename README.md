# api-demo

`api-demo` is an API automation testing assignment.

## Requirements
* Windows
* Java 8
* Eclipse IDE

## Maven Depencies
* JSON (version 20180813)
* TestNG (version 6.14.3)
* Log4j (version 1.2.17)

## Source Code Explanation

* ApiTestData.java
```
This class stores the API to be tested and the acceptance criteria for it.
```

* ConnectionUtil.java
```
This class is used to connect or disconnect to the API url.
```

* ApiTest.java
```
This class is used to test the acceptance criteria for API using TestNG.
```

## Execution

For execution, please run the `ApiTest.java` file as `TestNGTest`.
Kindly install `TestNG for Eclipse` software on Eclipse Marketplace for executing the test file.

## Test Report
Test Report is generated using TestNG and can be viewed in files `emailable-report.html` and `index.html` under `test-output` folder.


## Authors
Aparna Khera
