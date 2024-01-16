package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader=By.tagName("h1");
	private By ProductImages=By.cssSelector("ul.thumbnails img");
	
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}

	public String getProductHeaderValue()
	{
		String prodcutHeaderValue=eleUtil.doElementGetText(productHeader);
		System.out.println("product header :" +prodcutHeaderValue);
		return prodcutHeaderValue;
	}
	
	
	public int getImageCount()
	{
		
		int imageCount=eleUtil.waitForElementsVisible(ProductImages, AppConstant.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("ProdcutImages :"+imageCount);
		return imageCount;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
