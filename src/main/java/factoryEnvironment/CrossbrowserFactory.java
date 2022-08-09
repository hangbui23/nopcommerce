package factoryEnvironment;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import commons.GlobalContants;

public class CrossbrowserFactory {
	private WebDriver driver;
	private String osName; 
	private String browserName; 
	private String browserVersion;
	
	public CrossbrowserFactory(String osName, String browserName, String browserVersion) {
		this.osName = osName;
		this.browserName = browserName;
		this.browserVersion = browserVersion;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities ();
		capability.setCapability("platform",osName);
	 capability.setCapability("browserName",browserName);		
	 capability.setCapability("version",browserVersion);
			try {
			driver = new RemoteWebDriver(new URL(GlobalContants.CROSS_BROWSER_URL),capability);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}
