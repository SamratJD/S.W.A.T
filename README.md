<a href="https://github.com/SamratJD/S.W.A.T"><img src="https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/Framework_Logo.png" title="S.W.A.T." alt="S.W.A.T." width="200"></a>

# Sustainable Web Automation Tester
### A hybrid selenium-based web automation framework

**S.W.A.T.** is an open source maven-based framework for UI automation of web applications. You can write test cases based on a predefined keyword library provided in the framework itself. These keywords comprises of mostly all the probable browser interactions.

Test execution is controlled by the **'TestRunner'** file where you can provide which **Test Case** will be executed by turning on/off the run mode. Test data is controlled by the **'TestData'** file where you can provide which **Test Data Set** will be executed.

Test execution can be triggered from a ***.bat*** file or running the *TestNG* runner file. Report generation uses the *Extent Reports*, which displays all the test run information as well as test step level screenshots during execution.

# Index
| **SECTIONS**             | **SUB-SECTIONS**                    |
| ----------------     | ------------------------------- |
| [An Introduction](#sustainable-web-automation-tester)   | [Why S.W.A.T ?](#sustainable-web-automation-tester)|
| [Features](#features)      | [Test Case Examples](#test-case-examples) : [References](#references)|
| [Getting Started](#getting-started) | [Java](#java) : [Maven](#maven) : [IDE Support](#ide-support) : [TestNG](#testng) : [Framework Configuration](#framework-configuration) : [Folder Structure](#folder-structure) |
|[Predefined Libraries](#predefined-libraries) | [Functional Library](#functional-library) : [Database Library](#database-library) : [PDF Library](#pdf-library) : [Sikuli Library](#sikuli-library) : [Email Library](#email-library) : [Keywords List](#keywords-list)|
|[Configuring the Test Cases and Test Data](#configuring-the-test-cases-and-test-data) | [Configuring the TestRunner sheet](#configuring-the-testrunner-sheet) : [Configuring the TestData sheet](#configuring-the-testdata-sheet) |
| [Running the tests](#running-the-tests) | [Running the FrameworkRunner](#running-the-frameworkrunner) : [Running the Runner file](#running-the-runner-file) |
| [Test Reports](#test-reports)      | [Generating the Reports](#test-reports)|
| [Logs](#logs)| [Generating the Logs](#logs)|
|[Contributing](#contributing)| [How to contribute to S.W.A.T. ?](#contributing)|
|[FAQs](#faqs)| [Facing problems ?](#faqs)|
|[Support](#support)| [Where to contact ?](#support)|

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
* Interactive and detailed execution reports are generated after each test run. It used the **[Extent Reports](http://extentreports.com/)** for generating results. s

## Test Case Examples
A sample test case using S.W.A.T. can be found [here](https://github.com/SamratJD/S.W.A.T/blob/master/src/test/java/testCases/BlazeDemoTest.java).

<img src="https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/Framework%20Test%20case.png" title="S.W.A.T." alt="S.W.A.T." width="800" >

## References
* Need for hybrid automation frameworks - [ToolsQA](https://www.toolsqa.com/selenium-webdriver/selenium-automation-hybrid-framework/)
* Testing framework used in creating S.W.A.T. - [TestNG](https://testng.org/doc/)
* Build tool for managing the different APIs used - [Apache Maven](https://maven.apache.org/)
* Creating reports - [Extent Reports](http://extentreports.com/)

# Getting Started
S.W.A.T. requires Java 8, Maven, TestNG and either Eclipse or IntelliJ.

## Java
You can download and install the latest version of Java from [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html).

Then set the path to Java in Environment variables, for details refer to this [website](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html).

## Maven
You can download and install the latest version of Maven from [here](https://maven.apache.org/download.cgi)

Then set the path to Maven in Environment variables, for details refer to this [website](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)

## IDE Support
* Eclipse IDE is available for download from [here](https://www.eclipse.org/downloads/) website. No need for separate installation, it can be directly run from the eclipse executable file.
* IntelliJ IDEA can be download from [this](https://www.jetbrains.com/idea/download/) website. Once downloaded, install IntelliJ in your system.

## TestNG
* Eclipse IDE requires TestNG to be installed separately. Details can be found [here](https://testng.org/doc/eclipse.html).
* IntelliJ IDEA comes with pre-installed TestNG.

## Framework Configuration
Once all the above pre-requisites have been setup, you can [clone](https://github.com/SamratJD/S.W.A.T.git) or download the S.W.A.T. framework in your system and import it as a Maven project.

## Folder Structure
A test case is of the `.java` file extension.
As per the Maven convention, all the `*.java` source files are in the path `src/main/java`. The test cases are in the path `src/test/java`.
All the data files (e.g `*.xlsx`, `*.properties` and `*.xml` etc)are kept in the path `src/test/resources`.


```
S.W.A.T.
|
+---src/main/java
|	|
|	+---frameworkRunner
|	+---libraries
|	+---listeners
|	\---utilities
|
+---src/test/java
|	|
|	+---pageObjects
|	|	|
|	|	+---BlazeDemoRegisterPage.java
|	|	\---BlazeDemoLoginPage.java
|	|
|	+---sampleTests
|	+---testCases
|	|	|
|	|	+---BlazeDemoTest.java
|	|	
|	\---testCaseTemplate
|	
+---src/test/resources
|	|
|	+---features
|	+---runner
|	|	|
|	|	+---TestRunner.xlsx
|	|	
|	\---testData
|		|
|		+---TestData.xlsx
|
+---Logs
+---TestResults
+---pom.xml
\---Runner.bat
```

# Predefined Libraries
There are 5 libraries which have been provided with this framework for ease of use.

## Functional Library
This library consists of keywords which would help the user to write test cases for performing various actions in a web page.  This includes browser navigation, web element actions, alert handling, window handling, authentication pop-ups and screenshot capture methods etc.
## Database Library
This library consists of keywords to fetch data from the database when the *column name* and *SQL query* is provided. S.W.A.T. is configured to handle both MySQL and Oracle databases.
## PDF Library
This library consists of keywords for implementing PDF validations. It contains methods for comparision of 2 PDFs, returning the page count of a PDF file etc.
## Sikuli Library
This library consists of keywords for handling web actions based on image inputs provided by the user. It consists of methods such as click on object or verify if object is present in web page or not etc.
## Email Library
This library consists of a keyword for sending email reports with the last execution run containing the test case information along with the Test report which was generated in zipped format.

## Keywords List:
| Keywords                                                                                   | Parameters                                                     | Description                                                                    |
|--------------------------------------------------------------------------------------------|----------------------------------------------------------------|--------------------------------------------------------------------------------|
| minimizeAllWindows()                                                                       |                                                                | To minimize all the open windows                                               |
| openURL(String url)                                                                        | String url                                                     | To navigate to a URL                                                           |
| verifyPageTitle(String expectedTitle)                                                      | String expectedTitle                                           | To verify the page title                                                       |
| isSelected(By by)                                                                          | By by                                                          | To verify if the checkbox/radiobutton is selected or not                       |
| selectDropdownByValue(By by, String value)                                                 | By by, String value                                            | To select from a drop down by using value                                      |
| selectDropdownByIndex(By by, int index)                                                    | By by, int index                                               | To select from a drop down by using index                                      |
| selectDropdownByText(By by, String text)                                                   | By by, String text                                             | To select from a drop down by using text                                       |
| verifyText(By by, String expected)                                                         | By by, String expected                                         | To verify the text of a label etc.                                             |
| clickOnElement(String elementName, By by)                                                  | String elementName, By by                                      | To click on a link/button etc.                                                 |
| verifyElementPresent(String elementName, By by)                                            | String elementName, By by                                      | To verify if the element is presenet in the web page or not                    |
| setText(By by, String text)                                                                | By by, String text                                             | To enter text in a textbox                                                     |
| setSecureText(By by, String encodedtext)                                                   | By by, String encodedtext                                      | To enter encoded text in a textbox                                             |
| waitForElementPresent(By by, int timeToWait)                                               | By by, int timeToWait                                          | To wait for a particular web element to be present in web page                 |
| waitForPageToLoad()                                                                        |                                                                | To wait for a web page to load                                                 |
| acceptAlert(By by)                                                                         | By by                                                          | To accept an alert pop-up                                                      |
| verifyAlertText(By by, String expectedText)                                                | By by, String expectedText                                     | To verify text in an alert pop-up                                              |
| enterTextAlert(By by, String input)                                                        | By by, String input                                            | To enter text in an alert pop-up                                               |
| switchWindow()                                                                             |                                                                | To switch to child window                                                      |
| switchFrame(By by)                                                                         | By by                                                          | To switch to frame containing the web element                                  |
| windowAuthenticationFirefox()                                                              |                                                                | To hand windows authentication pop-up for Firefox browser                      |
| windowAuthenticationIE()                                                                   |                                                                | To hand windows authentication pop-up for I.E. browser                         |
| windowAuthenticationChrome()                                                               |                                                                | To hand windows authentication pop-up for Chrome browser                       |
| fileDownload()                                                                             |                                                                | To download a file from web page                                               |
| killChrome()                                                                               |                                                                | To kill chrome driver process                                                  |
| killFirefox()                                                                              |                                                                | To kill gecko driver process                                                   |
| killIE()                                                                                   |                                                                | To kill IE driver process                                                      |
| authenticatePopUp(String username, String password)                                        | String username, String password                               | To handle authentication pop-up using keyboard actions                         |
| captureScreen()                                                                            |                                                                | To get the screenshot of the current web page                                  |
| getcurrentdateandtime()                                                                    |                                                                | To fetch the current date and time                                             |
| executeDB(String columnName, String query)                                                 | String columnName, String query                                | To fetch the data from the database                                            |
| sendEmailOnComplete()                                                                      |                                                                | To send an email containing the test results                                   |
| checkPDFPageCount(String filePath, int length)                                             | String filePath, int length                                    | To check the count of pages in PDF                                             |
| comparePDFText(String file1Path, String file2Path)                                         | String file1Path, String file2Path                             | To compare 2 PDF documents                                                     |
| comparePDFTextSpecificPage(String file1Path, String file2Path, int pageStart, int pageEnd) | String file1Path, String file2Path, int pageStart, int pageEnd | To compare 2 PDF documents from a mentioned start page to a mentioned end page |
| comparePDFImageModeSame(String file1Path, String file2Path, int pageStart, int pageEnd)    | String file1Path, String file2Path, int pageStart, int pageEnd | To compare 2 PDF documents based on image accuracy                             |
| comparePDFWithoutNumbers(String file1Path, String file2Path, int pageStart, int pageEnd)   | String file1Path, String file2Path, int pageStart, int pageEnd | To compare 2 PDF documents without numbers                                     |
| clickObject(String path , String fileName)                                                 | String path , String fileName                                  | To click on an object using image technique                                    |
| doubleClickObject(String path , String fileName)                                           | String path , String fileName                                  | To double click on an object using image technique                             |
| rightClickObject(String path , String fileName)                                            | String path , String fileName                                  | To right click on an object using image technique                              |
| typeObject(String path , String fileName , String text)                                    | String path , String fileName , String text                    | To type on an object based on image technique                                  |
| objectExists(String path , String fileName)                                                | String path , String fileName                                  | To verify if an object exists based on image technique                         |

# Configuring the Test Cases and Test Data:
## Configuring the TestRunner sheet
Navigate to the `src/test/resources/runner` folder path and open the *TestRunner.xlsx* sheet. Set which test cases will get executed and in which browser to be executed.

<img src="https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/TestRunner%20Sheet.png" title="S.W.A.T." alt="S.W.A.T." width="800" >

## Configuring the TestData sheet
Navigate to the `src/test/resources/testData` folder path and open the *TestData.xlsx* sheet. Provide the sheet name same as the test case name provided earlier in the *TestRunner* sheet. Create column headers for all test data and one named `RunMode`. Now specify which test data suite will be executed by putting the appropriate run mode i.e. **Y** or **N**.

<img src="https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/TestData%20Sheet.png" title="S.W.A.T." alt="S.W.A.T." width="800" >

# Running the Tests:
## Running the FrameworkRunner
Inside any IDE like *Eclipse* or *IntelliJ* navigate to the FrameworkRunner.xml file inside `src/main/java/Runner` package and choose the option Run As `TestNG Suite` as shown below and all the test execution will begin.

![Runner method 1](https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/Framework%20Runner1.gif)

## Running the `Runner` file
As stated earlier this is a maven project, and setting have been added so that we can execute the test cases using maven as well. Using Windows Explorer navigate to the path where the project is kept in local machine. Double click on the Runner.bat file which is present in project folder and test execution will get started.

![Runner method 2](https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/Framework%20Runner2.gif)

# Test Reports
Detailed and interactive reports are generated after each test execution. This framework uses the ExtentReports library created by *Anshoo Arora*, for generating test results at run time. Test step level screenshots are taken and are attached to the report.

![S.W.A.T. Report](https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/Framework%20Report.gif)

  
# Logs
Fresh test execution logs are generated after every test run. The logs are in HTML format for better readability purposes. It uses the *Apache log4j* library for creating these logs.

![S.W.A.T. Logs](https://github.com/SamratJD/S.W.A.T/blob/master/Misc_Files/Framework%20Logs.gif)


# Contributing
### Step 1
* Option 1
  * Fork this repository in your GitHub profile.
* Option 2 
  * Clone this repository in your local system.
### Step 2
Make updates, additions etc. to existing framework features.
### Step 3
Create a new `Pull Request` so that these changes can be viewed and merged with S.W.A.T.

# FAQs

# Support
Reach out to me at one of the below places!
* Email at SamratY92@gmail.com
* Facebook at [Samrat Nag](https://www.facebook.com/samrat.nag1)
