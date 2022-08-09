package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.User_OwnReviewPageUI;

public class User_OwnReviewPageObject extends BasePage {
	WebDriver driver;

	public User_OwnReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnRating(String ratingType) {
		waitForElementClickable(driver, User_OwnReviewPageUI.RATING, ratingType);
		clickToElement(driver, User_OwnReviewPageUI.RATING, ratingType);
	}
}
