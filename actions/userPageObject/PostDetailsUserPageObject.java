package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import userPageUIs.PostDetailsEndUserPageUI;

public class PostDetailsUserPageObject extends AbstractPage {
	WebDriver driver;

	public PostDetailsUserPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostTitleDisplayed(String newTitle) {
		waitToElementVisible(driver, PostDetailsEndUserPageUI.POST_TITLE_NAME, newTitle);
		return isElementDisplayed(driver, PostDetailsEndUserPageUI.POST_TITLE_NAME, newTitle);
	}

	public boolean isPostImageDisplayed(String newImage) {
		newImage = newImage.split("\\.")[0];
		waitForJStoLoad(driver);
		waitToElementVisible(driver, PostDetailsEndUserPageUI.POST_IAMGE_NAME, newImage);
		return isElementDisplayed(driver, PostDetailsEndUserPageUI.POST_IAMGE_NAME, newImage);
	}

	public boolean isPostDateCreateDisplayed(String today) {
		waitToElementVisible(driver, PostDetailsEndUserPageUI.POST_DATE_CREATE, today);
		return isElementDisplayed(driver, PostDetailsEndUserPageUI.POST_DATE_CREATE, today);
	}

	public boolean isPostAuthorDisplayed(String author) {
		waitToElementVisible(driver, PostDetailsEndUserPageUI.POST_AUTHOR_NAME, author);
		return isElementDisplayed(driver, PostDetailsEndUserPageUI.POST_AUTHOR_NAME, author);
	}

	public boolean isPostContentDisplayed(String newContent) {
		waitToElementVisible(driver, PostDetailsEndUserPageUI.POST_CONTENT, newContent);
		return isElementDisplayed(driver, PostDetailsEndUserPageUI.POST_CONTENT, newContent);
	}

	public boolean isPostTagDisplayed(String newTag) {
		waitToElementVisible(driver, PostDetailsEndUserPageUI.POST_TAG_NAME, newTag);
		return isElementDisplayed(driver, PostDetailsEndUserPageUI.POST_TAG_NAME, newTag);
	}

	public boolean isPostCategoryName(String newCategoryName) {
		waitToElementVisible(driver, PostDetailsEndUserPageUI.POST_CATEGORY_NAME, newCategoryName);
		return isElementDisplayed(driver, PostDetailsEndUserPageUI.POST_CATEGORY_NAME, newCategoryName);
	}
}
