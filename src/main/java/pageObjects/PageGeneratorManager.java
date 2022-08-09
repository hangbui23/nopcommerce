package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static User_HomePageObject getUserHomePage(WebDriver driver) {
		return new User_HomePageObject(driver);
	}
	
	public static User_LogInPageObject getUserLogInPage(WebDriver driver) {
		return new User_LogInPageObject(driver);
	}
	
	public static User_RegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new User_RegisterPageObject(driver);
	}
	
	public static User_MyAccountPageObject getUserMyAccountPage(WebDriver driver) {
		return new User_MyAccountPageObject(driver);
	}
	
	public static User_AddressPageObject getUserAddressPage(WebDriver driver) {
		return new User_AddressPageObject(driver);
	}
	
	public static User_ChangePasswordPageObject getUserChangePassPage(WebDriver driver) {
		return new User_ChangePasswordPageObject(driver);
	}
	
	public static User_MyProductReviewsPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new User_MyProductReviewsPageObject(driver);
	}
	
	public static User_SubMenuPageObject getUserSubMenuPage(WebDriver driver) {
		return new User_SubMenuPageObject(driver);
	}
	
	public static User_DetailProductPageObject getUserDetailProductPage(WebDriver driver) {
		return new User_DetailProductPageObject(driver);
	}
	
	public static User_OwnReviewPageObject getUserOwnReviewPage(WebDriver driver) {
		return new User_OwnReviewPageObject(driver);
	}
	
	public static User_SearchPageObject getUserSearchPage(WebDriver driver) {
		return new User_SearchPageObject(driver);
	}
	
	public static WishlistPageObject getUserWishlistPage(WebDriver driver) {
		return new WishlistPageObject(driver);
	}
	
	public static User_AddToCartPageObject getUserAddToCartPage(WebDriver driver) {
		return new User_AddToCartPageObject(driver);
	}
	
	public static User_CompareProductPageObject getUserCompareProductPage(WebDriver driver) {
		return new User_CompareProductPageObject(driver);
	}
	
	public static User_CheckOutPageObject getUserCheckOutPage(WebDriver driver) {
		return new User_CheckOutPageObject(driver);
	}
	
	public static User_OrderPageObject getUserOrderPage(WebDriver driver) {
		return new User_OrderPageObject(driver);
	}
	
	public static User_OrderDetailPageObject getUserOrderDetailPage(WebDriver driver) {
		return new User_OrderDetailPageObject(driver);
	}
	
	public static Admin_LogInPageObject getAdminLogInPage(WebDriver driver) {
		return new Admin_LogInPageObject(driver);
	}
	
	public static Admin_HomePageObject getAdminHomePage(WebDriver driver) {
		return new Admin_HomePageObject(driver);
	}
	
	public static Admin_ProductPageObject getAdminProductPage(WebDriver driver) {
		return new Admin_ProductPageObject(driver);
	}
	
	public static Admin_ProductDetailPageObject getAdminProductDetailPage(WebDriver driver) {
		return new Admin_ProductDetailPageObject(driver);
	}
	
	public static Admin_CustomerPageObject getAdminCustomerPage(WebDriver driver) {
		return new Admin_CustomerPageObject(driver);
	}
	
	public static Admin_AddNewCustomerPageObject getAdminNewCustomerPage(WebDriver driver) {
		return new Admin_AddNewCustomerPageObject(driver);
	}
}
