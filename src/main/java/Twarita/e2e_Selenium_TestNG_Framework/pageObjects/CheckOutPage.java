package Twarita.e2e_Selenium_TestNG_Framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Twarita.e2e_Selenium_TestNG_Frameworks.utilities.Utility_Or_AbstractClassed;

public class CheckOutPage extends Utility_Or_AbstractClassed {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement countryTextBox;
	@FindBy(css = "section.ng-star-inserted span")
	List<WebElement> countryList;
	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;

	By ListedCountry = By.cssSelector("section.ng-star-inserted span");

	public OrderSuccessPage checkOutWithCountryName(String countryName) {
		countryTextBox.sendKeys(countryName);
		waitForVisibilityOfElementLocated(ListedCountry);
		countryList.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).forEach(a -> a.click());
		placeOrderButton.click();
		OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);
		return orderSuccessPage;
	}

}
