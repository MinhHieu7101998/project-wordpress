package userPageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class PostDetailsUserPageObject extends AbstractPage {
	WebDriver driver;

	public PostDetailsUserPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
