package abstractComponents;

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

import pageObjects.CartPage;
import pageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver)     //Constructor
	{
		this.driver=driver;
		PageFactory.initElements(driver, this); 
	}
	
	
	//***Page Factory (driver.elements)
    //driver.findElement(By.cssSelector("[routerlink*='cart']"))
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public CartPage goToCart()
	  {
		cartHeader.click(); 
		
		return new CartPage(driver);
	  }
	
	public OrderPage goToOrder()
	  {
		orderHeader.click(); 
		
		return new OrderPage(driver);
	  }
	
	
	public void waitForElementToAppear(By findBy)
	  {	
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	  }
	
	
	public void waitForWebElementToAppear(WebElement findBy)
	  {	
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOf(findBy));
	  }
	
	
	public void waitForElementToDisappear(WebElement ele)
	  {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	  }
	
	
	
	
	
	

}
