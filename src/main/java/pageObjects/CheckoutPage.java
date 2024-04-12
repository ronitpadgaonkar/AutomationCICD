package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
WebDriver driver;
	
	//***Constructor
	public CheckoutPage(WebDriver driver)       //Invoking driver in this class from SubmitOrderTest class
	  {
		super(driver);         //Invoking parent class driver
		//initialization
		this.driver=driver;       //Assign the driver from SubmitOrderTest class to this Class
		PageFactory.initElements(driver, this);         //Initializing all the WebElements in @FindBy (PageFactory) using driver argument send by the method in other class
	  }
	
	
	//***Page Factory
	//driver.findElement(By.cssSelector(".form-group input"))
	@FindBy(css=".form-group input")
	WebElement country;
	
	@FindBy(xpath="//section/button[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	//Objects of By.locators (By findBy) used for wait elements
    By results = By.cssSelector(".ta-results");
    
    
    public void selectCountry(String countryName)
      {
    	Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
      }
    
    
    public ConfirmationPage submitOrder()
      {
    	submit.click();
    	
    	return new ConfirmationPage(driver);
      }

}
