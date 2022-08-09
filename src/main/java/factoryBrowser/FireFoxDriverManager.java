package factoryBrowser;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FireFoxDriverManager implements BrowserFactory {
	String projectPath = System.getProperty("user.dir");
	
	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options= new FirefoxOptions();
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, projectPath + File.separator + "browserLog" + File.separator + "Firefoxlog.txt");
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", 2);
		options.addPreference("browser.download.userDownloadDir", true);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip, application/zip, application/x-zip-compressed, application/x-compressed, application/msword, application/csv, text/csv, image/png , image/jpeg, application/pdf, text/html, text/plain, application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
		options.addPreference("pdfjs.disabled", true);
		return new FirefoxDriver(options);
	}

}
