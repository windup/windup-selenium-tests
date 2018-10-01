# windup-selenium-tests
*Selenium tests for checking the operation of windup-web ui*

Thanks to @elisedd for the original version of these tests

This PR provides code which runs tests against the Web-UI which correspond to the test scripts [here in google docs](https://docs.google.com/spreadsheets/d/1BJGNnAlXPFARxo4Pk6FRiWVQXh-q_ZCQ32E9E_t8f0I/edit?ts=5b98e6c7#gid=814464998).

The main difference here to previous versions is that the tests run in headless mode using the Chrome browser Selenium driver.

The tests in classes SeleniumXXTest can be run as JUnit tests from within an IDE though the intention is to run them as a suite on the CI server eventually.

**Pre-requisites**

- install Chrome in [standard directory](https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver#requirements)
- install locally [ChromeDriver](https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver)
- run locally RHAMT Web UI
   - download the RHAMT Web UI release `rhamt-web-distribution-<version>-with-authentication.zip` you want to test from https://oss.sonatype.org/content/repositories/public/org/jboss/windup/web/rhamt-web-distribution/
   - unzip it
   - run `$ ./run_rhamt.sh` script

**Run a single test**
From `windup-selenium-tests/` folder execute the command
```bash
$ mvn -Dtest=SeleniumXXTest clean test
```
