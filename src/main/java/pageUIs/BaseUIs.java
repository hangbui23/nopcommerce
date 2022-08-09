package pageUIs;

public class BaseUIs {
	public static final String HEADER_LINK = "//*[@class='header-links']//*[text()='%s']";
	public static final String BTN_NAME = "//button[contains(.,'%s')]";
	public static final String TXT_NAME = "//label[contains(text(),'%s')]/following-sibling::input";
	public static final String TEXTAREA_NAME = "//label[contains(text(),'%s')]/following-sibling::textarea";
	public static final String RD_NAME = "//label[contains(text(),'%s')]/preceding-sibling::input";
	public static final String CB_NAME = "//select[contains(@name,'%s')]";
	public static final String CHK_NAME = "//label[contains(text(),'%s')]/following-sibling::input";
	public static final String MESSAGE_ERROR = "//label[contains(text(),'%s')]/following-sibling::span[@class='field-validation-error']";
	public static final String MESSAGE_ERROR_HEADER = "//div[contains(@class,'message-error')]";
	public static final String FOOTER_LINK = "//*[@class='footer']//*[text()='%s']";
	public static final String TOP_MENU = "//*[contains(@class,'menu notmobile')]//*[contains(text(),'%s')]";
	public static final String SUB_TOP_MENU = "//*[contains(@class,'notmobile')]//*[contains(@class,'first-level')]//*[contains(text(),'%s')]";
	public static final String LIST_PRODUC_TITLE = "//*[@class='product-title']/*";
	public static final String PRODUC_TITLE_NAME = "//*[@class='product-title']/*[text()='%s']";
	public static final String PRODUC_PRICE = "//*[@class='prices']/*";
	public static final String COLUMN_INDEX = "//th[contains(text(),'%s')]/preceding-sibling::th";
	public static final String POSITION_TEXT = "//tr[%s]//td[%s]";
	public static final String NOTI_SUCCESS ="//*[contains(@class,'notification success')]";
	public static final String BTN_CLOSE = "//*[contains(@class,'close')]";
	public static final String NO_DATA = "//*[@class='no-data']";
	public static final String RD_CHK_NAME = "//*[text()='%s']/preceding-sibling::input";
	public static final String COMFIRM_ORDER = "//*[text()='%s']/parent::div/following-sibling::*/*[@class='%s']";
	public static final String TABLE_VALUE ="//tbody//*[@class='%s']";
	public static final String NAV_LINK_ADMIN_PARENT = "(//*[contains(@class,'nav-sidebar')]//p[contains(.,'%s')])[1]";
	public static final String NAV_LINK_ADMIN_CHILD = "(//*[contains(@class,'nav-treeview')]//p[contains(.,'%s')])[1]";
	public static final String TXT_BY_ID = "//input[@id='%s']";
	public static final String STATUS_NAV_LINK_ADMIN = "(//p[contains(.,'%s')]/ancestor::li)[1]";
	public static final String CHK_BY_ID_ADMIN="//input[@id='%s']";
	public static final String EMPTY_DATA_TABLE = "//*[@class='dataTables_empty']";
	public static final String PARENT_CUSTOMER_ROLE = "//*[@id='SelectedCustomerRoleIds_taglist']/parent::div";
	public static final String CHILD_CUSTOMER_ROLE = "//ul[@id='SelectedCustomerRoleIds_listbox']//li[contains(@class,'k-item')]";
	public static final String EXPECTED_CHILD_CUSTOMER_ROLE = "//ul[@id='SelectedCustomerRoleIds_listbox']//li[contains(.,'%s')]";
}
