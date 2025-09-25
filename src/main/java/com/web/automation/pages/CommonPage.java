package com.web.automation.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.antlr.AntlrParserPlugin;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.STPageOrderImpl;

import com.aventstack.extentreports.Status;
import com.web.automation.constants.ApplicationConstants;
import com.web.automation.constants.PathConstants;
import com.web.automation.enums.CalenderMonth;
import com.web.automation.locators.ICommonPage;
import com.web.automation.logging.InitiateLogger;
import com.web.automation.reports.ExtentTestManager;
import com.web.automation.testdata.RandomString;
import com.web.automation.utils.DateAndTimeUtils;
import com.web.automation.utils.ExcelSheetUtility;
import com.web.automation.utils.PropertyFilesUtility;
import com.web.automation.utils.TableUtils;
import com.web.automation.utils.WebActions;
import com.web.automation.utils.WebWaits;

public class CommonPage extends BasePage implements ICommonPage {

	static WebDriver driver = getDriver();
	Properties commonProperties = PropertyFilesUtility.readCommonDataProperties();

	@FindBy(xpath = CAL_MONTH_YEAR_HEADING)
	WebElement calenderMonthYearHeading;

	@FindBy(xpath = CAL_PREVIOUS_NAVIGATION_BTN)
	WebElement calenderPreviouNavigationBtn;

	@FindBy(xpath = CAL_NEXT_NAVIGATION_BTN)
	WebElement calenderNextNavigationBtn;

	@FindBy(xpath = CAL_YEAR_MONTH_DATE_OPTION)
	List<WebElement> calenderYearMontheDateOption;

	@FindBy(xpath = UPLOAD_BTN)
	WebElement uploadBtn;

	@FindBy(xpath = FILE_NAME_INPUTBOX)
	WebElement fileInput;

	@FindBy(xpath = BROWSE_FILE_BTN)
	WebElement browseFileBtn;

	@FindBy(xpath = UPLOAD_POPUP_CLOSE_BTN)
	WebElement uploadPopUpCloseBtn;

	@FindBy(xpath = FILE_NAME_LIST_ON_UPLOAD_HISTORY_TAB)
	List<WebElement> fileNameListOnUploadHistory;

	@FindBy(xpath = STATUS_LIST_ON_UPLOAD_HISTORY_TAB)
	List<WebElement> statusListOnuploadHistory;

	@FindBy(xpath = TOAST_MESSASGE)
	WebElement toastMessage;
	
	@FindBy(xpath = PAGINATION_NEXT_BTN)
	WebElement paginationNextBTn;
	
	@FindBy(xpath = PAGINATION_PREVIOUS_BTN)
	WebElement paginationPreviousBtn;
	
	@FindBy(xpath = NUMBER_OF_PAGES)
	List<WebElement> numberOfPages;
	
	

	public CommonPage(WebDriver driver) {
		PageFactory.initElements(getDriver(), this);
	}


	public void uploadRecords(String filepath) {
		WebWaits.waitForElementToClikable(uploadBtn);
		WebActions.clickElementWithJS(uploadBtn);
		WebWaits.waitForElementToClikable(browseFileBtn);
		InitiateLogger.info("Uploading the File ");
		fileInput.sendKeys(filepath);
		WebWaits.waitForFileToUpload();
		WebWaits.waitForToastMessageToDisappear();
		WebWaits.waitForLoaderToDisappear();
	}

	public void closeUpoadPopUp() {
		if (WebActions.isElementpresent(browseFileBtn)) {
			InitiateLogger.info("Closing the upload pop up..");
			WebWaits.waitForElementToClikable(uploadPopUpCloseBtn);
			WebActions.clickElement(uploadPopUpCloseBtn);

		} else {
			InitiateLogger.info("Upload pop up is already close..");
		}
	}

	

	public void selectDateFromCalender(WebElement element, int year, String month, int date) {
		element.click();
		WebActions.scrollUp();
		calenderMonthYearHeading.click();
		//calenderMonthYearHeading.click();
		int calenderYear = Integer.parseInt(calenderMonthYearHeading.getText());
		if (year > calenderYear) {
			for (int i = 0; i < (year - calenderYear); i++) {
				calenderNextNavigationBtn.click();
			}
		} else if (year < calenderYear) {
			for (int i = 0; i < (calenderYear - year); i++) {
				calenderPreviouNavigationBtn.click();
			}
		}
		WebWaits.waitToLoad(5000);
		String month1 = CalenderMonth.valueOf(month.toUpperCase()).getMonthValue();
		calenderYearMontheDateOption.get(Integer.valueOf(month1)).click();
		WebWaits.waitToLoad(5000);
		calenderYearMontheDateOption.get(date).click();
	}

	public static File captureScreenShot() {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(PathConstants.FAILED_SCREENSHOT_PATH + "//"
				+ DateAndTimeUtils.getCurrentdateAndTime(ApplicationConstants.REPORT_NAME_DATE_AND_TIME_FORMAT)
				+ ".png");
		// Copy file at destination
		System.out.println("File Path :" + destFile);
		try {
			FileUtils.copyFile(file, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}

	public boolean compareList(List<String> actualList, List<String> expectedList) {
		boolean flag = false;
		if (actualList.size() == expectedList.size()) {

			for (int i = 0; i < actualList.size(); i++) {
				if (actualList.get(i).equalsIgnoreCase(expectedList.get(i))) {
					flag = true;
					System.out.println("Actual value :" + actualList.get(i));
					System.out.println("Expected value :" + expectedList.get(i));
					InitiateLogger.pass(actualList.get(i) + " Value matched Properly");
				} else {
					flag = false;
					InitiateLogger.error("Values not matched");
					InitiateLogger.error("Actual value :" + actualList.get(i));
					InitiateLogger.error("Expected value :" + expectedList.get(i));
				}
			}
		} else {
			System.out.println("Mismatch Number of values");
		}
		return flag;

	}

	public boolean compareHashMap(HashMap<String, String> actualMap, HashMap<String, String> expectedMap) {
		boolean flag = false;
		if (actualMap.size() == expectedMap.size()) {
			List<String> keyList = new ArrayList<String>(expectedMap.keySet());
			for (int i = 0; i < keyList.size(); i++) {
				if (actualMap.get(keyList.get(i)).equals(expectedMap.get(keyList.get(i)))) {

				}

			}

		} else {
			InitiateLogger.error("Number of Values not matched");
		}

		return false;

	}

	public static void attachedScreenshotAtSteps() {
		File screenshot = captureScreenShot();
		ExtentTestManager.getTest().log(Status.INFO, ExtentTestManager.getTest()
				.addScreenCaptureFromPath(screenshot.getAbsolutePath()).getModel().getMedia().get(0));

	}
	
	public static void attachedScreenshotAtSteps(int number) {
		File screenshot = captureScreenShot();
		ExtentTestManager.getTest().log(Status.INFO, ExtentTestManager.getTest()
				.addScreenCaptureFromPath(screenshot.getAbsolutePath()).getModel().getMedia().get(number));

	}

	public void refreshTab(String tabName) {
		String element = "//span[contains(text(),'" + tabName + "')]";
		WebElement tab = driver.findElement(By.xpath(element));
		WebWaits.waitForElementToClikable(tab);
		WebActions.clickElementWithJS(tab);
		WebWaits.waitForLoaderToDisappear();
	}
	
	public void refreshTab(int tabNumber) {
		WebElement element = driver.findElement(By.xpath("//ul[@role=\"tablist\"]/li["+tabNumber+"]"));
		WebWaits.waitForElementToClikable(element);
		WebActions.clickElementWithJS(element);
		WebWaits.waitForLoaderToDisappear();
	}
	
	public boolean checkNextPaginationEnable() {
		WebWaits.waitForElementToPresent(paginationNextBTn);
		return WebActions.isElementEnable(paginationNextBTn);
	}
	
	public void navigateToNextPagination() {
		WebWaits.waitForElementToClikable(paginationNextBTn);
		WebActions.clickElementWithJS(paginationNextBTn);
		WebWaits.waitForLoaderToDisappear();
	}
	
	public  void selectRandomValueFromDropdown(WebElement element) {
		element.click();
		WebWaits.waitToLoad(5000);
		List<WebElement>values =driver.findElements(By.xpath("//kendo-list/div/ul/li"));
		int randomNumber =RandomString.generatRandomNumberInRange(0,(values.size()-2) );
		WebActions.scrollElementToView(values.get(randomNumber));
		WebActions.clickElementWithJS(values.get(randomNumber));		
	}
	
	
	
	
	

}
