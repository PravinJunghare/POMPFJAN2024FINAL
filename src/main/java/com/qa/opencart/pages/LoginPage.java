package com.qa.opencart.pages;

import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// 1. private webdriver
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2.public construtor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgottenPwdlink = By.id("input-email");
	private By registerlink = By.linkText("Register");

	// 4 Page Actions
	@Step("Getting LoginPage Title......")
	public String getLoginPageTitle() {
		// String title = driver.getTitle();
		// String title = eleUtil.waitForTitleIsAndFetch(10, "Account Login");

		String title = eleUtil.waitForTitleIsAndFetch(AppConstant.DEFAULT_MEDIUM_TIMEOUT,
				AppConstant.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Loginpage title:" + title);
		return title;
	}

	@Step("Getting LoginPage URL......")
	public String getLoginUrl() {
		// String url = driver.getCurrentUrl();
		String url = eleUtil.waitForURLContainsAndFetch(AppConstant.DEFAULT_MEDIUM_TIMEOUT,
				AppConstant.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Loginpage url:" + url);
		return url;
	}

	@Step("Checking forgotpwd link......")
	// ******Encapuslation private variable and public method******* //
	public boolean isForgotpwdLinkExits() {
		// return driver.findElement(forgottenPwdlink).isDisplayed();
		return eleUtil.waitForElementVisible(forgottenPwdlink, AppConstant.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}

	@Step("Login to app using username:{0} and password:{1}")
	public AccountsPage doLogin(String un, String pwd) {
		/*
		 * driver.findElement(emailId).sendKeys(un);
		 * driver.findElement(password).sendKeys(pwd);
		 * driver.findElement(loginBtn).click();
		 */
		System.out.println("User credentilas are :" + un + ":" + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstant.DEFAULT_MEDIUM_TIMEOUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("Navigating to Registration Page")

	public RegistrationPage navigateToRegistarionPage() {
		eleUtil.doClick(registerlink);
		return new RegistrationPage(driver);
	}
}
