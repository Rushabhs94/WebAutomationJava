package com.web.automation.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.devtools.v116.input.model.DragData;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import com.web.automation.logging.InitiateLogger;
import com.web.automation.pages.BasePage;
import com.web.automation.pages.CommonPage;

public class WebActions {

	static WebDriver driver = BasePage.getDriver();

	public static void setText(WebElement element, String text) {
		WebWaits.waitForElementToPresent(element);
		if (element.getText() != null) {
			element.clear();
		}
		element.sendKeys(text);
	}
	
	public static String getText(WebElement element) {
		WebWaits.waitForElementToPresent(element);
		return element.getText();
	}

	public static void clickElement(WebElement element) {
		WebWaits.waitForElementToClikable(element);
		element.click();
		InitiateLogger.debug("Clicked on the Element" + element);
	}

	public static Boolean isElementpresent(WebElement element) {
		Boolean flag = false;

		List<WebElement> elementList = new ArrayList<WebElement>(Arrays.asList(element));
		if (elementList.size() > 0) {
			InitiateLogger.info(element + "Element is Present");
			flag = true;
		} else {
			InitiateLogger.error(element + " Element is not Present");
			flag = false;
		}
		return flag;
	}

	public static void selectValuefromDropdown(WebElement element, String text) {
		WebWaits.waitForElementToClikable(element);
		clickElement(element);
		WebWaits.waitToLoad(2000);
		WebElement serachBox = driver.findElement(By.xpath("//kendo-popup/div/div[1]/span/input"));
		setText(serachBox, text);
		WebWaits.waitToLoad(2000);
		List<WebElement> optionList = driver.findElements(By.xpath("//kendo-list/div/ul/li"));
		WebWaits.waitToLoad(2000);
		InitiateLogger.info("Clicking on the option "+optionList.get(0).getText());
		optionList.get(0).click();
		InitiateLogger.info("Selected the Option from the dropdown");
	}

	public static void hoverElement(WebElement element) {
		Actions action = new Actions(driver);
		WebWaits.waitForElementToPresent(element);
		action.moveToElement(element).click().build().perform();
	}

	public static Boolean isElementEnable(WebElement element) {
		Boolean flag = false;
		if (element.isEnabled()) {
			flag = true;
			System.out.println(flag);
			InitiateLogger.info("Element is enabled");
		}else {
			System.out.println(flag);
			InitiateLogger.info("Element is disable");
		}
		
		return flag;

	}
	
	public static void clickElementWithJS(WebElement element) {
		WebWaits.waitForElementToClikable(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);

	}
	
	public static void scrollElementToView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
		WebWaits.waitToLoad(2000);
	}
	
	public static void scrollDown() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,200)");
		WebWaits.waitToLoad(2000);
	}
	public static void scrollUp() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,-200)");
		WebWaits.waitToLoad(2000);
	}
	
	public static void selectValuefromDropdownWithoutSearch(WebElement element, String text) {
		clickElement(element);
		//WebWaits.waitToLoad(2000);
		//WebElement serachBox = driver.findElement(By.xpath("//kendo-popup/div/div[1]/span/input"));
		//setText(serachBox, text);
		WebWaits.waitToLoad(5000);
		List<WebElement> optionList = driver.findElements(By.xpath("//kendo-list/div/ul/li"));
		WebWaits.waitToLoad(2000);
		for (int i = 0; i < optionList.size(); i++) {
			if(optionList.get(i).getText().equalsIgnoreCase(text)) {
				InitiateLogger.info("Clicking on the option "+optionList.get(i).getText());
				optionList.get(i).click();
				break;
			}
			
		}
		//InitiateLogger.info("Clicking on the option "+optionList.get(0).getText());
		//optionList.get(0).click();
		InitiateLogger.info("Selected the Option from the dropdown");
	}
	
	
}
