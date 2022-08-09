package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.User_OrderPageUI;

public class User_OrderPageObject extends BasePage{
	WebDriver driver;

	public User_OrderPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getOrderNumber() {
		waitForElementVisible(driver, User_OrderPageUI.ORDER_NUMBER);
		return getTextElement(driver, User_OrderPageUI.ORDER_NUMBER).toLowerCase();
	}
	
	public String getOrderStatus() {
		waitForElementVisible(driver, User_OrderPageUI.INFO_INDEX,"1");
		return getTextElement(driver, User_OrderPageUI.INFO_INDEX,"1");
	}
	
	public String getOrderDate() {
		waitForElementVisible(driver, User_OrderPageUI.INFO_INDEX,"2");
		return getTextElement(driver, User_OrderPageUI.INFO_INDEX,"2");
	}
	
	public String getOrderTotal() {
		waitForElementVisible(driver, User_OrderPageUI.INFO_INDEX,"3");
		String value = getTextElement(driver, User_OrderPageUI.INFO_INDEX,"3");
		value = value.replace("$", "").replace(",", "");
		return value;
	}
}
