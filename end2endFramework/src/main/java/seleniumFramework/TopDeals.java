package seleniumFramework;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopDeals {

	private static WebDriver driver;

	TopDeals(WebDriver driver) {

		this.driver = driver;
	}

	private static WebElement nextButton = driver.findElement(By.cssSelector("a[aria-label='Next']"));
	private static List<WebElement> vegItems = driver.findElements(By.xpath("//div[@class='container']/table/tbody/tr/td[1]"));

	static String productPrice(String product) {
		List<String> pricevalue;

		do {
			
			pricevalue = vegItems.stream().filter(s->s.getText().contains(product)).map(s->getThePriceValue(s)).collect(Collectors.toList());
			if (pricevalue.size() < 1) {
				nextButton.click();
			}

		} while ((pricevalue.size() < 1));

		return pricevalue.get(0);

	}

	static String productDiscountPrice(String product) {
		List<String> discountPriceValue;

		do {

			discountPriceValue = vegItems.stream().filter(s->s.getText().contains(product)).map(s->getTheDiscountPriceValue(s)).collect(Collectors.toList());

			if (discountPriceValue.size() < 1) {
				nextButton.click();
			}

		} while ((discountPriceValue.size() < 1));

		return discountPriceValue.get(0);

	}

	private static String getTheDiscountPriceValue(WebElement s) {
		// TODO Auto-generated method stub
		return s.findElement(By.xpath("following-sibling::td[2]")).getText();
	}

	private static String getThePriceValue(WebElement s) {
		// TODO Auto-generated method stub
		return s.findElement(By.xpath("following-sibling::td[1]")).getText();

	}

	// Top deals page, returns the webElement of the particular products price
	String[] topDeals(String productName, boolean price, boolean DiscountPrice) {

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

}
