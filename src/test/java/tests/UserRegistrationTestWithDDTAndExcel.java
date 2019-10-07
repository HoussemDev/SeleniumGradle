package tests;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

import java.io.IOException;

public class UserRegistrationTestWithDDTAndExcel extends TestBase {

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	@DataProvider(name = "ExcelData")
	public Object[][] userRegisterData() throws IOException {
		ExcelReader ER = new ExcelReader();

		return ER.getExcelData();

	}

	@Test(priority = 1, alwaysRun = true, dataProvider = "ExcelData")
	public void UserCanRegisterSuccessfully(String fname, String lname, String email, String passwrd) {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(fname, lname, email, passwrd);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.userLogout();
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin(email, passwrd);
		registerObject.userLogout();

	}

}
