package seleniumFramework;

import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import seleniumFramework.CheckOut;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckOutTest {

	Properties props = new Properties();
	private String propertiesFilesLocation = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";

	//private static Logger log;
	
	WebDriver driver;
	CheckOut checkOut;
	WebDriverWait w;

//	@BeforeTest()
//	public void settingTestProperties() throws IOException {
//
//		FileInputStream fileInputStream = new FileInputStream(propertiesFilesLocation);
//		props.load(fileInputStream);
//
//		if (props.getProperty("browser").equals("chrome")) {
//
//			driver = new ChromeDriver();
//
//			System.setProperty("webdriver.chrome.driver", props.getProperty("driverLocation"));
//
//		}
//
//		else if (props.getProperty("browser").equals("mozilla")) {
//
//			driver = new FirefoxDriver();
//
//			System.setProperty("webdriver.gecko.driver", props.getProperty("driverLocation"));
//
//		}
//
//		else if (props.getProperty("browser").equals("IE")) {
//
//			driver = new InternetExplorerDriver();
//
//			System.setProperty("webdriver.ie.driver", props.getProperty("driverLocation"));
//
//		}
//		
//		w = new WebDriverWait(driver, 5);
//		
//		//log = LogManager.getLogger(CheckOutTest.class);
//
//	}
	
	@Parameters({"promocode"})
	@Test(timeOut = 10000)
	public void cartTest(String code) {
		
		driver.get(props.getProperty("cartUrl"));
		
		System.out.println(props.getProperty("cartUrl"));
		
		//checkOut = new CheckOut(driver);
		
		checkOut = new CheckOut();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoCode")));
		
		driver.findElement(checkOut.promoCode()).sendKeys(code);
		
		driver.findElement(checkOut.applyPromoCode()).click();
		
		String style = driver.findElement(checkOut.verifyPromoApplicabilty()).getAttribute("style");
		
		if(style.contains("red")){
			
			String errorMessage = "The entered promoCode is invalid, please retry with correct one's";
			
		//	log.error(errorMessage);
			
			Assert.assertTrue(false);	
			
		}		
		
//		driver.findElement(checkOut.placeOrder()).click();
//		
//		String latestUrl = driver.getCurrentUrl();
//		
//		props.setProperty("cartTwoUrl", latestUrl);
	}
	

}
