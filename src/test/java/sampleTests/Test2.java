package sampleTests;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import libraries.FunctionalLib;
import utilities.TestBase;
import utilities.TestUtil;

public class Test2 extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "data")
	public void test2(Hashtable<String, String> data) throws Throwable {
		if (!TestUtil.isTestRunnable("Test1", excel)) {
			throw new SkipException("Test case skipped");
		} else if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Test Iteration skipped");
		}
		FunctionalLib fl = new FunctionalLib("Test1");
		fl.openURL("https://www.google.com/");
		fl.verifyPageTitle("Google");

	}

	@AfterMethod()
	public void aftertest2() {
		driver.close();
		driver = null;
	}

}
