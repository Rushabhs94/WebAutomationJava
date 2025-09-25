package com.web.automation.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.web.automation.pages.CommonPage;
import com.web.automation.reports.ExtentManager;
import com.web.automation.reports.ExtentTestManager;

public class InitiateLogger {

	private static Logger logger = LogManager.getLogger();
	static ExtentReports extent = ExtentManager.getInstance();
	public static ExtentTest reportLog = ExtentTestManager.getTest();
	
	
			
	public static void info(String message) {
		logger.info(message);
		ExtentTestManager.getTest().log(Status.INFO, message);
		if (!(reportLog == null)) {
			//reportLog.info(message);
		}
		
	}
	
	public static void pass(String message) {
		logger.info(message);
		ExtentTestManager.getTest().log(Status.PASS, message);
		if (!(reportLog == null)) {
			//reportLog.info(message);
		}
		
	}

	public static void warn(String message) {
		logger.warn(message);
		ExtentTestManager.getTest().log(Status.WARNING, message);
		if (!(reportLog == null)) {
			//reportLog.warning(message);
		}
	}

	public static void error(String message) {
		logger.error(message);
		ExtentTestManager.getTest().log(Status.FAIL, message);
		if (!(reportLog == null)) {
			//reportLog.fail(message);
		}
	}

	public static void fatal(String message) {
		logger.fatal(message);
		ExtentTestManager.getTest().log(Status.FAIL, message);
		if (!(reportLog == null)) {
			//reportLog.fail(message);
		}
	}

	public static void debug(String message) {
		logger.debug(message);
		
	}

}
