package seleniumFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import net.bytebuddy.implementation.bind.annotation.Super;
import resources.Configurations;
import seleniumFramework.HomePage;

public class HomePageTest extends Configurations {
	WebDriver driver;
	HomePage homeObject;
	Properties props = new Properties();
	String location = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";
	
	public static Logger log = LogManager.getLogger(HomePageTest.class.getName());
	
	@BeforeTest()
	public void settingsBeforeTest() throws IOException {
		
		FileInputStream fileInputStream = new FileInputStream(location);
		props.load(fileInputStream);
		
        
		//System.out.println(location);
		
		this.driver = super.settingTestProperties(driver, location, props);
		
		log.info("Driver is initialized");
	}

	@Test
	public void orderVegetablesTest() {
		
		driver.get(props.getProperty("homeUrl"));
		
		driver.manage().window().maximize();
		
		homeObject = new HomePage(driver);

		homeObject.addVegetables("Brocolli", 6);

		AssertJUnit.assertEquals(600, homeObject.totalPrice());

		log.info("Sucessfully validated the price computation");
		
//		driver.findElement(By.cssSelector("[alt='Cart']")).click();
//		
//		driver.findElement(By.xpath("//*[@class='action-block']//button")).click();
		
		//Now update the properties value of the cartUrl
		
//		String latestUrl = driver.getCurrentUrl();
		
//		props.replace("cartUrl", props.getProperty("cartUrl"), latestUrl);
		
		//Assert.assertTrue(false);
		
		
		
	}
	
	
	@AfterTest
	public void closeWeb() {
		// This closes the opened web application and saves up the memory.
		driver.close();
		
	}
}
