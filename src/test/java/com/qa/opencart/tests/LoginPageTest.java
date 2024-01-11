package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest{
	
	
	@Test
	public void loginPageTitleTest()
	{
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, "Account Login");
	
	}
	
	@Test
	public void loginPageUrlTest()
	{
		String actUrl=loginPage.getLoginUrl();
		Assert.assertTrue(actUrl.contains("route=account/login"));
	}
	
	@Test
	public void forgotpassLinkExistTest()
	{
	Assert.assertTrue(loginPage.isForgotpwdLinkExits());
	}
	
	@Test
	public void loginTest()
	{
		loginPage.doLogin("pravinjunghare01@yahoo.com","Test@1234");
	}
	
	
	
	
	
}




