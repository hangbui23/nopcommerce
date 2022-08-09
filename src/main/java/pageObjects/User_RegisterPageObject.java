package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.BaseUIs;
import pageUIs.User_HomePageUI;
import pageUIs.User_RegisterPageUI;

public class User_RegisterPageObject extends BasePage{
	WebDriver driver;

	public User_RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
