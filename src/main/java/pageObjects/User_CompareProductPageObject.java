package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.User_CompareProductPageUI;

public class User_CompareProductPageObject extends BasePage{
	WebDriver driver;

	public User_CompareProductPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isInfoDisplay(String type, String value) {
		waitForElementVisible(driver, User_CompareProductPageUI.INFO, type,value);
		return isElementDisplay(driver, User_CompareProductPageUI.INFO, type,value);
	}
	
	public String getSizeOfRemoveButton() {
		return String.valueOf(getElementSize(driver, User_CompareProductPageUI.LST_BTN_REMOVE));
	}
	
	public void clickOnClearListLink() {
		waitForElementVisible(driver, User_CompareProductPageUI.LNK_CLEARLIST);
		clickToElement(driver, User_CompareProductPageUI.LNK_CLEARLIST);
	}
}
