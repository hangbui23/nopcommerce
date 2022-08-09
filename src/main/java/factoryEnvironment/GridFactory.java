package factoryEnvironment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GridFactory {
	private WebDriver driver;
	private String browserName;
	private String ipAddress;
	private String port;
	
	
	public GridFactory(String browserName, String ipAddress, String port) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities capability = null;
		if(browserName.contentEquals("chrome_ui")) {
		WebDriverManager.chromedriver().setup();
		capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome_ui");
		capability.setPlatform(Platform.WINDOWS);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.merge(capability);
		
		}else if(browserName.contentEquals("chrome_headless")) {
			WebDriverManager.chromedriver().setup();
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome_headless");
			capability.setPlatform(Platform.WINDOWS);
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			options.merge(capability);
		}
		else if(browserName.contentEquals("firefox_ui")){
			WebDriverManager.firefoxdriver().setup();
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox_ui");
			capability.setPlatform(Platform.WINDOWS);
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capability);
		}
		else if(browserName.contentEquals("firefox_headless")) {
			WebDriverManager.firefoxdriver().setup();
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox_headless");
			//capability.setPlatform(Platform.WINDOWS);
			capability.setPlatform(Platform.WINDOWS);
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			options.merge(capability);
		}
		else if(browserName.contentEquals("safari")) {
			capability = DesiredCapabilities.safari();
			capability.setBrowserName("safari");
			capability.setJavascriptEnabled(true);
			capability.setPlatform(Platform.MAC);
		}
		else if(browserName.contentEquals("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("ie");
			capability.setJavascriptEnabled(true);
			capability.setPlatform(Platform.WINDOWS);
		}
		
		else {
			WebDriverManager.edgedriver().setup();
			capability = DesiredCapabilities.edge();
			capability.setBrowserName("edge");
			capability.setPlatform(Platform.ANY);
			capability.setJavascriptEnabled(true);
		}
		return driver;
	}
}
