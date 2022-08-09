package pageUIs;

public class User_SubMenuPageUI {
	public static final String PRODUCT_TITLE = "//*[contains(@class,'product-title')]//*[text()='%s']";
	public static final String AJAX_LOADING = "//*[@class='ajax-products-busy' and @style='display: none;']";
	public static final String ICON_NEXT = "//*[@class='next-page']/a[contains(text(),'Next')]";
	public static final String ICON_PRE = "//*[@class='previous-page']/a[contains(text(),'Previous')]";
	public static final String PAGE_INACTIVE = "//*[@class='individual-page']/a[contains(text(),'%s')]";
	public static final String BUTTON_ICON="//*[text()='%s']/parent::h2/following-sibling::div//button[text()='%s']";
	public static final String PRICES  ="//*[text()='%s']/parent::h2/following-sibling::*[@class='add-info']//*[contains(@class,'actual-price')]";
}
