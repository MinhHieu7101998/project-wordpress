package adminPageObject;

import org.openqa.selenium.WebDriver;

import adminPageUIs.NewEditPostAdminPageUI;
import commons.AbstractPage;

public class NewEditPostAdminPageObject extends AbstractPage {
	WebDriver driver;

	public NewEditPostAdminPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToPublishOrUpdateButton() {
		waitToElementClickable(driver, NewEditPostAdminPageUI.PUBLISH_OR_UPDATE_BUTTON);
		clickToElementByJS(driver, NewEditPostAdminPageUI.PUBLISH_OR_UPDATE_BUTTON);
	}

	public void removeOldTags(String newTag) {
		waitToElementClickable(driver, NewEditPostAdminPageUI.REMOVE_TAGS_ICON, newTag);
		clickToElementByJS(driver, NewEditPostAdminPageUI.REMOVE_TAGS_ICON, newTag);
	}

	public void unClickToCheckboxByCategoryName(String newCategoryName) {
		waitToElementClickable(driver, NewEditPostAdminPageUI.UNCHECK_CATEGORY, newCategoryName);
		clickToElementByJS(driver, NewEditPostAdminPageUI.UNCHECK_CATEGORY, newCategoryName);
	}

}