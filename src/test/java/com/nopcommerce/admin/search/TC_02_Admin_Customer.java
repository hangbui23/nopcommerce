package com.nopcommerce.admin.search;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.Environment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.Admin_AddNewCustomerPageObject;
import pageObjects.Admin_CustomerPageObject;
import pageObjects.Admin_HomePageObject;
import pageObjects.Admin_LogInPageObject;
import pageObjects.Admin_ProductPageObject;
import pageObjects.PageGeneratorManager;
import utilitiesConfig.DataFacker;

public class TC_02_Admin_Customer extends BaseTest{
	WebDriver driver;
	Admin_LogInPageObject logInPage;
	Admin_HomePageObject homePage;
	Admin_ProductPageObject productPage;
	Admin_CustomerPageObject customerPage;
	Admin_AddNewCustomerPageObject newCustomerPage;
	String email,password,productName,customerEmail,customerPass,customerFirst,customerLast,customerCompany,gender,dob,comment,customerRoleDefault,customerRole;
	DataFacker faker = DataFacker.getData();
	
	@Parameters({"browser","envi"})
	@BeforeClass
	public void beforeClass(String browserName,String envi) {
		email ="admin@yourstore.com";
		password = "admin";
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		customerEmail = faker.getEmail();
		customerPass = faker.getPassword();
		customerFirst = faker.getFirstName();
		customerLast = faker.getLastName();
		customerCompany = faker.getCompanyName();
		gender = "Gender_" + faker.getGender();
		dob = randomPastValue("M/d/yyyy");
		comment = "Add new customer (Guest)";
		customerRole = "Guests";
		customerRoleDefault = "Registered";
		String environment = System.getProperty("environment");
		System.out.println("Get environment variable from command line " + environment);
		
		//envi get từ RunAdminTCs.xml or có thể ConfigFactory.setProperty("environment",environment) từ cmd. Nếu lấy environment từ cmd thì bỏ parameter envi trên;
		ConfigFactory.setProperty("environment",envi);
		Environment env=ConfigFactory.create(Environment.class);
		driver = getBrowserName(browserName,env.url());
		
		logInPage = PageGeneratorManager.getAdminLogInPage(driver);
		
		log.info("Enter value to Email textbox");
		logInPage.enterValueToTextbox(driver, "Email", email);
		
		log.info("Enter value to Password textbox");
		logInPage.enterValueToTextbox(driver, "Password", password);
		
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		homePage = PageGeneratorManager.getAdminHomePage(driver);
	}
	
	@Description("Admin - Create new customer")
	@Step("Admin - Create new customer")
	@Test
	public void TC01_CreateNewCustomer(){
		log.info("Click on Customers>Customers");
		homePage.clickOnNavLinkOnAdmin(driver, "Customers>Customers");
		customerPage = PageGeneratorManager.getAdminCustomerPage(driver);
		
		log.info("Click on Add New button");
		customerPage.clickOnAddNewButton();
		newCustomerPage = PageGeneratorManager.getAdminNewCustomerPage(driver);
		
		log.info("Enter email to email text box");
		newCustomerPage.enterValueToTextboxById(driver, "Email", customerEmail);
		
		log.info("Enter password to password text box");
		newCustomerPage.enterValueToTextboxById(driver, "Password", customerPass);
		
		log.info("Enter first name to first name text box");
		newCustomerPage.enterValueToTextboxById(driver, "FirstName", customerFirst);
		
		log.info("Enter last name to last name text box");
		newCustomerPage.enterValueToTextboxById(driver, "LastName", customerLast);
		
		log.info("Click on Gender button");
		newCustomerPage.checkOnRadioCheckboxOnAdmin(driver, gender);
		
		log.info("Enter DateOfBirth to DOB text box");
		newCustomerPage.enterValueToTextboxById(driver, "DateOfBirth", dob);
		
		log.info("Enter Company to Company text box");
		newCustomerPage.enterValueToTextboxById(driver, "Company", customerCompany);
		
		log.info("Deselect Registered role");
		newCustomerPage.deselectItemInCustomerRole(driver,customerRoleDefault);
		
		log.info("Select Guest Role");
		newCustomerPage.selectItemInCustomerRole(driver, customerRole);
		
		log.info("Enter value to Comment");
		newCustomerPage.enterValueToCommentTextArea(comment);
		
		log.info("Click on Save button");
		newCustomerPage.clickOnButton(driver, "Save");
		customerPage = PageGeneratorManager.getAdminCustomerPage(driver);
		
		log.info("Verify The new customer has been added successfully. message display");
		verifyTrue(customerPage.getAlerSuccess().contains("The new customer has been added successfully."));
		customerPage = PageGeneratorManager.getAdminCustomerPage(driver);
	}
	
	@Description("Admin - Search with email")
	@Step("Admin - Search with email")
	@Test
	public void TC02_SearchWithEmail(){
		log.info("Enter email to email text box");
		customerPage.enterValueToTextboxById(driver, "SearchEmail", customerEmail);
		
		log.info("Deselect Registered role");
		customerPage.deselectItemInCustomerRole(driver,customerRoleDefault);
		
		log.info("Select Guest Role");
		customerPage.selectItemInCustomerRole(driver, customerRole);
		
		log.info("Click on Search button");
		customerPage.clickOnButton(driver, "Search");
		
		log.info("Click on Search button");
		customerPage.sleepInSecond(2);
		
		System.out.println("Full Name: " + customerFirst + " " + customerLast);
		log.info("Verify Name display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Name","1"),customerFirst + " " + customerLast);
		
		log.info("Verify Customer roles is Guests display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Customer roles","1"),customerRole);
		
		log.info("Verify Company name display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Company name","1"),customerCompany);
	}
	
	@Description("Admin - Search with First and Last name")
	@Step("Admin - Search with First and Last name")
	@Test
	public void TC03_SearchWithFirstLastName(){
		log.info("Refresh page");
		customerPage.refreshPage(driver);
		
		log.info("Search with First Name");
		customerPage.enterValueToTextboxById(driver, "SearchFirstName", customerFirst);
		
		log.info("Search with Last Name");
		customerPage.enterValueToTextboxById(driver, "SearchLastName", customerLast);
		
		log.info("Deselect Registered role");
		customerPage.deselectItemInCustomerRole(driver,customerRoleDefault);
		
		log.info("Select Guest Role");
		customerPage.selectItemInCustomerRole(driver, customerRole);
		
		log.info("Click on Search button");
		customerPage.clickOnButton(driver, "Search");
		
		log.info("Click on Search button");
		customerPage.sleepInSecond(2);
		
		System.out.println("Full Name: " + customerFirst + " " + customerLast);
		log.info("Verify Name display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Name","1"),customerFirst + " " + customerLast);
		
		log.info("Verify Customer roles is Guests display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Customer roles","1"),customerRole);
		
		log.info("Verify Company name display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Company name","1"),customerCompany);
	}
	
	@Description("Admin - Search with Company name")
	@Step("Admin - Search with Company name")
	@Test
	public void TC04_SearchWithCompanyName(){
		log.info("Refresh page");
		customerPage.refreshPage(driver);
		
		log.info("Enter email to email text box");
		customerPage.enterValueToTextboxById(driver, "SearchEmail", customerEmail);
		
		log.info("Search with First Name");
		customerPage.enterValueToTextboxById(driver, "SearchFirstName", customerFirst);
		
		log.info("Search with Last Name");
		customerPage.enterValueToTextboxById(driver, "SearchLastName", customerLast);
		
		log.info("Search with Company Name");
		customerPage.enterValueToTextboxById(driver, "SearchCompany", customerCompany);
		
		log.info("Deselect Registered role");
		customerPage.deselectItemInCustomerRole(driver,customerRoleDefault);
		
		log.info("Select Guest Role");
		customerPage.selectItemInCustomerRole(driver, customerRole);
		
		log.info("Click on Search button");
		customerPage.clickOnButton(driver, "Search");
		
		log.info("Click on Search button");
		customerPage.sleepInSecond(2);
		
		System.out.println("Full Name: " + customerFirst + " " + customerLast);
		log.info("Verify Name display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Name","1"),customerFirst + " " + customerLast);
		
		log.info("Verify Customer roles is Guests display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Customer roles","1"),customerRole);
		
		log.info("Verify Company name display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Company name","1"),customerCompany);
	}
	
	@Description("Admin - Search with Full Data")
	@Step("Admin - Search with Full Data")
	@Test
	public void TC05_SearchWithFullData(){
		log.info("Refresh page");
		customerPage.refreshPage(driver);
		
		log.info("Search with Company Name");
		customerPage.enterValueToTextboxById(driver, "SearchCompany", customerCompany);
		
		log.info("Deselect Registered role");
		customerPage.deselectItemInCustomerRole(driver,customerRoleDefault);
		
		log.info("Select Guest Role");
		customerPage.selectItemInCustomerRole(driver, customerRole);
		
		log.info("Click on Search button");
		customerPage.clickOnButton(driver, "Search");
		
		log.info("Click on Search button");
		customerPage.sleepInSecond(2);
		
		System.out.println("Full Name: " + customerFirst + " " + customerLast);
		log.info("Verify Name display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Name","1"),customerFirst + " " + customerLast);
		
		log.info("Verify Customer roles is Guests display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Customer roles","1"),customerRole);
		
		log.info("Verify Company name display");
		verifyEquals(customerPage.getTextBasedOnColumAndRow(driver, "Company name","1"),customerCompany);
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}

}
