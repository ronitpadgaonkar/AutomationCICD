package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	
	public WebDriver initializeDriver() throws IOException
	 {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//Using Java Ternary Operator to use Global Parameters coming from Maven command or else to use default from GlobalData.properties 
		String browserName =System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = System.getProperty("user.dir");
		
		
		if (browserName.contains("chrome"))
		   {
		     WebDriverManager.chromedriver().setup();
		     ChromeOptions options = new ChromeOptions();
		     
		     if(browserName.contains("headless")) 
		     {
		    	 options.addArguments("headless");
		     }
		     driver = new ChromeDriver(options);
		     driver.manage().window().setSize(new Dimension(1440,900));     //Running full screen in headless mode
		   }
		
		else if(browserName.equalsIgnoreCase("firefox"))
		   {
			          //firefox
	   	   }  
		
		else if(browserName.equalsIgnoreCase("edge"))
		   {
			          //edge
			   System.setProperty("webDriver.edge.driver", "C:\\Users\\ronit\\Downloads\\Course-AutomationTesting\\Files\\edgedriver\\msedgedriver.exe");
			   driver = new EdgeDriver();
	   	   }
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
   	 }
	
	
	//Getting data from json file and returning data as a list
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
		//Read Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//Convert String to HashMap using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
	}
	
	
	public String getScreenshot (String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)               //TestNG will always run this method no matter what condition(grouping, include, exclude) is given 
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);     // create the object of other class to invoke driver in that class
		landingPage.goTo();
		return landingPage;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void closingBrowser()
	{
		driver.close();
	}


}
