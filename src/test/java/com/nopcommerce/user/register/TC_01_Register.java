package com.nopcommerce.user.register;
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
import pageObjects.PageGeneratorManager;
import pageObjects.User_RegisterPageObject;
import utilitiesConfig.DataFacker;

public class TC_01_Register extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
	User_RegisterPageObject registerPage;
	String invalidEmail,invalidPassword,password;
	DataFacker faker = DataFacker.getData();
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		homePage.clickOnHeaderLink(driver, "Register");
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);
		invalidEmail="1234";
		invalidPassword="12345";
		password= faker.getPassword();
	}
	
	@BeforeMethod
	public void navigateBrowser() {
		registerPage.navigateToUrl(driver,"https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Description("Register - Register with empty data")
	@Step("Register - Register with empty data")
	@Test
	public void TC01_Register_EmptyData(){
		log.info("Click on Register button");
		registerPage.clickOnButton(driver, "Register");
		
		log.info("Get error message on First Name text box");
		verifyEquals(registerPage.getTextOfMessageError(driver,"First name"),"First name is required.");
		
		log.info("Get error message on Last Name text box");
		verifyEquals(registerPage.getTextOfMessageError(driver,"Last name"),"Last name is required.");
		
		log.info("Get error message on Email text box");
		verifyEquals(registerPage.getTextOfMessageError(driver,"Email"),"Email is required.");
		
		log.info("Get error message on Password text box");
		verifyEquals(registerPage.getTextOfMessageError(driver,"Password"),"Password is required.");
		
		log.info("Get error message on Confirm Password text box");
		verifyEquals(registerPage.getTextOfMessageError(driver,"Confirm password"),"Password is required.");
	}
	
	@Description("Register - Register with invalid email")
	@Step("Register - Register with invalid email")
	@Test
	public void TC02_Register_InvalidEmail(){
		log.info("Input valid FirstName to textbox");
		registerPage.enterValueToTextbox(driver,"First name",faker.getFirstName());
		
		log.info("Input valid LastName to textbox");
		registerPage.enterValueToTextbox(driver,"Last name",faker.getLastName());
			
		log.info("Input invalid email to textbox");
		registerPage.enterValueToTextbox(driver,"Email",invalidEmail);
		
		log.info("Input password to textbox");
		registerPage.enterValueToTextbox(driver,"Password",password);

		log.info("Input valid Confirm password to textbox");
		registerPage.enterValueToTextbox(driver,"Confirm password",password);

		log.info("Click on Register button");
		registerPage.clickOnButton(driver, "Register");
		
		log.info("Get error message on Email text box");
		verifyEquals(registerPage.getTextOfMessageError(driver,"Email"),"Wrong email");
		}
	
	@Description("Register - Register with existed email")
	@Step("Register - Register with existed email")
	@Test
	public void TC03_Register_ExistedEmail(){
		log.info("Input valid FirstName to textbox");
		registerPage.enterValueToTextbox(driver,"First name",faker.getFirstName());
		
		log.info("Input valid LastName to textbox");
		registerPage.enterValueToTextbox(driver,"Last name",faker.getLastName());
			
		log.info("Input existed email to textbox");
		registerPage.enterValueToTextbox(driver,"Email",User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Input password to textbox");
		registerPage.enterValueToTextbox(driver,"Password",password);

		log.info("Input valid Confirm password to textbox");
		registerPage.enterValueToTextbox(driver,"Confirm password",password);

		log.info("Click on Register button");
		registerPage.clickOnButton(driver, "Register");
		
		log.info("Get error message on Email text box");
		verifyEquals(registerPage.getErrorMessageOnHeader(driver),"The specified email already exists");
		}
	
	@Description("Register - Register with invalid password (less 6 characters)")
	@Step("Register - Register with invalid password (less 6 characters)")
	@Test
	public void TC03_Register_InvalidPassword(){
		log.info("Input valid FirstName to textbox");
		registerPage.enterValueToTextbox(driver,"First name",faker.getFirstName());
		
		log.info("Input valid LastName to textbox");
		registerPage.enterValueToTextbox(driver,"Last name",faker.getLastName());
			
		log.info("Input existed email to textbox");
		registerPage.enterValueToTextbox(driver,"Email",User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Input password to textbox");
		registerPage.enterValueToTextbox(driver,"Password",invalidPassword);

		log.info("Input valid Confirm password to textbox");
		registerPage.enterValueToTextbox(driver,"Confirm password",password);

		log.info("Click on Register button");
		registerPage.clickOnButton(driver, "Register");
		
		log.info("Get error message on Password text box");
		verifyTrue(registerPage.getTextOfMessageError(driver,"Password").contains("must have at least 6 characters"));
		
		log.info("Get error message on Confirm password text box");
		verifyEquals(registerPage.getTextOfMessageError(driver,"Confirm password"),"The password and confirmation password do not match.");
		}
	
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
}
