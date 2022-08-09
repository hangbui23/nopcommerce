package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.Admin_ProductDetailPageUI;

public class Admin_ProductDetailPageObject extends BasePage{
	WebDriver driver;
	
	public Admin_ProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getHeaderTitle() {
		waitForElementVisible(driver, Admin_ProductDetailPageUI.LBL_TITLE_HEADER);
		return getTextElement(driver, Admin_ProductDetailPageUI.LBL_TITLE_HEADER);
	}
	
	public String getValueOnTextbox(String valueName) {
		waitForElementVisible(driver, Admin_ProductDetailPageUI.TXT_BY_NAME,valueName);
		return getAttributeElement(driver, Admin_ProductDetailPageUI.TXT_BY_NAME, "value", valueName);
	}
}
