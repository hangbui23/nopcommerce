package pageObjects;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.BaseUIs;
import pageUIs.User_SearchPageUI;

public class User_SearchPageObject extends BasePage{
	WebDriver driver;

	public User_SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getMessage() {
		waitForElementVisible(driver, User_SearchPageUI.ERROR_MESSAGE);
		return getTextElement(driver, User_SearchPageUI.ERROR_MESSAGE);
	}
	
	public boolean areProductDisplay(String productName) {
		boolean status = false;
		waitForElementVisible(driver, BaseUIs.LIST_PRODUC_TITLE);
		List<WebElement> list_product =  findListWebElement(driver, BaseUIs.LIST_PRODUC_TITLE);
		for(WebElement item:list_product) {
			if(item.getText().contains(productName)) {
				status = true;
			}
			else {
				status = false;
			}
		}
		return status;
	}
	
	public void clickOnSearchButton() {
		waitForElementClickable(driver, User_SearchPageUI.BTN_SEARCH);
		clickToElement(driver, User_SearchPageUI.BTN_SEARCH);
	}
	
	public void clickOnCheckboxBasedOnName(String chkName) {
		 waitForElementVisible(driver, User_SearchPageUI.CHK_NAME,chkName);
		 clickToElement(driver, User_SearchPageUI.CHK_NAME,chkName);
	 }
	
	public void selectComboboxBasedOnName(String cbbName, String value) {
		waitForElementVisible(driver, User_SearchPageUI.CBB_NAME,cbbName);
		selectItemInDropDown(driver, User_SearchPageUI.CBB_NAME, value, cbbName);
	}
}
