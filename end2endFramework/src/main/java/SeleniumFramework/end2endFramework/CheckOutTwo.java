package SeleniumFramework.end2endFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckOutTwo {

	WebDriver driver;

	public CheckOutTwo(WebDriver driver) {

		this.driver = driver;

	}

	public void dropDown(String country) {

		Select select = new Select(driver.findElement(By.xpath("//div[@class = 'products']/div/div/select")));

		select.selectByValue(country);
	}

	public By termsAgreement() {

		return By.cssSelector("[type = 'checkbox']");

	}

	public By proceedButton() {

		return By.xpath("//div[@class = 'products']/div/button");

	}

}
