package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.Admin_ProductPageUI;

public class Admin_ProductPageObject extends BasePage{
	WebDriver driver;
	
	public Admin_ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForAjaxInvisiblle() {
		waitForElementInvisible(driver, Admin_ProductPageUI.AJAX_BUSY_ADMIN);
	}
	
	public String getNumberItemDisplay() {
		waitForElementVisible(driver, Admin_ProductPageUI.ITEM_INFO);
		return getTextElement(driver, Admin_ProductPageUI.ITEM_INFO);
	}
}
