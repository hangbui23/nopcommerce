package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;

public class User_LogInPageObject extends BasePage {
	WebDriver driver;

	public User_LogInPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
