package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	//***Constructor
	public LandingPage(WebDriver driver)       //Invoking driver in this class from SubmitOrderTest class
	{
		super(driver);         //Invoking parent class driver
		//initialization
		this.driver=driver;       //Assign the driver from SubmitOrderTest class to this Class
		PageFactory.initElements(driver, this);         //Initializing all the WebElements in @FindBy (PageFactory) using driver argument send by the method in other class
	}
	
	
	//***Page Factory
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
    @FindBy(id="login")
    WebElement submit;
    
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;
    
    //***Actions Method
    public ProductCatalogue loginApplication(String email, String password)
      {
    	userEmail.sendKeys(email);
    	userPassword.sendKeys(password);
    	submit.click();
    	
    	ProductCatalogue productCatalogue = new ProductCatalogue (driver);
    	return productCatalogue;
      }
    
    
    public void goTo()
      {
    	driver.get("https://rahulshettyacademy.com/client");
      }
    
    
    public String getErrorMessage()
      {
    	waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
      }
	
	
}
