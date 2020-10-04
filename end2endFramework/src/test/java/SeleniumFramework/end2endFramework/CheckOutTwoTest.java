package SeleniumFramework.end2endFramework;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckOutTwoTest {

	Properties props = new Properties();
	private String propertiesFilesLocation = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/data.properties";

	WebDriver driver;
	CheckOutTwo checkOutTwo;

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
//	}
//	
	
	@Test(timeOut = 4000)
	public void proceedTest() {
		
		driver.get(props.getProperty("cartTwoUrl"));
		
		checkOutTwo = new CheckOutTwo(driver);
		
		checkOutTwo.dropDown("Germany");
		
		driver.findElement(checkOutTwo.termsAgreement()).click();
		
		driver.findElement(checkOutTwo.proceedButton()).click();
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
