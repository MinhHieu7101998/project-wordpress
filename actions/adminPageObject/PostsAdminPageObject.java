package adminPageObject;

import org.openqa.selenium.WebDriver;

import adminPageUIs.PostsAdminPageUI;
import commons.AbstractPage;

public class PostsAdminPageObject extends AbstractPage {
	WebDriver driver;

	public PostsAdminPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToAddNewSelectionMenu() {
		waitToElementClickable(driver, PostsAdminPageUI.SELECTION_MENU_EDITOR);
		clickToElementByJS(driver, PostsAdminPageUI.SELECTION_MENU_EDITOR);
	}


	public NewEditPostAdminPageObject clickToClassicEditor() {
		waitToElementClickable(driver, PostsAdminPageUI.CLASSIC_EDITOR_BUTTON);
		clickToElement(driver, PostsAdminPageUI.CLASSIC_EDITOR_BUTTON);
		return PageGeneratorManager.getNewEditPostAdminPage(driver);
	}

	public void inputToSearchPostsTextbox(String newTitle) {
		waitToElementVisible(driver, PostsAdminPageUI.SEARCH_POSTS_TEXTBOX);
		sendkeyToELement(driver, PostsAdminPageUI.SEARCH_POSTS_TEXTBOX, newTitle);
	}

	public void clickToSearchPostsButton() {
		waitToElementClickable(driver, PostsAdminPageUI.SEARCH_POSTS_BUTTON);
		clickToElement(driver, PostsAdminPageUI.SEARCH_POSTS_BUTTON);
	}

	public NewEditPostAdminPageObject clickToClassicEditorAtColumnTitleByTitle(String newTitle) {
		waitToElementVisible(driver, PostsAdminPageUI.TITLE_AT_COLUMN_TITLE_LINK, newTitle);
		hoverMouseToElment(driver, PostsAdminPageUI.TITLE_AT_COLUMN_TITLE_LINK, newTitle);
		waitToElementClickable(driver, PostsAdminPageUI.CLASSIC_LINK_HIDDEN_AT_TITLE, newTitle);
		clickToElement(driver, PostsAdminPageUI.CLASSIC_LINK_HIDDEN_AT_TITLE, newTitle);
		return PageGeneratorManager.getNewEditPostAdminPage(driver);
	}

	public void clickToTrashAtColumnTitleByTitle(String editTitle) {
		waitToElementVisible(driver, PostsAdminPageUI.TITLE_AT_COLUMN_TITLE_LINK, editTitle);
		hoverMouseToElment(driver, PostsAdminPageUI.TITLE_AT_COLUMN_TITLE_LINK, editTitle);
		waitToElementClickable(driver, PostsAdminPageUI.TRASH_HIDDEN_AT_TITLE, editTitle);
		clickToElement(driver, PostsAdminPageUI.TRASH_HIDDEN_AT_TITLE, editTitle);
	}

	public boolean isRowValueUndisplayedAtColumnName(WebDriver driver, String columnName, String editValue) {
		return isElementUndisplayed(driver, PostsAdminPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, columnName, editValue);
	}

	public boolean isNoItemsTextDisplayed() {
		waitToElementVisible(driver, PostsAdminPageUI.NO_ITEMS_MESSAGE);
		return isElementDisplayed(driver, PostsAdminPageUI.NO_ITEMS_MESSAGE);
	}

}
