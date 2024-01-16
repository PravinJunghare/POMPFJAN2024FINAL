package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	// precodition is that user should be login for AccountsPage

	@BeforeClass

	public void producInfoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] 
				{ 
			{ "MacBook", "MacBook Pro",4 }, 
			{ "MacBook", "MacBook Air" ,4}, 
			{ "iMac", "iMac",3 },
			{ "Apple", "Apple Cinema 30\"", 6},

		};
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagecountTest(String searchkey,String ProdcutName,int imagecount) {
		searchPage = accountsPage.performSearch(searchkey);
		productInfoPage = searchPage.selectProduct(ProdcutName);
		int actualImagecount = productInfoPage.getImageCount();
		Assert.assertEquals(actualImagecount, imagecount);
	}

	/*@Test()
	public void productImagecountTest(String searchkey,String ProdcutName,int imagecount) {
		searchPage = accountsPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		int actualImagecount = productInfoPage.getImageCount();
		Assert.assertEquals(actualImagecount, 4);
	}*/

}
