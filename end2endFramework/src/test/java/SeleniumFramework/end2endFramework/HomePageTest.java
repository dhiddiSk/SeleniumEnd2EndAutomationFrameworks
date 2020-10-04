package SeleniumFramework.end2endFramework;

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
import SeleniumFramework.end2endFramework.HomePage;
import net.bytebuddy.implementation.bind.annotation.Super;

public class HomePageTest extends Configuration {
	WebDriver driver;
	HomePage homeObject;
	Properties props = new Properties();
	String location = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/centralData.properties";
	
	public static Logger log = LogManager.getLogger(HomePageTest.class.getName());
	
	@BeforeTest()
	public void settingsBeforeTest() throws IOException {
		
		FileInputStream fileInputStream = new FileInputStream(location);
		props.load(fileInputStream);
        
		System.out.println(location);
		
		this.driver = super.settingTestProperties(driver, location, props);
	}

	@Test
	public void orderVegetablesTest() {
		
		driver.get(props.getProperty("homeUrl"));
		
		driver.manage().window().maximize();
		
		homeObject = new HomePage(driver);

		homeObject.addVegetables("Brocolli", 5);

		AssertJUnit.assertEquals(600, homeObject.totalPrice());

		log.info("Sucessfully validated the price computation");
		log.info("Sucessfully validated the price computation");
		log.info("Sucessfully validated the price computation");
		log.info("Sucessfully validated the price computation");
		log.info("Sucessfully validated the price computation");
		log.info("Sucessfully validated the price computation");
		log.info("Sucessfully validated the price computation");
		log.info("Sucessfully validated the price computation");
		
//		driver.findElement(By.cssSelector("[alt='Cart']")).click();
//		
//		driver.findElement(By.xpath("//*[@class='action-block']//button")).click();
		
		//Now update the properties value of the cartUrl
		
//		String latestUrl = driver.getCurrentUrl();
		
//		props.replace("cartUrl", props.getProperty("cartUrl"), latestUrl);
	}
	
	
	@AfterTest
	public void closeWeb() {
		// This closes the opened web application and saves up the memory.
		driver.close();
		
	}
}
