package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider= "getData", groups= {"ErrorValidate"})
	public void submitOrder(HashMap<String, String> input) throws IOException
	{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		productCatalogue.getProductsList();                  //Will wait for product to appear and then getting the list of products
		productCatalogue.addProductToCart(input.get("productName"));        //Add product to cart
		CartPage cartPage = productCatalogue.goToCart();                       //click on cart to go to Cart Page
		
		Boolean match = cartPage.verifyProductdisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goTocheckout();		
		
		checkoutPage.selectCountry("India"); 
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistory()
	  {
		ProductCatalogue productCatalogue = landingPage.loginApplication("ronit123@gmail.com", "Ronit@123");
		OrderPage orderPage = productCatalogue.goToOrder();
		Assert.assertTrue(orderPage.verifyOrderdisplay(productName));
	  	
	  }
	
	
	
	 //***Using HashMap with Json
	 @DataProvider
	  public Object[][] getData() throws IOException                                                
	  {
		 List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	  }
	 
	
	/*
	
	 //***Without HashMap
	  @DataProvider     
	  public Object[][] getData()
	    {
		return new Object[][] {{"ronit123@gmail.com", "Ronit@123", "ZARA COAT 3"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" }};
	    }
	    
	    
	 //***Using HashMap  
	  @DataProvider
	  public Object[][] getData()
	  {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "ronit123@gmail.com");
		map.put("password", "Ronit@123");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map}, {map1}};
	  }
	 
    */
}
