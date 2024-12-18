package Twarita.e2e_Selenium_TestNG_Framework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Twarita.e2e_Selenium_TestNG_Framework.testComponent.Retry;

import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.CartPage;
import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.CheckOutPage;
import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.OrderPage;
import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.OrderSuccessPage;
import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.ProductCatalogue;
import Twarita.e2e_Selenium_TestNG_Framework.testComponent.BaseTests;

public class SubmitOrderTest extends BaseTests{

	String product = "Adids";
	String countryName="India";
	String email = "testT@mail.com";
	String pwd = "Password1!";
	
	
	@Test(groups = "Sanity", dataProvider="getData", retryAnalyzer = Retry.class)
	public void submitOrder(String email, String pwd, String product, String countryName) throws IOException {
		
		
		String orderSucessMessage = "Thankyou for the order.";
		
		ProductCatalogue productsPage = landingPage.login(email, pwd);
		productsPage.addProductToCart(product);
		CartPage cartPage = productsPage.goToCartPage();		
		
		boolean productAddedToCart = cartPage.verifyProductAddedTocart(product);
		Assert.assertTrue(productAddedToCart);		
		
		CheckOutPage checkout = cartPage.checkout();
		OrderSuccessPage orderSuccessPage = checkout.checkOutWithCountryName(countryName);
		
		boolean successMessageMatchs = orderSuccessPage.validateOrderPlaced(orderSucessMessage);	
		Assert.assertTrue(successMessageMatchs);
		
		System.out.println(orderSuccessPage.GetOrderNumber());
		
	}
	
	@Test(dependsOnMethods = "submitOrder",dataProvider="getData", groups = "Sanity")
	public void verifyOrderPlaced(String email, String pwd, String product, String countryName) {
		
		ProductCatalogue productsPage = landingPage.login(email, pwd);
		OrderPage orderPage = productsPage.goToOrderPage();
		boolean orderPlaced = orderPage.verifyOrderPlaced(product);
		Assert.assertTrue(orderPlaced);		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {
				{"testT@mail.com", "Password1!", "Zara Coat", "India"}, 
				{"testTC@mail.com", "Password1!", "Addas", "Australia"}
			};
		}
	

}
