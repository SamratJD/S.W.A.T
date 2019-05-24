package utilities;

public class Constants {
	public static final String TEST_CASE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\runner\\TestRunner.xlsx";
	public static final String TEST_DATA_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\TestData.xlsx";
	public static final String TEST_CASE_SHEET_NAME = "TestCases";
	public static final String TEST_CASE_COL = "TestCase";
	public static final String TEST_CASE_RUNMODE_COL = "RunMode";
	public static final String BROWSER_NAME_COL = "Browser";
	public static final String RUMMODE_NO = "N";
	public static final String RUMMODE_YES = "Y";
	public static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\config.Properties";
	public static final String GRID_FILE_PATH = System.getProperty("user.dir") + "\\Grid_Files";
	public static final String PHANTOMJS_PATH = System.getProperty("user.dir") + "\\Drivers\\phantomjs.exe";
	public static final String DRIVER_PATH = System.getProperty("user.dir") + "\\Drivers\\";
	public static final String EMAIL_ATTACH_PATH = System.getProperty("user.dir") + "\\TestResults\\ExtentReportsTestNG.html";
	public static final String SCREENSHOTS_PATH = System.getProperty("user.dir") + "\\TestResults\\Screenshots";
	public static final String EXTENTREPORT_PATH = System.getProperty("user.dir") + "\\TestResults";
	public static final String DATAPROVIDER_NAME = "data";
}
