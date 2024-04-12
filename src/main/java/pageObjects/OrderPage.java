package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	
	//***Constructor
	public OrderPage(WebDriver driver)       //Invoking driver in this class from SubmitOrderTest class
	  {
		super(driver);         //Invoking parent class driver
		this.driver=driver;       //Assign the driver from SubmitOrderTest class to this Class
		PageFactory.initElements(driver, this);         //Initializing all the WebElements in @FindBy (PageFactory) using driver argument send by the method in other class
	  }
	
	
	//***Page Factory
	//List<WebElement> cart = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	public Boolean verifyOrderdisplay (String productName)
	  {
		Boolean match =productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName)); 
		return match;
	  }
	
	
	
	
	
	
}
