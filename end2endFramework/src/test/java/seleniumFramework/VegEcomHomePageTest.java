package seleniumFramework;

import org.testng.annotations.Test;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import resources.Configurations;
import seleniumFramework.CheckOut;
import seleniumFramework.CheckOutTwo;

public class VegEcomHomePageTest extends Configurations {
	WebDriver driver;
	VegEcomHomePage vegEcomHomeObject;
	CheckOut checkOutObject;
	CheckOutTwo checkOutTwoObject;
	Properties props = new Properties();
	String location = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";
	
	String currentUpdatedUrl;
	
	private Logger log = LogManager.getLogger(VegEcomHomePageTest.class.getName());
	
	@BeforeTest()
	public void settingsBeforeTest() throws IOException {
		
		FileInputStream fileInputStream = new FileInputStream(location);
		props.load(fileInputStream);
		this.driver = super.settingTestProperties(driver, location, props);
		log.info("Driver is initialized");
	}

	@Test
	public void orderVegetablesTest() {
		/*
		 * This test is to add the vegetables to the cart and test if the total price is correct.
		 */
		
		driver.get(props.getProperty("homeUrl"));
		
		driver.manage().window().maximize();
		
		vegEcomHomeObject = new VegEcomHomePage(driver);

		vegEcomHomeObject.addVegetables("Brocolli", 6);

		Assert.assertEquals(720, vegEcomHomeObject.totalPrice());
		Assert.assertEquals(1, vegEcomHomeObject.totalItems());
		
		vegEcomHomeObject.addVegetables("Cucumber", 5);
		
		Assert.assertEquals(2, vegEcomHomeObject.totalItems());
		Assert.assertTrue(vegEcomHomeObject.totalPrice()>800);
		
		vegEcomHomeObject.addVegetables("Brinjal", 2);
		Assert.assertEquals(3, vegEcomHomeObject.totalItems());
		
		log.info("Sucessfully validated the price and cost computations of ordered Items");
		
		driver.findElement(vegEcomHomeObject.checkOut()).click();
		
		currentUpdatedUrl = driver.getCurrentUrl();
		
		
		
		
}
		
	
//	@Test(dependsOnMethods ={"orderVegetablesTest"})
//	public void removeVegetablesTest() throws IllegalArgumentException, InterruptedException{
//		
//	vegEcomHomeObject = new VegEcomHomePage(driver);
//	
//	List<String> xpathforRemoveingElements = vegEcomHomeObject.removeItemsElement("Brocolli", "Brinjal");
//	
//	for (String string : xpathforRemoveingElements) {
//		
//		driver.findElement(By.xpath(string)).click();
//		
//	}
//	
//	Assert.assertEquals(vegEcomHomeObject.totalItems(), 1);
//	
//	driver.close();
//		
//	}
//	
	@Test(timeOut = 5000)
	public void searchProducts() {
		
		/*
		 * This is to search vegetables in the search bar and then verify if the desired products have been displayed
		 */
		
		driver.navigate().refresh();
		driver.get(props.getProperty("homeUrl"));
		
		driver.manage().window().maximize();
		
		vegEcomHomeObject = new VegEcomHomePage(driver);
		
		List<String> displayedProducts = vegEcomHomeObject.searchByLetter("br", props);
		
		assertTrue(displayedProducts.contains("Brocolli"), "The search results display brocolli");
		assertTrue(displayedProducts.contains("Brinjal"), " The search results display brinjal");	
	}
	
	
	@Test(dependsOnMethods = {"orderVegetablesTest"} )
	public void checkOutTest() {
		
		driver.get((currentUpdatedUrl));
		
		checkOutObject = new CheckOut(driver);
		
		driver.findElement(checkOutObject.promoCode()).sendKeys("rahulshettyacademy");
		
		driver.findElement(checkOutObject.applyPromoCode()).click();
		
		String discountValue = driver.findElement(checkOutObject.discount()).getText();
		
		Assert.assertTrue(discountValue.contains("10"), "The promocode giving us the discount");
		
		driver.findElement(checkOutObject.placeOrder()).click();
		
		currentUpdatedUrl = driver.getCurrentUrl();
		
		
	}
	
	@Test(dependsOnMethods = {"checkOutTest"})
	public void checkOutTwoTest() {
		
		driver.get(currentUpdatedUrl);
		
		checkOutTwoObject = new CheckOutTwo(driver);
		
		checkOutTwoObject.dropDown("Germany");
		
		driver.findElement(checkOutTwoObject.termsAgreement()).click();
		
		driver.findElement(checkOutTwoObject.proceedButton()).click();
		
		currentUpdatedUrl = driver.getCurrentUrl();
		
		
	}
	
	
	
	
	
	@AfterTest
	public void closeWeb() {
		// This closes the opened web application and saves up the memory.
		driver.close();
		
	}
}
