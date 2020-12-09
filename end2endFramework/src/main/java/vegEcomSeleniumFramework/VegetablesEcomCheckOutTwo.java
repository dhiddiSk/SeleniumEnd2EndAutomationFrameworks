package vegEcomSeleniumFramework;

import org.openqa.selenium.By;

public class VegetablesEcomCheckOutTwo {
	//webelement of dropdown button
	public By vegetablesEcomCheckOutTwo_dropDownButton() {
			return By.xpath("//div[@class = 'products']/div/div/select");
		}
	
	//webelement of terms&conditions button
	public By vegetablesEcomCheckOutTwo_termsAgreementButton() {
		return By.cssSelector("[type = 'checkbox']");
	}
	
	//webelement of proceed button
	public By vegetablesEcomCheckOutTwo_proceedButton() {
		return By.xpath("//div[@class = 'products']/div/button");
	}
}
