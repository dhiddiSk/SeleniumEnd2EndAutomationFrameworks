package vegEcomSeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckOutTwo {

	private static WebDriver driver;

	public CheckOutTwo(WebDriver driver) {

		this.driver = driver;

	}

	//Choosing a country in the last ButOnePage
	public void dropDown(String country) {

		Select select = new Select(driver.findElement(By.xpath("//div[@class = 'products']/div/div/select")));

		select.selectByValue(country);
	}

	// The element of terms and conditions
	public By termsAgreement() {

		return By.cssSelector("[type = 'checkbox']");

	}

	public By proceedButton() {

		return By.xpath("//div[@class = 'products']/div/button");

	}

}
