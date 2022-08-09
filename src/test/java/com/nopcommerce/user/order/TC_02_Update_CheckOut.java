package com.nopcommerce.user.order;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.User_Common_RegisterAndLogInSuccess;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.User_AddToCartPageObject;
import pageObjects.User_CheckOutPageObject;
import pageObjects.User_DetailProductPageObject;
import pageObjects.User_HomePageObject;
import pageObjects.User_LogInPageObject;
import pageObjects.User_MyAccountPageObject;
import pageObjects.User_OrderDetailPageObject;
import pageObjects.User_OrderPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.User_SubMenuPageObject;
import utilitiesConfig.DataFacker;

public class TC_02_Update_CheckOut extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
	User_SubMenuPageObject subMenuPage;
	User_DetailProductPageObject detailPage;
	User_AddToCartPageObject addToCart;
	User_LogInPageObject logInPage;
	User_CheckOutPageObject checkOutPage;
	User_MyAccountPageObject myAccountPage;
	User_OrderPageObject orderPage;
	User_OrderDetailPageObject orderDetailPage;
	String productName,quality,price,term,productName1,country,state,city,address,zipcode,phoneNumber,rdName,paymentMethod,sku,firstName,lastName,orderNumber,subTotal,status,total,paymentMethod1,creditCardType,fullName,cardNumber,updatedQuality;
	Float subTotalPrice;
    DataFacker faker=DataFacker.getData();
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		productName1 = "Apple MacBook Pro 13-inch";
		quality = "5";
		term = "I agree with the terms of service and I adhere to them unconditionally";
		country = "Angola";
		state = "Other";
		firstName = faker.getFirstName();
		lastName = faker.getLastName();
		city = faker.getCityName();
		address = faker.getAddress();
		zipcode = faker.getZipCode();
		phoneNumber = faker.getPhone();
		fullName = faker.getFullName();
		cardNumber = "6007220770539553";
		rdName = "Ground";
		paymentMethod = "Check / Money Order";
		paymentMethod1 = "Credit Card";
		status = "Pending";
		creditCardType = "Visa";
		updatedQuality = "10";
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
		log.info("Select Desktops in Computer menu");
		homePage.selectTopMenu(driver, "Computers>Desktops");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);
		price = subMenuPage.getPriceBaseOnProductName(productName);
		log.info("Click on product name");
		subMenuPage.clickOnProductTitle(productName);
		detailPage = PageGeneratorManager.getUserDetailProductPage(driver);
		log.info("Click on Add to cart");
		detailPage.clickOnButton(driver, "Add to cart");
		}

	@Description("Order - Update shopping cart")
	@Step("Order - Update shopping cart")
	@Test
	public void TC04_UpdateShoppingCart(){
		log.info("Verify message The product has been added to your shopping cart display");
		verifyEquals(detailPage.getNotificationMessageSuccess(driver), "The product has been added to your shopping cart");
		
		log.info("Click on Close button");
		detailPage.clickOnCloseButton(driver);
		
		log.info("Click On Shopping cart");
		detailPage.clickOnHeaderLink(driver, "Shopping cart");
		addToCart = PageGeneratorManager.getUserAddToCartPage(driver);
		
		log.info("Enter value 5 to quality");
		addToCart.enterValueToQuality(quality);
		
		log.info("Click On update shopping cart");
		addToCart.clickOnButton(driver, "Update shopping cart");
		
		log.info("Verify price increase to $2500");
		verifyEquals(addToCart.getSubTotalOnShopCart(), addToCart.calculateSubTotal(quality,price));
		
		log.info("Click on remove button");
		addToCart.clickOnRemoveButton();
	}
	
	@Description("Order - Check out product on Money Order")
	@Step("Order - Check out product on Money Order")
	@Test
	public void TC05_CheckoutProduct(){
		log.info("Select Computers>Notebooks");
		addToCart.selectTopMenu(driver, "Computers>Notebooks");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);
		
		log.info("Click on Product Name");
		subMenuPage.clickOnProductTitle(productName1);
		detailPage = PageGeneratorManager.getUserDetailProductPage(driver);
		
		log.info("Click on Add to cart");
		detailPage.clickOnButton(driver, "Add to cart");
		
		log.info("Click on Close button");
		detailPage.clickOnCloseButton(driver);
		
		log.info("Click On Shopping cart");
		detailPage.clickOnHeaderLink(driver, "Shopping cart");
		addToCart = PageGeneratorManager.getUserAddToCartPage(driver);
		
		log.info("Get sku info");
		sku = addToCart.getSKUInfo();
		
		log.info("Get price info");
		price = addToCart.getPriceInfo();
		
		log.info("Get quality info");
		quality = addToCart.getQualityInfo();
		
		log.info("Click On Agree term");
		addToCart.clickOnRadioCheckboxName(driver,term);
		
		log.info("Click On Checkout button");
		addToCart.clickOnButton(driver, " Checkout ");
		checkOutPage = PageGeneratorManager.getUserCheckOutPage(driver);

		log.info("Enter value to First Name");
		checkOutPage.enterValueToTextbox(driver, "First name", firstName);

		log.info("Enter value to Last Name");
		checkOutPage.enterValueToTextbox(driver, "Last name", lastName);
		
		log.info("Select Angola country");
		checkOutPage.selectComboboxName("Country", country);
		
		log.info("Select Other State / province");
		checkOutPage.selectComboboxName("State / province", state);
		
		log.info("Enter value to City");
		checkOutPage.enterValueToTextbox(driver, "City", city);
		
		log.info("Enter value to Address");
		checkOutPage.enterValueToTextbox(driver, "Address 1", address);
		
		log.info("Enter value to Zip / postal code");
		checkOutPage.enterValueToTextbox(driver, "Zip / postal code", zipcode);
		
		log.info("Enter value to Phone code");
		checkOutPage.enterValueToTextbox(driver, "Phone number", phoneNumber);
		
		log.info("Enter value to Phone code");
		checkOutPage.enterValueToTextbox(driver, "Fax number", phoneNumber);
		
		log.info("Click on Continue button on Checkout");
		checkOutPage.clickOnButton(driver, "Continue");
		
		log.info("Click on Ground radio button");
		checkOutPage.clickOnRadioButton(driver, rdName);
		
		log.info("Click on Continue button on Shipping method");
		checkOutPage.clickOnContinueButton("Shipping method");
		
		log.info("Click on Check / Money Order radio button");
		checkOutPage.clickOnRadioButton(driver, paymentMethod);
		
		log.info("Click on Continue button on Payment method");
		checkOutPage.clickOnContinueButton("Payment method");
		
		log.info("Click on Continue button on Payment information");
		checkOutPage.clickOnContinueButton("Payment information");
		
		log.info("Verify name display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "email"), "Email: " + User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Verify Phone display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "phone"), "Phone: " + phoneNumber);
		
		log.info("Verify Fax display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "fax"), "Fax: " + phoneNumber);
		
		log.info("Verify Address display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "address1"), address);
		
		log.info("Verify City, zipcode display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "city-state-zip"), city + "," + zipcode);
		
		log.info("Verify Country display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "country"),country);
		
		log.info("Verify Payment method display correct in Payment");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Payment", "payment-method"),"Payment Method: " + paymentMethod);
		
		log.info("Verify name display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "email"), "Email: " + User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Verify Phone display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "phone"), "Phone: " + phoneNumber);
		
		log.info("Verify Fax display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "fax"), "Fax: " + phoneNumber);
		
		log.info("Verify Address display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "address1"), address);
		
		log.info("Verify City, zipcode display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "city-state-zip"), city + "," + zipcode);
		
		log.info("Verify Country display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "country"),country);
		
		log.info("Verify Payment method display correct in Shipping");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping", "shipping-method"),"Shipping Method: " + rdName);
		
		log.info("Verify SKU display correct");
		verifyTrue(checkOutPage.getSKUInfo(driver).contains(sku));
		
		log.info("Verify product name display correct");
		verifyEquals(checkOutPage.getProductNameInfo(driver),productName1);
		
		log.info("Verify Price display correct");
		verifyTrue(checkOutPage.getPriceInfo(driver).contains(price));
		
		log.info("Verify Quality display correct");
		verifyTrue(checkOutPage.getQualityInfo(driver).contains(quality));
		
		log.info("Get Total Price");
		subTotal = String.valueOf(checkOutPage.calculateSubTotal(quality,price));
		
		log.info("Verify SubTotal display correct");
		verifyTrue(checkOutPage.getSubTotalInfo(driver).contains(subTotal));
		
		log.info("Click on Confirm button");
		checkOutPage.clickOnButton(driver, "Confirm");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Thank you header display");
		verifyTrue(homePage.isThankYouTitleDisplay());
		
		log.info("Verify the Your order has been successfully processed! message display");
		verifyEquals(homePage.getCompletedOrderMessage(),"Your order has been successfully processed!");
		
		log.info("Get orderNumber");
		orderNumber = homePage.getOrderNumber();
		
		log.info("Click on My account link");
		homePage.clickOnHeaderLink(driver, "My account");
		myAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);
		
		log.info("Click on Order Navigation link");
		myAccountPage.clickOnNavigationLink("Orders");
		orderPage = PageGeneratorManager.getUserOrderPage(driver);
		
		log.info("Verify Order Number display");
		verifyEquals(orderNumber, orderPage.getOrderNumber());
		
		log.info("Verify Order Status display");
		verifyEquals(orderPage.getOrderStatus(),"Order status: "+ status);
		
		log.info("Verify Order Date display");
		verifyTrue(orderPage.getOrderDate().contains(getCurrentDate("M/dd/yyyy","America/New_York")));
		
		log.info("Verify Order Total display");
		verifyTrue(orderPage.getOrderTotal().contains(subTotal));
		
		log.info("Click on Details link");
		orderPage.clickOnButton(driver, "Details");
		orderDetailPage = PageGeneratorManager.getUserOrderDetailPage(driver);
		
		log.info("Verify name display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "email"), "Email: " + User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Verify Phone display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "phone"), "Phone: " + phoneNumber);
		
		log.info("Verify Fax display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "fax"), "Fax: " + phoneNumber);
		
		log.info("Verify Address display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "address1"), address);
		
		log.info("Verify City, zipcode display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "city-state-zip"), city + "," + zipcode);
		
		log.info("Verify Country display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "country"),country);
		
		log.info("Verify Payment method display correct in Payment");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Payment", "payment-method"),"Payment Method: " + paymentMethod);
		
		log.info("Verify Payment status method display correct in Payment");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Payment", "payment-method-status"),"Payment Status: " + status);
		
		log.info("Verify name display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "email"), "Email: " + User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Verify Phone display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "phone"), "Phone: " + phoneNumber);
		
		log.info("Verify Fax display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "fax"), "Fax: " + phoneNumber);
		
		log.info("Verify Address display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "address1"), address);
		
		log.info("Verify City, zipcode display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "city-state-zip"), city + "," + zipcode);
		
		log.info("Verify Country display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "country"),country);
		
		log.info("Verify Shipping method display correct in Shipping");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping", "shipping-method"),"Shipping Method: " + rdName);
		
		log.info("Verify Shipping method display correct in Shipping");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping", "shipping-status"),"Shipping Status: Not yet shipped");
		
		log.info("Verify SKU display correct");
		verifyTrue(orderDetailPage.getSKUInfo(driver).contains(sku));
		
		log.info("Verify product name display correct");
		verifyEquals(orderDetailPage.getProductNameInfo(driver),productName1);
		
		log.info("Verify Price display correct");
		verifyTrue(orderDetailPage.getPriceInfo(driver).contains(price));
		
		log.info("Verify Quality display correct");
		verifyTrue(orderDetailPage.getQualityInfo(driver).contains(quality));
		
		log.info("Get Total Price");
		total = String.valueOf(orderDetailPage.calculateSubTotal(quality,price));
		
		log.info("Verify Total display correct");
		verifyTrue(orderDetailPage.getTotalInfo(driver).contains(total));
		
		log.info("Verify Sub Total display correct");
		verifyTrue(orderDetailPage.getSubTotalInfo(driver).contains(total));
		
		log.info("Verify Order Total display correct");
		verifyTrue(orderDetailPage.getOrderTotalInfo(driver).contains(total));
	}
	
	
	
	
	@Description("Order - Check out product on visa card")
	@Step("Order - Check out product on visa card")
	@Test
	public void TC07_CheckoutProduct(){
		log.info("Select Computers>Notebooks");
		orderDetailPage.selectTopMenu(driver, "Computers>Notebooks");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);
		
		log.info("Click on Product Name");
		subMenuPage.clickOnProductTitle(productName1);
		detailPage = PageGeneratorManager.getUserDetailProductPage(driver);
		
		log.info("Click on Add to cart");
		detailPage.clickOnButton(driver, "Add to cart");
		
		log.info("Click on Close button");
		detailPage.clickOnCloseButton(driver);
		
		log.info("Click On Shopping cart");
		detailPage.clickOnHeaderLink(driver, "Shopping cart");
		addToCart = PageGeneratorManager.getUserAddToCartPage(driver);
		
		log.info("Get sku info");
		sku = addToCart.getSKUInfo();
		
		log.info("Get price info");
		price = addToCart.getPriceInfo();
		
		log.info("Get quality info");
		quality = addToCart.getQualityInfo();
		
		log.info("Click On Agree term");
		addToCart.clickOnRadioCheckboxName(driver,term);
		
		log.info("Click On Checkout button");
		addToCart.clickOnButton(driver, " Checkout ");
		checkOutPage = PageGeneratorManager.getUserCheckOutPage(driver);

		log.info("Click on Continue button on Checkout");
		checkOutPage.clickOnButton(driver, "Continue");
		
		log.info("Click on Ground radio button");
		checkOutPage.clickOnRadioButton(driver, rdName);
		
		log.info("Click on Continue button on Shipping method");
		checkOutPage.clickOnContinueButton("Shipping method");
		
		log.info("Click on Credit Card radio button");
		checkOutPage.clickOnRadioButton(driver, paymentMethod1);
		
		log.info("Click on Continue button on Payment method");
		checkOutPage.clickOnContinueButton("Payment method");
		
		log.info("Click on Continue button on Payment information");
		checkOutPage.selectCombobox(driver, "CreditCardType", creditCardType);
		
		log.info("Enter value to Cardholder name on Payment information");
		checkOutPage.enterValueToTextboxName("Cardholder name", fullName);
		
		log.info("Enter value to Card number on Payment information");
		checkOutPage.enterValueToTextboxName("Card number", cardNumber);
		
		log.info("Select Month on Payment information");
		checkOutPage.selectCombobox(driver, "ExpireMonth", randomFutureValue("MM"));
		
		log.info("Select Year on Payment information");
		checkOutPage.selectCombobox(driver, "ExpireYear", randomFutureValue("yyyy"));
		
		log.info("Enter value to Card number on Payment information");
		checkOutPage.enterValueToTextboxName("Card code", "423");
		
		log.info("Click on Continue button on Payment information");
		checkOutPage.clickOnContinueButton("Payment information");
		
		log.info("Verify name display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "email"), "Email: " + User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Verify Phone display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "phone"), "Phone: " + phoneNumber);
		
		log.info("Verify Fax display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "fax"), "Fax: " + phoneNumber);
		
		log.info("Verify Address display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "address1"), address);
		
		log.info("Verify City, zipcode display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "city-state-zip"), city + "," + zipcode);
		
		log.info("Verify Country display correct in Billing Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Billing Address", "country"),country);
		
		log.info("Verify Payment method display correct in Payment");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Payment", "payment-method"),"Payment Method: " + paymentMethod1);
		
		log.info("Verify name display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "email"), "Email: " + User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Verify Phone display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "phone"), "Phone: " + phoneNumber);
		
		log.info("Verify Fax display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "fax"), "Fax: " + phoneNumber);
		
		log.info("Verify Address display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "address1"), address);
		
		log.info("Verify City, zipcode display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "city-state-zip"), city + "," + zipcode);
		
		log.info("Verify Country display correct in Shipping Address");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping Address", "country"),country);
		
		log.info("Verify Payment method display correct in Shipping");
		verifyEquals(checkOutPage.getConfirmOrderInfo(driver,"Shipping", "shipping-method"),"Shipping Method: " + rdName);
		
		log.info("Verify SKU display correct");
		verifyTrue(checkOutPage.getSKUInfo(driver).contains(sku));
		
		log.info("Verify product name display correct");
		verifyEquals(checkOutPage.getProductNameInfo(driver),productName1);
		
		log.info("Verify Price display correct");
		verifyTrue(checkOutPage.getPriceInfo(driver).contains(price));
		
		log.info("Verify Quality display correct");
		verifyTrue(checkOutPage.getQualityInfo(driver).contains(quality));
		
		log.info("Get Total Price");
		subTotal = String.valueOf(checkOutPage.calculateSubTotal(quality,price));
		
		log.info("Verify SubTotal display correct");
		verifyTrue(checkOutPage.getSubTotalInfo(driver).contains(subTotal));
		
		log.info("Sleep in 10s to click on Confirm button");
		homePage.sleepInSecond(10);
	
		log.info("Click on Confirm button");
		checkOutPage.clickOnButton(driver, "Confirm");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Thank you header display");
		verifyTrue(homePage.isThankYouTitleDisplay());
		
		log.info("Verify the Your order has been successfully processed! message display");
		verifyEquals(homePage.getCompletedOrderMessage(),"Your order has been successfully processed!");
		
		log.info("Get orderNumber");
		orderNumber = homePage.getOrderNumber();
		
		log.info("Click on My account link");
		homePage.clickOnHeaderLink(driver, "My account");
		myAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);
		
		log.info("Click on Order Navigation link");
		myAccountPage.clickOnNavigationLink("Orders");
		orderPage = PageGeneratorManager.getUserOrderPage(driver);
		
		log.info("Verify Order Number display");
		verifyEquals(orderNumber, orderPage.getOrderNumber());
		
		log.info("Verify Order Status display");
		verifyEquals(orderPage.getOrderStatus(),"Order status: "+ status);
		
		log.info("Verify Order Date display");
		verifyTrue(orderPage.getOrderDate().contains(getCurrentDate("M/dd/yyyy","America/New_York")));
		
		log.info("Verify Order Total display");
		verifyTrue(orderPage.getOrderTotal().contains(subTotal));
		
		log.info("Click on Details link");
		orderPage.clickOnButton(driver, "Details");
		orderDetailPage = PageGeneratorManager.getUserOrderDetailPage(driver);
		
		log.info("Verify name display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "email"), "Email: " + User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Verify Phone display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "phone"), "Phone: " + phoneNumber);
		
		log.info("Verify Fax display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "fax"), "Fax: " + phoneNumber);
		
		log.info("Verify Address display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "address1"), address);
		
		log.info("Verify City, zipcode display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "city-state-zip"), city + "," + zipcode);
		
		log.info("Verify Country display correct in Billing Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Billing Address", "country"),country);
		
		log.info("Verify Payment method display correct in Payment");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Payment", "payment-method"),"Payment Method: " + paymentMethod1);
		
		log.info("Verify Payment status method display correct in Payment");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Payment", "payment-method-status"),"Payment Status: " + status);
		
		log.info("Verify name display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "email"), "Email: " + User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Verify Phone display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "phone"), "Phone: " + phoneNumber);
		
		log.info("Verify Fax display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "fax"), "Fax: " + phoneNumber);
		
		log.info("Verify Address display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "address1"), address);
		
		log.info("Verify City, zipcode display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "city-state-zip"), city + "," + zipcode);
		
		log.info("Verify Country display correct in Shipping Address");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping Address", "country"),country);
		
		log.info("Verify Shipping method display correct in Shipping");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping", "shipping-method"),"Shipping Method: " + rdName);
		
		log.info("Verify Shipping method display correct in Shipping");
		verifyEquals(orderDetailPage.getConfirmOrderInfo(driver,"Shipping", "shipping-status"),"Shipping Status: Not yet shipped");
		
		log.info("Verify SKU display correct");
		verifyTrue(orderDetailPage.getSKUInfo(driver).contains(sku));
		
		log.info("Verify product name display correct");
		verifyEquals(orderDetailPage.getProductNameInfo(driver),productName1);
		
		log.info("Verify Price display correct");
		verifyTrue(orderDetailPage.getPriceInfo(driver).contains(price));
		
		log.info("Verify Quality display correct");
		verifyTrue(orderDetailPage.getQualityInfo(driver).contains(quality));
		
		log.info("Get Total Price");
		total = String.valueOf(orderDetailPage.calculateSubTotal(quality,price));
		
		log.info("Verify Total display correct");
		verifyTrue(orderDetailPage.getTotalInfo(driver).contains(total));
		
		log.info("Verify Sub Total display correct");
		verifyTrue(orderDetailPage.getSubTotalInfo(driver).contains(total));
		
		log.info("Verify Order Total display correct");
		verifyTrue(orderDetailPage.getOrderTotalInfo(driver).contains(total));
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
		
}
