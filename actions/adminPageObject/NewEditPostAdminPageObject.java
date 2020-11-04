package adminPageObject;

import org.openqa.selenium.WebDriver;

import adminPageUIs.NewEditPostAdminPageUI;
import commons.AbstractPage;

public class NewEditPostAdminPageObject extends AbstractPage {
	WebDriver driver;

	public NewEditPostAdminPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToPublishButton() {
		waitToElementClickable(driver, NewEditPostAdminPageUI.PUBLISH_BUTTON);
		clickToElementByJS(driver, NewEditPostAdminPageUI.PUBLISH_BUTTON);
	}

}