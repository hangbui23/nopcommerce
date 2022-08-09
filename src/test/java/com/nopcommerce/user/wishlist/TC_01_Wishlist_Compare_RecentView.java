package com.nopcommerce.user.wishlist;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.User_AddToCartPageObject;
import pageObjects.User_CompareProductPageObject;
import pageObjects.User_DetailProductPageObject;
import pageObjects.User_HomePageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.User_SubMenuPageObject;
import pageObjects.WishlistPageObject;

public class TC_01_Wishlist_Compare_RecentView extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
    User_SubMenuPageObject subMenuPage;
    User_DetailProductPageObject detailProductPage;
    WishlistPageObject wishlistPage;
    User_AddToCartPageObject addToCartPage;
    User_CompareProductPageObject compareProductPage;
    String productName,productName1,priceProductName,priceProductName1;
    int producNameSize;
    ArrayList<String> listProductName;
    
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Select Notebooks in Computer menu");
		homePage.selectTopMenu(driver, "Computers>Notebooks");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);
		
		productName = "Samsung Series 9 NP900X4C Premium Ultrabook";
		productName1 = "Apple MacBook Pro 13-inch";
		
		log.info("Click on product name");
		subMenuPage.clickOnProductTitle(productName);
		detailProductPage = PageGeneratorManager.getUserDetailProductPage(driver);
		}

	@Description("Wishlist - Add product to wishlist")
	@Step("Wishlist - Add product to wishlist ")
	@Test
	public void TC01_AddProductToWishlist(){
		log.info("Click on Add to wishlist button");
		detailProductPage.clickOnButton(driver, "Add to wishlist");
		
		log.info("Verify the success notification display");
		verifyEquals(detailProductPage.getNotificationMessageSuccess(driver),"The product has been added to your wishlist");
		
		log.info("Click On Close button");
		detailProductPage.clickOnCloseButton(driver);
		
		log.info("Click On Wishlist link");
		detailProductPage.clickOnHeaderLink(driver, "Wishlist");
		wishlistPage = PageGeneratorManager.getUserWishlistPage(driver);
		
		log.info("Verify product name display in wishlist");
		verifyEquals(wishlistPage.getTextBasedOnColumAndRow(driver,"Product(s)","1"),productName);
	}
	
	@Description("Wishlist - Add product to card")
	@Step("Wishlist - Add product to card")
	@Test
	public void TC02_AddProductToCard(){
		log.info("Click on Add to cart checkbox");
		wishlistPage.clickOnCheckBoxVBasedOnColumnAndRow("Add to cart","1");
		
		log.info("Click on Add to card button");
		wishlistPage.clickOnButton(driver, "Add to cart");
		addToCartPage = PageGeneratorManager.getUserAddToCartPage(driver);
		
		log.info("Verify product display in Add To Cart page");
		verifyEquals(addToCartPage.getTextBasedOnColumAndRow(driver,"Product(s)","1"),productName);
		
		log.info("Click on wishlist link");
		addToCartPage.clickOnHeaderLink(driver, "Wishlist");
		wishlistPage = PageGeneratorManager.getUserWishlistPage(driver);
		
		log.info("Verify the message The wishlist is empty!");
		verifyEquals(wishlistPage.getNoResult(driver), "The wishlist is empty!");
	}
	
	@Description("Wishlist - Remove product")
	@Step("Wishlist - Remove product")
	@Test
	public void TC03_RemoveProduct(){
		log.info("Select Notebooks in Computer menu");
		wishlistPage.selectTopMenu(driver, "Computers>Notebooks");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);

		log.info("Click On Add to wishlist icon");
		subMenuPage.clickOnButtonIconName(productName,"Add to wishlist");
		
		log.info("Click On Close button");
		subMenuPage.clickOnCloseButton(driver);
		
		log.info("Click On Wishlist link");
		homePage.clickOnHeaderLink(driver, "Wishlist");
		wishlistPage = PageGeneratorManager.getUserWishlistPage(driver);
		
		log.info("Click On Remove icon");
		wishlistPage.clickOnIconBasedOnColumnAndRow("Remove","1");
			
		log.info("Verify the message The wishlist is empty! and product name not display");
		verifyEquals(wishlistPage.getNoResult(driver), "The wishlist is empty!");
		verifyFalse(wishlistPage.isInfoBasedOnColumAndRow(driver,"Product(s)","1"));
	}
	
	@Description("Compare - Compare product")
	@Step("Compare - Compare product")
	@Test
	public void TC04_CompareProduct(){
		log.info("Select Notebooks in Computer menu");
		wishlistPage.selectTopMenu(driver, "Computers>Notebooks");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);

		log.info("Click On Add to compare list icon");
		subMenuPage.clickOnButtonIconName(productName,"Add to compare list");
		
		log.info("Get price of product name");
		priceProductName = subMenuPage.getPriceBaseOnProductName(productName);
		
		log.info("The product has been added to your product comparison message display");
		verifyEquals(homePage.getNotificationMessageSuccess(driver),"The product has been added to your product comparison");
		
		log.info("Click On Add to compare list icon");
		subMenuPage.clickOnButtonIconName(productName1,"Add to compare list");
		
		log.info("Get price of product name");
		priceProductName1 = subMenuPage.getPriceBaseOnProductName(productName1);
		
		log.info("The product has been added to your product comparison message display");
		verifyEquals(subMenuPage.getNotificationMessageSuccess(driver),"The product has been added to your product comparison");
		
		log.info("Click On Close button");
		subMenuPage.clickOnCloseButton(driver);
		
		log.info("Click on Compare products list link on footer");
		subMenuPage.clickOnFooterLink(driver, "Compare products list");
		compareProductPage = PageGeneratorManager.getUserCompareProductPage(driver);
		
		log.info("Verify product name display correct");
		compareProductPage.isInfoDisplay("Name", productName);
		compareProductPage.isInfoDisplay("Name", productName1);
		
		log.info("Verify price display correct");
		compareProductPage.isInfoDisplay("Price", priceProductName);
		compareProductPage.isInfoDisplay("Price", priceProductName1);
		
		log.info("Verify there are 2 Remove icon");
		verifyEquals(compareProductPage.getSizeOfRemoveButton(),"2");
		
		log.info("Click on Clear list link");
		compareProductPage.clickOnClearListLink();
		
		log.info("Verify message You have no items to compare. display");
		verifyEquals(compareProductPage.getNoResult(driver),"You have no items to compare.");
	}
	
	@Description("Recent Review Product - review product")
	@Step("Recent Review Product - review product")
	@Test
	public void TC05_RecentReviewProduct(){
		log.info("Select Notebooks in Computer menu");
		compareProductPage.selectTopMenu(driver, "Computers>Notebooks");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);
		
		log.info("Get size of product Name");
		producNameSize = subMenuPage.getProductNameSize();
		
		log.info("Get all product Name");
		listProductName = subMenuPage.getAllProductName();
		
		log.info("Click on all product name");
		subMenuPage.clickOnAllProduct();
		
		log.info("Click on Recent Review product");
		subMenuPage.clickOnFooterLink(driver, "Recently viewed products");
		
		log.info("Verify the last 3 items display");
		verifyTrue(subMenuPage.areProductRecentDisplay(listProductName,producNameSize,3));
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
}
