package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorValidate"}, retryAnalyzer= Retry.class)
	public void LoginErrorValidations() throws IOException
	{
		
		landingPage.loginApplication("ronit123@gmail.com", "Ronit@1234");
		Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
	
	}
	
	
	@Test
	public void ProductErrorValidations() throws IOException
	{
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("ronit123@gmail.com", "Ronit@123");
		productCatalogue.getProductsList();                  //Will wait for product to appear and then getting the list of products
		productCatalogue.addProductToCart(productName);        //Add product to cart
		CartPage cartPage = productCatalogue.goToCart();                       //click on cart to go to Cart Page
		Boolean match = cartPage.verifyProductdisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
	}


}
