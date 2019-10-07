package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.util.concurrent.TimeUnit;

public class TestBase {

	public static WebDriver driver;

	@BeforeSuite
	@Parameters({ "browser" })
	public void startDriver(@Optional("chrome") String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		// Headless browser testring
		/*
		 * else if (browserName.equalsIgnoreCase("headless")) {
		 * 
		 * DesiredCapabilities caps = new DesiredCapabilities();
		 * caps.setJavascriptEnabled(true);
		 * caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		 * System.getProperty("user.dir") + "/drivers/phantomjs.exe"); String[]
		 * phatomJsArgs = { "--web-security=no", "--ignore-ssl-errors=yes" };
		 * caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phatomJsArgs);
		 * driver = new PhantomJSDriver();
		 * 
		 * }
		 */

		else if (browserName.equalsIgnoreCase("chrom-headless")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--window-size-1920,1080");
			driver = new ChromeDriver(options);

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.navigate().to("https://demo.nopcommerce.com/");
	}

	// take screenshot when test case fail and add it in the screenshot folder
	@AfterMethod
	public void screenShotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed");
			System.out.println("Taking screenshot ..");
			Helper.captureScreenShot(driver, result.getName());

		}
	}

	@AfterSuite
	public void stopDriver() {
		driver.quit();
	}

}
