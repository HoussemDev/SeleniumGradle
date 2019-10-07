package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class ChangeCurrencyTest extends TestBase {

	HomePage homeObject;
	String productName = "Apple MacBook Pro 13-inch";

	SearchPage searchbject;
	ProductDetailsPage detailsObject;

	@Test(priority = 1)
	public void UserCanChangeCurrency() {
		homeObject = new HomePage(driver);
		homeObject.changeCurrency();

	}

	@Test(priority = 2)
	public void userCanSearchWithAutoSuggest() {

		try {
			searchbject = new SearchPage(driver);
			searchbject.productSearchAutoSuggest("MacB");
			detailsObject = new ProductDetailsPage(driver);
			Assert.assertTrue(detailsObject.productNameBreadCrumb.getText().equalsIgnoreCase(productName));
			Assert.assertTrue(detailsObject.productPriceLbl.getText().contains("Ђ"));
			System.out.println(detailsObject.productPriceLbl.getText());
		} catch (Exception e) {
			System.out.println("Error occured" + e.getMessage());
		}

	}
}
