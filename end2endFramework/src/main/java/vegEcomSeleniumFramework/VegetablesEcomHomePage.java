package vegEcomSeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VegetablesEcomHomePage {
		
	private WebDriver driver;

	public VegetablesEcomHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public VegetablesEcomHomePage() {
		// TODO Auto-generated constructor stub
	}

	// Returns the webElement of search bar of vegetables.
	public By vegetablesEcomHomePage_search() {
		return By.cssSelector("input[type ='search']");
	}
	
	public By vegetablesEcomHomePage_productActionButtons() {
		return By.cssSelector("div[class='product-action']");
	}
	
	public By vegEcomHomePage_productNames() {
		return By.cssSelector("h4[class='product-name']");
	}

	public int vegetablesEcomHomePage_totalPrice() {
		return Integer.parseInt(driver.findElement(By.xpath("//*[@class='cart-info']/table/tbody/tr[2]/td[3]/strong")).getText());
	}

	public int vegetablesEcomHomePage_totalItems() {

		return Integer.parseInt(
				driver.findElement(By.xpath("//*[@class='cart-info']/table/tbody/tr[1]/td[3]/strong")).getText());
	}

	// This method returns the basketElement and allows to proceed for checkout or
	// remove the selected items
	public By vegetablesEcomHomePage_basketElement() {
		return By.className("cart-icon");
	}

	public By checkOut() {
		return By.xpath("//div[@class = 'cart-preview active']/div[2]/button");
	}
	
	// The whole cartItems
	public By cartItems() {
		return By.className("cart-items");
	}
	// Individual cartItem
	public By cartItem() {
		return By.className("cart-item");
		
	}
	
	public By productName() {
		return By.className("product-name");
	}
	
	public By removeProduct() {
		return By.className("product-remove");		
	}

	
	
}
