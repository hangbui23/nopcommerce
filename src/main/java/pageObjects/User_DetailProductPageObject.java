package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.User_DetailProductPageUI;

public class User_DetailProductPageObject extends BasePage{
	WebDriver driver;

	public User_DetailProductPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnPreviewLink() {
		waitForElementClickable(driver, User_DetailProductPageUI.ADD_YOUR_REVIEW);
		clickToElement(driver,  User_DetailProductPageUI.ADD_YOUR_REVIEW);
	}
	
	public String getPriceOnDetail() {
		sleepInSecond(2);
		return getTextElement(driver, User_DetailProductPageUI.LBL_PRICE);
	}
	
	public void enterValueToQualityTextbox(String value) {
		waitForElementVisible(driver, User_DetailProductPageUI.TXT_QUALITY);
		senKeyToElement(driver, User_DetailProductPageUI.TXT_QUALITY, value);
	}
	
	public void waitForAjaxLoadingInvisibility() {
		waitForElementInvisible(driver, User_DetailProductPageUI.AJAX_LOADING);
	}
}
