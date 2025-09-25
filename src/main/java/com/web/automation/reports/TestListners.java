package com.web.automation.reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		ITestListener.super.onTestStart(result);
		System.out.println((" Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	
	@Override
	public void onTestSuccess(ITestResult result) {

		ITestListener.super.onTestSuccess(result);
		if (ExtentTestManager.getTest().getStatus().equals(Status.FAIL)) {
			result.setStatus(2);
			onTestFailure(result);
		} else {
			System.out.println(" Executed " + result.getMethod().getMethodName() + " test successfully...");
			ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ITestListener.super.onTestSkipped(result);
		System.out.println(" Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
		System.out.println(" Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {

		ITestListener.super.onStart(context);
		System.out.println(" Test Suite " + context.getName() + " started");
	}

	@Override
	public void onFinish(ITestContext context) {

		ITestListener.super.onFinish(context);
		System.out.println((" Test Suite " + context.getName() + " ending"));

		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

}
