package testCases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import libraries.FunctionalLib;
import pageObjects.BlazeDemoLoginPage;
import pageObjects.BlazeDemoRegisterPage;
import utilities.Constants;
import utilities.TestBase;
import utilities.TestUtil;

public class BlazeDemoTest extends TestBase {

	// =======================================================================================================================================================================
	// Enter test case name in the testCase variable, the same name which is
	// entered in the
	// TestRunner excel file, also put same name in lower case for the method
	// name in @Test
	// =======================================================================================================================================================================

	public static String testCase = "BlazeDemoTest";

	@Test(dataProviderClass = TestUtil.class, dataProvider = Constants.DATAPROVIDER_NAME)
	public void blazeDemoTest(Hashtable<String, String> data) throws Throwable {

		if (!TestUtil.isTestRunnable(testCase, excel)) {
			throw new SkipException("Test case has been skipped.");
		} else if (!data.get(Constants.TEST_CASE_RUNMODE_COL).equalsIgnoreCase(Constants.RUMMODE_YES)) {
			throw new SkipException("Test iteration set has been skipped.");
		}
		FunctionalLib functions = new FunctionalLib(testCase);

		// ===================================================================================================================================================================
		// Write test steps below:
		// ===================================================================================================================================================================
		
		functions.openURL("http://blazedemo.com/register");
		BlazeDemoRegisterPage registerPage = new BlazeDemoRegisterPage(driver);
		functions.waitForElementPresent(registerPage.Register_Button, 6);
		functions.setText(registerPage.Name_Textbox, data.get("Name"));
		functions.setText(registerPage.Company_Texbox, data.get("Company"));
		functions.setText(registerPage.Email_Textbox, data.get("Email"));
		functions.setText(registerPage.Password_Textbox, data.get("Password"));
		functions.setText(registerPage.ConfirmPassword_Textbox, data.get("Password"));
		functions.clickOnElement("Register button", registerPage.Register_Button);
		functions.clickOnElement("Login header", registerPage.Login_Button);
		
		BlazeDemoLoginPage loginPage = new BlazeDemoLoginPage(driver);
		functions.waitForElementPresent(loginPage.Login_Button, 6);
		functions.setText(loginPage.EmailAddress_Textbox, data.get("Email"));
		functions.setText(loginPage.Password_Textbox, data.get("Password"));
		functions.clickOnElement("Login button", loginPage.Login_Button);
		
	}

	@AfterMethod()
	public void aftermethod() {
		driver.close();
		driver = null;
	}

}
