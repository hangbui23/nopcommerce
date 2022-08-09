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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.apifan.common.random.source.DateTimeSource;

import factoryEnvironment.BrowserstackFactory;
import factoryEnvironment.CrossbrowserFactory;
import factoryEnvironment.GridFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SauceLabFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest2 {
	private WebDriver driver;
	protected final Log log;
	public String osName = System.getProperty("os.name");
	public String projectPath = System.getProperty("user.dir");
	
	protected BaseTest2(){
		log=LogFactory.getLog(getClass());
	}
	
	protected WebDriver getBrowserDriver(String environmentUrl, String envName, String browserName,String browserVersion, String ipAddress, String portNumber, String osName, String osVersion) {
	switch(envName) {
	case "local":
		driver = new LocalFactory(browserName).createDriver();
		break;
	case "grid":
		driver = new GridFactory(browserName, ipAddress, portNumber).createDriver();
		break;
	case "browserStack":
		driver = new BrowserstackFactory(osName, osVersion, browserName, browserVersion).createDriver();
		break;
	case "sauceLab":
		driver = new SauceLabFactory(osName,browserName,browserVersion).createDriver();
		break;
	case "crossBrowser":
		driver = new CrossbrowserFactory(osName, browserName, browserVersion).createDriver();
		break;
	default:
		driver = new LocalFactory(browserName).createDriver();
		break;
	}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(getEnvironment(environmentUrl));
		//driver.get("https://admin-demo.nopcommerce.com/");
		driver.manage().window().maximize();
		return driver;
	}
	
	
	protected String getEnvironment(String environment) {
		String url=null;
		if(environment.equals("DEV")) {
			url = "https://admin-demo.nopcommerce.com/";
		}else if (environment.equals("TEST")) {
			url = "https://admin-demo.nopcommerce.com/v1";
		}else {
			url = "https://admin-demo.nopcommerce.com/v2";
		}
		return url;
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
