package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();// created threadlocal class object and
																					// given Webdriver

	/**
	 * this method is initizating the driver on the basis of browser name
	 * 
	 * @param browserName
	 * @return browser
	 */

	// public WebDriver initDriver(String browserName) {
	public WebDriver initDriver(Properties prop) {
		optionManger = new OptionsManger(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();

		System.out.println("browser name is:" + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(optionManger.getChromeOptions());
			// here we are creating object of chromeclass giving it to threadlocal by using
			// set method
			tlDriver.set(new ChromeDriver(optionManger.getChromeOptions()));

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver(optionManger.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionManger.getFirefoxOptions()));
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(optionManger.getEdgeOptions());
		}

		else {
			System.out.println("Please enter correct browser name :" + browserName);
		}

		WebDriverManager.chromedriver().setup();

		// driver.manage().deleteAllCookies();
		// driver.manage().window().maximize();
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		// driver.get(prop.getProperty("url"));
		// return driver;

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

/*
 * This method will retun local copy of driver
 */

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
		// get () is ued to get local copy of the driver
	}

	/**
	 * This method is used to read properties from .properties file
	 * 
	 * @return
	 */
	// environment specific config.file
	
	public Properties initProp() {

		// mvn clean install -Denv="qa"
		// mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is passed....Running tests on QA env...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
				// break;
				}

			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
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
	}*/
	
	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			org.openqa.selenium.io.FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}
