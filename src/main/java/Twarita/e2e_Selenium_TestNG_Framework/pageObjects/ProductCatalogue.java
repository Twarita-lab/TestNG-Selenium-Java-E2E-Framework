package Twarita.e2e_Selenium_TestNG_Framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Twarita.e2e_Selenium_TestNG_Frameworks.utilities.Utility_Or_AbstractClassed;

public class ProductCatalogue extends Utility_Or_AbstractClassed{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".card-body") WebElement productCards;
	@FindBy(xpath= "//div[@class='card-body']") List<WebElement> cards;	
	@FindBy(css = ".ng-animating") WebElement spinner;
	@FindBy(css = ".toast-container div") WebElement successMessage;
	
	By addToCartButton = By.xpath("button[2]");
	
	public WebElement getProduct(String productName) {
		WaitForVisibilityOfAllElements(productCards);
		return cards.stream().filter(items->items.findElement(By.tagName("h5")).getText().contains(productName.toUpperCase())).findFirst().orElse(null);
				
	};
	
	public void addProductToCart(String productName) {
		getProduct(productName).findElement(addToCartButton).click();
		WaitForInvisibilityOfElement(spinner);
		WaitForInvisibilityOfElement(successMessage);

	}
	

}
