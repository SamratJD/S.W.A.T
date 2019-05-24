package testCaseTemplate;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import libraries.FunctionalLib;
import utilities.Constants;
import utilities.TestBase;
import utilities.TestUtil;

public class SampleTestTemplate extends TestBase {

	// =======================================================================================================================================================================
	// Enter test case name in the testCase variable, the same name which is
	// entered in the
	// TestRunner excel file, also put same name in lower case for the method
	// name in @Test
	// =======================================================================================================================================================================

	public static String testCase = "Test1";

	@Test(dataProviderClass = TestUtil.class, dataProvider = Constants.DATAPROVIDER_NAME)
	public void test1(Hashtable<String, String> data) throws Throwable {

		if (!TestUtil.isTestRunnable(testCase, excel)) {
			throw new SkipException("Test case has been skipped.");
		} else if (!data.get(Constants.TEST_CASE_RUNMODE_COL).equalsIgnoreCase(Constants.RUMMODE_YES)) {
			throw new SkipException("Test iteration set has been skipped.");
		}
		FunctionalLib functions = new FunctionalLib(testCase);

		// ===================================================================================================================================================================
		// Write test steps below:
		// ===================================================================================================================================================================

	}

	@AfterMethod()
	public void aftertest1() {
		driver.close();
		driver = null;
	}

}
