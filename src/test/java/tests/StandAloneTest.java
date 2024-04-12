package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		String productName = "ZARA COAT 3";
		driver.findElement(By.id("userEmail")).sendKeys("ronit123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ronit@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));     //Waiting for page to load after login
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement product = products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);        //Using Streams to find the element. {findFirst() will return the first value and if it doesn't find the value then orElse() will return null}
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();         //click on product
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));        //Waiting for pop-up to appear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));        //Waiting for loading animation to be invisible
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();             //click on cart
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match =cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));               //anyMatch will give an boolean output (T/F)
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//*[@class='totalRow']/button")).click();       //click on checkout
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//section/button[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();         //click on place order
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
		/*
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		List<WebElement> country = driver.findElements(By.cssSelector(".ta-item span"));
		WebElement element = country.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		element.click();
        driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
        */
	}

}
