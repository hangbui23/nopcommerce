package pageObjects;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.User_AddToCartPageUI;
import pageUIs.BaseUIs;
import pageUIs.User_CheckOutPageUI;

public class User_CheckOutPageObject extends BasePage{
	WebDriver driver;
	public User_CheckOutPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectComboboxName(String cbbName, String value) {
		waitForElementVisible(driver, User_CheckOutPageUI.CBB_NAME,cbbName);
		selectItemInDropDown(driver, User_CheckOutPageUI.CBB_NAME, value, cbbName);
	}
	
	public void selectBillingAddressCBB(String value) {
		waitForElementVisible(driver, User_CheckOutPageUI.BILLING_ADDRESS_CBB);
		selectItemInDropDown(driver, User_CheckOutPageUI.BILLING_ADDRESS_CBB, value);
	}
	
	public void clickOnContinueButton(String stepTitle) {
		waitForElementClickable(driver, User_CheckOutPageUI.BTN_CONTINUE, stepTitle);
		clickToElement(driver, User_CheckOutPageUI.BTN_CONTINUE, stepTitle);
	}
	
	public void enterValueToTextboxName(String txtName, String value) {
		waitForElementVisible(driver, User_CheckOutPageUI.TXT_NAME, txtName);
		senKeyToElement(driver, User_CheckOutPageUI.TXT_NAME, value, txtName);
	}
}
