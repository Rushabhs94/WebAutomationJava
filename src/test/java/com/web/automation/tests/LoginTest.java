package com.web.automation.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.web.automation.common.BaseTest;
import com.web.automation.logging.InitiateLogger;
import com.web.automation.pages.BasePage;
import com.web.automation.pages.LoginPage;
import com.web.automation.reports.ExtentTestManager;
import com.web.automation.utils.PropertyFilesUtility;

public class LoginTest extends BaseTest {

	WebDriver driver = BasePage.getDriver();
	SoftAssert sassert = new SoftAssert();
	Properties configProp = PropertyFilesUtility.readConfigProperties();

	@Test
	public void exampleTestScript() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(configProp.getProperty("client.admin.username"),
				configProp.getProperty("client.admin.password"));
		

	}
	
	@Test
	public void exampleLogging() {

		InitiateLogger.info(System.getProperty("user.dir"));
		InitiateLogger.debug("Debug Message");
		InitiateLogger.error("Error Message");
		InitiateLogger.fatal("Fatal Messgae");
		InitiateLogger.warn("Warn Message");
		sassert.assertAll();

	}
	
	

}
