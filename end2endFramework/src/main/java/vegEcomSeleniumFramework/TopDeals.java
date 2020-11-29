package vegEcomSeleniumFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TopDeals {

	public WebDriver driver;

	public TopDeals(WebDriver driver) {

		this.driver = driver;
	}

	// This @method "nextPageButton" returns the web element of next button
	public By nextPageButton() {

		return By.cssSelector("a[aria-label='Next']");
	}

	// This @method "vegItems" returns the web element from which one can collect
	// the list of vegetables
	public By vegItems() {

		return By.xpath("//div[@class='container']/table/tbody/tr/td[1]");
	}

	// This @method "getTheDiscountPriceValue" returns the web element of the
	// discounted price values
	public By getTheDiscountPriceValue() {
		// TODO Auto-generated method stub
		return By.xpath("following-sibling::td[2]");
	}

	// This @method "getThePriceValue" returns the web element of the actual price
	// value
	public By getThePriceValue() {
		// TODO Auto-generated method stub
		return By.xpath("following-sibling::td[1]");
	}

	// Top deals page, returns the webElement of the particular products price
	public String[] topDeals(String productName, boolean price, boolean DiscountPrice) {

		driver.findElement(By.linkText("Top Deals")).click();

		String productValue = productPrice(productName);
		String productDiscountValue = productDiscountPrice(productName);

		String[] deals = { productName, productValue, productDiscountValue };

		if (price && !DiscountPrice) {
			deals[2] = "0";
		}

		else if (!price && DiscountPrice) {
			deals[1] = "0";
		}

		else if (!price && !DiscountPrice) {
			deals[1] = "0";
			deals[2] = "0";
		}
		return deals;

	}

//	public void setPageSize(String pageSize) {
//		Select s = new Select(driver.findElement(By.id("page-menu")));
//		s.selectByValue("value");
//	}
//	

	// This @method setPageSize returns the web element of it.
	public By setPageSize() {
		return By.id("page-menu");
	}

	// This @method vegOrFruitNameSortButton returns the web element of the sorting
	// button
	public By vegOrFruitNameSortButton() {
		return By.xpath("//div[@class='container']/table/thead/tr/th[1]/span[2]");
	}

	// This @method switchToPreviousPages returns the web element of the previous
	// button
	public By previousPageButton() {
		return By.cssSelector("//a[contains(@aria-label,'Previous')]");
	}

	// This @method searchBox returns the web element of the search box
	public By searchBox() {
		return By.id("search-file");
	}

}
