package com.nopcommerce.admin.search;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest2;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.Admin_HomePageObject;
import pageObjects.Admin_LogInPageObject;
import pageObjects.Admin_ProductDetailPageObject;
import pageObjects.Admin_ProductPageObject;
import pageObjects.PageGeneratorManager;
import utilitiesConfig.DataFacker;

public class TC_01_Admin_Search extends BaseTest2{
	WebDriver driver;
	Admin_LogInPageObject logInPage;
	Admin_HomePageObject homePage;
	Admin_ProductPageObject productPage;
	Admin_ProductDetailPageObject productDetailPage;
	String email,password,productName;
	DataFacker faker = DataFacker.getData();
	
	@Parameters({"environmentUrl","envName","browserName","browserVersion","ipAddress","portNumber","osName","osVersion"})
	@BeforeClass
	public void beforeClass(@Optional("DEV") String environmentUrl, @Optional("local") String envName, @Optional("Chrome_ui") String browserName, @Optional("89.0.1") String browserVersion, @Optional("localhost") String ipAddress, @Optional("8080") String portNumber, @Optional("WINDOW") String osName, @Optional("11") String osVersion) {
		email ="admin@yourstore.com";
		password = "admin";
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		//driver = getBrowserName(browserName,"https://admin-demo.nopcommerce.com/");
		driver = getBrowserDriver(environmentUrl,envName,browserName,browserVersion,ipAddress,portNumber,osName,osVersion);
		logInPage = PageGeneratorManager.getAdminLogInPage(driver);
		
		log.info("Enter value to Email textbox");
		logInPage.enterValueToTextbox(driver, "Email", email);
		
		log.info("Enter value to Password textbox");
		logInPage.enterValueToTextbox(driver, "Password", password);
		
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		homePage = PageGeneratorManager.getAdminHomePage(driver);
	}
	
	@BeforeMethod
	public void navigateToBrowser() {
		log.info("Click on Catalog>Products");
		homePage.clickOnNavLinkOnAdmin(driver, "Catalog>Products");
		productPage = PageGeneratorManager.getAdminProductPage(driver);
	}
	
	@Description("Admin - Search with product name")
	@Step("Admin - Search with product name")
	@Test
	public void TC01_Search_With_Product_Name(){
		log.info("Enter value to product name");
		productPage.enterValueToTextboxById(driver, "SearchProductName", productName);
		
		log.info("Click on Search button");
		productPage.clickOnButton(driver, "Search");
		productPage.waitForAjaxInvisiblle();
		
		log.info("Verify product name display correct");
		verifyEquals(productPage.getTextBasedOnColumAndRow(driver, "Product name", "1"),productName);
		
		log.info("Verify only one item display");
		verifyTrue(productPage.getNumberItemDisplay().contains("of 1 items"));
	}
	
	
	@Description("Admin - Search with product name, category,search")
	@Step("Admin - Search with product name, category,search")
	@Test
	public void TC02_Search_With_Product_Name_Category_Search(){
		log.info("Enter value to product name");
		productPage.enterValueToTextboxById(driver, "SearchProductName", productName);
		
		log.info("Select Computers in Category combobox");
		productPage.selectCombobox(driver, "SearchCategoryId", "Computers");
		
		log.info("Uncheck on Search subcategories");
		productPage.uncheckOnCheckboxOnAdmin(driver, "SearchIncludeSubCategories");
		
		log.info("Click on Search button");
		productPage.clickOnButton(driver, "Search");
		productPage.waitForAjaxInvisiblle();
		
		log.info("Verify No data available in table display");
		verifyEquals(productPage.getNoDataOnTable(driver),"No data available in table");
		
		log.info("Verify No records display");
		verifyEquals(productPage.getNumberItemDisplay(),"No records");
	}
	
	@Description("Admin - Search with product name, category,check on sub search")
	@Step("Admin - Search with product name, category,check on sub search")
	@Test
	public void TC03_Search_With_Product_Name_Category_Search(){
		log.info("Enter value to product name");
		productPage.enterValueToTextboxById(driver, "SearchProductName", productName);
		
		log.info("Select Computers in Category combobox");
		productPage.selectCombobox(driver, "SearchCategoryId", "Computers");
		
		log.info("Check on Search subcategories");
		productPage.checkOnRadioCheckboxOnAdmin(driver, "SearchIncludeSubCategories");
		
		log.info("Click on Search button");
		productPage.clickOnButton(driver, "Search");
		productPage.waitForAjaxInvisiblle();
		
		log.info("Verify product name display correct");
		verifyEquals(productPage.getTextBasedOnColumAndRow(driver, "Product name", "1"),productName);
		
		log.info("Verify only one item display");
		verifyTrue(productPage.getNumberItemDisplay().contains("of 1 items"));
	}
	
	@Description("Admin - Search with product name, sub category,uncheck on sub search")
	@Step("Admin - Search with product name, sub category,uncheck on sub search")
	@Test
	public void TC04_Search_With_Product_Name_Category_Search(){
		log.info("Enter value to product name");
		productPage.enterValueToTextboxById(driver, "SearchProductName", productName);
		
		log.info("Select Computers >> Desktops in Category combobox");
		productPage.selectCombobox(driver, "SearchCategoryId", "Computers >> Desktops");
		
		log.info("Check on Search subcategories");
		productPage.uncheckOnCheckboxOnAdmin(driver, "SearchIncludeSubCategories");
		
		log.info("Click on Search button");
		productPage.clickOnButton(driver, "Search");
		productPage.waitForAjaxInvisiblle();
		
		log.info("Verify product name display correct");
		verifyEquals(productPage.getTextBasedOnColumAndRow(driver, "Product name", "1"),productName);
		
		log.info("Verify only one item display");
		verifyTrue(productPage.getNumberItemDisplay().contains("of 1 items"));
	}
	
	@Description("Admin - Search with product name, sub category,uncheck on sub search")
	@Step("Admin - Search with product name, sub category,uncheck on sub search")
	@Test
	public void TC05_Search_With_Product_Name_Manufacturer(){
		log.info("Enter value to product name");
		productPage.enterValueToTextboxById(driver, "SearchProductName", productName);
		
		log.info("Select All in Category combobox");
		productPage.selectCombobox(driver, "SearchCategoryId", "All");
		
		log.info("Check on Search subcategories");
		productPage.uncheckOnCheckboxOnAdmin(driver, "SearchIncludeSubCategories");
		
		log.info("Select Apple Manufacturer combobox");
		productPage.selectCombobox(driver, "SearchManufacturerId", "Apple");
		
		log.info("Click on Search button");
		productPage.clickOnButton(driver, "Search");
		productPage.waitForAjaxInvisiblle();
		
		log.info("Verify No data available in table display");
		verifyEquals(productPage.getNoDataOnTable(driver),"No data available in table");
		
		log.info("Verify No records display");
		verifyEquals(productPage.getNumberItemDisplay(),"No records");
	}
	
	@Description("Admin - Search with product SKU name")
	@Step("Admin - Search with product SKU name")
	@Test
	public void TC06_Search_With_Product_SKU_NAME(){
		log.info("Enter value to product name");
		productPage.enterValueToTextboxById(driver, "GoDirectlyToSku", "LE_IC_600");
		
		log.info("Click on Search button");
		productPage.clickOnButton(driver, "Go");
		productPage.waitForAjaxInvisiblle();
		productDetailPage = PageGeneratorManager.getAdminProductDetailPage(driver);
		
		log.info("Verify header edit detail page display");
		verifyTrue(productDetailPage.getHeaderTitle().contains("Edit product details - " + productName));
		
		log.info("Verify product name display correct");
		verifyEquals(productDetailPage.getValueOnTextbox("Name"), productName);
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}

}
