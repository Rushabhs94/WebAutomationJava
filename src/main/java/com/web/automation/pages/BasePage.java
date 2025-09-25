package com.web.automation.pages;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.web.automation.constants.ApplicationConstants;
import com.web.automation.constants.PathConstants;
import com.web.automation.logging.InitiateLogger;
import com.web.automation.utils.PropertyFilesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	private static WebDriver driver;
	Properties envprop= PropertyFilesUtility.readEnvProperties();

	public BasePage() {
		if (driver == null) {
			driver = initilizeDriver(envprop.getProperty("browser"));
			openBrowser();

		} else {
			InitiateLogger.debug("Driver already initilized...");
		}
		
	}

	private WebDriver initilizeDriver(String browserName) {

		switch (browserName.toUpperCase()) {
		case "CHROME":
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", PathConstants.DOWNLOAD_FILE_PATH);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			break;
		case "EDGE":
			EdgeOptions edgeOptions = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(edgeOptions);
			break;
		case "FIREFOX":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(firefoxOptions);
			break;

		default:
			InitiateLogger.error("Wrong browser Value specified");
			break;
		}
		InitiateLogger.debug("Initilizing driver with " + browserName + "browser..");
		return driver;

	}

	public static WebDriver getDriver() {
		if (driver == null) {
			setDriver(driver);
		}
		return driver;

	}

	public static WebDriver setDriver(WebDriver driver) {
		return driver = BasePage.driver;
	}
	
	private  void openBrowser() {
		String entity = envprop.getProperty(ApplicationConstants.ENTITY);
		String url = null;
		switch (entity.toLowerCase()) {
		case ApplicationConstants.CLIENT_ENTITY:
			 url=ApplicationConstants.CLIENT_ENTITY+"."+ApplicationConstants.URL;
			
			break;
		case ApplicationConstants.FIRM_ENTITY:
			 url=ApplicationConstants.FIRM_ENTITY+"."+ApplicationConstants.URL;
			break;

		default:
			break;
		}
		InitiateLogger.debug("Opening the browser with "+entity+ " entity");
		driver.get(PropertyFilesUtility.readConfigProperties().getProperty(url));
		driver.manage().window().maximize();
	}

}
