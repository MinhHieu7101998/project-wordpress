package adminPageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class PagesAdminPageObject extends AbstractPage{
	WebDriver driver;

	public PagesAdminPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
