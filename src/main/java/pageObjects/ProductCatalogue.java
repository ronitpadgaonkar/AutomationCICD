package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	//***Constructor
	public ProductCatalogue(WebDriver driver)       //Invoking driver in this class from SubmitOrderTest class
	{
		super(driver);         //Invoking parent class driver
		//initialization
		this.driver=driver;       //Assign the driver from SubmitOrderTest class to this Class
		PageFactory.initElements(driver, this);         //Initializing all the WebElements in @FindBy (PageFactory) using driver argument send by the method in other class
	}
	
	
	//***Page Factory (driver.elements)
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	//Objects of By.locators (By findBy) used for wait elements
	By productBy = By.cssSelector(".mb-3");       //List of Products
	By addToCart = By.cssSelector(".card-body button:last-of-type");      //Product
	By toastMessage = By.id("toast-container");
	
	
	//***Actions method
	public List<WebElement> getProductsList()
	  {
		waitForElementToAppear(productBy);
		return products;
	  }
	
	
	public WebElement getProductByName(String productName)
	  {
		WebElement product = getProductsList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return product;
	  }
	
	
	public void addProductToCart(String productName)
	  {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		   
	  }
    
   
    
	
	
}
