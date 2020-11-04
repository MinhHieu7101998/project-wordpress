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

}
