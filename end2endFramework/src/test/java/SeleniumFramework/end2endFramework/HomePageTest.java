package SeleniumFramework.end2endFramework;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import SeleniumFramework.end2endFramework.HomePage;

public class HomePageTest 
{
	
	Properties props = new Properties();
	private String propertiesFilesLocation = "/home/saikrishna/Practical/selenium/endToendFramework/data.properties";

	
	WebDriver driver;
	HomePage homeObject;
	
	@BeforeTest()
	public void settingTestProperties() throws IOException   {
		
		FileInputStream fileInputStream = new FileInputStream(propertiesFilesLocation);
		
		props.load(fileInputStream);	
		
		System.setProperty("webdriver.chrome.driver", props.getProperty("chromeDriverLocation"));
		
		driver.get(props.getProperty("url"));
		
		homeObject = new HomePage(driver);
	}
	
	@Test
    public void orderVegetablesTest()
    {
       homeObject.addVegetables("Brocolli", 5);
		
		Assert.assertEquals(600, homeObject.totalPrice());
	}
}
