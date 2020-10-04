package SeleniumFramework.end2endFramework;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.*;

public class HomePage {
	private static WebDriver driver;
	
	
	public HomePage() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	HomePage(WebDriver driver) {

		this.driver = driver;
	}

	// search of vegetables
	By search() {
		By searchElement = By.cssSelector("input[type ='search']");
		return searchElement;
	}

	// This method adds the particular quantity of given vegetables into the cart.

	void addVegetables(String vegetableName, int quantity) {

		List<WebElement> buttons = driver.findElements(By.cssSelector("div[class='product-action']"));

		List<WebElement> items = driver.findElements(By.cssSelector("h4[class='product-name']"));

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
	
	
	int totalPrice() {
		
		return Integer.parseInt(driver.findElement(By.xpath("//*[@class='cart-info']/table/tbody/tr[2]/td[3]/strong")).getText());
	}

}
