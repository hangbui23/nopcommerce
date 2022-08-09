package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.BaseUIs;
import pageUIs.User_HomePageUI;

public class User_HomePageObject extends BasePage{
	WebDriver driver;

	public User_HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getRegisterMessage() {
		return getTextElement(driver, User_HomePageUI.REGISTER_SUCESS_MESSAGE);
	}
	
	public String getCompletedOrderMessage() {
		return getTextElement(driver, User_HomePageUI.COMPLETE_ORDER_MESSAGE);
	}
	
	public String getOrderNumber() {
		return getTextElement(driver, User_HomePageUI.ORDER_NUMBER).toLowerCase();
	}
	
	public boolean isThankYouTitleDisplay() {
		return isElementDisplay(driver, User_HomePageUI.PAGE_TITLE);
	}
	
	public void clickOnDetailLink() {
		waitForElementClickable(driver, User_HomePageUI.DETAIL_LINK);
		clickToElement(driver, User_HomePageUI.DETAIL_LINK);
	}
}
