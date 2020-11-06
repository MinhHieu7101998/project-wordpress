package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import userPageUIs.SearchResultUserPageUI;

public class SearchResultUserPageObject extends AbstractPage{
	WebDriver driver;

	public SearchResultUserPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isValueSearchDisplayedAtSearchResultsFor(String newTitle) {
		waitToElementVisible(driver, SearchResultUserPageUI.SEARCH_RESULT_FOR, newTitle);
		return isElementDisplayed(driver, SearchResultUserPageUI.SEARCH_RESULT_FOR, newTitle);
	}
	public boolean isResultNoItemsMessageDisplayed() {
		waitToElementVisible(driver, SearchResultUserPageUI.SEARCH_RESULT_NO_ITEM_MESSAGE);
		return isElementDisplayed(driver, SearchResultUserPageUI.SEARCH_RESULT_NO_ITEM_MESSAGE);
	}
	
}
