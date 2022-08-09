package com.nopcommerce.user.sort;
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
import pageObjects.User_DetailProductPageObject;
import pageObjects.User_HomePageObject;
import pageObjects.User_LogInPageObject;
import pageObjects.User_MyAccountPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.User_SearchPageObject;
import pageObjects.User_SubMenuPageObject;

public class TC_01_Sort_Display_Paging extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
    User_SubMenuPageObject subMenuPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Select Notebooks in Computer menu");
		homePage.selectTopMenu(driver, "Computers>Notebooks");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);
		}

	@Description("Sort - Sort with name: A-Z")
	@Step("Sort - Sort with name: A-Z")
	@Test
	public void TC01_Sort_By_Name_AZ(){
		log.info("Select Name: A to Z in Sort By combobox");
		subMenuPage.selectCombobox(driver, "products-orderby", "Name: A to Z");
	
		log.info("Verify product name are sort correctly");
		verifyTrue(subMenuPage.isProductNameSortAsc());
	}
	
	@Description("Sort - Sort with name: Z-A")
	@Step("Sort - Sort with name: Z-A")
	@Test
	public void TC02_Sort_By_Name_ZA(){
		log.info("Select Name: Z to A in Sort By combobox");
		subMenuPage.selectCombobox(driver, "products-orderby", "Name: Z to A");
		
		log.info("Verify product name are sort correctly");
		verifyTrue(subMenuPage.isProductNameSortDesc());
	}
	
	@Description("Sort - Price: Low to High")
	@Step("Sort - Sort with Price: Low to High")
	@Test
	public void TC03_Sort_By_Price_LowToHigh(){
		log.info("Select Price: Low to High in Sort By combobox");
		subMenuPage.selectCombobox(driver, "products-orderby", "Price: Low to High");
			
		log.info("Verify product price are sort correctly");
		verifyTrue(subMenuPage.isProductPriceSortAsc());
	}
	
	@Description("Sort - Price: High to Low")
	@Step("Sort - Sort with Price: High to Low")
	@Test
	public void TC04_Sort_By_Price_HighToLow(){
		log.info("Select Price: Low to High in Sort By combobox");
		subMenuPage.selectCombobox(driver, "products-orderby", "Price: High to Low");
			
		log.info("Verify product price are sort correctly");
		verifyTrue(subMenuPage.isProductPriceSortDesc());
	}
	
	@Description("Sort - Display 3 items")
	@Step("Sort - Display 3 items")
	@Test
	public void TC05_Three_Item_Display(){
		log.info("Select display 3 items per page");
		subMenuPage.selectCombobox(driver, "products-pagesize", "3");
		
		log.info("Verify threre are 3 item display");
		verifyEquals(String.valueOf(subMenuPage.getItemSize(driver)), "3");
		
		log.info("Verify the next icon display");
		verifyTrue(subMenuPage.isNextIconDisplay());
		verifyFalse(subMenuPage.isPreviousIconDisplay());
		
		log.info("Click on page 2");
		subMenuPage.clickOnPaging("2");
		
		log.info("Verify the previous icon display");
		verifyTrue(subMenuPage.isPreviousIconDisplay());
		verifyFalse(subMenuPage.isNextIconDisplay());
	}
	
	@Description("Sort - Display 6 items")
	@Step("Sort - Display 6 items")
	@Test
	public void TC06_Six_Item_Display(){
		log.info("Select display 6 items per page");
		subMenuPage.selectCombobox(driver, "products-pagesize", "6");
		
		log.info("Verify threre are 6 item display");
		verifyEquals(String.valueOf(subMenuPage.getItemSize(driver)), "6");
		
		log.info("Verify the next icon display");
		verifyFalse(subMenuPage.isNextIconDisplay());
		verifyFalse(subMenuPage.isPreviousIconDisplay());
	}
	
	@Description("Sort - Display 9 items")
	@Step("Sort - Display 9 items")
	@Test
	public void TC07_Nine_Item_Display(){
		log.info("Select display 9 items per page");
		subMenuPage.selectCombobox(driver, "products-pagesize", "9");
		
		log.info("Verify threre are 6 item display");
		verifyEquals(String.valueOf(subMenuPage.getItemSize(driver)), "6");
		
		log.info("Verify the next icon display");
		verifyFalse(subMenuPage.isNextIconDisplay());
		verifyFalse(subMenuPage.isPreviousIconDisplay());
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
}