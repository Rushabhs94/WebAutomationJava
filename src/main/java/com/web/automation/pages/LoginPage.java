package com.web.automation.pages;

import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.automation.locators.ILoginPage;
import com.web.automation.utils.PropertyFilesUtility;
import com.web.automation.utils.WebActions;
import com.web.automation.utils.WebWaits;

public class LoginPage extends BasePage implements ILoginPage {
	
	
	static WebDriver driver = getDriver();
	Properties configProp = PropertyFilesUtility.readConfigProperties();
	
	@FindBy(id=USERNAME)
	WebElement usernameInput;
	
	@FindBy(id=PASSWORD)
	WebElement passwordInput;
	
	@FindBy(id=SIGNIN)
	WebElement signinBtn;
	
	@FindBy(xpath=PROFILE_ICON)
	WebElement profileIcon;
	
	@FindBy(xpath=LOGOUT)
	WebElement logOut;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(getDriver(), this);
	}

	

	public void login(String username,String password) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebActions.setText(usernameInput, username);
		WebActions.setText(passwordInput, password);
		WebActions.clickElement(signinBtn);
	}
	
	public void logout() {
		WebWaits.waitForElementToClikable(profileIcon);
		WebActions.clickElement(profileIcon);
		WebActions.scrollElementToView(logOut);
		WebWaits.waitForElementToClikable(logOut);
		WebActions.clickElement(logOut);
		
	}
}