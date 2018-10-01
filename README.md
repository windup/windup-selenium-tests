# windup-selenium-tests
Selenium tests for checking the operation of windup-web ui

Thanks to @elisedd for the initial version of the tests.

This PR provides code which runs tests against the Web-UI which correspond to the test scripts here in google docs.

The main difference here to previous versions is that the tests run in headless mode using the Chrome browser Selenium driver.

The tests in classes SeleniumXXTest can be run as JUnit tests from within an IDE though the intention is to run them as a suite on the CI server eventually.

Pre-requisites

install Chrome in standard directory
install locally ChromeDriver
run locally RHAMT Web UI
download the RHAMT Web UI release rhamt-web-distribution-<version>-with-authentication.zip you want to test from https://oss.sonatype.org/content/repositories/public/org/jboss/windup/web/rhamt-web-distribution/
unzip it
run $ ./run_rhamt.sh script
Run a single test
From windup-web/ui/ folder execute the command

$ mvn -Dtest=SeleniumXXTest clean test
