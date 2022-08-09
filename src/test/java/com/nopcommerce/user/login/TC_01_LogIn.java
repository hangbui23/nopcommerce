package com.nopcommerce.user.login;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.User_Common_RegisterAndLogInSuccess;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.User_HomePageObject;
import pageObjects.User_LogInPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.User_RegisterPageObject;
import utilitiesConfig.DataFacker;

public class TC_01_LogIn extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
	User_LogInPageObject logInPage;
	String invalidEmail,invalidPassword;
	DataFacker faker = DataFacker.getData();
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		homePage.clickOnHeaderLink(driver, "Log in");
		logInPage = PageGeneratorManager.getUserLogInPage(driver);
		invalidEmail="1234";
		invalidPassword="1234";
	}
	
	@BeforeMethod
	public void navigateBrowser() {
		logInPage.navigateToUrl(driver,"https://demo.nopcommerce.com/login?returnUrl=%2F");
	}

	@Description("LogIn - LogIn with empty data")
	@Step("LogIn - LogIn with empty data")
	@Test
	public void TC01_LogIn_EmptyData(){
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		
		log.info("Get error message on Email text box");
		verifyEquals(logInPage.getTextOfMessageError(driver,"Email"),"Please enter your email");
	}
	
	@Description("LogIn - LogIn with invalid email")
	@Step("LogIn - LogIn with invalid email")
	@Test
	public void TC02_LogIn_InvalidEmail(){
		log.info("Input valid Email to textbox");
		logInPage.enterValueToTextbox(driver,"Email",invalidEmail);
		
		
		log.info("Input valid password to textbox");
		logInPage.enterValueToTextbox(driver,"Password",User_Common_RegisterAndLogInSuccess.password);
			
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		
		log.info("Get error message on Email text box");
		verifyEquals(logInPage.getTextOfMessageError(driver,"Email"),"Wrong email");
		}
	
	
	@Description("LogIn - LogIn with the email didn't register")
	@Step("LogIn - LogIn with the email didn't register")
	@Test
	public void TC03_LogIn_NotExistedEmail(){
		log.info("Input valid Email to textbox");
		logInPage.enterValueToTextbox(driver,"Email",faker.getEmail());
		
		
		log.info("Input valid password to textbox");
		logInPage.enterValueToTextbox(driver,"Password",User_Common_RegisterAndLogInSuccess.password);
			
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		
		log.info("Get error message on Email text box");
		verifyTrue(logInPage.getErrorMessageOnHeader(driver).contains("Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(logInPage.getErrorMessageOnHeader(driver).contains("No customer account found"));
		}
	
	
	@Description("LogIn - Login with existed email don't enter password")
	@Step("LogIn - Login with existed email don't enter password")
	@Test
	public void TC04_LogIn_EmptyPassword(){
		
		log.info("Input valid Email to textbox");
		logInPage.enterValueToTextbox(driver,"Email",User_Common_RegisterAndLogInSuccess.email);
		
		
		log.info("Input valid password to textbox");
		logInPage.enterValueToTextbox(driver,"Password","");
			
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		
		log.info("Get error message on Email text box");
		verifyTrue(logInPage.getErrorMessageOnHeader(driver).contains("Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(logInPage.getErrorMessageOnHeader(driver).contains("The credentials provided are incorrect"));	
	}
	
	@Description("LogIn - Login with existed email, enter wrong password")
	@Step("LogIn - Login with existed email, enter wrong password")
	@Test
	public void TC05_LogIn_WrongPassword(){
		
		log.info("Input valid Email to textbox");
		logInPage.enterValueToTextbox(driver,"Email",User_Common_RegisterAndLogInSuccess.email);
		
		
		log.info("Input valid password to textbox");
		logInPage.enterValueToTextbox(driver,"Password",invalidPassword);
			
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		
		log.info("Get error message on Email text box");
		verifyTrue(logInPage.getErrorMessageOnHeader(driver).contains("Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(logInPage.getErrorMessageOnHeader(driver).contains("The credentials provided are incorrect"));	
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
}

