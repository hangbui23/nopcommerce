package com.nopcommerce.user.search;
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
import pageObjects.User_MyAccountPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.User_SearchPageObject;

public class TC_01_Search extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
	User_LogInPageObject logInPage;
	User_MyAccountPageObject myAccountPage;
	User_SearchPageObject searchPage;
	String invalidData,relativeData,absoluteData,category,validData,incorrectManufacturer,correctManufacture;
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Click on Log in link");
		homePage.clickOnHeaderLink(driver, "Log in");
		logInPage = PageGeneratorManager.getUserLogInPage(driver);
		log.info("Enter value to Email text box");
		logInPage.enterValueToTextbox(driver, "Email", User_Common_RegisterAndLogInSuccess.email);
		log.info("Enter value to Password text box");
		logInPage.enterValueToTextbox(driver, "Password", User_Common_RegisterAndLogInSuccess.password);
		log.info("Click on Log in button");
		logInPage.clickOnButton(driver, "Log in");
		myAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);
		log.info("Click on Search link on footer");
		myAccountPage.clickOnFooterLink(driver, "Search");
		searchPage = PageGeneratorManager.getUserSearchPage(driver);
		
		invalidData = "Macbook pro 2050";
		relativeData = "Lenovo";
		absoluteData = "Lenovo Thinkpad X1 Carbon Laptop";
		validData = "Apple MacBook Pro";
		category = "Computers";
		incorrectManufacturer = "HP";
		correctManufacture = "Apple";
		}
	
	@BeforeMethod
	public void navigateBrowser() {
		searchPage.navigateToUrl(driver,"https://demo.nopcommerce.com/search");
	}

	@Description("Search/Advanced search - Search with empty data")
	@Step("Search/Advanced search - Search with empty data")
	@Test
	public void TC01_Search_WithEmptyData(){
		log.info("Click on Search button");
		searchPage.clickOnSearchButton();
		
		log.info("Verify message display");
		verifyEquals(searchPage.getMessage(),"Search term minimum length is 3 characters");
	}
	
	@Description("Search/Advanced search - Search with data not existed")
	@Step("Search/Advanced search - Search with data not existed")
	@Test
	public void TC02_Search_WithNotExistedData(){	
		log.info("Search with invalid data");
		searchPage.enterValueToTextbox(driver, "Search keyword",invalidData);
		
		log.info("Click on Search button");
		searchPage.clickOnSearchButton();
		
		log.info("Verify message display");
		verifyEquals(searchPage.getMessage(),"No products were found that matched your criteria.");
	}
	
	
	@Description("Search/Advanced search - Search with product name")
	@Step("Search/Advanced search - Search with product name")
	@Test
	public void TC03_Search_WithProductName(){	
		log.info("Search with valid data");
		searchPage.enterValueToTextbox(driver, "Search keyword",relativeData);
		
		log.info("Click on Search button");
		searchPage.clickOnSearchButton();
		
		log.info("Verify message display");
		verifyTrue(searchPage.areProductDisplay(relativeData));
	}
	
	@Description("Search/Advanced search - Search with exactly product name")
	@Step("Search/Advanced search - Search with exactly product name")
	@Test
	public void TC04_Search_WithExactlyProductName(){	
		log.info("Search with valid data");
		searchPage.enterValueToTextbox(driver, "Search keyword",absoluteData);
		
		log.info("Click on Search button");
		searchPage.clickOnSearchButton();
		
		log.info("Verify product display");
		verifyTrue(searchPage.areProductDisplay(absoluteData));
	}
	
	@Description("Search/Advanced search - Search with advanced parent category")
	@Step("Search/Advanced search - Search with advanced parent category")
	@Test
	public void TC05_Search_AdvancedWithParentCategory(){	
		log.info("Search with valid data");
		searchPage.enterValueToTextbox(driver, "Search keyword",validData);
		
		log.info("Click on Advance check box");
		searchPage.clickOnCheckboxBasedOnName("Advanced search");
		
		log.info("Click on Category combobox");
		searchPage.selectComboboxBasedOnName("Category", category);
		
		log.info("Click on Search button");
		searchPage.clickOnSearchButton();
		
		log.info("Verify message display");
		verifyEquals(searchPage.getMessage(),"No products were found that matched your criteria.");
	}
	
	@Description("Search/Advanced search - Search with sub category")
	@Step("Search/Advanced search - Search with sub category")
	@Test
	public void TC06_Search_AdvancedWithSubCategory(){	
		log.info("Search with valid data");
		searchPage.enterValueToTextbox(driver, "Search keyword",validData);
		
		log.info("Click on Advance check box");
		searchPage.clickOnCheckboxBasedOnName("Advanced search");
		
		log.info("Click on Category combobox");
		searchPage.selectComboboxBasedOnName("Category", category);
		
		log.info("Click on Automatically search sub categories check box");
		searchPage.clickOnCheckboxBasedOnName("Automatically search sub categories");
		
		log.info("Click on Search button");
		searchPage.clickOnSearchButton();
		
		log.info("Verify product display");
		verifyTrue(searchPage.areProductDisplay(validData));
	}
	
	@Description("Search/Advanced search - Search with incorrect manufacture")
	@Step("Search/Advanced search - Search with incorrect manufacture")
	@Test
	public void TC07_Search_AdvancedWithInCorrectManufacturer(){	
		log.info("Search with valid data");
		searchPage.enterValueToTextbox(driver, "Search keyword",validData);
		
		log.info("Click on Advance check box");
		searchPage.clickOnCheckboxBasedOnName("Advanced search");
		
		log.info("Click on Category combobox");
		searchPage.selectComboboxBasedOnName("Category", category);
		
		log.info("Click on Automatically search sub categories check box");
		searchPage.clickOnCheckboxBasedOnName("Automatically search sub categories");
		
		log.info("Click on Manufactuer combobox");
		searchPage.selectComboboxBasedOnName("Manufacturer", incorrectManufacturer);
		
		log.info("Click on Search button");
		searchPage.clickOnSearchButton();
		
		log.info("Verify message display");
		verifyEquals(searchPage.getMessage(),"No products were found that matched your criteria.");
	}
	
	@Description("Search/Advanced search - Search with correct manufacture")
	@Step("Search/Advanced search - Search with correct manufacture")
	@Test
	public void TC08_Search_AdvancedWitCorrectManufacturer(){	
		log.info("Search with valid data");
		searchPage.enterValueToTextbox(driver, "Search keyword",validData);
		
		log.info("Click on Advance check box");
		searchPage.clickOnCheckboxBasedOnName("Advanced search");
		
		log.info("Click on Category combobox");
		searchPage.selectComboboxBasedOnName("Category", category);
		
		log.info("Click on Automatically search sub categories check box");
		searchPage.clickOnCheckboxBasedOnName("Automatically search sub categories");
		
		log.info("Click on Manufactuer combobox");
		searchPage.selectComboboxBasedOnName("Manufacturer", correctManufacture);
		
		log.info("Click on Search button");
		searchPage.clickOnSearchButton();
		
		log.info("Verify product display");
		verifyTrue(searchPage.areProductDisplay(validData));
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
}
