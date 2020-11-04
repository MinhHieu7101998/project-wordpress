package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class SearchResultUserPageObject extends AbstractPage{
	WebDriver driver;

	public SearchResultUserPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
