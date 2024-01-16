package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstant.LOGIN_PAGE_TITLE_VALUE);

	}

	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginUrl();
		Assert.assertTrue(actUrl.contains(AppConstant.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Test(priority = 3)
	public void forgotpassLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotpwdLinkExits());
	}

	@Test(priority = 4)
	public void loginTest() {
		// loginPage.doLogin("pravinjunghare01@yahoo.com", "Test@1234");
		// accountsPage = loginPage.doLogin("pravinjunghare01@yahoo.com", "Test@1234");
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	
	

}
