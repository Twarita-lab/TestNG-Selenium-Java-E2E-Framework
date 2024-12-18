package Twarita.e2e_Selenium_TestNG_Framework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Twarita.e2e_Selenium_TestNG_Framework.pageObjects.ProductCatalogue;
import Twarita.e2e_Selenium_TestNG_Framework.testComponent.BaseTests;

public class ErrorValidationTests extends BaseTests {

	@Test
	public void loginErrorValidation() throws IOException {

		String email = "testT@mail.co";
		String pwd = "Password1!";

		landingPage.login(email, pwd);
		Assert.assertEquals(landingPage.getLoginError(), "Incorrect email or password.");

	}

	@Test
	public void submitOrderFailure() throws IOException {

		String product = "Invalid Product Name";
		String email = "testTC@mail.com";
		String pwd = "Password1!";

		ProductCatalogue productsPage = landingPage.login(email, pwd);
		Assert.assertEquals(productsPage.getProduct(product),null);
		
	}

}
