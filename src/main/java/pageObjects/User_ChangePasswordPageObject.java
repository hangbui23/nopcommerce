package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;

public class User_ChangePasswordPageObject extends BasePage{
	WebDriver driver;

	public User_ChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
