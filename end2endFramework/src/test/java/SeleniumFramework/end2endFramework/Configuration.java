package SeleniumFramework.end2endFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Configuration {

	public WebDriver settingTestProperties(WebDriver driver, String location, Properties props) throws IOException {

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

		return driver;

	}

}
