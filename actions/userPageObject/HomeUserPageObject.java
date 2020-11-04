package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class HomeUserPageObject extends AbstractPage {
	WebDriver driver;

	public HomeUserPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
