package factoryEnvironment;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import commons.GlobalContants;

public class BrowserstackFactory {
	private WebDriver driver;
	private String osName;
	private String osVersion;
	private String browserName;
	private String browserVersion;
	
	public BrowserstackFactory(String osName, String osVersion, String browserName, String browserVersion) {
		this.osName=osName;
		this.osVersion=osVersion;
		this.browserName=browserName;
		this.browserVersion=browserVersion;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities ();
		capability.setCapability("os",osName);
		capability.setCapability("os_version",osVersion);
		capability.setCapability("browser",browserName);		
		capability.setCapability("browser_version",browserVersion);
		capability.setCapability("browser.debug","true");
		capability.setCapability("name","Run on" + osName + " and"+browserName + " with version "+ browserVersion);
		try {
		driver = new RemoteWebDriver(new URL(GlobalContants.BROWSER_STACK_URL),capability);
		} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
		e.printStackTrace();
		}
			
		return driver;
	}
}
