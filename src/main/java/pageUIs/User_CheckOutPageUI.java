package pageUIs;

public class User_CheckOutPageUI {
	public static final String CBB_NAME = "//label[contains(text(),'%s')]/following-sibling::select";
	public static final String BTN_CONTINUE = "//*[text()='%s']/parent::div/following-sibling::div//button[text()='Continue']";
	public static final String TXT_NAME = "//label[contains(text(),'%s')]/parent::td/following-sibling::td/input";
	public static final String BILLING_ADDRESS_CBB = "//select[@name='billing_address_id']";
}
