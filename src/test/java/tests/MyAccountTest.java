package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistrationPage;

public class MyAccountTest extends TestBase {

	HomePage homeObject;
	UserRegistrationPage registerObject;
	MyAccountPage myaccountObject;
	LoginPage loginObject;

	String oldPassword = "123456789";
	String newPassword = "12345678";
	String firstName = "Houssem";
	String lastName = "Mhamdi";
	String email = "Houssem126558@gmail.com";

	@Test(priority = 1, alwaysRun = true)
	public void UserCanRegisterSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, oldPassword);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));

	}

	@Test(priority = 2)
	public void RegistredUserCanChangePassword() {

		myaccountObject = new MyAccountPage(driver);
		registerObject.openMyAccountPage();
		myaccountObject.openChangePasswordPage();
		myaccountObject.ChangePassword(oldPassword, newPassword);
		Assert.assertTrue(myaccountObject.resultLbl.getText().contains("Password was changed"));

	}

	@Test(priority = 3)
	public void RegistredUserCanLogOut() {
		registerObject.userLogout();
	}

	@Test(priority = 4)
	public void RegistredUserCanLogIn() {

		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin(email, newPassword);
	}
}
