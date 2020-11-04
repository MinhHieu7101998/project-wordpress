package commons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import adminPageObject.DashboardAdminPageObject;
import adminPageObject.PageGeneratorManager;
import adminPageObject.PostsAdminPageObject;
import adminPageUIs.AbstractPageUI;
import adminPageUIs.PostsAdminPageUI;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import userPageObject.HomeUserPageObject;
import userPageObject.SearchResultUserPageObject;

public class AbstractPage {
	private JavascriptExecutor jsExecutor;
	private WebDriverWait explicitWait;
	private Actions action;
	private WebElement element;
	private List<WebElement> elements;
	private Select select;
	private String osName = System.getProperty("os.name");

	protected final Log log;

	protected AbstractPage() {
		log = LogFactory.getLog(getClass());
	}

	protected void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	protected String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getCurrentPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurrentPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver);
		driver.switchTo().alert().accept();
	}

	protected void cancleAlert(WebDriver driver) {
		waitAlertPresence(driver);
		driver.switchTo().alert().dismiss();
	}

	protected String getTextAlert(WebDriver driver) {
		waitAlertPresence(driver);
		return driver.switchTo().alert().getText();
	}

	protected void setTextAlert(WebDriver driver, String value) {
		waitAlertPresence(driver);
		driver.switchTo().alert().sendKeys(value);
	}

	protected void waitAlertPresence(WebDriver driver) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			log.debug(e.getMessage());
		}

	}

	protected void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentTitleWindows = getCurrentPageTitle(driver);
			if (currentTitleWindows.equals(title)) {
				break;
			}
		}
	}

	protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	protected WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	protected List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	protected List<WebElement> getElements(WebDriver driver, String locator, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		return driver.findElements(getByXpath(locator));
	}

	protected By getByXpath(String locator) {
		return By.xpath(locator);
	}

	protected void clickToElement(WebDriver driver, String locator) {
		if (driver.toString().toLowerCase().contains("edge")) {
			sleepInMiliSecond(500);
		}
		try {
			element = getElement(driver, locator);
			element.click();
		} catch (Exception e) {
			log.debug("Element is not clickable: " + e.getMessage());
		}

	}

	protected void pressKeyEnter(WebDriver driver, String locator) {
		getElement(driver, locator).sendKeys(Keys.ENTER);
	}

	protected void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		if (driver.toString().toLowerCase().contains("edge")) {
			sleepInMiliSecond(500);
		}
		try {
			element = getElement(driver, locator);
			element.click();
		} catch (Exception e) {
			log.debug("Element is not clickable: " + e.getMessage());
		}
	}

	protected void sendkeyToELement(WebDriver driver, String locator, String value) {
		if (driver.toString().toLowerCase().contains("edge") || driver.toString().toLowerCase().contains("chrome")) {
			sleepInMiliSecond(500);
		}
		try {
			element = getElement(driver, locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			log.debug("Element is not sendkey: " + e.getMessage());
		}
	}

	protected void sendkeyToELement(WebDriver driver, String locator, String value, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		if (driver.toString().toLowerCase().contains("edge") || driver.toString().toLowerCase().contains("chrome")) {
			sleepInMiliSecond(500);
		}
		try {
			element = getElement(driver, locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			log.debug("Element is not sendkey: " + e.getMessage());
		}
	}

	protected void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
		try {
			element = getElement(driver, locator);
			select = new Select(element);
			select.selectByVisibleText(itemValue);
		} catch (Exception e) {
			log.debug("Item dropdown is not select: " + e.getMessage());
		}
	}

	protected void selectItemInDropdown(WebDriver driver, String locator, String itemValue, String... dynamicValues) {
		try {
			locator = castRestParamter(locator, dynamicValues);
			element = getElement(driver, locator);
			select = new Select(element);
			select.selectByVisibleText(itemValue);
		} catch (Exception e) {
			log.debug("Item dropdown is not select: " + e.getMessage());
		}
	}

	protected String getFirstSelectedTextDropdown(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	protected String getFirstSelectedTextDropdown(WebDriver driver, String locator, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		element = getElement(driver, locator);
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locator) {
		try {
			element = getElement(driver, locator);
			Select select = new Select(element);
			return select.isMultiple();
		} catch (Exception e) {
			log.debug("Dropdown not multiple: " + e.getMessage());
			return false;
		}
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		elements = getElements(driver, childItemLocator);

		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void sleepInMiliSecond(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		element = getElement(driver, locator);
		return element.getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String locator, String attributeName, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		element = getElement(driver, locator);
		return element.getAttribute(attributeName);
	}

	protected String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	protected String getElementText(WebDriver driver, String locator, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		return getElement(driver, locator).getText();
	}

	protected int countElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	protected int countElementSize(WebDriver driver, String locator, String... dynamicValues) {
		return getElements(driver, castRestParamter(locator, dynamicValues)).size();
	}

	protected void checkToCheckbox(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void unCheckToCheckbox(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			log.debug("Element is not displayed with error: " + e.getMessage());
			return false;
		}

	}

	protected boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValues) {
		try {
			locator = castRestParamter(locator, dynamicValues);
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			log.debug("Element is not displayed with error: " + e.getMessage());
			return false;
		}
	}

	protected boolean isElementSelected(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isSelected();
		} catch (Exception e) {
			log.debug("Element is not selected with error: " + e.getMessage());
			return false;
		}
	}

	protected boolean isElementSelected(WebDriver driver, String locator, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		try {
			return getElement(driver, locator).isSelected();
		} catch (Exception e) {
			log.debug("Element is not selected with error: " + e.getMessage());
			return false;
		}
	}

	protected boolean isElementEnabled(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isEnabled();
		} catch (Exception e) {
			log.debug("Element is not enabled with error: " + e.getMessage());
			return false;
		}

	}

	protected void switchToFrame(WebDriver driver, String locator) {
		try {
			driver.switchTo().frame(getElement(driver, locator));
		} catch (Exception e) {
			log.debug("No frame/iframe with: " + e.getMessage());
		}
	}

	protected void switchToDefaultContent(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.debug("Can't switch to default content with error: " + e.getMessage());
		}

	}

	protected void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	protected void rightClickToElment(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	protected void hoverMouseToElment(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	protected void clickAndHoldToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.clickAndHold(getElement(driver, locator)).perform();
	}

	protected void drapAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
	}

	protected void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	protected Object executeForBrowser(WebDriver driver, String javaSript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}

	protected boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	protected void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	protected void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	protected void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	protected void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void scrollToElement(WebDriver driver, String locator, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	protected boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		if (status) {
			return true;
		}
		return false;
	}

	protected void waitToElementVisible(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
		} catch (Exception e) {
			log.debug("Wait for element visible with error: " + e.getMessage());
		}
	}

	protected void waitToElementVisible(WebDriver driver, String locator, String... dynamicValues) {
		try {
			locator = castRestParamter(locator, dynamicValues);
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
		} catch (Exception e) {
			log.debug("Wait for element visible with error: " + e.getMessage());
		}
	}

	protected void waitToElementInvisible(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		} catch (Exception e) {
			log.debug("Wait for element invisible with error: " + e.getMessage());
		}
	}

	protected void waitToElementInvisible(WebDriver driver, String locator, String... dynamicValues) {
		try {
			locator = castRestParamter(locator, dynamicValues);
			explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		} catch (Exception e) {
			log.debug("Wait for element invisible with error: " + e.getMessage());
		}
	}

	protected void waitToElementsInvisible(WebDriver driver, String locator, String... dynamicValues) {
		try {
			locator = castRestParamter(locator, dynamicValues);
			explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			elements = getElements(driver, locator);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		} catch (Exception e) {
			log.debug("Wait for element invisible with error: " + e.getMessage());
		}
	}

	protected void waitToElementClickable(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
		} catch (Exception e) {
			log.debug("Wait for click to element with error: " + e.getMessage());
		}
	}

	protected void waitToElementClickable(WebDriver driver, String locator, String... dynamicValues) {
		try {
			locator = castRestParamter(locator, dynamicValues);
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
		} catch (Exception e) {
			log.debug("Wait for click to element with error: " + e.getMessage());
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeoutInSecond) {
		driver.manage().timeouts().implicitlyWait(timeoutInSecond, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... dynamicValues) {
		locator = castRestParamter(locator, dynamicValues);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}

	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0 || osName.toLowerCase().indexOf("aix") > 0);
	}

	public boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");

		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		sendkeyToELement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);
	}

	public boolean areFileUploadDisplayed(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;

		waitToElementsInvisible(driver, AbstractPageUI.MEDIA_PROGRESS_BAR_ICON);
		sleepInSecond(5);
		elements = getElements(driver, AbstractPageUI.ALL_UPLOAD_IMAGE);

		List<String> imageValues = new ArrayList<String>();

		int i = 0;

		for (WebElement image : elements) {
			imageValues.add(image.getAttribute("src"));
			i++;
			if (i == number) {
				break;
			}
		}

		for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();

			for (i = 0; i < imageValues.size(); i++) {
				if (!imageValues.get(i).contains(fileName)) {
					status = false;
					if (i == imageValues.size() - 1) {
						return status;
					}
				} else {
					status = true;
					break;
				}
			}
		}
		return status;
	}

	protected String castRestParamter(String locator, String... dynamicValues) {
		return locator = String.format(locator, (Object[]) dynamicValues);
	}

	public void inputToAddTitleTextobox(WebDriver driver, String titleName) {
		waitToElementClickable(driver, AbstractPageUI.ADD_TITLE_TEXTBOX);
		sendkeyToELement(driver, AbstractPageUI.ADD_TITLE_TEXTBOX, titleName);
	}

	public void inputToAddContentTextarea(WebDriver driver, String contentText) {
		switchToFrame(driver, AbstractPageUI.IFRAME_CONTENT_TEXTAREA);
		waitToElementVisible(driver, AbstractPageUI.ADD_CONTENT_TEXTAREA);
		sendkeyToELement(driver, AbstractPageUI.ADD_CONTENT_TEXTAREA, contentText);
		switchToDefaultContent(driver);
	}

	public void clickToCheckboxCategoriesByCategoryName(WebDriver driver, String categoryName) {
		scrollToElement(driver, AbstractPageUI.CATEGORIES_TAB);
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_CATEGORY_CHECKBOX_BY_NAME, categoryName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_CATEGORY_CHECKBOX_BY_NAME, categoryName);
	}

	public void inputToTagsTextbox(WebDriver driver, String tagsValue) {
		waitToElementVisible(driver, AbstractPageUI.TAGS_TEXTBOX);
		sendkeyToELement(driver, AbstractPageUI.TAGS_TEXTBOX, tagsValue);
	}

	public void clickToAddTagsButton(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.ADD_TAGS_BUTTON);
		clickToElement(driver, AbstractPageUI.ADD_TAGS_BUTTON);
	}

	public void clickToSetFeaturedImageLink(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.SET_FEATURED_IMAGE_LINK);
		clickToElement(driver, AbstractPageUI.SET_FEATURED_IMAGE_LINK);
	}

	public boolean isTagsDisplayed(WebDriver driver, String tagName) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TAGS_ADD_SUCCESS_BY_TAGS_NAME, tagName);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_TAGS_ADD_SUCCESS_BY_TAGS_NAME, tagName);
	}

	public void clickToUploadFilesButton(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.UPLOAD_FILES_BUTTON);
		clickToElement(driver, AbstractPageUI.UPLOAD_FILES_BUTTON);
	}

	public void clickToSetFeaturedImageButton(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.SET_FEATURED_IMAGE_BUTTON);
		clickToElement(driver, AbstractPageUI.SET_FEATURED_IMAGE_BUTTON);
	}

	public boolean isRemoveThumbnailLinkDisplayed(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.REMOVE_THUMBNAIL_LINK);
		return isElementDisplayed(driver, AbstractPageUI.REMOVE_THUMBNAIL_LINK);
	}

	public boolean isTextResultPostEditDeleteSuccessDisplayed(WebDriver driver, String successMessage) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_NEW_EDIT_DELETE_SUCCESS_MESSAGE, successMessage);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_NEW_EDIT_DELETE_SUCCESS_MESSAGE, successMessage);
	}

	public PostsAdminPageObject clickToAllPostsLink(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.ALL_POSTS_LINK);
		clickToElement(driver, AbstractPageUI.ALL_POSTS_LINK);
		return PageGeneratorManager.getPostsAdminPage(driver);
	}

	public boolean isThumbnailImageSelectedDisplayed(WebDriver driver, String imageName) {
		String[] files = imageName.toLowerCase().split("\\.");
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_IMAGE_THUMBNAIL_SELECTED, files[0]);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_IMAGE_THUMBNAIL_SELECTED, files[0]);
	}

	public AbstractPage openMenuPageByTextName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
		switch (pageName) {
		case "Posts":
			return PageGeneratorManager.getPostsAdminPage(driver);
		case "Media":
			return PageGeneratorManager.getMediaAdminPage(driver);
		case "Links":
			return PageGeneratorManager.getLinksAdminPage(driver);
		default:
			return PageGeneratorManager.getPagesAdminPage(driver);
		}
	}

	public void openMenuPageByPageName(WebDriver driver, String pageName) {
		waitToElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
	}

	public HomeUserPageObject openEndUserPage(WebDriver driver) {
		openPageUrl(driver, GlobalConstants.END_USER_WORDPRESS_PAGE);
		return PageGeneratorManager.getHomeUserPage(driver);
	}

	public DashboardAdminPageObject openAdminPage(WebDriver driver) {
		openPageUrl(driver, GlobalConstants.ADMIM_WORDPRESS_PAGE);
		return PageGeneratorManager.getDashboardAdminPage(driver);
	}

	public SearchResultUserPageObject clickToSearchIconInSearchTextboxEndUserPage(WebDriver driver) {

		return PageGeneratorManager.getSearchResultUserPage(driver);
	}

	public boolean isPostDisplayedOnLatestPost(WebDriver driver, String titleName, String categoryName, String today) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_POSTS_LATEST_BY_TITLE_CATEGORY_AND_TODAY, titleName, categoryName, today);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_POSTS_LATEST_BY_TITLE_CATEGORY_AND_TODAY, titleName, categoryName, today);
	}

	public boolean isRowValueDisplayedAtColumnName(WebDriver driver, String colname, String value) {
		waitToElementVisible(driver, PostsAdminPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, colname, value);
		return isElementDisplayed(driver, PostsAdminPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, colname, value);
	}

	public boolean compareImageAshot(WebDriver driver, String locator, String fileName) throws IOException {

		element = getElement(driver, locator);

		BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") + getDirectorySlash("uploadFiles") + fileName));
		Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, element);

		BufferedImage actualImage = logoImageScreenshot.getImage();

		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);

		if (diff.hasDiff()) {
			return false;
		} else {
			return true;
		}

	}

	public void captureImage(WebDriver driver, String locator) throws IOException {

		waitToElementVisible(driver, locator);

		element = getElement(driver, locator);

		Screenshot imageScreenshot = new AShot().takeScreenshot(driver, element);

		ImageIO.write(imageScreenshot.getImage(), "png", new File("D:\\Automation Testing Online\\03 - Java Hybrid Framework\\Screenshot\\screenshot.png"));

		File f = new File("D:\\Automation Testing Online\\03 - Java Hybrid Framework\\Screenshot\\screenshot.png");

		if (f.exists()) {
			System.out.println("Image File Captured");
		} else {
			System.out.println("Image NOT exist");
		}
	}
}
