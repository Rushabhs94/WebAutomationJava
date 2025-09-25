package com.web.automation.common;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.web.automation.pages.BasePage;
import com.web.automation.pages.LoginPage;
import com.web.automation.utils.PropertyFilesUtility;

public class BaseTest {
	public static WebDriver driver = BasePage.getDriver();;
	Properties configProp = PropertyFilesUtility.readConfigProperties();
	
	@BeforeSuite
	public void setUp() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(configProp.getProperty("group.admin.username"), configProp.getProperty("group.admin.password"));
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * @BeforeClass public void setUp() {
	 * 
	 * LoginPage loginPage = new LoginPage(driver);
	 * loginPage.login(configProp.getProperty("group.admin.username"),
	 * configProp.getProperty("group.admin.password")); try { Thread.sleep(20000); }
	 * catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	
	
	/*
	 * @AfterClass public void classCleanUp() { LoginPage loginPage = new
	 * LoginPage(driver); loginPage.logout();
	 * 
	 * }
	 */
	
	@AfterSuite
	public void tearDown() {
		driver=BasePage.getDriver();
		driver.quit();
	}
	

}
