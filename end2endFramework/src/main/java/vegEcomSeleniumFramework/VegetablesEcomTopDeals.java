package vegEcomSeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VegetablesEcomTopDeals {

	private WebDriver driver;


	public VegetablesEcomTopDeals(WebDriver driver) {

		this.driver = driver;
	}

	// @method "nextPageButton" returns the web element of next button
	public WebElement vegTopDeals_nextPageButton() {
		return driver.findElement(By.cssSelector("a[aria-label='Next']"));
	}

	// @method switchToPreviousPages returns the web element of the previous button
	public WebElement vegTopDeals_previousPageButton() {
		return driver.findElement(By.cssSelector("//a[contains(@aria-label,'Previous')]"));
	}

	// @method searchBox returns the web element of the search box
	public WebElement vegTopDeals_searchBox() {
		return driver.findElement(By.id("search-file"));
	}

	// @method setPageSize returns the web element of it.
	public WebElement vegTopDeals_setPageSize() {
		return driver.findElement(By.id("page-menu"));
	}

	// @method vegOrFruitNameSortButton returns the web element of the sorting button
	public WebElement vegTopDeals_vegOrFruitNameSortButton() {
		return driver.findElement(By.xpath("//div[@class='container']/table/thead/tr/th[1]/span[2]"));
	}

	// @method "vegItems" returns the web element from which one can collect the list of vegetables
	public By vegTopDeals_vegItems() {
		return By.xpath("//div[@class='container']/table/tbody/tr/td[1]");
	}

	// @method "getTheDiscountPriceValue" returns the web element of the discounted price values
	public WebElement vegTopDeals_getTheDiscountPriceValue() {
		return driver.findElement(By.xpath("following-sibling::td[2]"));
	}

	// @method "getThePriceValue" returns the web element of the actual price value
	public WebElement vegTopDeals_getThePriceValue() {
		return driver.findElement(By.xpath("following-sibling::td[1]"));
	}


}
