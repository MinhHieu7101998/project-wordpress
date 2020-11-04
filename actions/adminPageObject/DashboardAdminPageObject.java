package adminPageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DashboardAdminPageObject extends AbstractPage{
	WebDriver driver;
	
	public DashboardAdminPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
