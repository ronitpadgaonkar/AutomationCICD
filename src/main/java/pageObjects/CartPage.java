package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	//***Constructor
	public CartPage(WebDriver driver)       //Invoking driver in this class from SubmitOrderTest class
	  {
		super(driver);         //Invoking parent class driver
		//initialization
		this.driver=driver;       //Assign the driver from SubmitOrderTest class to this Class
		PageFactory.initElements(driver, this);         //Initializing all the WebElements in @FindBy (PageFactory) using driver argument send by the method in other class
	  }
	
	
	//***Page Factory
	//List<WebElement> cart = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//*[@class='totalRow']/button")
	WebElement checkout;
	
	
	public Boolean verifyProductdisplay (String productName)
	  {
		Boolean match =cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName)); 
		return match;
	  }
	
	
	public CheckoutPage goTocheckout()
	  {
		checkout.click();
		
		return new CheckoutPage(driver);
	  }
	
	
	
}
