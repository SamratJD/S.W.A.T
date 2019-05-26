<a href="https://github.com/SamratJD/S.W.A.T"><img src="https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/Framework_Logo.png" title="S.W.A.T." alt="S.W.A.T." width="200"></a>

# Sustainable Web Automation Tester
### A hybrid selenium-based web automation framework

**S.W.A.T.** is an open source maven-based framework for UI automation of web applications. You can write test cases based on a predefined keyword library provided in the framework itself. These keywords comprises of mostly all the probable browser interactions.

Test execution is controlled by the **'TestRunner'** file where you can provide which **Test Case** will be executed by turning on/off the run mode. Test data is controlled by the **'TestData'** file where you can provide which **Test Data Set** will be executed.

Test execution can be triggered from a ***.bat*** file or running the *TestNG* runner file. Report generation uses the *Extent Reports*, which displays all the test run information as well as test step level screenshots during execution.

# Index

# Features
* Java knowledge is not required, anyone can write the test scripts.
* It is a selenium-based maven project, using TestNG to run the test cases.
* Testers can refer to the predefined keyword library to write the test cases.
* The keyword library consists of 5 separate libraries for handling different aspects of web automation:
  * *Functional library* - for handling the web-based actions.
  * *Database library* - for fetching data from the database(MySQL/Oracle).
  * *PDF library* - for handling PDF related validations.
  * *Sikuli library* - for handling image-based validations in a web page.
  * *Email library* - for sending email containing logs and test reports of the current execution.
  
* Test case run modes are set in the **TestRunner** excel file in the path: *src/test/resources*.
* Test data run modes are set in the **TestData** excel file in the path: *src/test/resources*.
* Test cases can be executed in 3 different browsers namely - **Internet Explorer**, **Mozilla Firefox** and **Google Chrome**.
* **Headless browser testing** is also enabled in this framework. It uses PhantomJS for headless testing.
* **[Selenium Grid](https://github.com/SeleniumHQ/selenium/wiki/Grid2)** - Remote execution can be also be done using Grid capabilities.
* **Cloud testing using [SauceLabs](https://saucelabs.com/)** - this feature is also enabled, so that you can execute test cases remotely in the SauceLabs server.

* Fresh logs are generated in *HTML* format after each test run. It uses standard **[log4j](https://logging.apache.org/log4j/2.x/)** for generating the logs.
* Interactive and detailed execution reports are generated after each test run. It used the **[Extent Reports](http://extentreports.com/)** for generating results. 

## Test Case Examples
A sample test case using S.W.A.T. can be found [here](https://github.com/SamratJD/S.W.A.T/blob/master/src/test/java/testCases/BlazeDemoTest.java).

## References
* Need for hybrid automation frameworks - [ToolsQA](https://www.toolsqa.com/selenium-webdriver/selenium-automation-hybrid-framework/)
* Testing framework used in creating S.W.A.T. - [TestNG](https://testng.org/doc/)
* Build tool for managing the different APIs used - [Apache Maven](https://maven.apache.org/)
* Creating reports - [Extent Reports](http://extentreports.com/)

# Getting Started
S.W.A.T. requires Java 8, Maven, TestNG and either Eclipse or IntelliJ.

## Java
You can download and install the latest version of Java from [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html).
Then set the path to Java in Environment variables, details given [here](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html).
