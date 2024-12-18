package Twarita.e2e_Selenium_TestNG_Framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Twarita.e2e_Selenium_TestNG_Frameworks.utilities.Utility_Or_AbstractClassed;

public class OrderSuccessPage extends Utility_Or_AbstractClassed {
	WebDriver driver;

	public OrderSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1.hero-primary") WebElement successMessage;
	@FindBy(css = "label.ng-star-inserted") WebElement orderNumber;
	@FindBy(css = ".action__submit")WebElement placeOrderButton;

	By BysuccessMessage = By.cssSelector("label.ng-star-inserted");

	public boolean validateOrderPlaced(String Message) {
		waitForVisibilityOfElementLocated(BysuccessMessage);
		boolean successMessageMatch = successMessage.getText().equalsIgnoreCase(Message);
		return successMessageMatch;
		
	}
	
	public String GetOrderNumber() {
		return orderNumber.getText().split(" ")[1];
		
	}
}
