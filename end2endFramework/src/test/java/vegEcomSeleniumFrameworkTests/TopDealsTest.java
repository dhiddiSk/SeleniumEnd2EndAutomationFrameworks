package vegEcomSeleniumFrameworkTests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import resources.Configurations;
import vegEcomSeleniumFramework.TopDeals;

public class TopDealsTest extends Configurations {
	WebDriver driver;
	Properties props = new Properties();
	String location = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";
	private static TopDeals topdeal;
	private static List<WebElement> vegItems;
	private String[] products = { "Toamto", "Rice", "Potato" };
	private static final Logger logger = LogManager.getLogger(TopDealsTest.class.getName());

	@BeforeTest()
	public void vegTopDeals_SettingsBeforeTest() throws IOException {

		FileInputStream fileInputStream = new FileInputStream(location);
		props.load(fileInputStream);
		this.driver = super.settingTestProperties(driver, props);
		logger.info("TopDeals modules web driver has been initialized");

		logger.debug("The TopDealsTest web driver has been initialized with web browser " + props.getProperty("browser"));
	}

	@Test
	public void vegTopDeals_CheckProductsPriceTest() {

		// @List productPriceList stores the list of product prices

		driver.get(props.getProperty("topdealsUrl"));

		logger.info("The web driver has been directed to the topdeals url as per the properties file");

		logger.error("Top deals URL has not been found");

		driver.manage().window().maximize();

		topdeal = new TopDeals(driver);

		// Initialize a list for storing product prices

		List<String> productPriceList;

		vegItems = driver.findElements(topdeal.vegTopDeals_vegItems());

		// Logging, if vegItems are not available

		if (vegItems.isEmpty()) {

			logger.debug("The vegItems web elements are not available");

			AssertJUnit.assertTrue(false);

		}

		try {
			do {

				/*
				 * Here, vegItems web elements are passed to streams and filters are applied on
				 * the stream and verified if that particular web element is the one which we
				 * are looking for. Now, for every iteration of do while loop the web element is
				 * verified with logic and the prices are collected from the method product
				 * Price values.
				 * 
				 * Here, the loop is used with the web elements instead of string elements from
				 * because the product price is extracted based on the web element.
				 * 
				 * One can use a simplified approach to the about detailed one's by using
				 * mapping and store all the values with certain keys, but for the practice
				 * purpose of Selenium, I have chosen this approach.
				 * 
				 */
				productPriceList = vegItems.stream().filter(s -> s.getText().contains(products[0]))
						.map(s -> vegTopDeals_ProductPriceValue()).collect(Collectors.toList());
				if (productPriceList.size() < 1) {
					// Clicked to the next page when the product is not present in the current page
					// or if the productPriceList.size() < 1
					topdeal.vegTopDeals_nextPageButton().click();
				}
			} while ((productPriceList.size() < 1));

			AssertJUnit.assertTrue(productPriceList.get(0).equals("37"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private String vegTopDeals_ProductPriceValue() {

		String priceValue = topdeal.vegTopDeals_getThePriceValue().getText();

		if (priceValue == null) {

			logger.error("The pricevalue is not string :" + priceValue);
		}

		return priceValue;

	}

	@Test()
	public void vegTopDeals_CheckDiscountedPriceTest() {

		List<String> productDiscountedPriceList;


		if (vegItems.isEmpty()) {

			logger.debug("The vegItems web elements are not available");

			AssertJUnit.assertTrue(false);

		}
		
		try {
			do {
				productDiscountedPriceList = vegItems.stream().filter(s -> s.getText().contains(products[0]))
						.map(s -> vegTopDeals_DiscountedProductPriceValue()).collect(Collectors.toList());
				if (productDiscountedPriceList.size() < 1) {
					// Clicked to the next page when the product is not present in the current page
					// or if the productPriceList.size() < 1
					topdeal.vegTopDeals_nextPageButton().click();
				}

			} while ((productDiscountedPriceList.size() < 1));

			AssertJUnit.assertTrue(productDiscountedPriceList.get(0).equals("26"));

		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private String vegTopDeals_DiscountedProductPriceValue() {
		
		
		String discountedPriceValue = topdeal.vegTopDeals_getThePriceValue().getText();

		if (discountedPriceValue == null) {

			logger.error("The discountedPriceValue is not string :" + discountedPriceValue);
		}

		return discountedPriceValue;

	}

	// Validate the products display by passing text in the search box and check if
	// all the related products are been displayed
	@Test
	public void vegTopDeals_ValidateTheSearchTest() {

		try {

			// Increase the page size, before passing the character sequences in the search
			// box.
			Select select = new Select(topdeal.vegTopDeals_setPageSize());
			select.selectByValue("20");
			topdeal.vegTopDeals_searchBox().sendKeys("b");

			// Now validate the display of the products

			vegItems = driver.findElements(topdeal.vegTopDeals_vegItems());

			List<String> vegies;

			vegies = vegItems.stream().map(x -> x.getText()).collect(Collectors.toList());

			AssertJUnit.assertTrue("The products are displaying as expected", vegies.contains("Brinjal"));

			AssertJUnit.assertFalse(vegies.contains("Rice"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void vegTopDeals_SortTheProductsAlphabeticallyTest() {

		// Get all the displayed vegetable or fruit names into a list

		boolean itemsSorted = true;

		try {
			topdeal.vegTopDeals_vegOrFruitNameSortButton().click();

			List<String> itemsListAfterSorting;

			vegItems = driver.findElements(topdeal.vegTopDeals_vegItems());

			itemsListAfterSorting = vegItems.stream().map(x -> x.getText()).collect(Collectors.toList());

			for (int item = vegItems.size(); item > 0;) {

				String tempItem = itemsListAfterSorting.get(vegItems.size() - 1);

				String tempSecondaryItem = itemsListAfterSorting.get(item);

				if (tempItem.charAt(0) > tempSecondaryItem.charAt(0)) {
					item--;
				}

				else {
					itemsSorted = false;
					break;
				}

			}

			// If the below assertion fails, then the items are not sorted
			AssertJUnit.assertEquals(true, itemsSorted);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
