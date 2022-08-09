package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.User_AddToCartPageUI;
import pageUIs.User_AddressPageUI;
import pageUIs.BaseUIs;

public class User_AddToCartPageObject extends BasePage{
	WebDriver driver;

	public User_AddToCartPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getNotiItemInCart() {
		waitForElementVisible(driver, User_AddToCartPageUI.NOTI_ITEM_IN_CART);
		return getTextElement(driver, User_AddToCartPageUI.NOTI_ITEM_IN_CART);
	}
	
	public String getProductInfo(String attribute) {
		waitForElementVisible(driver, User_AddToCartPageUI.PRODUCT_INFO_HEADER, attribute);
		return getTextElement(driver, User_AddToCartPageUI.PRODUCT_INFO_HEADER, attribute);
	}
	
	public void clickOnEditLink() {
		waitForElementVisible(driver, User_AddToCartPageUI.LNK_EDIT);
		clickToElement(driver, User_AddToCartPageUI.LNK_EDIT);
	}
	
   public Float getSubTotalOnMiniShopCart() {
	   waitForElementVisible(driver, User_AddToCartPageUI.LBL_SUBTOTAL);
	   String price = getTextElement(driver, User_AddToCartPageUI.LBL_SUBTOTAL);
		String value = price.replace("$", "").replace(",", "");
		return Float.parseFloat(value);
   }
   
   public void clickOnRemoveButton() {
	   waitForElementVisible(driver, User_AddToCartPageUI.BTN_REMOVE);
	   clickToElement(driver, User_AddToCartPageUI.BTN_REMOVE);
   }
   
   public boolean isProductNameUndisplay(String productName) {
	  return isElementUndisplay(driver,User_AddToCartPageUI.PRODUCT_INFO_HEADER,productName);
   }
   
   public void enterValueToQuality(String value) {
	   waitForElementVisible(driver, User_AddToCartPageUI.TXT_QUALITY);
	   senKeyToElement(driver, User_AddToCartPageUI.TXT_QUALITY, value);
   }
   
   public Float getSubTotalOnShopCart() {
	   waitForElementVisible(driver, User_AddToCartPageUI.SHOPPING_CART_TOTAL);
	   String price = getTextElement(driver, User_AddToCartPageUI.SHOPPING_CART_TOTAL);
		String value = price.replace("$", "").replace(",", "");
		return Float.parseFloat(value);
   }
   
   public String getSKUInfo() {
		waitForElementVisible(driver, User_AddToCartPageUI.SKU_PRODUCT);
		return getTextElement(driver, User_AddToCartPageUI.SKU_PRODUCT);
	}
   
   public String getPriceInfo() {
		waitForElementVisible(driver, User_AddToCartPageUI.PRICE_PRODUCT);
		return getTextElement(driver, User_AddToCartPageUI.PRICE_PRODUCT);
	}
   
   public String getQualityInfo() {
		waitForElementVisible(driver, User_AddToCartPageUI.TXT_QUALITY);
		return getAttributeElement(driver, User_AddToCartPageUI.TXT_QUALITY, "value");
	}
   
   public void clickOnCheckoutButton() {
	   waitForElementVisible(driver, User_AddToCartPageUI.BTN_CHECKOUT);
	   clickToElement(driver, User_AddToCartPageUI.BTN_CHECKOUT);
   }
}
