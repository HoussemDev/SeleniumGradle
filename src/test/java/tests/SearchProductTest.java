package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductTest extends TestBase {

	String productName = "Apple MacBook Pro 13-inch";
	SearchPage searchbject;
	ProductDetailsPage detailsObject;

	@Test
	public void userCanSearchForProduct() {

		searchbject = new SearchPage(driver);
		detailsObject = new ProductDetailsPage(driver);
		searchbject.ProductSearch(productName);
		searchbject.openProductDetailsPage();
		Assert.assertTrue(detailsObject.productNameBreadCrumb.getText().equalsIgnoreCase(productName));

	}

}
