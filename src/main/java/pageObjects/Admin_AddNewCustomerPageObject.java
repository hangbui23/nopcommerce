package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.Admin_AddNewCustomerPageUI;
import pageUIs.Admin_CustomerPageUI;

public class Admin_AddNewCustomerPageObject extends BasePage{
	WebDriver driver;
	
	public Admin_AddNewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterValueToCommentTextArea(String value) {
		waitForElementVisible(driver, Admin_AddNewCustomerPageUI.TEXT_AREA_COMMENT);
		senKeyToElement(driver, Admin_AddNewCustomerPageUI.TEXT_AREA_COMMENT, value);
	}
}
