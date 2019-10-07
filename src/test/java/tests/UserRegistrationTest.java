package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTest extends TestBase {

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	@Test(priority = 1, alwaysRun = true)
	public void UserCanRegisterSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration("Houssem", "Mhamdi", "Houssemjhhg1255@gmail.com", "123456789");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));

	}

	@Test(dependsOnMethods = { "UserCanRegisterSuccessfully" }, priority = 2)
	public void RegistredUserCanLogOut() {
		registerObject.userLogout();
	}

	@Test(dependsOnMethods = { "RegistredUserCanLogOut" }, priority = 3)
	public void RegistredUserCanLogIn() {

		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin("Houssem1265@gmail.com", "123456789");
	}

}
