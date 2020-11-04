package com.wordpress.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import adminPageObject.HomeAdminPageObject;
import adminPageObject.PageGeneratorManager;
import commons.AbstractTest;
import commons.GlobalConstants;

public class Login extends AbstractTest {
	WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
	}

	@Test
	public void TC_01_Login_With_Empty_Username_Or_Email_Address() {
		homeAdminPage = PageGeneratorManager.getHomeAdminPage(driver);

		homeAdminPage.inputToEmailAddressOrUsernameTextbox("");

		homeAdminPage.clickToContinueButton();

		Assert.assertEquals(homeAdminPage.getErrorUsernameOrEmailMessage(), "Please enter a username or email address.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Username_Or_Email_Address() {
		homeAdminPage = PageGeneratorManager.getHomeAdminPage(driver);

		homeAdminPage.inputToEmailAddressOrUsernameTextbox("MinhHieu");

		homeAdminPage.clickToContinueButton();

		Assert.assertEquals(homeAdminPage.getErrorUsernameOrEmailMessage(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void TC_03_Login_With_Valid_Username_Or_Email_Address_And_Invalid_Password() {
		homeAdminPage = PageGeneratorManager.getHomeAdminPage(driver);

		homeAdminPage.inputToEmailAddressOrUsernameTextbox(GlobalConstants.USERNAME);

		homeAdminPage.clickToContinueButton();

		homeAdminPage.inputToPasswordTextbox("123123");

		homeAdminPage.clickToLoginButton();

		Assert.assertEquals(homeAdminPage.getErrorInvalidPassword(), "Oops, that's not the right password. Please try again!");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		removeDriver();
	}

	HomeAdminPageObject homeAdminPage;
}
