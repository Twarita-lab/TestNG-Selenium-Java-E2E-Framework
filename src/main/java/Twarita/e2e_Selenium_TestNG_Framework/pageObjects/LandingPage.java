package Twarita.e2e_Selenium_TestNG_Framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Twarita.e2e_Selenium_TestNG_Frameworks.utilities.Utility_Or_AbstractClassed;

public class LandingPage extends Utility_Or_AbstractClassed {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input#userEmail")
	WebElement loginEmail;
	@FindBy(css = "input#userPassword")
	WebElement loginPwd;
	@FindBy(css = ("input#login"))
	WebElement loginButton;
	@FindBy(css = ("div[role='alert']"))
	WebElement loginError;

	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	};

	public ProductCatalogue login(String email, String pwd) {
		loginEmail.sendKeys(email);
		loginPwd.sendKeys(pwd);
		loginButton.click();

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getLoginError() {
		WaitForVisibilityOfAllElements(loginError);
		return loginError.getText();
	}

}
