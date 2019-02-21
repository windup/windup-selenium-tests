# windup-selenium-tests
*Selenium tests for checking the operation of windup-web ui*

Thanks to [@elisedd](https://github.com/elisedd) for the original version of these tests

This PR provides code which runs tests against the Web-UI which correspond to the test scripts [here in google docs]( https://docs.google.com/spreadsheets/d/11LC8PYUxAmoMngBbFYJebZys69qNNvNxJx8iCadpZLQ/).

The main difference here to previous versions is that the tests run in headless mode using the Chrome browser Selenium driver.

The tests in classes SeleniumXXTest can be run as JUnit tests from within an IDE though the intention is to run them as a suite on the CI server eventually.

## Pre-requisites

- install Chrome in [standard directory](https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver#requirements)
- install locally [ChromeDriver](https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver)
- run locally RHAMT Web UI
   - download the RHAMT Web UI release `rhamt-web-distribution-<version>-with-authentication.zip` you want to test from https://oss.sonatype.org/content/repositories/public/org/jboss/windup/web/rhamt-web-distribution/
   - unzip it
   - run `$ ./run_rhamt.sh` script

## Run tests
### Run a single test
From `windup-selenium-tests/` folder execute the command
```bash
$ mvn -Dtest=SeleniumXXTest clean test
```

### Run the test suite
From `windup-selenium-tests/` folder execute the command
```bash
$ mvn -Dtest=SeleniumSuiteTest clean test
```
### Tests parameters
The tests default behaviour is to test in headless mode a RHAMT instance running at `localhost:8080` without authentication.

The following options provide you a way to change the default settings:

* `-DwithLogin` = enables the tests to execute the login to RHAMT
* `-DbaseUrl=http://rhamt.example.com/` = enables the tests to work with a remote RHAMT instance 
* `-Dheadless=false` = disable the browser headless mode so that the user can check what happens during tests' execution
