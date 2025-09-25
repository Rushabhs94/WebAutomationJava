package com.web.automation.utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.web.automation.logging.InitiateLogger;
import com.web.automation.pages.BasePage;

public class WebWaits {

	static WebDriver driver = BasePage.getDriver();
	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	static FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);

	private static FluentWait<WebDriver> getShortDuration() {

		fluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		return fluentWait;
	}

	private static FluentWait<WebDriver> getLongDuration() {
		fluentWait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		return fluentWait;
	}

	private static FluentWait<WebDriver> getTooLongDuration() {
		fluentWait.withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		return fluentWait;
	}

	public static void waitForElementToClikable(WebElement element) {
		try {
			getLongDuration().until(ExpectedConditions.elementToBeClickable(element));
		} catch (NoSuchElementException e) {
			InitiateLogger.error("Element is not present " + element);
		}

	}

	public static void waitForElementToPresent(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException e) {
			InitiateLogger.error("Element is not present " + element);
		}

	}

	public static void waitToLoad(long millsec) {
		try {
			Thread.sleep(millsec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitForLoaderToDisappear() {
		try {
			getLongDuration().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//bdo_cfw_loader")));
			System.out.println("Data loaded");
			
		} catch (NoSuchElementException e) {
			InitiateLogger.info("Loader is Still Visible");
		}
	}
	
	public static void waitForToastMessageToDisappear() {
		try {
			getTooLongDuration().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id=\"toast-container\"]")));
			InitiateLogger.info("Toast message is disappear");
		} catch (NoSuchElementException e) {
			InitiateLogger.info("Toast Message is Still Visible");
		}
	}
	
	public static void waitForFileToUpload() {
		try {
			getTooLongDuration().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//bdo_gbl_upload_file")));
			InitiateLogger.info("File Uploading is done");
		} catch (NoSuchElementException e) {
			InitiateLogger.info("File Uploading is still in progress..");
		}
	}

}
