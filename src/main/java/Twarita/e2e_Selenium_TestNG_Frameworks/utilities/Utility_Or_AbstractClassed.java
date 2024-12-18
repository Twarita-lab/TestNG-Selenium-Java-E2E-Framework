package Twarita.e2e_Selenium_TestNG_Frameworks.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.CartPage;
import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.OrderPage;

public class Utility_Or_AbstractClassed {

	WebDriver driver;
	WebDriverWait waitE;
	
	public Utility_Or_AbstractClassed(WebDriver driver) {
		this.driver = driver;
		waitE = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css ="button[routerlink*='cart']") WebElement cartIcon;
	@FindBy(css ="button[routerlink*='myorders']") WebElement orderIcon;
	
	
	public void WaitForVisibilityOfAllElements(WebElement element) {
	waitE.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void WaitForInvisibilityOfElement(WebElement element) {
		waitE.until(ExpectedConditions.invisibilityOf(element));
		}
	
	public void waitForVisibilityOfElementLocated(By locator) {
		waitE.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	public CartPage goToCartPage() {
		cartIcon.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	};
	
	public OrderPage goToOrderPage() {
		orderIcon.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
		
}
