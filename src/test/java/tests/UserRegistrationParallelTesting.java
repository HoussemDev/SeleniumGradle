package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationParallelTesting extends TestBase2 {
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	Faker fakeData = new Faker();
	String firstname = fakeData.name().firstName();
	String lastname = fakeData.name().lastName();
	String email = fakeData.internet().emailAddress();
	String password = fakeData.number().digits(8).toString();

	@Test(priority = 1, alwaysRun = true)

	public void UserCanRegisterSuccessfully() {
		homeObject = new HomePage(getDriver());
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(getDriver());
		registerObject.userRegistration(firstname, lastname, email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));

	}

	@Test(dependsOnMethods = { "UserCanRegisterSuccessfully" }, priority = 2)
	public void RegistredUserCanLogOut() {
		registerObject.userLogout();
	}

	@Test(dependsOnMethods = { "RegistredUserCanLogOut" }, priority = 3)
	public void RegistredUserCanLogIn() {

		homeObject.openLoginPage();
		loginObject = new LoginPage(getDriver());
		loginObject.userLogin(email, password);
	}

}