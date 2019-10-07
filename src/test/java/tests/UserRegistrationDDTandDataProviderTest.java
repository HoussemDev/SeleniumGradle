package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationDDTandDataProviderTest extends TestBase {
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	@DataProvider(name = "testData")
	public static Object[][] userData() {
		return new Object[][] { { "Houssem", "Mhamdi", "Test1hmf1test@gmail.com", "123456" },
				{ "Houssem2", "Mhamdi", "Test2hmhmf11@gmail.com", "123456" },
				{ "Houssem3", "Mhamdi", "Test3hmhf1m@gmail.com", "123456" } };

	}

	@Test(priority = 1, alwaysRun = true, dataProvider = "testData")
	public void UserCanRegisterSuccessfully(String fname, String lname, String email, String password) {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(fname, lname, email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.userLogout();

		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin(email, password);

		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
		registerObject.userLogout();

	}

}
