package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import libraries.FunctionalLib;
import utilities.Constants;
import utilities.TestBase;

public class ExtentTestNGITestListener extends TestBase implements ITestListener {

	public static ExtentHtmlReporter htmlextent = null;
	public static ExtentReports report = null;
	public static ExtentTest reportLog = null;

	@Override
	public void onTestStart(ITestResult result) {
		log.info("Test has started " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("Test has successfully executed " + result.getMethod().getMethodName());
		reportLog.pass("Test has passed" + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("Test has been skipped " + result.getMethod().getMethodName());
		reportLog.skip("Test has skipped" + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		log.info("Execution has started....");
		FunctionalLib.minimizeAllWindows();
		
		htmlextent = new ExtentHtmlReporter(Constants.EXTENTREPORT_PATH + "\\ExtentReports.html");
		htmlextent.config().setChartVisibilityOnOpen(true);
		htmlextent.config().setDocumentTitle("S.W.A.T - ExtentReports");
		htmlextent.config().setEncoding("UTF-8");
		htmlextent.config().setReportName(Constants.EXTENTREPORT_PATH + "\\ExtentReports.html");
		htmlextent.config().setProtocol(Protocol.HTTPS);
		htmlextent.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlextent.config().setTheme(Theme.STANDARD);
		htmlextent.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
		htmlextent.config().setCSS("css-string");
		htmlextent.config().setJS("js-string");

		report = new ExtentReports();
		report.attachReporter(htmlextent);

	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
		log.info("Execution has finished....");
	}

}
