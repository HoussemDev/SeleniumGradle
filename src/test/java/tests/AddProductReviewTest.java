package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AddProductReviewTest extends TestBase {

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	String productName = "Apple MacBook Pro 13-inch";

	SearchPage searchbject;
	ProductDetailsPage detailsObject;
	ProductReviewPage reviewObject;

	@Test(priority = 1, alwaysRun = true)
	public void UserCanRegisterSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration("Houssem", "Mhamdi", "Houssem125519@gmail.com", "123456789");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));

	}

	@Test(priority = 2)
	public void userCanSearchWithAutoSuggest() {

		try {
			searchbject = new SearchPage(driver);
			searchbject.productSearchAutoSuggest("MacB");
			detailsObject = new ProductDetailsPage(driver);
			Assert.assertTrue(detailsObject.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
		} catch (Exception e) {
			System.out.println("Error occured" + e.getMessage());
		}

	}

	@Test(priority = 3)
	public void RegistredUserCanReviewProduct() {
		detailsObject.openAddReviewPage();
		reviewObject = new ProductReviewPage(driver);
		reviewObject.AddProductReview("Test Review", "New review from Houssem");
		Assert.assertTrue(reviewObject.reviewNotification.getText().contains("Product review is successfully added"));
	}

	@Test(priority = 4)
	public void RegistredUserCanLogOut() {
		registerObject.userLogout();
	}

}
