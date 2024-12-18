package Twarita.e2e_Selenium_TestNG_Framework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Twarita.e2e_Selenium_TestNG_Frameworks.utilities.Utility_Or_AbstractClassed;

public class OrderPage extends Utility_Or_AbstractClassed {

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="table td")
	List<WebElement> orderDetails;

	public boolean verifyOrderPlaced(String product) {
		// TODO Auto-generated method stub
		boolean match = orderDetails.stream().anyMatch(orders->orders.getText().contains(product.toUpperCase()));
		return match;
	}
	
	
	
	

}
