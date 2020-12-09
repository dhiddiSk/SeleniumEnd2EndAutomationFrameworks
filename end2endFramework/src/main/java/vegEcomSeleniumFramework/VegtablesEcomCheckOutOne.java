package vegEcomSeleniumFramework;

import org.openqa.selenium.By;

public class VegtablesEcomCheckOutOne extends VegEcomHomePage {

	public VegtablesEcomCheckOutOne() {
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
