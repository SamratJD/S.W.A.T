package testCases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import libraries.FunctionalLib;
import pageObjects.PHPTravelLoginPage;
import utilities.Constants;
import utilities.TestBase;
import utilities.TestUtil;

public class PHPTravelsTest extends TestBase {

	// =======================================================================================================================================================================
	// Enter test case name in the testCase variable, the same name which is
	// entered in the
	// TestRunner excel file, also put same name in lower case for the method
	// name in @Test
	// =======================================================================================================================================================================

	public static String testCase = "PHPTravelsTest";

	@Test(dataProviderClass = TestUtil.class, dataProvider = Constants.DATAPROVIDER_NAME)
	public void phpTravelsTest(Hashtable<String, String> data) throws Throwable {

		if (!TestUtil.isTestRunnable(testCase, excel)) {
			throw new SkipException("Test case has been skipped.");
		} else if (!data.get(Constants.TEST_CASE_RUNMODE_COL).equalsIgnoreCase(Constants.RUMMODE_YES)) {
			throw new SkipException("Test iteration set has been skipped.");
		}
		FunctionalLib functions = new FunctionalLib(testCase);

		// ===================================================================================================================================================================
		// Write test steps below:
		// ===================================================================================================================================================================

		functions.openURL("https://www.phptravels.net/admin");

		PHPTravelLoginPage loginPage = new PHPTravelLoginPage(driver);
		functions.waitForElementPresent(loginPage.Login_Button, 6);
		functions.setText(loginPage.Email_Textbox, data.get("Username"));
		functions.setText(loginPage.Password_Textbox, data.get("Password"));
		functions.clickOnElement("PHP travels login", loginPage.Login_Button);

	}

	@AfterMethod()
	public void method() {
		driver.close();
		driver = null;
	}

}
