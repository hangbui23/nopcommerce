package pageObjects;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.User_AddressPageUI;

public class User_AddressPageObject extends BasePage{
	WebDriver driver;

	public User_AddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTextBaseOnName(String name) {
		System.out.println(getTextElement(driver, User_AddressPageUI.INFO_NAME, name));
		return getTextElement(driver, User_AddressPageUI.INFO_NAME, name);
	}
	
	public void clickOnDeleteButton() {
		List<WebElement> lst_ele = findListWebElement(driver, User_AddressPageUI.BTN_DELETE);
		for(int i=0;i<lst_ele.size();i++) {
			clickToElement(driver, User_AddressPageUI.BTN_DELETE);
			acceptAlert(driver);
			sleepInSecond(2);
		}
	}
}
