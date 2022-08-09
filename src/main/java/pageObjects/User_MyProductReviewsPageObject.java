package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.User_MyProductReviewsPageUI;

public class User_MyProductReviewsPageObject extends BasePage {
	WebDriver driver;

	public User_MyProductReviewsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isPreviewLinkDisplayInMyProductReview(String productName) {
		waitForElementVisible(driver, User_MyProductReviewsPageUI.REVIEWLINK, productName);
		return isElementDisplay(driver, User_MyProductReviewsPageUI.REVIEWLINK, productName);
	}

}
