package pageObjects;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.BaseUIs;
import pageUIs.User_CheckOutPageUI;
import pageUIs.User_OrderDetailPageUI;

public class User_OrderDetailPageObject extends BasePage{
	WebDriver driver;

	public User_OrderDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, BaseUIs.TABLE_VALUE,"total");
		String value = getTextElement(driver, BaseUIs.TABLE_VALUE,"total");
		value = value.replace(",", "").replace("$", "");
		return value;
	}
	
	public String getSubTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, User_OrderDetailPageUI.LBL_SUBTOTAL);
		String value = getTextElement(driver, User_OrderDetailPageUI.LBL_SUBTOTAL);
		value = value.replace(",", "").replace("$", "");
		return value;
	}
	
	public String getOrderTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, User_OrderDetailPageUI.LBL_ORDER_TOTAL);
		String value = getTextElement(driver, User_OrderDetailPageUI.LBL_ORDER_TOTAL);
		value = value.replace(",", "").replace("$", "");
		return value;
	}
}
