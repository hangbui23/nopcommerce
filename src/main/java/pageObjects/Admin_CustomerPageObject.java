package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.Admin_CustomerPageUI;

public class Admin_CustomerPageObject extends BasePage{
	WebDriver driver;
	
	public Admin_CustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnAddNewButton() {
		waitForElementClickable(driver, Admin_CustomerPageUI.BTN_ADDNEW);
		clickToElement(driver, Admin_CustomerPageUI.BTN_ADDNEW);
	}
	
	public String getAlerSuccess() {
		waitForElementVisible(driver, Admin_CustomerPageUI.ALERT_SUCCESS);
		System.out.println("Message" + getTextElement(driver, Admin_CustomerPageUI.ALERT_SUCCESS));
		return getTextElement(driver, Admin_CustomerPageUI.ALERT_SUCCESS);
	}
}
