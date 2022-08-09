package commons;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.apifan.common.random.source.DateTimeSource;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	public String osName = System.getProperty("os.name");
	public String projectPath = System.getProperty("user.dir");
	
	protected BaseTest(){
		log=LogFactory.getLog(getClass());
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	protected WebDriver getBrowserName(String browser) {
		if(browser.contentEquals("chrome_ui")) {
		WebDriverManager.chromedriver().setup();
		//ghi log consolve vô log file
//		System.setProperty("webdriver.chrome.args","--disable-logging");
//		System.setProperty("webdriver.chrome.silentOutput","true");

		
		//install plugin extendsion for Chrome
		//File file = new File(projectPath + File.separator + "browserExtendsion" + File.separator + "google.crx");
		ChromeOptions options = new ChromeOptions();
		//options.addExtensions(file);
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");
		//set language to vietnam 
		//options.addArguments("--lang=vi");
		//disable developer mode
		options.addArguments("--disable-infobars");
		//disable notifications
		options.addArguments("--disable-notifications");
		//disable location
		options.addArguments("--disable-geolocation");
				
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		}else if(browser.contentEquals("chrome_headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}
		else if(browser.contentEquals("firefox_ui")){
			WebDriverManager.firefoxdriver().setup();
			//ghi log consolve vô log file
//			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
//			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, projectPath + File.separator + "browserLog" + File.separator + "Firefoxlog.txt");
			//Install extendsion for FF
//			FirefoxProfile ffprofile = new FirefoxProfile();
//			File file = new File(projectPath + File.separator + "browserExtendsion" + File.separator + "google.xpi");
//			ffprofile.addExtension(file);
//			FirefoxOptions options = new FirefoxOptions();
//			options.setProfile(ffprofile);
//			driver = new FirefoxDriver(options);
			driver = new FirefoxDriver();
		}
		else if(browser.contentEquals("firefox_headless")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			driver = new FirefoxDriver(options);
		}
		else if(browser.contentEquals("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		}
		
		else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		return driver;
	}
	
	protected WebDriver getBrowserName(String browser,  String url) {
		if(browser.contentEquals("chrome_ui")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		}else if(browser.contentEquals("chrome_headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}
		else if(browser.contentEquals("firefox_ui")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.contentEquals("firefox_headless")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			driver = new FirefoxDriver(options);
		}
		else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}
	
	protected WebDriver getBrowserName(String browser, Platform platform ,String ipAddress, String port) {
		DesiredCapabilities capability = null;
		
		if(browser.contentEquals("chrome_ui")) {
		WebDriverManager.chromedriver().setup();
		capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome_ui");
		capability.setPlatform(platform);
		
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
		
		}else if(browser.contentEquals("chrome_headless")) {
			WebDriverManager.chromedriver().setup();
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome_headless");
			capability.setPlatform(platform);
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			options.merge(capability);
		}
		else if(browser.contentEquals("firefox_ui")){
			WebDriverManager.firefoxdriver().setup();
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox_ui");
			capability.setPlatform(platform);
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capability);
		}
		else if(browser.contentEquals("firefox_headless")) {
			WebDriverManager.firefoxdriver().setup();
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox_headless");
			//capability.setPlatform(Platform.WINDOWS);
			capability.setPlatform(platform);
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			options.merge(capability);
		}
		else if(browser.contentEquals("safari")) {
			capability = DesiredCapabilities.safari();
			capability.setBrowserName("safari");
			capability.setJavascriptEnabled(true);
			capability.setPlatform(platform);
		}
		else if(browser.contentEquals("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("ie");
			capability.setJavascriptEnabled(true);
			capability.setPlatform(platform);
		}
		
		else {
			WebDriverManager.edgedriver().setup();
			capability = DesiredCapabilities.edge();
			capability.setBrowserName("edge");
			//capability.setPlatform(Platform.ANY);
			capability.setPlatform(platform);
			capability.setJavascriptEnabled(true);
		}
		
		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress,port)), capability);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		return driver;
	}
	
	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			// Get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = "";
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
			
			// Quit driver executable file in Task Manager
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("edge")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill msedgedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	private String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else if (isWindows()) {
			folderName = "\\" + folderName + "\\";
		} else {
			folderName = null;
		}
		return folderName;
	}
	
	private boolean isWindows() {
		return (osName.toLowerCase().indexOf("win")>=0);	
	}
	
	private boolean isMac() {
		return (osName.toLowerCase().indexOf("mac")>=0);	
	}
	
	private boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix")>=0 || osName.toLowerCase().indexOf("nux")>=0);	
	}
	
	private boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}
	
	
	//note: chỉ delete ReportNGScreenShots folder dùng cho report testNG
	@BeforeSuite
	public void deleteAllFilesInReportNGScreenshot() {
		System.out.println("-----------START DELETE FOLDER-----------");
		deleteAllFileInFolder();
		System.out.println("-----------END DELETE FOLDER-----------");
	}
	
	public void deleteAllFileInFolder() {
		try {
			String workingDir = System.getProperty("usr.dir");
			String pathFolderDownload = workingDir + "\\ReportNGScreenShots";
			File file = new File(pathFolderDownload);
			File[] listOfFile = file.listFiles();
			for (int i = 0; i < listOfFile.length; i++) {
				if (listOfFile[i].isFile()) {
					System.out.println(listOfFile[i].getName());
					new File(listOfFile[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	
	public int getRandomNumber() {
		Random rd = new Random();
		return rd.nextInt(9999);
	}
	
	public String DateFormat(String initDateFormat,String endDateFormat,String date){
		SimpleDateFormat originalFormat = new SimpleDateFormat(initDateFormat);
		SimpleDateFormat targetFormat = new SimpleDateFormat(endDateFormat);
		String newFormat = "";
		try {
			Date parseDate = originalFormat.parse(date);
			newFormat = targetFormat.format(parseDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newFormat;
	}
	
	public String randomDay() {
		LocalDate beginDate = LocalDate.of(2000,1,11);
		LocalDate endDate = LocalDate.of(2021,2,22);
		return  DateTimeSource.getInstance().randomDate(beginDate,endDate, "d");
	}
	
	public String randomMonth() {
		LocalDate beginDate = LocalDate.of(2000,1,11);
		LocalDate endDate = LocalDate.of(2021,2,22);
		return  DateTimeSource.getInstance().randomDate(beginDate,endDate, "MMMM");
	}
	
	public String randomYear() {
		LocalDate beginDate = LocalDate.of(2000,1,11);
		LocalDate endDate = LocalDate.of(2021,2,22);
		return  DateTimeSource.getInstance().randomDate(beginDate,endDate, "yyyy");
	}
	
	public String randomFutureValue(String format) {
		LocalDate beginDate = LocalDate.of(2021,1,10);
		LocalDate endDate = LocalDate.of(2035,2,22);
		return  DateTimeSource.getInstance().randomDate(beginDate,endDate, format);
	}
	
	public String randomPastValue(String format) {
		LocalDate beginDate = LocalDate.of(2000,1,10);
		LocalDate endDate = LocalDate.of(2021,8,01);
		return  DateTimeSource.getInstance().randomDate(beginDate,endDate, format);
	}
	
	public String getCurrentDate(String format, String timeZone) {
		SimpleDateFormat originalFormat = new SimpleDateFormat(format);
		TimeZone tzone = TimeZone.getTimeZone(timeZone);
		originalFormat.setTimeZone(tzone);
		String dateString = originalFormat.format(new Date());
		return dateString;
	}
	
	protected void showBrowserConsoleLog(WebDriver driver) {
		if(driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for(LogEntry logging:logList) {
				log.info("-------------"+ logging.getLevel().toString()+"---------\n" +logging.getMessage());
			}
		}
	}
	
}
