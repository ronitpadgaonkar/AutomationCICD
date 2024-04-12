package cucumberStepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class StepDefinitionImplement extends BaseTest {
	
	 public LandingPage landingPage;
	 public ProductCatalogue productCatalogue;
	 public ConfirmationPage confirmationPage;
	
	 @Given("I landed on Ecommerce page")
	 public void  I_landed_on_Ecommerce_page() throws IOException
	 {
		 landingPage = launchApplication();
	 }
	 
	 
	 @Given("^Logged in with Username (.+) and Password (.+)$")
	 public void Logged_in_with_Username_and_Password (String username, String password)
	 {
		  productCatalogue = landingPage.loginApplication(username, password);
	 }
	 
	 
	 @When("^I add product (.+) to Cart$")
	 public void I_add_product_to_Cart(String productName)
	 {
		    productCatalogue.getProductsList();                 
		    productCatalogue.addProductToCart(productName);       
	 }
	 
	 
	 @And("^Checkout (.+) and submit the order$")
	 public void Checkout_and_submit_the_order(String productName)
	 {
		    CartPage cartPage = productCatalogue.goToCart();                       //click on cart to go to Cart Page
			
			Boolean match = cartPage.verifyProductdisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartPage.goTocheckout();		
			
			checkoutPage.selectCountry("India"); 
			confirmationPage = checkoutPage.submitOrder();
	 }
	 
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_on_ConfirmationPage(String string)
	 {
		    String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();	
	 }
	 
	 
	 @Then("{string} message is displayed")
	 public void message_displayed_on(String string)
	 {
			Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
			driver.close();	
	 }

}
