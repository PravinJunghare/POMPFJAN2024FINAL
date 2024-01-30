package com.qa.opencart.pages;

import javax.swing.text.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchproductResult = By.cssSelector("div#content div .product-layout");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	/*
	 * this method will return search product count
	 */

	public int getSearchProductCount() {
		int produtCount= eleUtil.waitForElementsVisible(searchproductResult, AppConstant.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Product Count:"+produtCount);
		return produtCount;
	}

	/*
	 * this method will click on selected prodcut and return new Page Prodcut Info
	 */
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstant.DEFAULT_MEDIUM_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}

}
