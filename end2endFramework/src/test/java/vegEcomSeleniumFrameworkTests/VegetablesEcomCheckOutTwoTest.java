package vegEcomSeleniumFrameworkTests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Configurations;
import vegEcomSeleniumFramework.VegetablesEcomCheckOutTwo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class VegetablesEcomCheckOutTwoTest extends Configurations {

	WebDriver driver;
	VegetablesEcomCheckOutTwo checkOutTwoObject;
	Properties properties = new Properties();
	private String propertiesFilesLocation = "/home/saikrishna/Practical/selenium/SeleniumAutomationEndToEnd/end2endFramework/src/main/java/resources/centralData.properties";
	private static Logger logger = LogManager.getLogger(VegetablesEcomCheckOutTwoTest.class.getName());

	VegetablesEcomCheckOutTwo checkOutTwo;

	@BeforeTest()
	public void vegetablesEcomCheckOutTwoTest_settingsBeforeTest() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(propertiesFilesLocation);
		properties.load(fileInputStream);
		this.driver = super.settingTestProperties(driver, properties);
		logger.info("VegetablesEcomHomePageTest webDriver is initialized with respect to the given browser"
				+ properties.getProperty("browser"));
	}

	
	@Test
	public void vegetablesEcomCheckOutTwoTest_selectCountry() {
		
		driver.get(properties.getProperty("vegEcomProceedingPageURL"));

		checkOutTwo = new VegetablesEcomCheckOutTwo();

		Select select = new Select(driver.findElement(checkOutTwo.vegetablesEcomCheckOutTwo_dropDownButton()));

		select.selectByValue(properties.getProperty("country"));
		logger.info("The country selected is"+properties.getProperty("country"));
		logger.debug("The selected country as per the given data is : "+properties.getProperty("country"));
	}
	
	
	@Test
	public void vegetablesEcomCheckOutTwoTest_termsConditionAndProceed() {
		
		driver.findElement(checkOutTwo.vegetablesEcomCheckOutTwo_termsAgreementButton()).click();
		
		logger.info("Action performed on the terms and conditions of the webpage");
		
		logger.debug("The agreement of terms and conditions is not performed even after selection");
		
		driver.findElement(checkOutTwo.vegetablesEcomCheckOutTwo_proceedButton()).click();
	
	}
	
	
	@AfterTest()
	public void vegetablesEcomCheckOutTwoTest_settingsAfterTest() {
		// This close method closes the webpage after the completion of the tests execution of this class
		driver.close();
	}
	
	

}
