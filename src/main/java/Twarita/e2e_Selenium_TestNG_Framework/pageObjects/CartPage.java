package Twarita.e2e_Selenium_TestNG_Framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Twarita.e2e_Selenium_TestNG_Frameworks.utilities.Utility_Or_AbstractClassed;

public class CartPage extends Utility_Or_AbstractClassed {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "li.items")
	WebElement itemCartLayoutPresence;
	@FindBy(css = "li.items")
	List<WebElement> itemCartLayout;
	@FindBy(css = ".subtotal button")
	public WebElement checkoutButton;

	public Boolean verifyProductAddedTocart(String productName) {
		WaitForVisibilityOfAllElements(itemCartLayoutPresence);

		Boolean itemPresentInCartPage = itemCartLayout.stream()
				.anyMatch(a -> a.findElement(By.tagName("h3")).getText().contains(productName.toUpperCase()));
		return itemPresentInCartPage;
	}

	public CheckOutPage checkout() {
		checkoutButton.click();
		return new CheckOutPage(driver);
	}

}
