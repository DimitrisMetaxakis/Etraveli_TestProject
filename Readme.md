# Requirements
* Java 11
* Maven 3
* Allure 2


## Java Installation
Check your system to see if you have Java version 11.0.2 installed.
Command:
```bash
$ java --version
```
Ensure your JAVA_HOME environment to the location of the installed JDK.
## Maven Installation
1. Download Maven
2. Unzip the distribution archive to the directory you wish to install Maven.
3. Add Maven to the PATH
4. Verify Maven was correctly installed
```bash
$ mvn --version
```
## Allure Installation
1. Download Allure
2. Unzip the distribution archive to the directory you wish to install Allure.
3. Add Allure to the PATH
4. Verify Allure was correctly installed
```bash
$ allure --version
```

## How to build project
```bash
$ mvn build
```

## How to select browser
Choose browser from the TestNG file

## How to run tests
```bash
$ mvn clean test
```
## How to see test report
```bash
$ allure serve
```
# Built with
- Selenium : Browser automation framework
- Maven : Build automation & dependency management
- TestNG : Testing framework, control flow of tests
- Allure : Report tool
- Webdrivers : Webdriver Manager
