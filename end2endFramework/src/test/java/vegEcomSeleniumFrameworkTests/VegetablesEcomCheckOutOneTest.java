package vegEcomSeleniumFrameworkTests;

import org.testng.annotations.Test;
import vegEcomSeleniumFramework.VegtablesEcomCheckOutOne;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;

public class VegetablesEcomCheckOutOneTest {

	Properties props = new Properties();
	private String propertiesFilesLocation = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";

	//private static Logger log;
	
	WebDriver driver;
	VegtablesEcomCheckOutOne checkOut;
	WebDriverWait w;
	private static Logger logger = LogManager.getLogger(VegetablesEcomCheckOutOneTest.class.getName());

	
	@Parameters({"promocode"})
	@Test(timeOut = 10000)
	public void vegetablesEcomCheckOutOneTest_promoCodeTest(String code) {
		
		driver.get(props.getProperty("cartUrl"));
		logger.info("The property of driver is set to the given url: "+props.getProperty("cartUrl"));
		logger.debug("The property of driver is set to the given url: "+props.getProperty("cartUrl"));
		
		System.out.println(props.getProperty("cartUrl"));
				
		checkOut = new VegtablesEcomCheckOutOne();
		
		w = new WebDriverWait(driver, 3);
		
		// The webdriver waits until the promocode webelment is visible
		w.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoCode")));
		
		driver.findElement(checkOut.promoCode()).sendKeys(code);
		
		logger.info("promocode is applied");
		
		logger.debug("The applied promoCode is applied"+ code  );
		
		driver.findElement(checkOut.applyPromoCode()).click();
		
		logger.info("The apply promocode button is clicked");
		
		String style = driver.findElement(checkOut.verifyPromoApplicabilty()).getAttribute("style");
		
		if(style.contains("red")){
			
			String errorMessage = "The entered promoCode is invalid, please retry with correct one's";
			
			logger.debug("The entered promoCode "+code+"is invalid, please retry with correct one's");
			
		//	log.error(errorMessage);
			
			Assert.assertTrue(false);	
			
		}		
		
	}
	
	
	@Test
	public void vegetablesEcomCheckOutOneTest_placeOrderTest() {
		
		driver.findElement(checkOut.placeOrder()).click();
		logger.info("The order is placed");
		logger.debug("The webelement of placeorder is not valid");
	}	

}
