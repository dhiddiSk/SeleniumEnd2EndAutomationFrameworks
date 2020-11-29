package vegEcomSeleniumFrameworkTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Configurations;
import vegEcomSeleniumFramework.TopDeals;

public class TopDealsTest extends Configurations {
	WebDriver driver;
	Properties props = new Properties();
	String location = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";
	private static Logger log = LogManager.getLogger(VegEcomHomePageTest.class.getName());
	private static TopDeals topdeal;
	private static List<WebElement> vegItems;
	private String[] products = {"Toamto", "Rice", "Potato"};

	@BeforeTest()
	public void settingsBeforeTest() throws IOException {

		FileInputStream fileInputStream = new FileInputStream(location);
		props.load(fileInputStream);
	
		this.driver = super.settingTestProperties(driver, props);
		log.info("TopDeals Driver is initialized with respect to the given browser");
		
		// Initialize the object of topdeals class by passing driver
		topdeal = new TopDeals(driver);
		
		vegItems = driver.findElements(topdeal.vegItems());
		
		
		
	}

	@Test
	public void checkProductsPrice() {

		List<String> productPriceList;

		do {
			productPriceList = vegItems.stream().filter(s -> s.getText().contains(products[0])).map(s -> productPriceValue(s)).collect(Collectors.toList());
			if (productPriceList.size() < 1) {
				// Clicked to the next page when the product is not present in the current page or if the productPriceList.size() < 1
				driver.findElement(topdeal.nextPageButton()).click();
			}

		} while ((productPriceList.size() < 1));
	}

	private String productPriceValue(WebElement prodcut) {

		return driver.findElement(topdeal.getThePriceValue()).getText();

	}
	
	@Test
	public void checkDiscountedPrice() {
		
		List<String> productDiscountedPriceList;
		
		do {
			productDiscountedPriceList = vegItems.stream().filter(s -> s.getText().contains(products[0])).map(s -> discountedProductPriceValue(s)).collect(Collectors.toList());
			if (productDiscountedPriceList.size() < 1) {
				// Clicked to the next page when the product is not present in the current page or if the productPriceList.size() < 1
				driver.findElement(topdeal.nextPageButton()).click();
			}

		} while ((productDiscountedPriceList.size() < 1));
		
	}
	
	private String discountedProductPriceValue(WebElement product) {
		
		return driver.findElement(topdeal.getTheDiscountPriceValue()).getText();
		
	}
	
	// Validate the products display by passing text in the search box and check if all the products are been displayed	
	@Test
	public void validateTheProductsDisplay() {

		// Increase the page size, before passing the character sequences in the search box.
		
		Select select = new Select(driver.findElement(topdeal.setPageSize()));
		select.selectByValue("20");
		driver.findElement(topdeal.searchBox()).sendKeys("b");
		
		// Now validate the display of the products
		
		
		
	}

	@Test
	public void sortTheProductsAlphabetically() {

		TopDeals deals = new TopDeals(driver);

		// Get all the displayed vegetable or fruit names into a list

		List<String> itemsListBeforeSorting = new ArrayList();

		int theTotalItems = deals.vegItems.size();

		for (int i = 0; i < theTotalItems; i++) {
			itemsListBeforeSorting.add(deals.vegItems.get(i).getText());
		}

		// Now click on the sort button
		driver.findElement(deals.vegOrFruitNameSortButton()).click();

		List<String> itemsListAfterSorting = new ArrayList();

		for (int j = 0; j < theTotalItems; j++) {

			itemsListAfterSorting.add(deals.vegItems.get(j).getText());
		}

		Assert.assertTrue(itemsListBeforeSorting.get(0) != itemsListAfterSorting.get(0));
	}

}
