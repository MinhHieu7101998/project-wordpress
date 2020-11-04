package adminPageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class MediaAdminPageObject extends AbstractPage{
	public MediaAdminPageObject(WebDriver driver) {
		this.driver = driver;
	}

	WebDriver driver;
}
