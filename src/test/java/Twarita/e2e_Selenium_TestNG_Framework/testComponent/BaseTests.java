package Twarita.e2e_Selenium_TestNG_Framework.testComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.LandingPage;

public class BaseTests {

	WebDriver driver;
	protected LandingPage landingPage;
	//ITestContext context;

	public WebDriver initiateDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\Twarita\\e2e_Selenium_TestNG_Frameworks\\resources\\GlobalData.properties");
		prop.load(fs);

		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");

		if (browserName.contains("Chrome")) {
			ChromeOptions option = new ChromeOptions();
			
			if(browserName.contains("headless")) option.addArguments("headless");
				
			driver = new ChromeDriver(option);
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else
			System.out.println("enter appropriate browser name");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		 
		return driver;
	};
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = ((TakesScreenshot)driver);
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");
		File file = new File(System.getProperty("user.dir")+"//Screenshots//"+testCaseName+"_"+TimeStamp+".png");
		FileUtils.copyFile(srcFile, file);

		System.out.println("getScreenshot method got called");
		return System.getProperty("user.dir")+"//Screenshots//"+testCaseName+"_"+TimeStamp+".png";
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage openUrl(ITestContext context) throws IOException {
		driver = initiateDriver();
		context.setAttribute("WebDriver", driver);			
		landingPage = new LandingPage(driver);
		landingPage.goTo();	
		System.out.println("Before method");
		return landingPage;
	}
	
	@AfterMethod (alwaysRun = true)
	public void tearDown() {
		driver.quit();
		
	}
}
