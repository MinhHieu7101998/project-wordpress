package com.wordpress.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import adminPageObject.DashboardAdminPageObject;
import adminPageObject.HomeAdminPageObject;
import adminPageObject.NewEditPostAdminPageObject;
import adminPageObject.PageGeneratorManager;
import adminPageObject.PostsAdminPageObject;
import commons.AbstractTest;
import commons.GlobalConstants;
import userPageObject.HomeUserPageObject;
import userPageObject.PostDetailsUserPageObject;
import userPageObject.SearchResultUserPageObject;

public class Posts extends AbstractTest {
	WebDriver driver;
	String newTitle, editTitle;
	String newContent;
	String newCategoryName, editCategoryName;
	String newTag, editTag;
	String newImage, editImage;
	String today;
	String author;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {

		int fakeNumber = getRandomNumber();

		newTitle = "[VMH] New_Title_" + fakeNumber;
		newContent = "[VMH] New_Content_" + fakeNumber;
		newTag = "[VMH]_New " + fakeNumber;
		newCategoryName = "NEW LIVE CODING";
		editTitle = "[VMH] Edit_Title_" + fakeNumber;
		editTag = "[VMH]_Edit " + fakeNumber;
		editCategoryName = "EDIT LIVE CODING";
		author = "Automation FC";
		newImage = "NewGrandSeiko.jpg";
		today = getToday();

		driver = getBrowserDriver(browserName, url);

		homeAdminPage = PageGeneratorManager.getHomeAdminPage(driver);

		homeAdminPage.inputToEmailAddressOrUsernameTextbox(GlobalConstants.USERNAME);

		homeAdminPage.clickToContinueButton();

		homeAdminPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);

		dashboardAdminPage = homeAdminPage.clickToLoginButton();
	}

	@Test
	public void TC_01_Admin_Create_New_Post() {
		dashboardAdminPage.openMenuPageByPageName(driver, "Posts");

		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.clickToAddNewSelectionMenu();

		newEditPostAdminPage = postsAdminPage.clickToClassicEditor();

		newEditPostAdminPage.inputToAddTitleTextobox(driver, newTitle);

		newEditPostAdminPage.inputToAddContentTextarea(driver, newContent);

		newEditPostAdminPage.clickToCheckboxCategoriesByCategoryName(driver, newCategoryName);

		newEditPostAdminPage.inputToTagsTextbox(driver, newTag);

		newEditPostAdminPage.clickToAddTagsButton(driver);

		newEditPostAdminPage.clickToSetFeaturedImageLink(driver);

		newEditPostAdminPage.clickToUploadFilesButton(driver);

		newEditPostAdminPage.uploadMultipleFiles(driver, newImage);

		Assert.assertTrue(newEditPostAdminPage.areFileUploadDisplayed(driver, newImage));

		newEditPostAdminPage.clickToSetFeaturedImageButton(driver);

		Assert.assertTrue(newEditPostAdminPage.isThumbnailImageSelectedDisplayed(driver, newImage));
		Assert.assertTrue(newEditPostAdminPage.isRemoveThumbnailLinkDisplayed(driver));

		newEditPostAdminPage.clickToPublishOrUpdateButton();

		Assert.assertTrue(newEditPostAdminPage.isTextResultPostEditDeleteSuccessDisplayed(driver, "Post published. "));

		postsAdminPage = newEditPostAdminPage.clickToAllPostsLink(driver);

		postsAdminPage.inputToSearchPostsTextbox(newTitle);

		postsAdminPage.clickToSearchPostsButton();

		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Title", newTitle));
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Author", author));
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Categories", newCategoryName));
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Tags", newTag));

		homeUserPage = postsAdminPage.openEndUserPage(driver);

		Assert.assertTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, newTitle, newCategoryName, today));
		Assert.assertTrue(homeUserPage.isImagePostDisplayedByTitle(driver, newTitle, newImage));

		postDetailsUserPage = homeUserPage.clickToTitleLink(driver, newTitle);

		Assert.assertTrue(postDetailsUserPage.isPostTitleDisplayed(newTitle));
		Assert.assertTrue(postDetailsUserPage.isPostImageDisplayed(newImage));
		Assert.assertTrue(postDetailsUserPage.isPostDateCreateDisplayed(today));
		Assert.assertTrue(postDetailsUserPage.isPostAuthorDisplayed(author));
		Assert.assertTrue(postDetailsUserPage.isPostContentDisplayed(newContent));
		Assert.assertTrue(postDetailsUserPage.isPostTagDisplayed(newTag));
		Assert.assertTrue(postDetailsUserPage.isPostCategoryName(newCategoryName));

		searchResultUserPage = postDetailsUserPage.inputToSearchTextboxAtEndUserPage(driver, newTitle);

		Assert.assertTrue(searchResultUserPage.isValueSearchDisplayedAtSearchResultsFor(newTitle));
		Assert.assertTrue(searchResultUserPage.isPostDisplayedOnLatestPost(driver, newTitle, newCategoryName, today));
		Assert.assertTrue(searchResultUserPage.isImagePostDisplayedByTitle(driver, newTitle, newImage));
	}

	@Test
	public void TC_02_Admin_Edit_Post() {
		dashboardAdminPage = postDetailsUserPage.openAdminPage(driver);

		dashboardAdminPage.openMenuPageByPageName(driver, "Posts");

		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		newEditPostAdminPage = postsAdminPage.clickToClassicEditorAtColumnTitleByTitle(newTitle);

		newEditPostAdminPage.inputToAddTitleTextobox(driver, editTitle);

		newEditPostAdminPage.unClickToCheckboxByCategoryName(newCategoryName);

		newEditPostAdminPage.clickToCheckboxCategoriesByCategoryName(driver, editCategoryName);

		newEditPostAdminPage.removeOldTags(newTag);

		newEditPostAdminPage.inputToTagsTextbox(driver, editTag);

		newEditPostAdminPage.clickToAddTagsButton(driver);

		newEditPostAdminPage.clickToPublishOrUpdateButton();

		Assert.assertTrue(newEditPostAdminPage.isTextResultPostEditDeleteSuccessDisplayed(driver, "Post updated. "));

		postsAdminPage = newEditPostAdminPage.clickToAllPostsLink(driver);

		postsAdminPage.inputToSearchPostsTextbox(editTitle);

		postsAdminPage.clickToSearchPostsButton();

		Assert.assertTrue(postsAdminPage.isRowValueUndisplayedAtColumnName(driver, "Title", newTitle));
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Title", editTitle));
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Author", author));
		Assert.assertTrue(postsAdminPage.isRowValueUndisplayedAtColumnName(driver, "Categories", newCategoryName));
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Categories", editCategoryName));
		Assert.assertTrue(postsAdminPage.isRowValueUndisplayedAtColumnName(driver, "Tags", newTag));
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Tags", editTag));

		homeUserPage = postsAdminPage.openEndUserPage(driver);

		Assert.assertTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, editTitle, editCategoryName, today));
		Assert.assertTrue(homeUserPage.isImagePostDisplayedByTitle(driver, editTitle, newImage));

		postDetailsUserPage = homeUserPage.clickToTitleLink(driver, editTitle);

		Assert.assertTrue(postDetailsUserPage.isPostTitleDisplayed(editTitle));
		Assert.assertTrue(postDetailsUserPage.isPostImageDisplayed(newImage));
		Assert.assertTrue(postDetailsUserPage.isPostDateCreateDisplayed(today));
		Assert.assertTrue(postDetailsUserPage.isPostAuthorDisplayed(author));
		Assert.assertTrue(postDetailsUserPage.isPostContentDisplayed(newContent));
		Assert.assertTrue(postDetailsUserPage.isPostTagDisplayed(editTag));
		Assert.assertTrue(postDetailsUserPage.isPostCategoryName(editCategoryName));

		searchResultUserPage = postDetailsUserPage.inputToSearchTextboxAtEndUserPage(driver, editTitle);

		Assert.assertTrue(searchResultUserPage.isValueSearchDisplayedAtSearchResultsFor(editTitle));
		Assert.assertTrue(searchResultUserPage.isPostDisplayedOnLatestPost(driver, editTitle, editCategoryName, today));
		Assert.assertTrue(searchResultUserPage.isImagePostDisplayedByTitle(driver, editTitle, newImage));
	}

	@Test
	public void TC_03_Admin_Delete_Post() {
		dashboardAdminPage = postDetailsUserPage.openAdminPage(driver);

		dashboardAdminPage.openMenuPageByPageName(driver, "Posts");

		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.clickToTrashAtColumnTitleByTitle(editTitle);

		postsAdminPage.inputToSearchPostsTextbox(editTitle);

		postsAdminPage.clickToSearchPostsButton();

		Assert.assertTrue(postsAdminPage.isRowValueUndisplayedAtColumnName(driver, "Title", editTitle));
		Assert.assertTrue(postsAdminPage.isRowValueUndisplayedAtColumnName(driver, "Author", author));
		Assert.assertTrue(postsAdminPage.isRowValueUndisplayedAtColumnName(driver, "Categories", editCategoryName));
		Assert.assertTrue(postsAdminPage.isRowValueUndisplayedAtColumnName(driver, "Tags", editTag));
		Assert.assertTrue(postsAdminPage.isNoItemsTextDisplayed());

		homeUserPage = postsAdminPage.openEndUserPage(driver);

		Assert.assertTrue(homeUserPage.isPostUndisplayedOnLatestPost(driver, editTitle, editCategoryName, today));
		Assert.assertTrue(homeUserPage.isImagePostUndisplayedByTitle(driver, editTitle, newImage));

		searchResultUserPage = postDetailsUserPage.inputToSearchTextboxAtEndUserPage(driver, editTitle);

		Assert.assertTrue(searchResultUserPage.isValueSearchDisplayedAtSearchResultsFor(editTitle));
		Assert.assertTrue(searchResultUserPage.isResultNoItemsMessageDisplayed());
		Assert.assertTrue(homeUserPage.isPostUndisplayedOnLatestPost(driver, editTitle, editCategoryName, today));
		Assert.assertTrue(homeUserPage.isImagePostUndisplayedByTitle(driver, editTitle, newImage));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		removeDriver();
	}

	HomeAdminPageObject homeAdminPage;
	DashboardAdminPageObject dashboardAdminPage;
	PostsAdminPageObject postsAdminPage;
	NewEditPostAdminPageObject newEditPostAdminPage;
	HomeUserPageObject homeUserPage;
	PostDetailsUserPageObject postDetailsUserPage;
	SearchResultUserPageObject searchResultUserPage;
}
