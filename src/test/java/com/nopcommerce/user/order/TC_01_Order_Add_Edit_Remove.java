package com.nopcommerce.user.order;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.User_AddToCartPageObject;
import pageObjects.User_DetailProductPageObject;
import pageObjects.User_HomePageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.User_SubMenuPageObject;


public class TC_01_Order_Add_Edit_Remove extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
	User_SubMenuPageObject subMenuPage;
	User_DetailProductPageObject detailPage;
	User_AddToCartPageObject addToCart;
	String productName,processor,ram,hdd,os,software,uncheckSoftware,prices,updateProcessor,updateRam,updateHdd,updateOs,quality;
	Float subTotalPrice;
    
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		productName = "Build your own computer";
		processor = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]"; 
		ram = "8GB [+$60.00]";
		hdd = "400 GB [+$100.00]";
		os = "Vista Premium [+$60.00]";
		software = "Total Commander [+$5.00]";
		uncheckSoftware ="Microsoft Office [+$50.00]"; 
		updateProcessor = "2.2 GHz Intel Pentium Dual-Core E2200";
		updateRam = "4GB [+$20.00]";
		updateHdd = "320 GB";
		updateOs = "Vista Home [+$50.00]";
		quality = "2";
		
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Select Desktops in Computer menu");
		homePage.selectTopMenu(driver, "Computers>Desktops");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);
		log.info("Click on Product Name");
		subMenuPage.clickOnProductTitle(productName);
		detailPage = PageGeneratorManager.getUserDetailProductPage(driver);
		}

	@Description("Order - Add Product to Cart")
	@Step("Order - Add Product to Cart")
	@Test
	public void TC01_AddProductToCart(){
		log.info("Select Processor");
		detailPage.selectCombobox(driver, "product_attribute_1", processor);
		detailPage.sleepInSecond(1);
		
		log.info("Select Ram");
		detailPage.selectCombobox(driver, "product_attribute_2", ram);
		detailPage.waitForAjaxLoadingInvisibility();
		
		log.info("Select HDD");
		detailPage.clickOnRadioCheckboxName(driver, hdd);
		detailPage.sleepInSecond(1);
		
		log.info("Select OS");
		detailPage.clickOnRadioCheckboxName(driver, os);
		detailPage.sleepInSecond(1);
		
		log.info("Select Solfware");
		detailPage.clickOnRadioCheckboxName(driver,software);		
		detailPage.sleepInSecond(1);
		
		log.info("uncheck software default");	
		detailPage.uncheckOnCheckbox(driver,uncheckSoftware);
		detailPage.sleepInSecond(1);
		
		log.info("Get price info");
		prices = detailPage.getPriceOnDetail();
		
		log.info("Click On Add to cart");
		detailPage.clickOnButton(driver, "Add to cart");
		detailPage.sleepInSecond(1);
		
		log.info("Verify message The product has been added to your shopping cart display");
		verifyEquals(detailPage.getNotificationMessageSuccess(driver), "The product has been added to your shopping cart");
		
		log.info("Click on Close button");
		detailPage.clickOnCloseButton(driver);
		
		log.info("Hover mouse to Shopping cart link");
		detailPage.hoverMouseToHeaderLink(driver,"Shopping cart");
		addToCart = PageGeneratorManager.getUserAddToCartPage(driver);
		
		log.info("Hover mouse to Shopping cart link");
		verifyEquals(addToCart.getNotiItemInCart(),"There are 1 item(s) in your cart.");
		
		log.info("Verify product name display correct");
		verifyEquals(addToCart.getProductInfo("name"), productName);
		
		log.info("Verify Processor display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("Processor: " + processor));
		
		log.info("Verify RAM display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("RAM: " + ram));
		
		log.info("Verify HDD display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("HDD: " + hdd));
		
		log.info("Verify OS display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("OS: " + os));
		
		log.info("Verify Software display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("Software: " + software));
		
		log.info("Verify prices display correct");
		verifyEquals(addToCart.getProductInfo("price"), "Unit price: " + prices);
		
		log.info("Verify quality display correct");
		verifyEquals(addToCart.getProductInfo("quantity"), "Quantity: 1");
		
		log.info("Verify sub total display correct");
		verifyEquals(addToCart.getProductInfo("totals"), "Sub-Total: " + prices);
	}
	
	@Description("Order - Edit Product In Shopping Cart")
	@Step("Order - Edit Product In Shopping Cart")
	@Test
	public void TC02_EditProductInCart(){
		log.info("Click on Shopping cart");
		addToCart.clickOnHeaderLink(driver, "Shopping cart");
		
		log.info("Click on Edit link");
		addToCart.clickOnEditLink();
		detailPage = PageGeneratorManager.getUserDetailProductPage(driver);
		
		log.info("Select Processor");
		detailPage.selectCombobox(driver, "product_attribute_1", updateProcessor);
		detailPage.sleepInSecond(1);
		
		log.info("Select Ram");
		detailPage.selectCombobox(driver, "product_attribute_2", updateRam);
		detailPage.sleepInSecond(1);
		
		log.info("Select HDD");
		detailPage.clickOnRadioCheckboxName(driver,updateHdd);
		detailPage.sleepInSecond(1);
		
		log.info("Select OS");
		detailPage.clickOnRadioCheckboxName(driver,updateOs);
		detailPage.sleepInSecond(1);
		
		log.info("uncheck Total Commander [+$5.00]");
		detailPage.uncheckOnCheckbox(driver,software);
		detailPage.sleepInSecond(1);
		
		log.info("Check Microsoft Office [+$50.00]");
		detailPage.clickOnRadioCheckboxName(driver,uncheckSoftware);		
		detailPage.sleepInSecond(1);
		
		log.info("Enter 2 to quality textbox");
		detailPage.enterValueToQualityTextbox(quality);
		detailPage.sleepInSecond(1);
		
		log.info("Get price info");
		prices = detailPage.getPriceOnDetail();
		
		log.info("Click On Update");
		detailPage.clickOnButton(driver, "Update");
		detailPage.waitForAjaxLoadingInvisibility();
		
		log.info("Verify message The product has been added to your shopping cart display");
		verifyEquals(detailPage.getNotificationMessageSuccess(driver), "The product has been added to your shopping cart");
		
		log.info("Click on Close button");
		detailPage.clickOnCloseButton(driver);
		
		log.info("Hover mouse to Shopping cart link");
		detailPage.hoverMouseToHeaderLink(driver,"Shopping cart");
		addToCart = PageGeneratorManager.getUserAddToCartPage(driver);
		
		log.info("Hover mouse to Shopping cart link");
		verifyEquals(addToCart.getNotiItemInCart(),"There are "+ quality +" item(s) in your cart.");
		
		log.info("Verify product name display correct");
		verifyEquals(addToCart.getProductInfo("name"), productName);
		
		log.info("Verify Processor display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("Processor: " + updateProcessor));
		
		log.info("Verify RAM display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("RAM: " + updateRam));
		
		log.info("Verify HDD display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("HDD: " + updateHdd));
		
		log.info("Verify OS display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("OS: " + updateOs));
		
		log.info("Verify Software display correct");
		verifyTrue(addToCart.getProductInfo("attributes").contains("Software: " + uncheckSoftware));
		
		log.info("Verify prices display correct");
		verifyEquals(addToCart.getProductInfo("price"), "Unit price: " + prices);
		
		log.info("Verify quality display correct");
		verifyEquals(addToCart.getProductInfo("quantity"), "Quantity: " + quality);
		
		log.info("Get Sub Total price");
		subTotalPrice = addToCart.getSubTotalOnMiniShopCart();
		
		log.info("Verify sub total display correct");
		verifyEquals(subTotalPrice,addToCart.calculateSubTotal(quality, prices));
	}
	
	@Description("Order - Remove Product in Shopping cart")
	@Step("Order - Remove Product in Shopping cart")
	@Test
	public void TC03_RemoveProductInCart(){
		log.info("Click on Shopping cart");
		addToCart.hoverMouseToHeaderLink(driver, "Shopping cart");
		
		log.info("Click on Go to cart button");
		addToCart.clickOnButton(driver, "Go to cart");
		
		log.info("Click on remove button");
		addToCart.clickOnRemoveButton();
		
		log.info("Verify message displays");
		verifyEquals(addToCart.getNoResult(driver), "Your Shopping Cart is empty!");
		
		log.info("Verify product name didn't display");
		verifyTrue(addToCart.isProductNameUndisplay("name"));
	}
	
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
}
