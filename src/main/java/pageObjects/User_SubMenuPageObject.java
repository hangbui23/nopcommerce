package pageObjects;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.GlobalContants;
import pageUIs.User_AddToCartPageUI;
import pageUIs.BaseUIs;
import pageUIs.User_SubMenuPageUI;

public class User_SubMenuPageObject extends BasePage{
	WebDriver driver;

	public User_SubMenuPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnProductTitle(String productName) {
		waitForElementClickable(driver, BaseUIs.PRODUC_TITLE_NAME, productName);
		clickToElement(driver, BaseUIs.PRODUC_TITLE_NAME, productName);
	}
	
	public int getItemSize(WebDriver driver) {
		waitForElementInvisible(driver, User_SubMenuPageUI.AJAX_LOADING);
		return getElementSize(driver,BaseUIs.LIST_PRODUC_TITLE);
	}
	
	public boolean isProductNameSortDesc() {
		waitForElementInvisible(driver, User_SubMenuPageUI.AJAX_LOADING);
		return isDataTextSortDESC(driver, BaseUIs.LIST_PRODUC_TITLE);
	}
	
	public boolean isProductNameSortAsc() {
		waitForElementInvisible(driver, User_SubMenuPageUI.AJAX_LOADING);
		return isDataTextSortASC(driver, BaseUIs.LIST_PRODUC_TITLE);
	}
	
	public boolean isProductPriceSortDesc() {
		waitForElementInvisible(driver, User_SubMenuPageUI.AJAX_LOADING);
		return isDataFloatSortDESC(driver, BaseUIs.PRODUC_PRICE);
	}
	
	public boolean isProductPriceSortAsc() {
		waitForElementInvisible(driver, User_SubMenuPageUI.AJAX_LOADING);
		return isDataFloatSortASC(driver, BaseUIs.PRODUC_PRICE);
	}
	
	public boolean isNextIconDisplay() {
		boolean status = false;
		overRideImplicitWait(driver, GlobalContants.SHORT_TIMEOUT);
		int numberElement = getElementSize(driver, User_SubMenuPageUI.ICON_NEXT);
		overRideImplicitWait(driver, GlobalContants.LONG_TIMEOUT);
		if(numberElement>0) {
			status= true;
		}
		else {
			status = false;
		}
		return status;
	}
	
	public boolean isPreviousIconDisplay() {
		boolean status = false;
		overRideImplicitWait(driver, GlobalContants.SHORT_TIMEOUT);
		int numberElement = getElementSize(driver, User_SubMenuPageUI.ICON_PRE);
		overRideImplicitWait(driver, GlobalContants.LONG_TIMEOUT);
		if(numberElement>0) {
			status= true;
		}
		else {
			status = false;
		}
		return status;
	}
	
	public void clickOnPaging(String pageNumber) {
		waitForElementVisible(driver, User_SubMenuPageUI.PAGE_INACTIVE,pageNumber);
		clickToElement(driver, User_SubMenuPageUI.PAGE_INACTIVE,pageNumber);
	}
	
	public void clickOnButtonIconName(String productName, String icon_buttonName) {
		waitForElementVisible(driver, User_SubMenuPageUI.BUTTON_ICON,productName, icon_buttonName);
		clickToElement(driver, User_SubMenuPageUI.BUTTON_ICON,productName, icon_buttonName);
	}
	
	public String getPriceBaseOnProductName(String productName) {
		waitForElementVisible(driver, User_SubMenuPageUI.PRICES, productName);
		return getTextElement(driver, User_SubMenuPageUI.PRICES, productName);
	}
	
	public int getProductNameSize() {
		return getElementSize(driver,BaseUIs.LIST_PRODUC_TITLE);
	}
	
	public ArrayList<String> getAllProductName() {
		List<WebElement> lst_ele = findListWebElement(driver,BaseUIs.LIST_PRODUC_TITLE);
		ArrayList<String> arr= new ArrayList<>();
		
		for(WebElement ele: lst_ele) {
			String productName = ele.getText();
			arr.add(productName);
		}
		return arr;
	}
	
	public void clickOnAllProduct() {
		int list_size = getProductNameSize();
		ArrayList<String> arr = new ArrayList<>();
		arr = getAllProductName();

		for(int i=0;i<list_size;i++) {
			clickOnProductTitle(arr.get(i));
			backToPage(driver);
		}
	}
	
	public boolean areProductRecentDisplay(ArrayList<String> lst_productName,int productNameSize, int numberItemLastDisplay) {
		boolean status = false;
		int countIndexDisplay = productNameSize - numberItemLastDisplay;
		for(int i =countIndexDisplay;i<productNameSize;i++) {
			System.out.println("Product name " +lst_productName. get(i));
			status = isElementDisplay(driver, BaseUIs.PRODUC_TITLE_NAME, lst_productName.get(countIndexDisplay));
			if(status == false) {
				break;
			}
		}
		return status;
	}
}
