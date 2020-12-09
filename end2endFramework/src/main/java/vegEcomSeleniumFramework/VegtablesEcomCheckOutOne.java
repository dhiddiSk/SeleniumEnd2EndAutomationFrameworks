package vegEcomSeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOut extends VegEcomHomePage {

	private static WebDriver driver;

	public CheckOut(WebDriver driver) {

		this.driver = driver;

		System.out.println(driver.getCurrentUrl());

	}

	public CheckOut() {
		// TODO Auto-generated constructor stub
	}

	public By promoCode() {
		return By.className("promoCode");// ("[class = 'promoCode']");
	}

	public By applyPromoCode() {
		return By.cssSelector("[class='promoBtn']");
	}

	public By verifyPromoApplicabilty() {

		return By.xpath("//div[@class='products']/div[2]/div[1]/span");

	}

	public By placeOrder() {
		return By.xpath("//div[@class='products']/div/button");
	}

	public By discount() {

		return (By.className("discountPerc"));

	}

}
