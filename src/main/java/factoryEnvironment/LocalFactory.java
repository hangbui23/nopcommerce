package factoryEnvironment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import commons.BrowserList;
import factoryBrowser.BrowserNotSupportException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FireFoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFireFoxDriverManager;
import factoryBrowser.IEDriverManager;
import factoryBrowser.SafariDriverManager;

public class LocalFactory {
public WebDriver driver;
public String browserName;
	public LocalFactory(String browserName) {
	this.browserName = browserName;
}
	
	public WebDriver createDriver() {
		BrowserList browser	= BrowserList.valueOf(browserName.toUpperCase());
		switch(browser) {
		case CHROME_UI:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case FIREFOX:
			driver = new FireFoxDriverManager().getBrowserDriver();
			break;
		case EDGE_LEGACY:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		case IE:
			driver = new IEDriverManager().getBrowserDriver();
			break;
		case CHROME_HEADLESS:
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;	
		case FIREFOX_HEADLESS:
			driver = new HeadlessFireFoxDriverManager().getBrowserDriver();
			break;	
		case SAFARI:
			driver = new SafariDriverManager().getBrowserDriver();
			break;	
		default:
				throw new BrowserNotSupportException(browserName);
		}
		
		return driver;
	}
}
