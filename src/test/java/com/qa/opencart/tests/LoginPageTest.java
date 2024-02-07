package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginPageTest extends BaseTest {

	@Epic("E101:Design Login for Opencart Application")
	@Story("User Story US-01: Design Features for Login Page")

	@Severity(SeverityLevel.TRIVIAL)
	@Description("---Getting Title of Page--- Author:Pravin")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstant.LOGIN_PAGE_TITLE_VALUE);

	}

	@Severity(SeverityLevel.BLOCKER)

	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginUrl();
		Assert.assertTrue(actUrl.contains(AppConstant.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("---Getting Forgot Password link--- Author:Pravin")
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
