package com.web.automation.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.web.automation.constants.ApplicationConstants;
import com.web.automation.constants.PathConstants;
import com.web.automation.logging.InitiateLogger;
import com.web.automation.pages.BasePage;

public class TableUtils {

	static WebDriver driver = BasePage.getDriver();

	public static List<String> getTableHeaders() {
		List<WebElement> columnList = driver.findElements(By.xpath("//thead[@role='presentation']/tr/th"));
		List<String> actaulColumnList = new ArrayList<String>();
		for (WebElement temp : columnList) {
			if (!(temp.getText().equals(""))) {
				actaulColumnList.add(temp.getText());
			}else {
				actaulColumnList.add("checkbox");
			}
		}
		return actaulColumnList;
	}

	public static List<String> getTableHeaderfromExcel(String tableName) {
		int tableNameRowNum;
		String rowData = null;
		// read excel data
		String FilePath = PathConstants.TABLE_HEADERS_FILE_PATH;
		ExcelSheetUtility ObjexcelUtility = new ExcelSheetUtility();
		List<String> readExcelColumnList = ObjexcelUtility.readExcelColumnData(FilePath,
				ApplicationConstants.EXCEL_TABLE_NAME);
		for (int i = 0; i < readExcelColumnList.size(); i++) {
			System.out.println(readExcelColumnList.get(i));
			if (readExcelColumnList.get(i).equals(tableName)) {
				tableNameRowNum = i + 1;
				rowData = ObjexcelUtility.getCellData(FilePath, tableNameRowNum, 1);
				break;
			} else {
				System.out.println("Incorrect Table Name....");
			}
		}
		List<String> excelColumnData = Arrays.asList(rowData.split(","));
		return excelColumnData;
	}

	public static List<WebElement> getColoumnData(String colName) {
		List<WebElement> coldata = new ArrayList<WebElement>();
		List<String> colList = getTableHeaders();
		System.out.println("Column list");
		for (int i = 0; i < colList.size(); i++) {
			if (colList.get(i).equalsIgnoreCase(colName)) {
				InitiateLogger.debug("Collection data for the column "+colName);
				WebWaits.waitToLoad(3000);
				System.out.println(i);
				coldata = driver.findElements(By.xpath("//tbody[@role='presentation']/tr/td[" + (i + 1) + "]"));
				System.err.println(coldata);
			}
		}
		return coldata;
	}

	public static HashMap<String, String> getRowDataWithHeader(int rownumber) {

		List<WebElement> allRowData = driver
				.findElements(By.xpath("//tbody[@role='presentation']/tr[" + rownumber + "]/td"));
		HashMap<String, String> rowData = new HashMap<String, String>();
		List<String> tableHeader = getTableHeaders();
		// allRowData.remove(0);
		System.out.println("tableHeader" + tableHeader.size() + " allRowData" + allRowData.size());
		for (int i = 0; i < allRowData.size(); i++) {
			System.out.println("i =" + i + " data = " + allRowData.get(i).getText());
			if (tableHeader.size() == allRowData.size()) {

				if (allRowData.get(i).getText().equals("")) {
					System.out.println(tableHeader.get(i) + " = Blank data");
					rowData.put(tableHeader.get(i), "Blank");
				} else {
					System.out.println(tableHeader.get(i) + " =" + allRowData.get(i).getText());
					rowData.put(tableHeader.get(i), allRowData.get(i).getText());
				}
			} else {
				System.out.println("header and value Mismatch");
			}
		}
		return rowData;

	}

	public static List<String> getRowData(int rownumber) {

		List<WebElement> allRowData = driver
				.findElements(By.xpath("//tbody[@role='presentation']/tr[" + rownumber + "]/td"));
		List<String> rowData = new ArrayList<String>();
		for (int i = 0; i < allRowData.size(); i++) {
			rowData.add(allRowData.get(i).getText());
		}
		return rowData;

	}
	
	public Map<String, String> getRowdataFromColumnValueWithHeader(String columnName, String columnValue) {
		List<WebElement> customerCodeList = TableUtils.getColoumnData(columnName);
		Map<String, String> rowdata = null;
		System.out.println("Column data");
		for (int i = 0; i < customerCodeList.size(); i++) {
			System.out.println("Index = " + i + " Customer code = " + customerCodeList.get(i).getText());
		}
		for (int i = 0; i < customerCodeList.size(); i++) {
			if (customerCodeList.get(i).getText().equals(columnValue)) {
				rowdata = TableUtils.getRowDataWithHeader(i + 1);
				break;
			}
		}
		System.out.println(rowdata);
		return rowdata;
	}

	public List<String> getRowdataFromColumnValue(String columnName, String columnValue) {
		List<WebElement> customerCodeList = TableUtils.getColoumnData(columnName);
		List<String> rowdata = null;
		System.out.println("Column data");
		for (int i = 0; i < customerCodeList.size(); i++) {
			System.out.println("Index = " + i + " Customer code = " + customerCodeList.get(i).getText());
		}
		for (int i = 0; i < customerCodeList.size(); i++) {
			if (customerCodeList.get(i).getText().equals(columnValue)) {
				rowdata = TableUtils.getRowData(i + 1);
				break;
			}
		}
		System.out.println(rowdata);
		return rowdata;
	}

}
