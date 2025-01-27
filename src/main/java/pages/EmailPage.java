package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage extends PageBase {

	public EmailPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "FriendEmail")
	WebElement emailFriendTxt;

	@FindBy(id = "YourEmailAddress")
	WebElement yourEmailTxt;

	@FindBy(id = "PersonalMessage")
	WebElement personalMsgTxt;

	@FindBy(name = "send-email")
	WebElement sendEmailBtn;

	@FindBy(css = "div.result")
	public WebElement messageNotification;

	public void SendEmailToFriend(String friendEmail, String personalMessage) {
		setTextElementText(emailFriendTxt, friendEmail);
		setTextElementText(personalMsgTxt, personalMessage);
		clickButton(sendEmailBtn);

	}

}
