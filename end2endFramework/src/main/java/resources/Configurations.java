package resources;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Configurations {

	private static Logger log = LogManager.getLogger(Configurations.class.getName());
	
	public WebDriver settingTestProperties(WebDriver driver, Properties props) throws IOException {

		if (props.getProperty("browser").equals("chrome")) {

			driver = new ChromeDriver();

			System.setProperty("webdriver.chrome.driver", props.getProperty("driverLocation"));

		}

		else if (props.getProperty("browser").equals("mozilla")) {

			driver = new FirefoxDriver();

			System.setProperty("webdriver.gecko.driver", props.getProperty("driverLocation"));

		}

		else if (props.getProperty("browser").equals("IE")) {

			driver = new InternetExplorerDriver();

			System.setProperty("webdriver.ie.driver", props.getProperty("driverLocation"));

		}

		// System.out.println(props.getProperty("homeUrl"));
		
		else {
			log.error("Please use valid property value" , driver);		
		}
		

		return driver;

	}
	
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		System.out.println("The destination path is "+System.getProperty("user.dir"));
		String destinationFile = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		//copyFile(source,new File(destinationFile));
		return destinationFile;

	}	
	
	static ExtentReports extent;
	public static ExtentReports getReportObject() {
		
		String path =System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Saikrishna");
			
		return extent;
		
		 
	}
	
	
	
	

}
