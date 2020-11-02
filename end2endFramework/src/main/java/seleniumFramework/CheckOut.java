package seleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOut extends VegEcomHomePage{
	
	private static WebDriver driver;
	
	public CheckOut(WebDriver driver) {
		
		this.driver = driver;
		
		System.out.println(driver.getCurrentUrl());
		
	}
	
	public CheckOut() {
		// TODO Auto-generated constructor stub
	}

	By promoCode(){
		return By.className("promoCode");//("[class = 'promoCode']");
	}
	
	By applyPromoCode() {
		return By.cssSelector("[class='promoBtn']");
	}
	
	
	By verifyPromoApplicabilty() {
		
		return By.xpath("//div[@class='products']/div[2]/div[1]/span");
		
	}
	
	By placeOrder() {
		return By.xpath("//div[@class='products']/div/button");
	}	
	
	By discount() {
		
	return (By.className("discountPerc"));
	
	}
	
	
	
	
}
