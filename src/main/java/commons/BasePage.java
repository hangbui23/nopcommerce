package commons;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import pageUIs.BaseUIs;
import pageUIs.User_DetailProductPageUI;

import java.io.File;
import java.io.IOException;

public class BasePage {
	
	WebDriverWait  explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver);
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void getTextAlert(WebDriver driver) {
		driver.switchTo().alert().getText();
	}

	public void senKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public By ByXpath(String locator) {
		return By.xpath(locator);
	}
	
//	public By ByXpath(String locator,String...values) {
//		return By.xpath(getDynamicLocator(locator,values));
//	}
	
	public WebElement findWebElement(WebDriver driver, String locator) {
		return driver.findElement(ByXpath(locator));
	}
	
	public WebElement findWebElement(WebDriver driver, String locator,String...values) {
		return driver.findElement(ByXpath(getDynamicLocator(locator,values)));
	}

	public String getDynamicLocator(String locator, String... values) {
		locator =  String.format(locator, (Object[])values);
		return locator;
				
	}
	
	public List<WebElement> findListWebElement(WebDriver driver, String locator) {
		return driver.findElements(ByXpath(locator));
	}
	
	public List<WebElement> findListWebElement(WebDriver driver, String locator, String...values) {
		System.out.println(ByXpath(getDynamicLocator(locator,values)));
		return driver.findElements(ByXpath(getDynamicLocator(locator,values)));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		if(driver.toString().contains("Edge")) {
			sleepInMiliSecond(500);
		}
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(3);
		}else {
		findWebElement(driver,locator).click();
		}
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		WebElement element = findWebElement(driver,locator);
		if(element.isEnabled()){
		return true;
		}
		else{
		return false;
		}
	}
	
	public void removeDisabledAttributeByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver,locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",element);
	}
	
	
	public void clickToElement(WebDriver driver, String locator, String...values) {
		if(driver.toString().contains("Edge")) {
			sleepInMiliSecond(500);
		}
		findWebElement(driver,getDynamicLocator(locator,values)).click();
	}
	
	public void clickToElement(WebElement element) {
		element.click();
	}
	
	public String getUrlByUserNameAndPassword(String url, String userName, String password){
		String[] spliUrl= url.split("//");
		url= spliUrl[0] + "//" + userName + ":" + password + "@" + spliUrl[1];
		return url;
	}
	
	public void executeScriptAlert(WebDriver driver,String url, String userName, String password) throws IOException{
		String firefoxAuthen = projectPath + "\\autoITScript\\authen_firefox.exe";
		String chromeAuthen = projectPath + "\\autoITScript\\authen_chrome.exe";
		
		if(driver.toString().contains("firefox")){
			Runtime.getRuntime().exec(new String[]{firefoxAuthen,userName,password});
		}
		if(driver.toString().contains("chrome")){
			Runtime.getRuntime().exec(new String[]{chromeAuthen,userName,password});
		}
		driver.get(url);
	}
	
	public void senKeyToElement(WebDriver driver, String locator, String value) {
		highlightElement(driver, locator);
		findWebElement(driver,locator).clear();
		findWebElement(driver,locator).sendKeys(value);
	}
	
	public void senKeyToElement(WebDriver driver, String locator, String value, String... values) {
		highlightElement(driver, getDynamicLocator(locator, values));
		findWebElement(driver,getDynamicLocator(locator,values)).clear();
		findWebElement(driver,getDynamicLocator(locator,values)).sendKeys(value);
	}
	
	public void selectItemInDropDown(WebDriver driver, String locator, String value) {
		Select element = new Select(findWebElement(driver,locator));
		highlightElement(driver, locator);
		element.selectByVisibleText(value);
	}
	
	public void selectItemInDropDown(WebDriver driver, String locator, String value, String...values) {
		Select element = new Select(findWebElement(driver,getDynamicLocator(locator,values)));
		element.selectByVisibleText(value);
	}
	
	public String getSelectedItemInDropDown(WebDriver driver, String locator) {
		Select element = new Select(findWebElement(driver,locator));
		return element.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemInDropDown(WebDriver driver, String locator, String...values) {
		Select element = new Select(findWebElement(driver,getDynamicLocator(locator,values)));
		return element.getFirstSelectedOption().getText();
	}
	
	public boolean isDropDownMultiple(WebDriver driver, String locator) {
		Select element = new Select(findWebElement(driver,locator));
		return element.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		clickToElement(driver,parentLocator);
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, 20);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByXpath(childItemLocator)));
		List<WebElement> allItems = findListWebElement(driver, childItemLocator);
		for (WebElement item : allItems) {
			System.out.println("List item" + item.getText());
			if (item.getText().equals(expectedItem)) {
//				jsExecutor = (JavascriptExecutor) driver;
//				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
//				sleepInSecond(1);
				clickToElement(item);
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem, String...values) {
		clickToElement(driver,parentLocator);
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, 20);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByXpath(childItemLocator)));
		List<WebElement> allItems = findListWebElement(driver, childItemLocator);
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				clickToElement(item);
				sleepInSecond(1);
				break;
			}
		}
	}
  
	public String getHiddenText(WebDriver driver,String cssLocator){
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector(" +cssLocator+ ").textContent");
	}
	
	public void selectTheItemInEditableDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItem){
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait= new WebDriverWait(driver,30);
		driver.findElement(By.xpath(parentXpath)).clear();
		sleepInSecond(1);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByXpath(childXpath)));
		List<WebElement> childItems = findListWebElement(driver, childXpath);
		for(WebElement actualItem:childItems){
			if(actualItem.getText().trim().equals(expectedItem)){
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);",actualItem);
				sleepInSecond(2);
				actualItem.click();
				break;
			}
		}
	}
	
	
	public void selectMultiItemInDropdown(WebDriver driver, String parentXpath, String childXpath, String[] expectedValueItem) {
		jsExecutor = (JavascriptExecutor) driver;
		// 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();

		// 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

		// Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {
			// "January", "April", "July"
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					// 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);

					// 4: click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}
	
	public boolean areItemSelected(WebDriver driver, String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
	}
	
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getAttributeElement(WebDriver driver, String locator, String attribute) {
		return findWebElement(driver, locator).getAttribute(attribute);
	}
	
	public String getAttributeElement(WebDriver driver, String locator, String attribute, String...values) {
		return findWebElement(driver, getDynamicLocator(locator,values)).getAttribute(attribute);
	}
	
	public String getTextElement(WebDriver driver, String locator) {
		return findWebElement(driver, locator).getText().trim();
	}
	
	public String getTextElement(WebDriver driver, String locator, String...values) {
		return findWebElement(driver, getDynamicLocator(locator,values)).getText().trim();
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return findListWebElement(driver, locator).size();
	}
	
	public int getElementSize(WebDriver driver, String locator, String...values) {
		return findListWebElement(driver, getDynamicLocator(locator,values)).size();
	}
	
	public void checkTheRadioOrCheckbox(WebDriver driver, String locator) {
		WebElement element = findWebElement(driver, locator);
		if(!element.isSelected()) {
			element.click();
		};
	}
	
	public void checkTheRadioOrCheckbox(WebDriver driver, String locator, String... values) {
		WebElement element = findWebElement(driver,getDynamicLocator(locator,values));
		if(!element.isSelected()) {
			element.click();
		};
	}
	
	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		WebElement element = findWebElement(driver, locator);
		if(element.isSelected()) {
			element.click();
		};
	}
	
	public void uncheckTheCheckbox(WebDriver driver, String locator, String...values) {
		WebElement element = findWebElement(driver, getDynamicLocator(locator,values));
		if(element.isSelected()) {
			element.click();
		};
	}
	
	public boolean isElementDisplay(WebDriver driver, String locator) {
		return findWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplay(WebDriver driver, String locator, String...values) {
		return findWebElement(driver, getDynamicLocator(locator,values)).isDisplayed();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String...values) {
		return findWebElement(driver, getDynamicLocator(locator,values)).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, WebElement ele) {
		return ele.isSelected();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator, String...values) {
		return findWebElement(driver, getDynamicLocator(locator,values)).isEnabled();
	}
	
	public void switchToIFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(findWebElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void doubleClickElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick().perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findWebElement(driver, locator)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator, String...values) {
		action = new Actions(driver);
		action.moveToElement(findWebElement(driver, getDynamicLocator(locator,values))).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findWebElement(driver, locator)).perform();
	}
	
	public void dragDrop(WebDriver driver, String locatorSource, String locatorTarget) {
		action = new Actions(driver);
		action.dragAndDrop(findWebElement(driver, locatorSource), findWebElement(driver, locatorTarget)).perform();
	}
	
	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findWebElement(driver, locator), key).perform();
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void navigateToUrl(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollToElementOnTop(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", element);
	}
	
	public void scrollToElement(WebDriver driver, String locator, String...values) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, getDynamicLocator(locator,values));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active === 0);");
				} 
			};
			return explicitWait.until(jQueryLoad);
	}
	
					
	public boolean isJQueryAndAjaxIconLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return $('.raDiv').is(':visible')").toString().equals("false");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(ajaxIconLoading);
	}
	
	
	public boolean isJQueryAndPageLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator) {
		WebElement element = findWebElement(driver, locator);
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete&&typeof arguments[0].naturalWidth!=\"undefined\"&& arguments[0].naturalWidth>0", element);
		return status;
	} 
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ByXpath(locator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String...values) {
	explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ByXpath(getDynamicLocator(locator,values))));
	}
	
	public void waitForListElementVisible(WebDriver driver, String locator, String...values) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByXpath(getDynamicLocator(locator,values))));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(ByXpath(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String...values) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(ByXpath(getDynamicLocator(locator,values))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(ByXpath(locator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator, String...values) {
		explicitWait = new WebDriverWait(driver, GlobalContants.SHORT_TIMEOUT);
		overRideImplicitWait(driver,GlobalContants.SHORT_TIMEOUT);
		System.out.println("Start Date " + new Date().toString());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(ByXpath(getDynamicLocator(locator,values))));
		System.out.println("End Date " + new Date().toString());
		overRideImplicitWait(driver,GlobalContants.LONG_TIMEOUT);
	}
	
	 public String covertRgbaToHex(String rgbaValue) {
		 return Color.fromString(rgbaValue).asHex();
	 }
	 
	 public void clickOnHeaderLink(WebDriver driver, String linkName) {
		 waitForElementVisible(driver, BaseUIs.HEADER_LINK, linkName);
		 clickToElement(driver, BaseUIs.HEADER_LINK, linkName);
	 }
	 
	 public void clickOnFooterLink(WebDriver driver, String linkName) {
		 waitForElementVisible(driver, BaseUIs.FOOTER_LINK, linkName);
		 clickToElement(driver, BaseUIs.FOOTER_LINK, linkName);
	 }
	 
	 public boolean isHeaderLinkDisplay(WebDriver driver, String linkName) {
		 waitForElementVisible(driver, BaseUIs.HEADER_LINK, linkName);
		 return isElementDisplay(driver, BaseUIs.HEADER_LINK, linkName);
	 }
	 
	 public void enterValueToTextbox(WebDriver driver, String textboxName, String value) {
		 waitForElementVisible(driver, BaseUIs.TXT_NAME, textboxName);
		senKeyToElement(driver, BaseUIs.TXT_NAME,value,textboxName);
	 }
	 
	 public void enterValueToTextArea(WebDriver driver, String textAreaName, String value) {
		 waitForElementVisible(driver, BaseUIs.TEXTAREA_NAME, textAreaName);
		senKeyToElement(driver, BaseUIs.TEXTAREA_NAME,value,textAreaName);
	 }
	 
	 public void clickOnButton(WebDriver driver, String buttonName) {
		 waitForElementClickable(driver, BaseUIs.BTN_NAME, buttonName);
		 clickToElement(driver, BaseUIs.BTN_NAME, buttonName);
	 }
	 
	 public void clickOnRadioButton(WebDriver driver, String radioButtonName) {
		 waitForElementVisible(driver, BaseUIs.RD_NAME, radioButtonName);
		 clickToElement(driver, BaseUIs.RD_NAME, radioButtonName);
	 }
 
	 public String getTextOfMessageError(WebDriver driver, String fieldName) {
		 return getTextElement(driver, BaseUIs.MESSAGE_ERROR, fieldName);
	 }
	 
	public String getAttributeOnTextBox(WebDriver driver, String textboxName, String value) {
			return getAttributeElement(driver, BaseUIs.TXT_NAME,value,textboxName);
	}
	
	public boolean isRadioSelected(WebDriver driver, String radioName) {
		return isElementSelected(driver, BaseUIs.RD_NAME, radioName);
	}
	
	public String getSelectedIntemInDropDown(WebDriver driver, String cbbName) {
		waitForElementVisible(driver, BaseUIs.CB_NAME,cbbName);
		return getSelectedItemInDropDown(driver, BaseUIs.CB_NAME,cbbName);
	}
	
	
	public void selectCombobox(WebDriver driver,String cbbName, String value) {
		waitForElementVisible(driver, BaseUIs.CB_NAME,cbbName);
		selectItemInDropDown(driver, BaseUIs.CB_NAME, value, cbbName);
	}
	
	public String getErrorMessageOnHeader(WebDriver driver) {
		return getTextElement(driver,BaseUIs.MESSAGE_ERROR_HEADER);
	}
	
	
	public void selectTopMenu(WebDriver driver, String menu) {
		String[] item = menu.split(">");
		if(String.valueOf(item.length).equals("2")) {
			hoverMouseToElement(driver, BaseUIs.TOP_MENU, item[0]);		
			hoverMouseToElement(driver,BaseUIs.SUB_TOP_MENU,item[1]);
			clickToElement(driver, BaseUIs.SUB_TOP_MENU, item[1]);
		}else if(String.valueOf(item.length).equals("3")) {
			clickToElement(driver, BaseUIs.TOP_MENU, item[0]);
			hoverMouseToElement(driver,BaseUIs.SUB_TOP_MENU,item[1]);
			hoverMouseToElement(driver,BaseUIs.SUB_TOP_MENU,item[2]);
			clickToElement(driver, BaseUIs.SUB_TOP_MENU, item[3]);
		}
	}
	
	public void hoverMouseToHeaderLink(WebDriver driver, String headerLink) {
		waitForElementVisible(driver, BaseUIs.HEADER_LINK, headerLink);
		hoverMouseToElement(driver, BaseUIs.HEADER_LINK, headerLink);
	}
	
	public String getTextBasedOnColumAndRow(WebDriver driver, String columName, String rowIndex) {
		int columIndex = getElementSize(driver, BaseUIs.COLUMN_INDEX, columName) + 1;
		System.out.println("Text " + getTextElement(driver, BaseUIs.POSITION_TEXT, rowIndex,String.valueOf(columIndex)));
		return getTextElement(driver, BaseUIs.POSITION_TEXT, rowIndex,String.valueOf(columIndex));
	}
	
	public void clickOnRadioCheckboxName(WebDriver driver, String chkName) {
		waitForElementVisible(driver, BaseUIs.RD_CHK_NAME, chkName);
		checkTheRadioOrCheckbox(driver, BaseUIs.RD_CHK_NAME, chkName);
		waitForElementInvisible(driver, User_DetailProductPageUI.AJAX_LOADING);
	}
	
	public void uncheckOnCheckbox(WebDriver driver, String chkName) {
		waitForElementVisible(driver, BaseUIs.RD_CHK_NAME, chkName);
		uncheckTheCheckbox(driver,BaseUIs.RD_CHK_NAME, chkName);
	}
	
	public String getDirectorySlash(String folderName){
	String separator = File.separator;
	return separator + folderName + separator;
	}
	
	public void sleepInSecond(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sleepInMiliSecond(int milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isDataTextSortASC(WebDriver driver,String locator) {
		List<String> arrList = new ArrayList<String>();
		List<WebElement> lst_ele = findListWebElement(driver,locator);
		//Add text to list
		for (WebElement webElement : lst_ele) {
			arrList.add(webElement.getText());
		}
		
		System.out.println("======Du lieu Sort ASC trên UI======");
		for(String text: arrList) {
			System.out.println(text);
		}
		
		//Create another list to sort
		List<String> isSorted = new ArrayList<String>();
		for(String text : arrList) {
			isSorted.add(text);
		}
		
		System.out.println("======Du lieu Sort ASC sau sort======");
		for(String name: isSorted) {
			System.out.println(name);
		}
		
		//Sort ASC
		Collections.sort(isSorted);
		return arrList.equals(isSorted);
	}
	
	public boolean isDataFloatSortASC(WebDriver driver,String locator) {
		List<Float> arrList = new ArrayList<Float>();
		List<WebElement> lst_ele = findListWebElement(driver,locator);
		//Add text to list
		for (WebElement webElement : lst_ele) {
			//arrList.add(Float.parseFloat(webElement.getText().replace("$", "").trim()));
			arrList.add(Float.parseFloat(webElement.getText().replace("$", "").replace(".", "").replace(",", "").trim()));
		}
		
		System.out.println("======Du lieu Sort ASC trên UI======");
		for(Float name: arrList) {
			System.out.println(name);
		}
		
		//Create another list to sort
		List<Float> isSorted = new ArrayList<Float>();
		for(Float number : arrList) {
			isSorted.add(number);
		}
		
		//Sort ASC
		Collections.sort(isSorted);
		
		System.out.println("======Du lieu Sort ASC sau sort======");
		for(Float name: isSorted) {
			System.out.println(name);
		}
		
		return arrList.equals(isSorted);
	}
	
	public boolean isDataDateSortASC(WebDriver driver,String locator) {
		List<Date> arrList = new ArrayList<Date>();
		List<WebElement> lst_ele = findListWebElement(driver,locator);
		//Add text to list
		for (WebElement webElement : lst_ele) {
			arrList.add(convertStringToDate(webElement.getText()));
		}
		
		//Create another list to sort
		List<Date> isSorted = new ArrayList<Date>();
		for(Date date : arrList) {
			isSorted.add(date);
		}
		
		//Sort ASC
		Collections.sort(isSorted);
		return arrList.equals(isSorted);
	}
	
	public Date convertStringToDate(String date) {
		date = date.replace(",", "");
		Date formatDate=null;
		try {
			formatDate = new SimpleDateFormat("MMM dd yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatDate;
	}
	public boolean isDataTextSortDESC(WebDriver driver,String locator) {
		List<String> arrList = new ArrayList<String>();
		List<WebElement> lst_ele = findListWebElement(driver,locator);
		//Add text to list
		for (WebElement webElement : lst_ele) {
			arrList.add(webElement.getText());
		}
		
		System.out.println("======Du lieu Sort DESC trên UI======");
		for(String text: arrList) {
			System.out.println(text);
		}
		
		//Create another list to sort
		List<String> isSorted = new ArrayList<String>();
		for(String text : arrList) {
			isSorted.add(text);
		}
		
		//Sort ASC
		Collections.sort(isSorted);
		
		//Sort DESC
		Collections.reverse(isSorted);
		
		System.out.println("======Du lieu Sort DESC sau sort======");
		for(String name: isSorted) {
			System.out.println(name);
		}
		
		return arrList.equals(isSorted);
	}
	
	public boolean isDataFloatSortDESC(WebDriver driver,String locator) {
		List<Float> arrList = new ArrayList<Float>();
		List<WebElement> lst_ele = findListWebElement(driver,locator);
		//Add text to list
		for (WebElement webElement : lst_ele) {
			//arrList.add(Float.parseFloat(webElement.getText().replace("$", "").trim()));
			arrList.add(Float.parseFloat(webElement.getText().replace("$", "").replace(".", "").replace(",", "").trim()));
		}
		
		System.out.println("======Du lieu Sort DESC trên UI======");
		for(Float price: arrList) {
			System.out.println(price);
		}
		
		//Create another list to sort
		List<Float> isSorted = new ArrayList<Float>();
		for(Float number : arrList) {
			isSorted.add(number);
		}
		
		//Sort ASC
		Collections.sort(isSorted);
		
		//Sort DESC
		Collections.reverse(isSorted);
		
		
		System.out.println("======Du lieu Sort DESC sau sort======");
		for(Float name: isSorted) {
			System.out.println(name);
		}
		
		return arrList.equals(isSorted);
	}
	
	public boolean isDataDateSortDESC(WebDriver driver,String locator) {
		List<Date> arrList = new ArrayList<Date>();
		List<WebElement> lst_ele = findListWebElement(driver,locator);
		//Add text to list
		for (WebElement webElement : lst_ele) {
			arrList.add(convertStringToDate(webElement.getText()));
		}
		
		//Create another list to sort
		List<Date> isSorted = new ArrayList<Date>();
		for(Date date : arrList) {
			isSorted.add(date);
		}
		
		//Sort ASC
		Collections.sort(isSorted);
		
		//Sort DESC
		Collections.reverse(isSorted);
		
		return arrList.equals(isSorted);
	}
	
	
	public boolean isElementUndisplay(WebDriver driver, String locator) {
		List<WebElement> elements = findListWebElement(driver, locator);
		if(elements.size()==0) {
			return true;
		}
		else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isInfoBasedOnColumAndRow(WebDriver driver, String columName, String rowIndex) {
		boolean status=false;
		overRideImplicitWait(driver, GlobalContants.SHORT_TIMEOUT);
		int columIndex = getElementSize(driver, BaseUIs.COLUMN_INDEX, columName) + 1;
		if(columIndex>1 && isElementDisplay(driver, BaseUIs.POSITION_TEXT, rowIndex,String.valueOf(columIndex))) {
			status = true;
		}
		else {
			status= false;
		}
		overRideImplicitWait(driver, GlobalContants.LONG_TIMEOUT);
		return status;
	}
	
	public boolean isElementUndisplay(WebDriver driver, String locator, String...values) {
		overRideImplicitWait(driver, GlobalContants.SHORT_TIMEOUT);
		System.out.println("Start Date " + new Date().toString());
		List<WebElement> elements = findListWebElement(driver, getDynamicLocator(locator,values));
		overRideImplicitWait(driver, GlobalContants.LONG_TIMEOUT);
		if(elements.size()==0) {
			System.out.println("Element not in DOM and UI");
			System.out.println("End Date " + new Date().toString());
			return true;
		}
		else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM and not in UI");
			System.out.println("End Date " + new Date().toString());
			return true;
		}
		else {
			System.out.println("End Date " + new Date().toString());
			return false;
		}
	}
	
	public String getNoResult(WebDriver driver) {
		return getTextElement(driver, BaseUIs.NO_DATA);
	}
	
	public String getNotificationMessageSuccess(WebDriver driver) {
		return getTextElement(driver, BaseUIs.NOTI_SUCCESS);
	}
	
	public void clickOnCloseButton(WebDriver driver) {
		sleepInSecond(1);
		clickToElement(driver, BaseUIs.BTN_CLOSE);
	}
	
	public Float calculateSubTotal(String quality, String price) {
		String value = price.replace("$", "").replace(",", "");
		float totalPrice = Float.parseFloat(value) *Float.parseFloat(quality);
		return totalPrice;
	}
	
	public String getConfirmOrderInfo(WebDriver driver, String titleName, String value) {
		waitForElementVisible(driver, BaseUIs.COMFIRM_ORDER,titleName,value);
		System.out.println("Text " + getTextElement(driver, BaseUIs.COMFIRM_ORDER,titleName,value));
		return getTextElement(driver, BaseUIs.COMFIRM_ORDER,titleName,value);
	}
	
	public String getSKUInfo(WebDriver driver) {
		waitForElementVisible(driver, BaseUIs.TABLE_VALUE,"sku");
		return getTextElement(driver, BaseUIs.TABLE_VALUE,"sku");
	}
	
	public String getProductNameInfo(WebDriver driver) {
		waitForElementVisible(driver, BaseUIs.TABLE_VALUE,"product");
		return getTextElement(driver, BaseUIs.TABLE_VALUE,"product");
	}
	
	public String getPriceInfo(WebDriver driver) {
		waitForElementVisible(driver, BaseUIs.TABLE_VALUE,"unit-price");
		return getTextElement(driver, BaseUIs.TABLE_VALUE,"unit-price");
	}
	
	public String getQualityInfo(WebDriver driver) {
		waitForElementVisible(driver, BaseUIs.TABLE_VALUE,"quantity");
		return getTextElement(driver, BaseUIs.TABLE_VALUE,"quantity");
	}
	
	public String getSubTotalInfo(WebDriver driver) {
		waitForElementVisible(driver, BaseUIs.TABLE_VALUE,"subtotal");
		String value = getTextElement(driver, BaseUIs.TABLE_VALUE,"subtotal");
		value = value.replace(",", "").replace("$", "");
		return value;
	}
	
	public void clickOnNavLinkOnAdmin(WebDriver driver, String navLink) {
		String[] item = navLink.split(">");
		waitForElementClickable(driver, BaseUIs.NAV_LINK_ADMIN_PARENT, item[0]);
		String attribute = getAttributeElement(driver, BaseUIs.STATUS_NAV_LINK_ADMIN, "class",item[0]);
		if(!attribute.contains("menu-open")) {
		clickToElement(driver, BaseUIs.NAV_LINK_ADMIN_PARENT, item[0]);
		}
		for(int i = 1; i<item.length;i++) {
			waitForElementClickable(driver, BaseUIs.NAV_LINK_ADMIN_CHILD, item[i]);
			clickToElement(driver, BaseUIs.NAV_LINK_ADMIN_CHILD, item[i]);
		}
	}
	
	public void enterValueToTextboxById(WebDriver driver,String id, String value) {
		waitForElementVisible(driver, BaseUIs.TXT_BY_ID,id);
		senKeyToElement(driver, BaseUIs.TXT_BY_ID, value, id);
	}
	
	public void checkOnRadioCheckboxOnAdmin(WebDriver driver, String id) {
		waitForElementVisible(driver, BaseUIs.CHK_BY_ID_ADMIN, id);
		checkTheRadioOrCheckbox(driver, BaseUIs.CHK_BY_ID_ADMIN, id);
	}
	
	public void uncheckOnCheckboxOnAdmin(WebDriver driver, String id) {
		waitForElementVisible(driver, BaseUIs.CHK_BY_ID_ADMIN, id);
		uncheckTheCheckbox(driver,BaseUIs.CHK_BY_ID_ADMIN, id);
	}
	
	public String getNoDataOnTable(WebDriver driver) {
		waitForElementVisible(driver, BaseUIs.EMPTY_DATA_TABLE);
		return getTextElement(driver, BaseUIs.EMPTY_DATA_TABLE);
	}
	
	public void selectItemInCustomerRole(WebDriver driver, String expectedItem) {
		selectItemInCustomDropdown(driver,BaseUIs.PARENT_CUSTOMER_ROLE,BaseUIs.CHILD_CUSTOMER_ROLE,expectedItem);
	}
	
	public void deselectItemInCustomerRole(WebDriver driver, String expectedItem) {
		clickToElement(driver,BaseUIs.PARENT_CUSTOMER_ROLE);
		sleepInSecond(1);
		waitForElementVisible(driver, BaseUIs.EXPECTED_CHILD_CUSTOMER_ROLE, expectedItem);
		clickToElement(driver, BaseUIs.EXPECTED_CHILD_CUSTOMER_ROLE, expectedItem);
	}
	
	public void overRideImplicitWait(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	//c1
	public WebElement findElement (WebDriver driver, String locator, long totalTimeWait, long pollTime) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(totalTimeWait)).pollingEvery(Duration.ofMillis(pollTime)).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(ByXpath(locator));
			}
		});
		return element;
	}
	
	//c2
//	public WebElement findElement (WebDriver driver, String locator, long totalTimeWait, long pollTime) {
//		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(totalTimeWait)).pollingEvery(Duration.ofMillis(pollTime)).ignoring(NoSuchElementException.class);
//		return wait.until(new Function<WebDriver, WebElement>(){
//			public WebElement apply(WebDriver driver) {
//				return driver.findElement(ByXpath(locator));
//			}
//		});
//	}
	
	public String getTextElement (WebDriver driver, String locator, long totalTimeWait, long pollTime) {
		WebElement element = waitElementVisible(driver, locator, totalTimeWait, pollTime);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(Duration.ofSeconds(totalTimeWait))
				.pollingEvery(Duration.ofSeconds(pollTime))
				.ignoring(NoSuchElementException.class);
		String value = wait.until(new Function<WebElement, String>(){
			public String apply(WebElement element) {
				String value = element.getText();
				return value;
			}
		});
		return value;
	}
	
	public boolean isElementDisplayed (WebDriver driver, String locator, long totalTimeWait, long pollTime) {
		WebElement element = waitElementVisible(driver, locator, totalTimeWait, pollTime);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(Duration.ofSeconds(totalTimeWait)).pollingEvery(Duration.ofMillis(pollTime)).ignoring(NoSuchElementException.class);
		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>(){
		public Boolean apply(WebElement element) {
			boolean flag = element.isDisplayed();
			return flag;
		}
		});
			return isDisplayed;
	}
	public void clickToElement(WebDriver driver, String locator, long totalTimeWait, long pollTime) {
		WebElement element = findElement(driver, locator, totalTimeWait, pollTime);
		element.click();
	}
	
	public List<String> getAllFileFromFolder(String folderName) {
		String directoryPath = projectPath + File.separator + folderName + File.separator;
		File file = new File(directoryPath);
		String contents[] = file.list();
		List<String> files = new ArrayList<String>();
		for(int i =0;i<contents.length;i++) {
			files.add(contents[i]);
		}
		return files;
	}
	
	public WebElement waitElementVisible (WebDriver driver, String locator, long totalTimeWait, long pollTime) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(totalTimeWait)).pollingEvery(Duration.ofMillis(pollTime)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(ByXpath(locator)));
	}
}
