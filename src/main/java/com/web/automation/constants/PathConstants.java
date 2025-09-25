package com.web.automation.constants;

public class PathConstants {
	
	
	public static final String SRC_PATH= System.getProperty("user.dir");
	public static final String RESOURCE_PATH= System.getProperty("user.dir")+"\\src\\main\\resources";
	public static final String ENV_PROPERTIES_PATH= RESOURCE_PATH+"\\environment.properties";
	public static final String CONFIG_PROPERTIES_PATH= RESOURCE_PATH+"\\PropertyFile\\*env*\\configuration.properties";
	public static final String DOWNLOAD_FILE_PATH=RESOURCE_PATH+"\\Download";
	public static final String COMMON_DATA_PROPERTIES_FILE=RESOURCE_PATH+"\\commonData.properties";
	
	//Table header file path
	public static final String TABLE_HEADERS_FILE_PATH=RESOURCE_PATH+"\\Excel\\TableHeaderData.xlsx";
	
	//failed screenshot path
	public static final String FAILED_SCREENSHOT_PATH=SRC_PATH+"\\TestReport\\Screenshot";	

}
