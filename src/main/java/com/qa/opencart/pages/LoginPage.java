package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	//1. private webdriver
	private WebDriver driver;
	
	//2.public construtor
	
     public LoginPage(WebDriver driver)
     {
    	 this.driver=driver;
     }


	//3. private By locators
	 private By emailId=By.id("input-email");
	 private By password=By.id("input-password");
	 private By loginBtn=By.xpath("//input[@value='Login']");
	 private By forgottenPwdlink=By.id("input-email");
	 
	 // 4 Page Actions
	 
	 public String getLoginPageTitle()
	 {
		 String title=driver.getTitle();
		 System.out.println("Loginpage title:"+title);
		 return title;
	 }
	 
	 public String getLoginUrl()
	 {
		 String url=driver.getCurrentUrl();
		 System.out.println("Loginpage url:"+url);
		 return url;
	 }
	 
	 
	 //******Encapuslation private variable and public method******* //
	 public boolean isForgotpwdLinkExits()
	 {
		 return driver.findElement(forgottenPwdlink).isDisplayed();
		 
	 }

	 public void doLogin(String un,String pwd)
	 {
		 driver.findElement(emailId).sendKeys(un);
		 driver.findElement(password).sendKeys(pwd);
		 driver.findElement(loginBtn).click();
	 }
	 
		
		
}
