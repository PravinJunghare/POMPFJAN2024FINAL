package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManger optionManger;
	public static String highlight;

	/**
	 * this method is initizating the driver on the basis of browser name
	 * 
	 * @param browserName
	 * @return browser
	 */

	// public WebDriver initDriver(String browserName) {
	public WebDriver initDriver(Properties prop) {
		optionManger = new OptionsManger(prop);
		highlight=prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		
		System.out.println("browser name is:" + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(optionManger.getChromeOptions());
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(optionManger.getFirefoxOptions());
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(optionManger.getEdgeOptions());
		}

		else {
			System.out.println("Please enter correct browser name :" + browserName);
		}

		WebDriverManager.chromedriver().setup();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.get(prop.getProperty("url"));
		return driver;
	}

	/**
	 * This method is used to read properties from .properties file
	 * 
	 * @return
	 */

	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;
	}

}
