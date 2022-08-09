package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import commons.BasePage;
import pageUIs.User_SubMenuPageUI;
import pageUIs.User_MyAccountPageUI;

public class User_MyAccountPageObject extends BasePage {
	WebDriver driver;

	public User_MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnNavigationLink(String linkName) {
		waitForElementClickable(driver, User_MyAccountPageUI.ACCOUNT_NAVIGATION, linkName);
		clickToElement(driver, User_MyAccountPageUI.ACCOUNT_NAVIGATION, linkName);
	}
}
