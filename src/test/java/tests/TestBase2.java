package tests;

import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.Helper;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase2 {

	// Sauce Labs Configuration
	public static final String USERNAME = LoadProperties.sauceLabData.getProperty("username");
	public static final String ACCESS_KEY = LoadProperties.sauceLabData.getProperty("accesskey");
	public static final String sauceURL = "https://" + USERNAME + ":" + ACCESS_KEY
			+ LoadProperties.sauceLabData.getProperty("seleniumURL");

	public static String BaseUrl = "https://demo.nopcommerce.com/";

	protected ThreadLocal<RemoteWebDriver> driver = null;

	@BeforeClass
	@Parameters(value = { "browser" })
	public void setUp(String browser) throws MalformedURLException {
		driver = new ThreadLocal<>();
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("browserName", browser);
		// Selenium Grid Local
		driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps));

		System.out.println(sauceURL);
		// Run on SAUCE LABS on Cloud
		// driver.set(new RemoteWebDriver(new URL(sauceURL), caps));

		getDriver().navigate().to(BaseUrl);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	@AfterClass
	public void stopDriver() {
		getDriver().quit();
		driver.remove();
	}

	// take screenshot when test case fail and add it in the screenshot folder
	@AfterMethod
	public void screenShotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed");
			System.out.println("Taking screenshot ..");
			Helper.captureScreenShot(getDriver(), result.getName());

		}
	}
}
