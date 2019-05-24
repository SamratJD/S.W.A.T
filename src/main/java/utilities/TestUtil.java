package utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class TestUtil extends TestBase {

	public static ExcelReader testDataExcel;

	@DataProvider(name = "data")
	public static Object[][] getData(Method m) {
		testDataExcel = new ExcelReader(Constants.TEST_DATA_PATH);
		String sheetName = m.getName();
		int rows = testDataExcel.getRowCount(sheetName);
		int cols = testDataExcel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(testDataExcel.getCellData(sheetName, colNum, 1),
						testDataExcel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}

		return data;
	}

	// To check if test case will be executed or not
	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = Constants.TEST_CASE_SHEET_NAME;
		int rows = excel.getRowCount(sheetName);
		for (int rNum = 2; rNum <= rows; rNum++) {
			String testCase = excel.getCellData(sheetName, Constants.TEST_CASE_COL, rNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, Constants.TEST_CASE_RUNMODE_COL, rNum);
				if (runmode.equalsIgnoreCase(Constants.RUMMODE_YES))
					return true;
				else
					return false;
			}

		}
		return false;
	}

	// To check on which browser the test case will be executed
	public static String browserName(String testName, ExcelReader excel) {
		String sheetName = Constants.TEST_CASE_SHEET_NAME;
		int rows = excel.getRowCount(sheetName);
		for (int rNum = 2; rNum <= rows; rNum++) {
			String testCase = excel.getCellData(sheetName, Constants.TEST_CASE_COL, rNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, Constants.TEST_CASE_RUNMODE_COL, rNum);
				if (runmode.equalsIgnoreCase(Constants.RUMMODE_YES)) {
					String browser = excel.getCellData(sheetName, Constants.BROWSER_NAME_COL, rNum);
					return browser;
				}
			}
		}
		return "";
	}
}
