package adminPageObject;

import org.openqa.selenium.WebDriver;

import userPageObject.HomeUserPageObject;
import userPageObject.PostDetailsUserPageObject;
import userPageObject.SearchResultUserPageObject;

public class PageGeneratorManager {
	public static HomeAdminPageObject getHomeAdminPage(WebDriver driver) {
		return new HomeAdminPageObject(driver);
	}
	public static DashboardAdminPageObject getDashboardAdminPage(WebDriver driver) {
		return new DashboardAdminPageObject(driver);
	}
	public static PostsAdminPageObject getPostsAdminPage(WebDriver driver) {
		return new PostsAdminPageObject(driver);
	}
	public static MediaAdminPageObject getMediaAdminPage(WebDriver driver) {
		return new MediaAdminPageObject(driver);
	}
	public static LinksAdminPageObject getLinksAdminPage(WebDriver driver) {
		return new LinksAdminPageObject(driver);	
	}
	public static PagesAdminPageObject getPagesAdminPage(WebDriver driver) {
		return new PagesAdminPageObject(driver);
	}
	public static NewEditPostAdminPageObject getNewEditPostAdminPage(WebDriver driver) {
		return new NewEditPostAdminPageObject(driver);
	}
	public static HomeUserPageObject getHomeUserPage(WebDriver driver) {
		return new HomeUserPageObject(driver);
	}
	public static PostDetailsUserPageObject getPostDetailsUserPage(WebDriver driver) {
		return new PostDetailsUserPageObject(driver);
	}
	public static SearchResultUserPageObject getSearchResultUserPage(WebDriver driver) {
		return new SearchResultUserPageObject(driver);
	}
}
