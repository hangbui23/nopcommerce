package com.nopcommerce.common;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.User_HomePageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.User_RegisterPageObject;
import utilitiesConfig.DataFacker;

public class User_Common_RegisterAndLogInSuccess extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
	User_RegisterPageObject registerPage;
	public static String email,password;
	DataFacker faker = DataFacker.getData();
	
	@Description("Register and login with valid data")
	@Step("RRegister and login with valid data")
	@Parameters("browser")
	@BeforeTest
	public void TC01_RegisterAndLoginSuccess(String browserName){
		log.info("Save email and password info");
		password = faker.getPassword();
		email = faker.getEmail();
		
		log.info("Open borwser and navigate to home page");
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Click on Register link");
		homePage.clickOnHeaderLink(driver, "Register");
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);
		
		log.info("Enter value to First name text box");
		registerPage.enterValueToTextbox(driver, "First name", faker.getFirstName());
		
		log.info("Enter value to Last name text box");
		registerPage.enterValueToTextbox(driver, "Last name", faker.getLastName());
		
		log.info("Enter value to Email text box");
		registerPage.enterValueToTextbox(driver, "Email", email);
		
		log.info("Enter value to Password text box");
		registerPage.enterValueToTextbox(driver, "Password", password);
		
		log.info("Enter value to Confirm password text box");
		registerPage.enterValueToTextbox(driver, "Confirm password", password);
		
		log.info("Click on Register button");
		registerPage.clickOnButton(driver, "Register");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Verify message 'Your registration completed' display");
		verifyEquals(homePage.getRegisterMessage(),"Your registration completed");
		
		log.info("Click on Logout link");
		homePage.clickOnHeaderLink(driver, "Log out");
		
		log.info("Click on Log in link");
		homePage.clickOnHeaderLink(driver, "Log in");
		
		log.info("Enter value to Email");
		homePage.enterValueToTextbox(driver, "Email", email);
		System.out.println("Emai: "+ email);
		
		
		log.info("Enter value to Password");
		homePage.enterValueToTextbox(driver, "Password", password);
		System.out.println("Password: "+ password);
		
		log.info("Click on Log in button");
		registerPage.clickOnButton(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Verify My Account link display");
		verifyTrue(homePage.isHeaderLinkDisplay(driver, "My account"));
		
		log.info("Verify Log out link display");
		verifyTrue(homePage.isHeaderLinkDisplay(driver, "Log out"));
		
		log.info("Click on Log out button");
		homePage.clickOnHeaderLink(driver, "Log out");
		
		log.info("Close browser");
		closeBrowserAndDriver(driver);
		
	}
}
