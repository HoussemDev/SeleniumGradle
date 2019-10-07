package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductReviewPage extends PageBase {

	public ProductReviewPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "AddProductReview_Title")
	WebElement reviewerTitleText;

	@FindBy(id = "AddProductReview_ReviewText")
	WebElement reviewerText;

	@FindBy(id = "addproductrating_4")
	WebElement rating4RdBtn;

	@FindBy(name = "add-review")
	WebElement submitReviewBtn;

	@FindBy(css = "div.result")
	public WebElement reviewNotification;

	public void AddProductReview(String reviewTitle, String reviewMessage) {
		setTextElementText(reviewerTitleText, reviewTitle);
		setTextElementText(reviewerText, reviewMessage);
		clickButton(rating4RdBtn);
		clickButton(submitReviewBtn);

	}

}
