package Twarita.e2e_Selenium_TestNG_Framework.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class E2E_Standalone_Test {
	
	public static void main(String[] Args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebDriverWait waitE = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		String product = "ZARA COAT";
		String countryName="India";
		
		WebElement loginEmail = driver.findElement(By.cssSelector("input#userEmail"));
		WebElement loginPwd = driver.findElement(By.cssSelector("input#userPassword"));
		WebElement loginButton = driver.findElement(By.cssSelector("input#login"));
		loginEmail.sendKeys("testT@mail.com");
		loginPwd.sendKeys("Password1!");
		loginButton.click();
		
		waitE.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".card-body"))));
		List<WebElement> cards = driver.findElements(By.xpath("//div[@class='card-body']"));
		cards.stream().filter(items->items.findElement(By.tagName("h5")).getText().contains(product))
					.forEach(items->items.findElement(By.xpath("button[2]")).click());
		
		waitE.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		waitE.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".toast-container div")));
		
		WebElement cartIcon = driver.findElement(By.cssSelector("button[routerlink*='cart']"));
		cartIcon.click();
		
		waitE.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.items")));
		List<WebElement> itemCartLayout = driver.findElements(By.cssSelector("li.items"));
		
		Boolean itemPresentInCartPage = itemCartLayout.stream().anyMatch (a->a.findElement(By.tagName("h3")).getText().contains(product));
		Assert.assertTrue(itemPresentInCartPage);
		
		WebElement checkoutButton = driver.findElement(By.cssSelector(".subtotal button"));
		checkoutButton.click();
		
		WebElement countryTextBox = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		countryTextBox.sendKeys(countryName);
		
		waitE.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ng-star-inserted span")));
		
		List<WebElement> countryList = driver.findElements(By.cssSelector("section.ng-star-inserted span"));
		countryList.stream().filter(country->country.getText().equalsIgnoreCase(countryName)).forEach(a->a.click());
		
		WebElement placeOrderButton = driver.findElement(By.cssSelector(".action__submit"));
		placeOrderButton.click();
		
		waitE.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label.ng-star-inserted")));
		String successMessage = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertTrue(successMessage.equalsIgnoreCase("Thankyou for the order."));
		String orderNumber = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText().split(" ")[1];
		System.out.println(orderNumber);
		
		driver.quit();
		
	}

}
