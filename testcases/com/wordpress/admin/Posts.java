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
	String newTitle;
	String newContent;
	String newCategoryName;
	String newTag;
	String newImage, editImage;
	String today;
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		
		int fakeNumber = getRandomNumber();

		newTitle = "[VMH] New_Title" + fakeNumber;

		newContent = "[VMH] New_content" + fakeNumber;
		
		newTag = "[VMH] " + fakeNumber;
		
		newCategoryName = "NEW LIVE CODING";
		
		newImage = "NewGrandSeiko.jpg";
		
		editImage = "EditGrandSeiko.png";
		
		today =  getToday();
		
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
		
		newEditPostAdminPage.clickToPublishButton();
		
		Assert.assertTrue(newEditPostAdminPage.isTextResultPostEditDeleteSuccessDisplayed(driver, "Post published. "));
		
		postsAdminPage = newEditPostAdminPage.clickToAllPostsLink(driver);
		
		postsAdminPage.inputToSearchPostsTextbox(newTitle);
		
		postsAdminPage.clickToSearchPostsButton();
		
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Title", newTitle));
		
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Author", "Automation FC"));
		
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Categories", newCategoryName));
		
		Assert.assertTrue(postsAdminPage.isRowValueDisplayedAtColumnName(driver, "Tags", newTag));
		
		homeUserPage = postsAdminPage.openEndUserPage(driver);
		
		Assert.assertTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, newTitle, newCategoryName, today));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
	}

	HomeAdminPageObject homeAdminPage;
	DashboardAdminPageObject dashboardAdminPage;
	PostsAdminPageObject postsAdminPage;
	NewEditPostAdminPageObject newEditPostAdminPage;
	HomeUserPageObject homeUserPage;
	PostDetailsUserPageObject postDetailsUserPage;
	SearchResultUserPageObject searchResultUserPage;
}
