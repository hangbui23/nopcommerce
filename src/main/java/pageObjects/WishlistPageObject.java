package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import commons.GlobalContants;
import pageUIs.BaseUIs;
import pageUIs.WishlishPageUI;

public class WishlistPageObject extends BasePage{
	WebDriver driver;
	public WishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnCheckBoxVBasedOnColumnAndRow(String columName, String rowIndex) {
		overRideImplicitWait(driver, GlobalContants.SHORT_TIMEOUT);
		int columIndex = getElementSize(driver, BaseUIs.COLUMN_INDEX, columName) + 1;
		overRideImplicitWait(driver, GlobalContants.LONG_TIMEOUT);
		clickToElement(driver, WishlishPageUI.POSITION_INPUT, rowIndex,String.valueOf(columIndex));
	}

	public void clickOnIconBasedOnColumnAndRow(String columName, String rowIndex) {
		overRideImplicitWait(driver, GlobalContants.SHORT_TIMEOUT);
		int columIndex = getElementSize(driver, BaseUIs.COLUMN_INDEX, columName) + 1;
		overRideImplicitWait(driver, GlobalContants.LONG_TIMEOUT);
		clickToElement(driver, WishlishPageUI.POSITION_ICON, rowIndex,String.valueOf(columIndex));
	}
}
