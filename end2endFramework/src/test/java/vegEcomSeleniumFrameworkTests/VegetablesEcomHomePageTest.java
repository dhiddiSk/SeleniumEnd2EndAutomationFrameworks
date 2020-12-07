package vegEcomSeleniumFrameworkTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import resources.Configurations;
import vegEcomSeleniumFramework.CheckOut;
import vegEcomSeleniumFramework.CheckOutTwo;
import vegEcomSeleniumFramework.VegEcomHomePage;
import vegEcomSeleniumFramework.VegetablesEcomHomePage;

public class VegetablesEcomHomePageTest extends Configurations {
	
	WebDriver driver;
	VegetablesEcomHomePage vegEcomHomeObject;
	CheckOut checkOutObject;
	CheckOutTwo checkOutTwoObject;
	WebDriverWait w;
	Properties props = new Properties();
	String location = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";
	private static Logger logger = LogManager.getLogger(VegetablesEcomHomePageTest.class.getName());

	@BeforeTest()
	public void vegetablesEcomHomePage_settingsBeforeTest() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(location);
		props.load(fileInputStream);
		this.driver = super.settingTestProperties(driver, props);
		logger.info("VegetablesEcomHomePageTest webDriver is initialized with respect to the given browser");
	}
	
	@Test
	public void vegetablesEcomHomePage_orderVegetablesTest() {
		driver.get(props.getProperty("homeUrl"));

		driver.manage().window().maximize();
		
		vegEcomHomeObject = new VegetablesEcomHomePage(driver);
		
		addVegetablesToCart("Brocolli", 6);
		
		AssertJUnit.assertEquals(720, vegEcomHomeObject.vegetablesEcomHomePage_totalPrice());
		logger.debug("mismatch between vegetables selected and the total price");

		AssertJUnit.assertEquals(1, vegEcomHomeObject.vegetablesEcomHomePage_totalItems());
		logger.debug("mismatch between items selected and the total items in the cart");

		addVegetablesToCart("Cucumber", 5);

		AssertJUnit.assertEquals(2, vegEcomHomeObject.vegetablesEcomHomePage_totalItems());
		logger.debug("mismatch between items selected and the total items in the cart");
		AssertJUnit.assertTrue(vegEcomHomeObject.vegetablesEcomHomePage_totalPrice() > 800);
		logger.debug("mismatch between vegetables selected and the total price");

		addVegetablesToCart("Brinjal", 2);
		AssertJUnit.assertEquals(3, vegEcomHomeObject.vegetablesEcomHomePage_totalItems());

		logger.info("Validated the price and cost computations of ordered Items");


	}
	
	private void addVegetablesToCart(String vegetableName, int quantity ) throws IllegalArgumentException, NullPointerException{
		// TODO Auto-generated method stub
		try {
			
			List<WebElement> buttons = driver.findElements(vegEcomHomeObject.vegetablesEcomHomePage_productActionButtons());

			List<WebElement> items = driver.findElements(vegEcomHomeObject.vegEcomHomePage_productNames());
					
			String[] vegetableNames;

			for (int i = 0; i < items.size(); i++) {

				vegetableNames = items.get(i).getText().split("-");
				String name = vegetableNames[0].trim();

				if (name.equals(vegetableName)) {

					for (int j = 1; j <= quantity; j++) {
						buttons.get(i).click();
					}
				}
			}

		}

		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception: ", e);
		}
	}

	@Test(dependsOnMethods = { "orderVegetablesTest" })
	public void vegetablesEcomHomePage_removeVegetablesTest() throws InterruptedException {
		
		List<String> xpathforRemoveingElements = removeVegetableItems("Brocolli", "Brinjal");

		for (String string : xpathforRemoveingElements) {

			driver.findElement(By.xpath(string)).click();

		}

		AssertJUnit.assertEquals(vegEcomHomeObject.vegetablesEcomHomePage_totalItems(), 1);
		
	}
	
	private List<String> removeVegetableItems(String... strings) throws InterruptedException {
		// TODO Auto-generated method stub
		if (strings.length <= 0) {
			return null;
		} else {

			// Get all the webelements of cart items into a list<WebElement>, use that list
			// to verify with the given strings(items) to be removed.

			driver.findElement(vegEcomHomeObject.vegetablesEcomHomePage_basketElement()).click();
					
			Thread.sleep(2000);

			if (driver.findElement(vegEcomHomeObject.cartItems()).isDisplayed()) {

				List<WebElement> cartItemsWebElements = driver.findElements(vegEcomHomeObject.cartItem());

				// Now use an cartItems to store the product string and its position. Since all
				// the products have the same remove tag, it's better to know the serial number
				// of every product in cart
				HashMap<String, Integer> cartItems = new HashMap<String, Integer>();

				int itemSerialNumbersInCart = 0;

				if (cartItemsWebElements.size() > 0) {
					for (WebElement element : cartItemsWebElements) {
						itemSerialNumbersInCart += 1;

						String productName = element.findElement(vegEcomHomeObject.productName()).getText();

						String[] produtNames;

						produtNames = productName.split("-");
						String name = produtNames[0].trim();
						cartItems.put(name, itemSerialNumbersInCart);
					}
				}

				List<String> removeElementXpaths = new ArrayList<>();

				for (String itemToBeRemoved : strings) {

					try {

						int position = cartItems.get(itemToBeRemoved);

						// System.out.println(position);

						String cartItemsXpath = "//ul[@class ='cart-items']/li[pos]/a";

						// StringBuilder builder = new StringBuilder(cartItemsXpath);

						String target = "pos";

						String replacement = Integer.toString(position);

						String finalProssessed = cartItemsXpath.replace(target, replacement);

						// System.out.println(finalProssessed);

						removeElementXpaths.add(finalProssessed);

					}

					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

				// this list can be used by the caller, and perform click operation in the list
				// to remove from the cart.
				return removeElementXpaths;
			} else {
				return null;
			}
		}
	}
	
	public void vegetablesEcomHomePage_validateSearchProductsTest() {

		/*
		 * This is to search vegetables in the search bar and then verify if the desired
		 * products have been displayed
		 */

		driver.get(props.getProperty("homeUrl"));

		driver.manage().window().maximize();

		vegEcomHomeObject = new VegetablesEcomHomePage(driver);

		SoftAssert softAssert = new SoftAssert();

		List<String> displayedProducts = searchVegetablesByLetters("br", props);

		AssertJUnit.assertTrue("The search results display brocolli", displayedProducts.contains("Brocolli"));
		AssertJUnit.assertTrue(" The search results display brinjal", displayedProducts.contains("Brinjal"));

		logger.debug("Search bar is not displaying all the expected products for given key charachters");

		logger.info("All the expected products are displayed");

	
	
	}
	private List<String> searchVegetablesByLetters(CharSequence charachters, Properties props) {
		// TODO Auto-generated method stub

		if (charachters.length() <= 0 || charachters.equals(null)) {

			return null;
		}

		List<String> displayedProducts = new ArrayList<>();

		List<WebElement> items = new ArrayList<WebElement>();

		driver.findElement(By.xpath("//div[@class = 'search']/form/input")).sendKeys(charachters);

		driver.findElement(By.xpath("//div[@class = 'search']/form/button")).click(); // here the page get's refreshed

		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("products")));

		try {
			items = driver.findElements(By.cssSelector("h4[class='product-name']"));
		} catch (StaleElementReferenceException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		String[] vegetableNames;

		for (int i = 0; i < items.size(); i++) {

			vegetableNames = items.get(i).getText().split("-");
			String vegetableName = vegetableNames[0].trim();
			displayedProducts.add(vegetableName);
		}

		return displayedProducts;
	}

	@AfterTest
	public void vegetablesEcomHomePage_settingsAfterTest() {
		// This closes the opened web application and saves up the memory.
				driver.close();

		
	}
	
	

}
