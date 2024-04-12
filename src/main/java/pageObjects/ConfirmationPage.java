package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
WebDriver driver;
	
	//***Constructor
	public ConfirmationPage(WebDriver driver)       //Invoking driver in this class from SubmitOrderTest class
	  {
		super(driver);         //Invoking parent class driver
		this.driver=driver;       //Assign the driver from SubmitOrderTest class to this Class
		PageFactory.initElements(driver, this);         //Initializing all the WebElements in @FindBy (PageFactory) using driver argument send by the method in other class
	  }
	
	
	//***Page Factory
	//driver.findElement(By.cssSelector(".hero-primary"))
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
		

	public String getConfirmationMessage()
	  {
		  return confirmMessage.getText();
	  }
}
