package utilities;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(Constants.TEST_CASE_PATH);
	public static WebDriverWait wait;
	public static String browser;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	
	public WebDriver setUp(String testCase) throws Exception {

		fis = new FileInputStream(Constants.CONFIG_FILE_PATH);
		prop.load(fis);
		// From excel
		String browserName = TestUtil.browserName(testCase, excel);
		String saucelabsEnabled = prop.getProperty("sauceLabs");
		String remoteEnabled = prop.getProperty("remoteEnabled");
		int globalTime = Integer.parseInt(prop.getProperty("globalWaitTime"));

		if (remoteEnabled.equalsIgnoreCase("false")) {
			log.info("Remote execution is disabled");
			if (saucelabsEnabled.equalsIgnoreCase("false")) {
				log.info("Sauce labs execution is disabled, execution will proceed in local machine");
				if (browserName.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH + "chromedriver.exe");
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				} else if (browserName.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", Constants.DRIVER_PATH + "geckodriver.exe");
					driver = new FirefoxDriver();
				} else if (browserName.equalsIgnoreCase("IE")) {
					System.setProperty("webdriver.ie.driver", Constants.DRIVER_PATH + "IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				} else if (browserName.equalsIgnoreCase("Headless")) {
					DesiredCapabilities dc = new DesiredCapabilities();
					dc.setJavascriptEnabled(true);
					dc.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
							Constants.PHANTOMJS_PATH);
					driver = new PhantomJSDriver(dc);
				}

				driver.manage().timeouts().implicitlyWait(globalTime, TimeUnit.SECONDS);
			} else if (saucelabsEnabled.equalsIgnoreCase("true")) {
				log.info("Sauce labs execution is enabled, execution will proceed in sauce labs cloud server.");
				DesiredCapabilities dc = new DesiredCapabilities();
				String sauceLabsBrowserName = prop.getProperty("sauceLabsBrowser");
				String sauceLabsBrowserVersion = prop.getProperty("sauceLabsBrowserVersion");
				String sauceLabsPlatform = prop.getProperty("sauceLabsPlatform");
				String sauceLabsUserID = prop.getProperty("sauceLabsUserID");
				String sauceLabsAccessKey = prop.getProperty("sauceLabsAccessKey");

				dc.setBrowserName(sauceLabsBrowserName);
				dc.setCapability("version", sauceLabsBrowserVersion);
				dc.setCapability("platform", sauceLabsPlatform);

				driver = new RemoteWebDriver(new URL(
						"http://" + sauceLabsUserID + ":" + sauceLabsAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
						dc);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(globalTime, TimeUnit.SECONDS);

			}
		} else if (remoteEnabled.equalsIgnoreCase("true")) {
			log.info("Remote set to true so browser will be launch in remote server");
			String gridDir = Constants.GRID_FILE_PATH;
			Runtime.getRuntime().exec("cmd /c start " + gridDir + "\\hub.bat");
			DesiredCapabilities dc = new DesiredCapabilities();
			if (browserName.equals("chrome")) {
				dc = DesiredCapabilities.chrome();
				dc.setBrowserName("chrome");
				dc.setPlatform(Platform.ANY);
			} else if (browserName.equals("firefox")) {
				dc = DesiredCapabilities.firefox();
				dc.setBrowserName("firefox");
				dc.setPlatform(Platform.ANY);
			} else if (browserName.equals("ie")) {
				dc = DesiredCapabilities.internetExplorer();
				dc.setBrowserName("iexplore");
				dc.setPlatform(Platform.WINDOWS);
			}
			driver = new RemoteWebDriver(new URL("http://localhost:" + prop.getProperty("hubPortNo") + "/wd/hub"), dc);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(globalTime, TimeUnit.SECONDS);
		}
		return driver;
	}
}
