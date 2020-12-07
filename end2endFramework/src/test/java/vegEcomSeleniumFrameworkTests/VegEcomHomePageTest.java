package vegEcomSeleniumFrameworkTests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import resources.Configurations;
import vegEcomSeleniumFramework.CheckOut;
import vegEcomSeleniumFramework.CheckOutTwo;
import vegEcomSeleniumFramework.VegEcomHomePage;

public class VegEcomHomePageTest extends Configurations {
	WebDriver driver;
	VegEcomHomePage vegEcomHomeObject;
	VegEcomHomePage vegEcomHomeObject2;
	CheckOut checkOutObject;
	CheckOutTwo checkOutTwoObject;
	WebDriverWait w;
	Properties props = new Properties();
	String location = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";

	String currentUpdatedUrl;

	private static Logger log = LogManager.getLogger(VegEcomHomePageTest.class.getName());

	@BeforeTest()
	public void settingsBeforeTest() throws IOException {

		FileInputStream fileInputStream = new FileInputStream(location);
		props.load(fileInputStream);
		this.driver = super.settingTestProperties(driver, props);
		log.info("Driver is initialized with respect to the given browser");
	}

	@Test(groups = "Regression")
	public void orderVegetablesTest() throws InterruptedException {
		/*
		 * This test is to add the vegetables to the cart and test if the total price is
		 * correct.
		 */

		driver.get(props.getProperty("homeUrl"));

		driver.manage().window().maximize();

		vegEcomHomeObject = new VegEcomHomePage(driver);

		vegEcomHomeObject.addVegetablesToCart("Brocolli", 6);

		AssertJUnit.assertEquals(720, vegEcomHomeObject.totalPrice());
		log.debug("mismatch between vegetables selected and the total price");

		AssertJUnit.assertEquals(1, vegEcomHomeObject.totalItems());
		log.debug("mismatch between items selected and the total items in the cart");

		vegEcomHomeObject.addVegetablesToCart("Cucumber", 5);

		AssertJUnit.assertEquals(2, vegEcomHomeObject.totalItems());
		log.debug("mismatch between items selected and the total items in the cart");
		AssertJUnit.assertTrue(vegEcomHomeObject.totalPrice() > 800);
		log.debug("mismatch between vegetables selected and the total price");

		vegEcomHomeObject.addVegetablesToCart("Brinjal", 2);
		AssertJUnit.assertEquals(3, vegEcomHomeObject.totalItems());

		log.info("Validated the price and cost computations of ordered Items");

	}

	@Test(dependsOnMethods = { "orderVegetablesTest" })
	public void removeVegetablesTest() throws IllegalArgumentException, InterruptedException {

		List<String> xpathforRemoveingElements = vegEcomHomeObject.removeVegetableItems("Brocolli", "Brinjal");

		for (String string : xpathforRemoveingElements) {

			driver.findElement(By.xpath(string)).click();

		}

		AssertJUnit.assertEquals(vegEcomHomeObject.totalItems(), 1);
	}

	@Parameters({ "promocode" })
	@Test(groups = { "smoke" })
	public void checkOutTest(String code) {

		driver.get(props.getProperty("cartUrl"));

		checkOutObject = new CheckOut(driver);

		driver.findElement(checkOutObject.checkOut()).click();

		System.out.println(driver.getCurrentUrl());

		w = new WebDriverWait(driver, 5);

		w.until(ExpectedConditions.visibilityOf(driver.findElement(checkOutObject.promoCode())));

		if (driver.findElement(checkOutObject.promoCode()).isDisplayed()) {

			driver.findElement(checkOutObject.promoCode()).sendKeys(code);

			log.info("The promocode is entered");

			driver.findElement(checkOutObject.applyPromoCode()).click();

			// String discountValue =
			// driver.findElement(checkOutObject.discount()).getText();

			// Assert.assertTrue(discountValue.contains("10"), "The promocode giving us the
			// discount");

			driver.findElement(checkOutObject.placeOrder()).click();
		}

		// currentUpdatedUrl = driver.getCurrentUrl();
	}

	@Test(groups = "smoke")
	public void checkOutTwoTest() {

		driver.get(props.getProperty("afterCartUrl"));

		checkOutTwoObject = new CheckOutTwo(driver);

		checkOutTwoObject.dropDown("Germany");

		driver.findElement(checkOutTwoObject.termsAgreement()).click();

		driver.findElement(checkOutTwoObject.proceedButton()).click();

		log.info("The user has checked out of the cart");

		// currentUpdatedUrl = driver.getCurrentUrl();
	}

	@Test(groups = "smoke")
	public void searchProducts() {

		/*
		 * This is to search vegetables in the search bar and then verify if the desired
		 * products have been displayed
		 */

		driver.get(props.getProperty("homeUrl"));

		driver.manage().window().maximize();

		vegEcomHomeObject = new VegEcomHomePage(driver);

		SoftAssert softAssert = new SoftAssert();

		List<String> displayedProducts = vegEcomHomeObject.searchVegetablesByLetters("br", props);

		AssertJUnit.assertTrue("The search results display brocolli", displayedProducts.contains("Brocolli"));
		AssertJUnit.assertTrue(" The search results display brinjal", displayedProducts.contains("Brinjal"));

		log.debug("Search bar is not displaying all the expected products for given key charachters");

		log.info("All the expected products are displayed");

	}

	@AfterTest
	public void closeWeb() {
		// This closes the opened web application and saves up the memory.
		driver.close();

	}
}
