package Twarita.e2e_Selenium_TestNG_Framework.testComponent;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Twarita.e2e_Selenium_TestNG_Frameworks.resources.ExtentReporterNG;

public class Listeners extends BaseTests implements ITestListener {
	ExtentTest test;
	ITestContext context;
	ExtentReports extent = ExtentReporterNG.extent();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread safe

	public void onTestStart(ITestResult result) {
		context = result.getTestContext();
		test = extent.createTest(result.getName());
		extentTest.set(test);
		System.out.println("OnTest Satrt method");
		

	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed"); //don't tie log with any Result info. If done, it'll hamper the graph
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL, "Test Failed");
		try {

			driver = (WebDriver) context.getAttribute("WebDriver");
			String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
		try {

			driver = (WebDriver) context.getAttribute("WebDriver");
			String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
