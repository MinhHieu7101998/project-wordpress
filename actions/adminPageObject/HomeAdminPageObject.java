package adminPageObject;

import org.openqa.selenium.WebDriver;

import adminPageUIs.HomeAdminPageUI;
import commons.AbstractPage;

public class HomeAdminPageObject extends AbstractPage {
	WebDriver driver;

	public HomeAdminPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailAddressOrUsernameTextbox(String textInput) {
		waitToElementVisible(driver, HomeAdminPageUI.EMAIL_ADDRESS_OR_USERNAME_TEXTBOX);
		sendkeyToELement(driver, HomeAdminPageUI.EMAIL_ADDRESS_OR_USERNAME_TEXTBOX, textInput);
	}

	public void clickToContinueButton() {
		waitToElementClickable(driver, HomeAdminPageUI.CONTINUE_BUTTON);
		clickToElement(driver, HomeAdminPageUI.CONTINUE_BUTTON);
	}

	public String getErrorUsernameOrEmailMessage() {
		waitToElementVisible(driver, HomeAdminPageUI.ERROR_EMAIL_OR_USERNAME_MESSAGE);
		return getElementText(driver, HomeAdminPageUI.ERROR_EMAIL_OR_USERNAME_MESSAGE);
	}

	public void inputToPasswordTextbox(String password) {
		waitToElementVisible(driver, HomeAdminPageUI.PASSWORD_TEXTBOX);
		sendkeyToELement(driver, HomeAdminPageUI.PASSWORD_TEXTBOX, password);
	}

	public String getErrorInvalidPassword() {
		waitToElementClickable(driver, HomeAdminPageUI.ERROR_INVALID_PASSWORD);
		return getElementText(driver, HomeAdminPageUI.ERROR_INVALID_PASSWORD);
	}

	public DashboardAdminPageObject clickToLoginButton() {
		waitToElementClickable(driver, HomeAdminPageUI.LOGIN_BUTTON);
		clickToElement(driver, HomeAdminPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getDashboardAdminPage(driver);
	}

}
