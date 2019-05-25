<a href="https://github.com/SamratJD/S.W.A.T"><img src="https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/Framework_Logo.png" title="S.W.A.T." alt="S.W.A.T." width="300"></a>

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
  
* Test cases can be executed in 3 different browsers namely - **Internet Explorer**, **Mozilla Firefox** and **Google Chrome**.

* **Headless browser testing** is also enabled in this framework. It uses PhantomJS for headless testing.

* **Selenium Grid** - Remote execution can be also be done using Grid capabilities.

* **Cloud testing using SauceLabs** - this feature is also enabled, so that you can execute test cases remotely in the SauceLabs server.

* Fresh logs are generated in *HTML* format after each test run. It uses standard log4j for generating the logs.
