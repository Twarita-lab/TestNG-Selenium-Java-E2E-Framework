package Twarita.e2e_Selenium_TestNG_Frameworks.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports extent() {
		
		String reportPath = System.getProperty("user.dir")+"\\Reportes\\extentReport.html";
		ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
		report.config().setReportName("Tutorial Extent Report");
		report.config().setDocumentTitle("Extent Report for Selenium TestNg Cucumber Project");
		
		 ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester Name", "Twarita Choudhury");
		
		return extent;
		
		
	}

}
