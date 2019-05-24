package libraries;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;

import listeners.ExtentTestNGITestListener;
import utilities.Constants;
import utilities.TestBase;

public class FunctionalLib extends TestBase {
	public Properties prop = new Properties();
	Process p;
	String workDir = System.getProperty("user.dir");

	public FunctionalLib(String testCase) throws Throwable {
		driver = setUp(testCase);
		ExtentTestNGITestListener.reportLog = ExtentTestNGITestListener.report.createTest(testCase);
	}

	/**
	 * @author Samrat
	 * @category Robot Function
	 */
	public static void minimizeAllWindows() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_WINDOWS);
			r.keyPress(KeyEvent.VK_M);
			r.keyRelease(KeyEvent.VK_WINDOWS);
			r.keyRelease(KeyEvent.VK_M);
			log.info("Minimizing all the windows....");
		} catch (AWTException e) {
			log.error("Unable to minimize all the windows " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @author Samrat
	 * @category Navigation function
	 * @param url
	 * @return boolean
	 */
	public boolean openURL(String url) {
		try {
			driver.get(url);
			if (!(driver.getCurrentUrl()).equals(null)) {
				log.info("Navigated to URL: " + url);
				ExtentTestNGITestListener.reportLog.pass("Navigated to URL: " + driver.getCurrentUrl(),
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Navigated to incorrect URL " + driver.getCurrentUrl());
				ExtentTestNGITestListener.reportLog.fail("Unable to navigate to URL",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to navigate to URL " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Unable to navigate to URL",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Navigation function
	 * @param expectedTitle
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean verifyPageTitle(String expectedTitle) throws Throwable {
		try {
			if (driver.getTitle().equalsIgnoreCase(expectedTitle)) {
				log.info("Page title is as expected");
				ExtentTestNGITestListener.reportLog.pass("Page title is as expected",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Expected title: " + expectedTitle + " Actual title: " + driver.getTitle());
				ExtentTestNGITestListener.reportLog.fail("Page title is not matching",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to verify page title " + e.getMessage());
			ExtentTestNGITestListener.reportLog.fail("Error while verifying page title",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Web Element function
	 * @param by
	 * @return String
	 * @throws Throwable
	 */
	public String isSelected(By by) throws Throwable {
		try {
			WebElement w = driver.findElement(by);
			String selectedOrNot;
			selectedOrNot = w.getAttribute("checked");
			if (selectedOrNot.equalsIgnoreCase("true") || w.isSelected()) {
				log.info("The checkbox/radiobutton is checked/selected");
				ExtentTestNGITestListener.reportLog.pass("The checkbox/radiobutton is checked/selected",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return selectedOrNot;
			} else {
				log.error("The checkbox/radiobutton is not checked/selected");
				ExtentTestNGITestListener.reportLog.fail("The checkbox/radiobutton is not checked/selected",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return selectedOrNot;
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to check element is selected or not " + e.getMessage());
			ExtentTestNGITestListener.reportLog.fail("Error while checking element is selected or not",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			e.printStackTrace();
			return "false";
		}

	}

	/**
	 * @author Samrat
	 * @category Drop down function
	 * @param by
	 * @param value
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean selectDropdownByValue(By by, String value) throws Throwable {
		try {
			WebElement w = driver.findElement(by);
			Select s = new Select(w);
			s.selectByValue(value);
			if (w.getText().equals(value)) {
				log.info("Selected value from the drop down");
				ExtentTestNGITestListener.reportLog.pass("Value " + value + " has been selected from dropdown",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Unable to select value: " + value + " from drop down");
				ExtentTestNGITestListener.reportLog.fail("Unable to select value from drop down",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to select from drop down " + e.getMessage());
			ExtentTestNGITestListener.reportLog.fail("Error when selecting option from drop down",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Drop down function
	 * @param by
	 * @param index
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean selectDropdownByIndex(By by, int index) throws Throwable {
		try {
			WebElement w = driver.findElement(by);
			Select s = new Select(w);
			s.selectByIndex(index);
			if (w.getText().equals(index)) {
				log.info("Selected index from the drop down");
				ExtentTestNGITestListener.reportLog.pass("Index " + index + " has been selected from dropdown",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Unable to select option at the index: " + index + " from drop down");
				ExtentTestNGITestListener.reportLog.fail("Unable to select value from drop down",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to select from drop down " + e.getMessage());
			ExtentTestNGITestListener.reportLog.fail("Error when selecting option from drop down",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Drop down function
	 * @param by
	 * @param text
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean selectDropdownByText(By by, String text) throws Throwable {
		try {
			WebElement w = driver.findElement(by);
			Select s = new Select(w);
			s.selectByVisibleText(text);
			if (w.getText().equals(text)) {
				log.info("Selected from the drop down");
				ExtentTestNGITestListener.reportLog.pass("Option " + text + " has been selected from dropdown",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Unable to select the option: " + text + " from drop down");
				ExtentTestNGITestListener.reportLog.fail("Unable to select value from drop down",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to select from drop down " + e.getMessage());
			ExtentTestNGITestListener.reportLog.fail("Error when selecting option from drop down",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Web Element function
	 * @param by
	 * @param expected
	 * @return boolean
	 * @throws Throwable
	 */
	public boolean verifyText(By by, String expected) throws Throwable {
		try {
			WebElement w = driver.findElement(by);
			if (w.getText() == null || w.getAttribute("value") == null) {
				log.error("Unable to fetch the text from application");
				ExtentTestNGITestListener.reportLog.fail("Unable to fetch text from Webpage",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			} else {
				if (w.getText().equals(expected)) {
					log.info("Text is displayed as expected");
					ExtentTestNGITestListener.reportLog.pass("Text is matching with expected text",
							MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
					return true;
				} else if (w.getAttribute("value").equals(expected)) {
					log.info("Text is displayed as expected");
					ExtentTestNGITestListener.reportLog.pass("Text is matching with expected text",
							MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
					return true;
				} else {
					log.error("Actual text: " + w.getText() + " is not matching with Expected text: " + expected);
					ExtentTestNGITestListener.reportLog.fail(
							"Actual text: " + w.getText() + "is not matching with expected text: " + expected,
							MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
					return false;
				}
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to verify the text " + e.getMessage());
			ExtentTestNGITestListener.reportLog.fail("Error while verifying the text",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Web Element function
	 * @param elementName
	 * @param by
	 * @return
	 */
	public boolean clickOnElement(String elementName, By by) {
		try {
			Actions actions = new Actions(driver);
			WebElement w = driver.findElement(by);
			if (w.isEnabled() && w.isDisplayed()) {
				actions.moveToElement(w);
				actions.perform();
				actions.click().build().perform();
				log.info("Clicked on element " + elementName);
				return true;
			} else {
				log.error("Unable to click on element");
				ExtentTestNGITestListener.reportLog.fail("Unable to click on the element",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to click on element " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Unable to click on the element",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Web Element function
	 * @param elementName
	 * @param by
	 * @return boolean
	 */
	public boolean verifyElementPresent(String elementName, By by) {
		try {
			if (driver.findElements(by).size() != 0) {
				log.info("The element " + elementName + " is present in the Webpage");
				ExtentTestNGITestListener.reportLog.pass("The element " + elementName + " is present in the Webpage",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("The element " + elementName + " is not present");
				ExtentTestNGITestListener.reportLog.fail("The element " + elementName + " is not present",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred while trying to find element " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Error occurred while trying to find element",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Web Element function
	 * @param by
	 * @param text
	 * @return boolean
	 */
	public boolean setText(By by, String text) {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(driver.findElement(by), text);
			actions.build().perform();
			log.info("Successfully entered " + text + " as input in textbox");
			ExtentTestNGITestListener.reportLog.pass("Successfully entered " + text + " as input in textbox",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			return true;

		} catch (Exception e) {
			log.error("Error occurred while trying to enter text " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Error occurred while trying to enter text",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @author Samrat
	 * @category Web Element function
	 * @param by
	 * @param text
	 * @return boolean
	 */
	public boolean setSecureText(By by, String text) {
		try {
			Actions actions = new Actions(driver);
			byte[] decodedBytes = Base64.decodeBase64(text.getBytes());
			actions.sendKeys(driver.findElement(by), decodedBytes.toString());
			actions.build().perform();
			log.info("Successfully entered password in textbox");
			ExtentTestNGITestListener.reportLog.pass("Successfully entered password in textbox",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			return true;

		} catch (Exception e) {
			log.error("Error occurred while trying to enter text " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Error occurred while trying to enter text",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Wait function
	 * @param by
	 * @param timeToWait
	 * @return boolean
	 */
	public boolean waitForElementPresent(By by, int timeToWait) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, timeToWait);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			wait.until(ExpectedConditions.elementToBeClickable(by));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

			if (driver.findElements(by).size() != 0) {
				log.info("Element is present in Webpage");
				ExtentTestNGITestListener.reportLog.pass("Element is found in WebPage",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Element is not found");
				ExtentTestNGITestListener.reportLog.fail("Element is not found in Webpage",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred while waiting for element on Webpage " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Error while waiting for element on Webpage",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Wait function
	 * @return boolean
	 */
	public boolean waitForPageToLoad() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				log.info("Page load complete");
				ExtentTestNGITestListener.reportLog.pass("Page has been loaded",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Page load not complete");
				ExtentTestNGITestListener.reportLog.fail("Page not loaded",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Error encountered while waiting for page to load " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Page not loaded",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Alert function
	 * @param by
	 * @return boolean
	 */
	public boolean acceptAlert(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				driver.switchTo().alert().accept();
				log.info("Alert is present in Webpage");
				ExtentTestNGITestListener.reportLog.pass("Alert is present in Webpage",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Alert is not present in Webpage");
				ExtentTestNGITestListener.reportLog.fail("Alert is not present in Webpage",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Alert is not present in Webpage");
			try {
				ExtentTestNGITestListener.reportLog.fail("Alert is not present in Webpage",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Alert function
	 * @param by
	 * @param expectedText
	 * @return boolean
	 */
	public boolean verifyAlertText(By by, String expectedText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				if (driver.switchTo().alert().getText().equals(expectedText)) {
					log.info("Expected text: " + expectedText + " is matching with actual text: "
							+ driver.switchTo().alert().getText());
					ExtentTestNGITestListener.reportLog.pass(
							"Expected text: " + expectedText + " is matching with actual text: "
									+ driver.switchTo().alert().getText(),
							MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
					driver.switchTo().alert().accept();
					return true;
				} else {
					log.error("Expected text: " + expectedText + " is not matching with actual text: "
							+ driver.switchTo().alert().getText());
					driver.switchTo().alert().accept();
					ExtentTestNGITestListener.reportLog.fail(
							"Expected text: " + expectedText + " is not matching with actual text: "
									+ driver.switchTo().alert().getText(),
							MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
					return false;
				}

			} else {
				log.error("Alert is not present");
				ExtentTestNGITestListener.reportLog.fail("Alert is not displayed",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Alert text is not matching with expected text " + expectedText);
			try {
				ExtentTestNGITestListener.reportLog.fail(
						"Alert text is not matching with expected text " + expectedText,
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Alert function
	 * @param by
	 * @param input
	 * @return boolean
	 */
	public boolean enterTextAlert(By by, String input) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				driver.switchTo().alert().sendKeys(input);
				driver.switchTo().alert().accept();
				log.info("Successfully entered text " + input + " in alert");
				ExtentTestNGITestListener.reportLog.pass("Successfully entered text " + input + " in alert",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			} else {
				log.error("Unable to enter text " + input + " in alert");
				ExtentTestNGITestListener.reportLog.fail("Unable to enter text " + input + " in alert",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			}
		} catch (Exception e) {
			log.error("Unable to enter text in alert");
			try {
				ExtentTestNGITestListener.reportLog.fail("Unable to enter text in alert",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Window handling
	 * @return boolean
	 */
	public boolean switchWindow() {
		try {
			Set<String> ids = driver.getWindowHandles();
			Iterator<String> id = ids.iterator();
			String parent = id.next();
			String parentTitle = driver.getTitle();
			String child = id.next();
			driver.switchTo().window(child);
			String childTitle = driver.getTitle();
			if (parentTitle.equalsIgnoreCase(childTitle)) {
				log.error("Unable to switch to window: " + childTitle);
				ExtentTestNGITestListener.reportLog.fail("Unable to switch to window",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return false;
			} else {
				log.info("Switched to window: " + childTitle);
				ExtentTestNGITestListener.reportLog.fail("Switched to window.",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
				return true;
			}
		} catch (Exception e) {
			log.error("Unable to switch to child window due to error " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Unable to switch to window",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category Frame switching
	 * @param by
	 * @return boolean
	 */
	public boolean switchFrame(By by) {
		try {
			int i, framecount = driver.findElements(By.tagName("iframe")).size();
			for (i = 0; i <= framecount; i++) {
				driver.switchTo().frame(i);
				int count = driver.findElements(by).size();
				if (count > 0) {
					log.info("Element is found in frame[" + i + "]");
					break;
				}
			}
			if (i != 0) {
				log.info("Element is found in frame[" + i + "]");
				return true;
			} else {
				log.error("Unable to find element in any frame");
				return false;
			}
		} catch (Exception e) {
			log.error("Unable to location element on Webpage due to error " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Samrat
	 * @category AutoIT functions
	 */
	public void windowAuthenticationFirefox() {
		try {
			Runtime.getRuntime().exec(workDir + "\\AutoIT\\Window Authentication Firefox.exe");
			log.info("Executed Authentication file for Firefox.");
		} catch (Exception e) {
			log.error("Unable to execute Window Authentication Firefox.exe due to error " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @author Samrat
	 * @category AutoIT functions
	 */
	public void windowAuthenticationIE() {
		try {
			Runtime.getRuntime().exec(workDir + "\\AutoIT\\Window Authentication IE.exe");
			log.info("Executed Authentication file for IE.");
		} catch (Exception e) {
			log.error("Unable to execute Window Authentication IE.exe due to error " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @author Samrat
	 * @category Windows authentication
	 */
	public void windowAuthenticationChrome() {
		try {
			FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH);
			prop.load(fis);
			int urlLen = prop.getProperty("url").length();
			String url = prop.getProperty("url").substring(7, urlLen);
			String userName = prop.getProperty("username");
			String password = prop.getProperty("password");
			driver.get("http://" + userName + ":" + password + "@" + url);
			log.info("Successfully logged into the application.");
			ExtentTestNGITestListener.reportLog.pass("Logged into the application.",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
		} catch (Exception e) {
			log.error("Unable to login to application due to error " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @author Samrat
	 * @category AutoIT functions
	 */
	public void fileDownload() {
		try {
			Runtime.getRuntime().exec(workDir + "\\AutoIT\\File Download.exe");
			log.info("File downloaded successfully.");
		} catch (Exception e) {
			log.error("Unable to download file due to error " + e.getMessage());
		}
	}

	/**
	 * @author Samrat
	 * @category Executable file execution
	 */
	public void killChrome() {
		try {
			WindowsUtils.killByName("chromedriver.exe");
			log.info("Killed Chrome Driver process....");
		} catch (Exception e) {
			log.error("Unable to kill Chrome Driver due to error " + e.getMessage());
		}
	}

	/**
	 * @author Samrat
	 * @category Executable file execution
	 */
	public void killFirefox() {
		try {
			WindowsUtils.killByName("geckodriver.exe");
			log.info("Killed Gecko Driver process....");
		} catch (Exception e) {
			log.error("Unabe to kill Gecko Driver due to error " + e.getMessage());
		}
	}

	/**
	 * @author Samrat
	 * @category Executable file execution
	 */
	public void killIE() {
		try {
			WindowsUtils.killByName("IEDriverServer.exe");
			log.info("Killed IE Driver process....");
		} catch (Exception e) {
			log.error("Unable to kill IE Driver due to error " + e.getMessage());
		}

	}

	/**
	 * @author Samrat
	 * @param username
	 * @param password
	 * @category ROBOT function
	 */
	public void authenticatePopUp(String username, String password) {
		try {
			Robot r = new Robot();
			StringSelection userName = new StringSelection(username);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(userName, null);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);

			// TAB to password entry field
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);

			// Entering the password
			StringSelection pwd = new StringSelection(password);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			// press enter
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			log.info("Successfully entered credentials in authentication pop-up");
			ExtentTestNGITestListener.reportLog.pass("Entered crendentials in authentication pop-up",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());

		} catch (Exception e) {
			log.error("Error occurred while trying to enter credentials " + e.getMessage());
			try {
				ExtentTestNGITestListener.reportLog.fail("Entered crendentials in authentication pop-up",
						MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @author Samrat
	 * @return Screenshot path
	 */
	public String captureScreen() {
		try {
			TakesScreenshot screen = (TakesScreenshot) driver;
			File src = screen.getScreenshotAs(OutputType.FILE);
			log.info("Screenshot of Web Page has been taken.");
			String dest = Constants.SCREENSHOTS_PATH + "\\Screenshot_" + getcurrentdateandtime() + ".png";
			File target = new File(dest);
			FileUtils.copyFile(src, target);
			return dest;
		} catch (Exception e) {
			log.error("Error occurred while trying to take screenshot " + e.getMessage());
			ExtentTestNGITestListener.reportLog.fail("Unable to take screenshot of Webpage");
			return "";
		}
	}

	/**
	 * @author Samrat
	 * @return current data and time stamp
	 */
	public String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
			log.error("Error occurred while trying to get system data and time " + e.getMessage());
		}
		return str;
	}
}
