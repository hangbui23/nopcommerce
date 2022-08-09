package com.nopcommerce.user.myaccount;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.nopcommerce.common.User_Common_RegisterAndLogInSuccess;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.User_AddressPageObject;
import pageObjects.User_ChangePasswordPageObject;
import pageObjects.User_SubMenuPageObject;
import pageObjects.User_DetailProductPageObject;
import pageObjects.User_HomePageObject;
import pageObjects.User_LogInPageObject;
import pageObjects.User_MyAccountPageObject;
import pageObjects.User_MyProductReviewsPageObject;
import pageObjects.User_OwnReviewPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.User_RegisterPageObject;
import utilitiesConfig.DataFacker;

public class TC_01_MyAccount extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
	User_LogInPageObject logInPage;
	User_MyAccountPageObject myAccountPage;
	User_AddressPageObject addressPage;
	User_ChangePasswordPageObject changePassPage;
	User_MyProductReviewsPageObject myProductReviewPage;
	User_SubMenuPageObject subMenuPage;
	User_DetailProductPageObject detailProductPage;
	User_OwnReviewPageObject ownReviewPage;
	DataFacker faker = DataFacker.getData();
	String gender,firstName,lastName,companyName, day,month,year,email,country,state,city,address1,address2,zipcode,phoneNumber,productName,title,text;
	public static String newPassword;
	
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
		homePage = PageGeneratorManager.getUserHomePage(driver);
		log.info("Click on My account link");
		homePage.clickOnHeaderLink(driver, "My account");
		myAccountPage = PageGeneratorManager.getUserMyAccountPage(driver);
		
		gender = faker.getGender();
		firstName = faker.getFirstName();
		lastName = faker.getLastName();
		companyName = faker.getCompanyName();
		email = faker.getEmail();
		city = faker.getCityName();
		address1 = faker.getAddress();
		address2 = faker.getAddress();
		zipcode = faker.getZipCode();
		phoneNumber = faker.getPhone();
		newPassword = faker.getPassword();
		day = randomDay();
		month = randomMonth();
		year = randomYear();
		country = "Angola";
		state = "Other";
		productName = "Build your own computer";
		title = "Review Build your own computer";
		text = "Text Build your own computer";
		}

	@Description("My Account - Edit Cutomer info")
	@Step("My Account - Edit Cutomer info")
	@Test
	public void TC01_MyAccount_EdiCustomerInfo(){
		log.info("Click on Customer info link");
		myAccountPage.clickOnNavigationLink("Customer info");
		
		log.info("Click on Gender radio button");
		myAccountPage.clickOnRadioButton(driver, gender);
		
		log.info("Enter value to  First name textbox");
		myAccountPage.enterValueToTextbox(driver, "First name", firstName);
		
		log.info("Enter value Last name textbox");
		myAccountPage.enterValueToTextbox(driver, "Last name", lastName);
		
		log.info("Select value in Day dropdown");
		myAccountPage.selectCombobox(driver, "Day",day);
		
		log.info("Select value in Month dropdown");
		myAccountPage.selectCombobox(driver, "Month",month);
		
		log.info("Select value in Year dropdown");
		myAccountPage.selectCombobox(driver, "Year",year);
		
		log.info("Enter value company name textbox");
		myAccountPage.enterValueToTextbox(driver, "Company name", companyName);
		
		log.info("Click on Save text box");
		myAccountPage.clickOnButton(driver, "Save");
		
		log.info("Verify Gender display correct");
		verifyTrue(myAccountPage.isRadioSelected(driver, gender));
		
		
		log.info("Verify First name display correct");
		verifyEquals(myAccountPage.getAttributeOnTextBox(driver, "First name","value"),firstName);
		
		System.out.println("First Name: " + lastName);
		log.info("Verify Last name display correct");
		verifyEquals(myAccountPage.getAttributeOnTextBox(driver, "Last name","value"),lastName);
		
		log.info("Verify Day display correct");
		verifyEquals(myAccountPage.getSelectedIntemInDropDown(driver, "Day"), day);
		
		log.info("Verify Month display correct");
		verifyEquals(myAccountPage.getSelectedIntemInDropDown(driver, "Month"), month);
		
		log.info("Verify Year display correct");
		verifyEquals(myAccountPage.getSelectedIntemInDropDown(driver, "Year"), year);
		
		log.info("Verify Company name display correct");
		verifyEquals(myAccountPage.getAttributeOnTextBox(driver, "Company name","value"),companyName);
	}
	
	@Description("My Account - Add Address Info")
	@Step("My Account - Add Address Info")
	@Test
	public void TC02_MyAccount_AddAddressInfo(){
		log.info("Click on Addresses link");
		myAccountPage.clickOnNavigationLink("Addresses");
		addressPage =  PageGeneratorManager.getUserAddressPage(driver);
		
		log.info("Click on Add New button");
		addressPage.clickOnButton(driver, "Add new");
		
		log.info("Enter value First name textbox");
		addressPage.enterValueToTextbox(driver, "First name", firstName);
		
		log.info("Enter value Last name textbox");
		addressPage.enterValueToTextbox(driver, "Last name", lastName);
		
		log.info("Enter value Email textbox");
		addressPage.enterValueToTextbox(driver, "Email", email);
		
		log.info("Enter value Company textbox");
		addressPage.enterValueToTextbox(driver, "Company", companyName);
		
		log.info("Enter value Country combobox");
		addressPage.selectCombobox(driver, "Country", country);
		
		log.info("Enter value State combobox");
		addressPage.selectCombobox(driver, "State", state);
		
		log.info("Enter value City textbox");
		addressPage.enterValueToTextbox(driver, "City", city);
		
		log.info("Enter value Address1 textbox");
		addressPage.enterValueToTextbox(driver, "Address 1", address1);
		
		log.info("Enter value Address2 textbox");
		addressPage.enterValueToTextbox(driver, "Address 2", address2);
		
		log.info("Enter value Zipcode textbox");
		addressPage.enterValueToTextbox(driver, "Zip / postal code", zipcode);
		
		log.info("Enter value Phone number textbox");
		addressPage.enterValueToTextbox(driver, "Phone number", phoneNumber);
		
		log.info("Enter value Fax number textbox");
		addressPage.enterValueToTextbox(driver, "Fax number", phoneNumber);
		
		log.info("Click on Save button");
		addressPage.clickOnButton(driver, "Save");
		
		System.out.println("First Name: " + firstName);
		System.out.println("First Name: " + lastName);
		log.info("Verify First name, Last Name display correct");
		verifyEquals(addressPage.getTextBaseOnName("name"), firstName + " " + lastName);
		
		log.info("Verify Email display correct");
		verifyTrue(addressPage.getTextBaseOnName("email").contains(email));
		
		log.info("Verify Phone Number display correct");
		verifyTrue(addressPage.getTextBaseOnName("phone").contains(phoneNumber));
		
		log.info("Verify Fax Number display correct");
		verifyTrue(addressPage.getTextBaseOnName("fax").contains(phoneNumber));
		
		log.info("Verify Company display correct");
		verifyTrue(addressPage.getTextBaseOnName("company").contains(companyName));
		
		log.info("Verify Address 1 display correct");
		verifyTrue(addressPage.getTextBaseOnName("address1").contains(address1));
		
		log.info("Verify City Zip display correct");
		verifyTrue(addressPage.getTextBaseOnName("city-state-zip").contains(city + ", " + zipcode));
		
		log.info("Verify City Zip display correct");
		verifyTrue(addressPage.getTextBaseOnName("country").contains(country));
	}
	
	
	@Description("My Account - Change Password")
	@Step("My Account - Change Password")
	@Test
	public void TC03_MyAccount_ChangePassword(){
		log.info("Click on Change password link");
		myAccountPage.clickOnNavigationLink("Change password");
		changePassPage =  PageGeneratorManager.getUserChangePassPage(driver);
		
		log.info("Enter value to password");
		changePassPage.enterValueToTextbox(driver, "Old password", User_Common_RegisterAndLogInSuccess.password);
		
		log.info("Change password");
		changePassPage.enterValueToTextbox(driver, "New password", newPassword);
		
		log.info("Confirm Change password");
		changePassPage.enterValueToTextbox(driver, "Confirm password", newPassword);
		
		log.info("Click on Change password button");
		changePassPage.clickOnButton(driver, "Change password");
		
		log.info("Verify the success notification display");
		verifyEquals(changePassPage.getNotificationMessageSuccess(driver),"Password was changed");
		
		log.info("Click On Close button");
		changePassPage.clickOnCloseButton(driver);
		
		log.info("Click on Log out link");
		changePassPage.clickOnHeaderLink(driver, "Log out");
		
		log.info("Click on Log in link");
		changePassPage.clickOnHeaderLink(driver, "Log in");
		logInPage = PageGeneratorManager.getUserLogInPage(driver);
		
		log.info("Enter value to email");
		logInPage.enterValueToTextbox(driver, "Email", User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Enter value to password");
		logInPage.enterValueToTextbox(driver, "Password", User_Common_RegisterAndLogInSuccess.password);
		
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		
		log.info("Get error message on Email text box");
		verifyTrue(logInPage.getErrorMessageOnHeader(driver).contains("Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(logInPage.getErrorMessageOnHeader(driver).contains("The credentials provided are incorrect"));	
		
		log.info("Enter value to email");
		logInPage.enterValueToTextbox(driver, "Email", User_Common_RegisterAndLogInSuccess.email);
		
		log.info("Enter value to password");
		logInPage.enterValueToTextbox(driver, "Password", newPassword);
		
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		
		log.info("Verify My Account link display");
		verifyTrue(homePage.isHeaderLinkDisplay(driver, "My account"));
		
		log.info("Verify Log out link display");
		verifyTrue(homePage.isHeaderLinkDisplay(driver, "Log out"));
	}
	
	@Description("My Account - My Product view")
	@Step("My Account - My Product View")
	@Test
	public void TC04_MyAccount_MyProductView(){
		log.info("Click on Computers>Desktops");
		myAccountPage.selectTopMenu(driver,"Computers>Desktops");
		subMenuPage = PageGeneratorManager.getUserSubMenuPage(driver);
		
		log.info("Click on product name");
		subMenuPage.clickOnProductTitle(productName);
		detailProductPage = PageGeneratorManager.getUserDetailProductPage(driver);
		
		log.info("Click on Preview link");
		detailProductPage.clickOnPreviewLink();
		ownReviewPage = PageGeneratorManager.getUserOwnReviewPage(driver);
		
		log.info("Enter value to title");
		ownReviewPage.enterValueToTextbox(driver, "Review title", title);
		
		log.info("Enter value to text");
		ownReviewPage.enterValueToTextArea(driver, "Review text", text);
		
		log.info("Enter value to text");
		ownReviewPage.clickOnButton(driver, "Submit review");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Click on My account link");
		homePage.clickOnHeaderLink(driver, "My account");
		
		log.info("Click on My product reviews link");
		myAccountPage.clickOnNavigationLink("My product reviews");
		myProductReviewPage =  PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		log.info("Verify Product preview link display");
		verifyTrue(myProductReviewPage.isPreviewLinkDisplayInMyProductReview(productName));
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
}