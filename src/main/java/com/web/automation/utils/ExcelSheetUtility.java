package com.web.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.store.Path;

import com.web.automation.constants.PathConstants;
import com.web.automation.logging.InitiateLogger;

public class ExcelSheetUtility {

	FileInputStream file;

	public XSSFSheet getExcelObject(String filePath) {
		XSSFSheet worksheet = null;
		try {
			file = new FileInputStream(filePath);
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			worksheet = wb.getSheet("Sheet1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return worksheet;

	}

	public XSSFSheet getExcelObject(String filePath, String sheetName) {
		XSSFSheet worksheet = null;
		try {
			file = new FileInputStream(filePath);
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			worksheet = wb.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return worksheet;

	}

	public void readExcelFileData(String filePath) {
		try {
			XSSFSheet ws = getExcelObject(filePath);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = ws.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = (Iterator<Cell>) row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() + " ");
						break;
					case STRING:
						System.out.print(cell.getStringCellValue() + " ");
						break;
					default:
						break;
					}
				}
				System.out.println(" ");
			}
			System.out.println("Reading File Completed.");
			file.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public List<String> readExcelRowData(String filePath, int rowNumber) {
		List<String> rowData = new ArrayList<String>();
		try {
			XSSFSheet ws = getExcelObject(filePath);
			// Iterate cells for particular row
			for (int i = 0; i < ws.getRow(0).getLastCellNum(); i++) {
				System.out.println(ws.getRow(rowNumber).getCell(i) + " ");
				rowData.add(ws.getRow(rowNumber).getCell(i).toString());
			}
			System.out.println("Reading Row Completed.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rowData;
	}

	public List<String> readExcelColumnData(String filePath, String columnName) {
		int columnNumber = 0;

		List<String> columnValue = new ArrayList<String>();
		try {
			XSSFSheet ws = getExcelObject(filePath);
			// Iterate cells for particular row
			for (int i = 0; i < ws.getRow(0).getLastCellNum(); i++) {
				if (ws.getRow(0).getCell(i).toString().equalsIgnoreCase(columnName)) {
					columnNumber = i;
				}
			}
			for (int i = 1; i <= ws.getLastRowNum(); i++) {
				// System.out.println(ws.getRow(i).getCell(columnNumber));
				columnValue.add(ws.getRow(i).getCell(columnNumber).toString().trim());
			}

			System.out.println("Reading column data Completed.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return columnValue;

	}

	public int getColumnNumberFromColumnName(String filepath, String columnName) {
		int columnNumber = 0;
		try {
			XSSFSheet ws = getExcelObject(filepath);
			// Iterate cells for particular row
			for (int i = 0; i < ws.getRow(0).getLastCellNum(); i++) {
				if (ws.getRow(0).getCell(i).toString().equalsIgnoreCase(columnName)) {
					columnNumber = i;
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return columnNumber;
	}

	public String getCellData(String filepath, int rowNumber, int colNumber) {
		String cellData = null;
		try {
			XSSFSheet ws = getExcelObject(filepath);
			// Iterate cells for particular row
			cellData = ws.getRow(rowNumber).getCell(colNumber).toString();
			System.out.println("Celldata :" + ws.getRow(rowNumber).getCell(colNumber));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cellData;
	}

	public Boolean compareExcel(String actualFilePath, String expectedFilePath, String sheetName) {
		Boolean flag = false;
		try {
			XSSFSheet worksheetAtual = getExcelObject(actualFilePath, sheetName);
			XSSFSheet worksheetExpected = getExcelObject(expectedFilePath, sheetName);
			String actualCellValue, expectedCellValue = null;
			if (worksheetAtual.getLastRowNum() == worksheetExpected.getLastRowNum()) {

				for (int i = 0; i <= worksheetAtual.getLastRowNum(); i++) {
					for (int j = 0; j <= worksheetAtual.getRow(i).getLastCellNum(); j++) {
						if (worksheetAtual.getRow(i).getCell(j) == null) {
							actualCellValue = "";
						} else {
							actualCellValue = worksheetAtual.getRow(i).getCell(j).toString();
						}
						if (worksheetExpected.getRow(i).getCell(j) == null) {
							expectedCellValue = "";
						} else {
							expectedCellValue = worksheetExpected.getRow(i).getCell(j).toString();
						}

						if (actualCellValue.equalsIgnoreCase(expectedCellValue)) {
							flag = true;
							System.out.println("Actual value :" + actualCellValue);
							System.out.println("Expected value :" + expectedCellValue);
							InitiateLogger.pass("Row " + i + " Column " + j + " cell Value matched Properly" + "<br />"
									+ "The value is :" + actualCellValue);

						} else {
							flag = false;
							InitiateLogger.error("Row " + i + " Column " + j + " cell Value not matched" + "<br />"
									+ "Actual value :" + actualCellValue + "<br />" + "Expected value :"
									+ expectedCellValue);
							System.err.println("Actual value :" + actualCellValue);
							System.err.println("Expected value :" + expectedCellValue);
						}
					}

				}
			}else {
				InitiateLogger.error("Number of Rows in both Excel are mismatch");
				InitiateLogger.error("Rows present in the Actual Excel File"+worksheetAtual.getLastRowNum());
				InitiateLogger.error("Rows present in the Downloaded Excel File"+worksheetExpected.getLastRowNum());
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return flag;

	}

	public Integer getTotalRows(String filePath, String sheetName, Integer headers) {
		Integer totalRows = null;

		try {
			XSSFSheet worksheet = getExcelObject(filePath, sheetName);
			totalRows = worksheet.getLastRowNum() + 1 - headers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalRows;

	}

	public Integer getTotalColumns(String filePath, String sheetName) {
		Integer totalColumns = null;
		try {
			XSSFSheet worksheet = getExcelObject(filePath, sheetName);
			totalColumns = (int) worksheet.getRow(0).getLastCellNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalColumns;
	}

	public void writeDataInExcelColumn(String filePath, String sheetName, String columnName, String value) {
		String excelFilePath = filePath;
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;

		try {
			inputStream = new FileInputStream(excelFilePath);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			outputStream = new FileOutputStream(excelFilePath);

			int columnNumber = getColumnNumberFromColumnName(excelFilePath, columnName);

			// Get the existing sheet
			XSSFSheet sheet = workbook.getSheet(sheetName);
			for (int i = 3; i <= sheet.getLastRowNum(); i++) {
				Cell cell = sheet.getRow(i).getCell(columnNumber); // Column index 2
				cell.setCellValue(value);
			}
			// Write the changes back to the file
			workbook.write(outputStream);
			System.out.println("Data added to the specified column in the existing Excel sheet successfully!");
			workbook.close();
			inputStream.close();
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ExcelSheetUtility excelSheetUtility = new ExcelSheetUtility();
		Properties pro = PropertyFilesUtility.readCommonDataProperties();
		
	}

}
